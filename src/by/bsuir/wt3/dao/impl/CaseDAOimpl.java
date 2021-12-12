package by.bsuir.wt3.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import by.bsuir.wt3.dao.CaseDAO;

public class CaseDAOimpl implements CaseDAO {
	private Hashtable<Integer, String> cases;
	
	public CaseDAOimpl(String filename)
	{
		cases = new Hashtable<Integer, String>();
		
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
			
			String strId = element.getAttribute("id");
			int id = Integer.parseInt(strId);
			String caseContent = element.getTextContent();
			
			cases.put(id, caseContent);
		}
	}
	
	@Override
	public String getCase(int id) {
		return cases.get(id);
	}

	@Override
	public String setCase(int id, String caseString) {
		if (cases.containsKey(id))
		{
			cases.replace(id, caseString);
		}
		else
		{
			cases.put(id, caseString);
		}
		
		return "Case set successfully.";
	}

}
