package by.bsuir.wt3.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.bsuir.wt3.dao.*;

public class UserDAOimpl implements UserDAO {
	private Hashtable<String, User> users;
	
	public UserDAOimpl(String filename)
	{
		users = new Hashtable<String, User>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		Document doc = null;
		
		try {
			doc = builder.parse(new File(filename));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return;
		}
		
		Element root = doc.getDocumentElement();
		
		NodeList childNodes = root.getChildNodes();
		
		for (int i = 0; i < childNodes.getLength(); ++i)
		{
			Node node = childNodes.item(i);
			
			if (node.getNodeType() != Node.ELEMENT_NODE)
			{
				continue;
			}
			
			Element element = (Element)node;
			
			String login = element.getAttribute("login");
			String strHash = element.getAttribute("hash");
			int hash = Integer.parseInt(strHash);
			String strStatus = element.getAttribute("status");
			UserStatus status = UserStatus.valueOf(strStatus);
			
			User user = new User(login, hash, status);
			
			users.put(strStatus, user);
		}
	}
	
	@Override
	public UserStatus getUserStatus(String login, int passwordHash) {
		User user = users.get(login);
		
		if (user == null)
		{
			return UserStatus.NotFound;
		}
		
		if (user.hash != passwordHash)
		{
			return UserStatus.NotFound;
		}
		
		return user.status;
	}

}
