package com.WebServer.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServletContext {
	private  static Map<String, String>servlet_mapping=new HashMap<String, String>(); 

	static {
		initservletmapping();
	}

	private static void initservletmapping() {
		try {
			SAXReader sax=new SAXReader();
			Document document=sax.read(new File("conf/servlet.xml"));
			Element root =document.getRootElement();
			List<Element>list=root.elements("servlet");
			for(Element e:list) {
				String key=e.attributeValue("url");
				String value=e.attributeValue("classname");
				servlet_mapping.put(key, value);
			}



		} catch (Exception e) {
			e.printStackTrace();
		}



   }
   public static String getservletmapping(String name) {
	   return servlet_mapping.get(name);
   }
   public static void main(String[] args) {
	   System.out.println(servlet_mapping);
	   System.out.println(servlet_mapping.size());
	 
}
}
