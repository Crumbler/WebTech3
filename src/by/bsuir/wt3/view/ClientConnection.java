package by.bsuir.wt3.view;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

import by.bsuir.wt3.controller.Controller;

public class ClientConnection implements Runnable {

	private Socket sock;
	private InputStream in;
	private OutputStream out;
	private BufferedReader reader;
	private PrintWriter writer;
	private StringBuilder builder;
	private byte[] lengthBuf;
	private char[] charBuf;
	
	public ClientConnection(Socket sock) throws IOException
	{
		this.sock = sock;
		this.in = sock.getInputStream();
		this.out = sock.getOutputStream();
		this.reader = new BufferedReader(new InputStreamReader(this.in));
		this.writer = new PrintWriter(this.out);
		this.builder = new StringBuilder();
		this.lengthBuf = new byte[4];
		this.charBuf = new char[256];
	}
	
	@Override
	public void run() {
		while (true)
		{
			String request = receiveRequest();
			
			if (request == null)
			{
				try {
					sock.close();
				} catch (IOException e) {
					
				}
				return;
			}
			
			sendResponse(Controller.executeTask(request));
		}
	}

	private String receiveRequest()
	{
		// read string length encoded as a BigEndian int
		int readBytes;
					
		try 
		{
			readBytes = in.readNBytes(lengthBuf, 0, 4);
		} catch (IOException e) {
			return null;
		}
					
		if (readBytes != 4)
		{
			return null;
		}

		int stringLength = ByteBuffer.wrap(lengthBuf).getInt();
					
		// read string
		builder.setLength(0);
					
		while (stringLength > 0)
		{
			try 
			{
				readBytes = reader.read(charBuf);
			} catch (IOException e) {
				return null;
			}
						
			if (readBytes == -1)
			{
				return null;
			}
						
			builder.append(charBuf, 0, readBytes);
						
			stringLength -= readBytes;
		}
					
		System.out.println("Received command:");
		System.out.println(builder.toString());
		
		return builder.toString();
	}
	
	private void sendResponse(String response)
	{
		ByteBuffer.wrap(lengthBuf).putInt(response.length());
		
		try {
			out.write(lengthBuf);
		} catch (IOException e) {
			try {
				sock.close();
			} catch (IOException e1) {
				
			}
			return;
		}
		
		writer.print(response);
		writer.flush();
	}
}
