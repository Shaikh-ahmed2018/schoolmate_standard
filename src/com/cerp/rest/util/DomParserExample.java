package com.cerp.rest.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;


public class DomParserExample {

	static DocumentBuilderFactory factory;
	static DocumentBuilder builder;
	
	static{
		factory = DocumentBuilderFactory.newInstance();
	}
	
	public int getsubdoaminstatus(String response) throws ParserConfigurationException,
    SAXException, IOException{
		
		int id = 0;
		
		try{
			 DocumentBuilder builder = factory.newDocumentBuilder();

		        // Load the input XML document, parse it and return an instance of the
		        // Document class.
		        Document document = builder.parse(new InputSource(new StringReader(response)));
		       
		        NodeList nodeList = document.getDocumentElement().getChildNodes();
		        
		        for (int i = 0; i < nodeList.getLength(); i++) {
		             Node node = nodeList.item(i);

		             if (node.getNodeType() == Node.ELEMENT_NODE) {
		                  Element elem = (Element) node;

		                  // Get the value of all sub-elements.
		                  String status = elem.getElementsByTagName("status").item(0).getChildNodes().item(0).getNodeValue();
		                  
		                  if(status.equals("ok")){
		                	  id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
		                	  //System.out.println("status==="+id);
		                  }
		                  
		             }
		        }
		}catch(Exception e){
			e.printStackTrace();
		}
       
		return id;
	}

	public int getdbid(String dbresponse) throws ParserConfigurationException,
    SAXException, IOException {
		
		int id = 0;
		
		try{
	        DocumentBuilder builder = factory.newDocumentBuilder();
	
	        // Load the input XML document, parse it and return an instance of the
	        // Document class.
	        Document document = builder.parse(new InputSource(new StringReader(dbresponse)));
	       
	        NodeList nodeList = document.getDocumentElement().getChildNodes();
	        
	        for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	
	             if (node.getNodeType() == Node.ELEMENT_NODE) {
	                  Element elem = (Element) node;
	
	                  // Get the value of all sub-elements.
	                  String status = elem.getElementsByTagName("status").item(0).getChildNodes().item(0).getNodeValue();
	                 
	                  if(status.equals("ok")){
	                	  id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
	                  }
	             }
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

	public int getdbuserid(String userdbresponse) throws ParserConfigurationException,
    SAXException, IOException {
		
		int id = 0;
		
		try{
	        DocumentBuilder builder = factory.newDocumentBuilder();
	
	        // Load the input XML document, parse it and return an instance of the
	        // Document class.
	        Document document = builder.parse(new InputSource(new StringReader(userdbresponse)));
	       
	        NodeList nodeList = document.getDocumentElement().getChildNodes();
	        
	        for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	
	             if (node.getNodeType() == Node.ELEMENT_NODE) {
	                  Element elem = (Element) node;
	
	                  // Get the value of all sub-elements.
	                  String status = elem.getElementsByTagName("status").item(0).getChildNodes().item(0).getNodeValue();
	                 
	                  if(status.equals("ok")){
	                	  id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
	                  }
	             }
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
	public static int verifysubdoamindetails(String userdbresponse) throws ParserConfigurationException,
    SAXException, IOException {
		
		int id = 0;
		
		try{
	        DocumentBuilder builder = factory.newDocumentBuilder();
	
	        // Load the input XML document, parse it and return an instance of the
	        // Document class.
	        Document document = builder.parse(new InputSource(new StringReader(userdbresponse)));
	       
	        NodeList nodeList = document.getDocumentElement().getChildNodes();
	        
	        for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	
	             if (node.getNodeType() == Node.ELEMENT_NODE) {
	                  Element elem = (Element) node;
	
	                  // Get the value of all sub-elements.
	                  String status = elem.getElementsByTagName("status").item(0).getChildNodes().item(0).getNodeValue();
	                 
	                  if(status.equals("ok")){
	                	  id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
	                  }
	             }
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
}
