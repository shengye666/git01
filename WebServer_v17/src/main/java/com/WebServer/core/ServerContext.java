package com.WebServer.core;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerContext {
  public static String protocl="HTTP/1.1";
  
  public static int port=8080;
  
  
  public static String URIEncoding="utf-8";
	
  
  public static int maxthreads=150;
	static {
		inserver();
	}
  public static void inserver() {
	try {
		SAXReader read =new SAXReader();
		Document document=read.read(new File("conf/serverth.xml"));
		Element root=document.getRootElement();
		List<Element>list=root.elements("Connector");
		for(Element e:list) {
			      URIEncoding=  e.attributeValue("URIEncoding"); 
			      protocl=e.attributeValue("protocol");
			      port=Integer.parseInt(e.attributeValue("port"));
			      maxthreads=Integer.parseInt(e.attributeValue("maxThreads"));
		}
		
	} catch (DocumentException e) {
		e.printStackTrace();
	}
  }
  public static void main(String[] args) {
	System.out.println(URIEncoding);
	System.out.println(protocl);
	System.out.println(port);
	System.out.println(maxthreads);
}
}

