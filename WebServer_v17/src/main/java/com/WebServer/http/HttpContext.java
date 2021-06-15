package com.WebServer.http;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HttpContext {
private  static Map<Integer, String>statusCode_Reason_Mapping=new HashMap<Integer, String>();

private static Map<String, String>mimeMapping=new HashMap<String, String>();
   public static final int CR=13;
   public static final int LR=10;
    static{
	initStatusCodeReasonMapping();
	inmimeMapping();
}
private  static void inmimeMapping() {
	try {
		SAXReader reader=new SAXReader();
		Document document=reader.read(new File("conf/web.xml"));
	
		Element root =document.getRootElement();
       List<Element> list =root.elements("mime-mapping");  
		for(Element e:list) {
			String key  = e.elementText("extension");
			String value=e.elementText("mime-type");
			mimeMapping.put(key, value);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}               
	
}
private static void initStatusCodeReasonMapping() {
	statusCode_Reason_Mapping.put(200, "OK");
	statusCode_Reason_Mapping.put(201, "Created");
	statusCode_Reason_Mapping.put(202, "Accepted");
	statusCode_Reason_Mapping.put(204, "No Content");
	statusCode_Reason_Mapping.put(301, "Moved Permanently");
	statusCode_Reason_Mapping.put(302, "Moved Temporarily");
	statusCode_Reason_Mapping.put(304, "Not Modified");
	statusCode_Reason_Mapping.put(400, "Bad Request");
	statusCode_Reason_Mapping.put(401, "Unauthorized");
	statusCode_Reason_Mapping.put(403, "Forbidden");
	statusCode_Reason_Mapping.put(404, "Not Found");
	statusCode_Reason_Mapping.put(500, "Internal Server Error");
	statusCode_Reason_Mapping.put(501, "Not Implemented");
	statusCode_Reason_Mapping.put(502, "Bad Gateway");
	statusCode_Reason_Mapping.put(503, "Service Unavailable");
}
 
public static String getStatus(int statusCode) {
	return statusCode_Reason_Mapping.get(statusCode);
}
    public static String getContentType(String ext) {
    	return mimeMapping.get(ext);
    }
public static void main(String[] args) {
	System.out.println(mimeMapping.size());
}
}
