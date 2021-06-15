package com.WebServer.antisopt;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class antisoptcContext {
	private static Map<String, String>antispot=new HashMap<String, String>();

	static {
		antispotContext();
	}

	private static void antispotContext() {
		try {
			SAXReader read=new SAXReader();
			Document document=read.read(new File("conf/antisopt.xml"));
			Element element=document.getRootElement();
			List<Element>list=element.elements("antisopt");
			for(Element e:list) {
				System.out.println(e.getName());
				String key = e.attributeValue("name");
				String value=e.attributeValue("web");
				System.out.println(key);
				System.out.println(value);
				antispot.put(key, value);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static String getAntispot(String name ) {
		return antispot.get(name);
	}
public static void main(String[] args) {
	System.out.println(antispot);
}
}
