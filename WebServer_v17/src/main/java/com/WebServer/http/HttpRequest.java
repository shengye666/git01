package com.WebServer.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.WebServer.core.ServerContext;

public class HttpRequest {
	private String method;//请求方式
	private String url;//请求路径
	private String potocol;//	请求版本

	private String requestURI;//详细解析的路径
	private String queryString;//参数
	private Map<String, String>parameter=new HashMap<String, String>();//参数通过散例表进行存进,让后调用访问



	private Map<String,String> headers=new LinkedHashMap<String, String>();


	private Socket socket;
	private InputStream in;
    //构造方法,自动解析客户端发送的请求
	public HttpRequest(Socket socket) throws EmptyRequestExtion{
		try {
			this.socket=socket;
			this.in=socket.getInputStream();
			parseReuqestLine();
			parseHears();
			parseConent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//解析请求行
	private void parseReuqestLine() throws EmptyRequestExtion {
		try {
			String line=rendLing();
			String[]arr=line.split("\\s");
			if(arr.length<3) {
				throw new EmptyRequestExtion();
			}
			method=arr[0];
			url=arr[1];
			parseurl();
			potocol=arr[2];
			System.out.println("解析完毕");
			System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//解析详细解析参数
	public void  parseurl() {
		if(url.contains("?")) {
			String[]arr=url.split("\\?");
			requestURI=arr[0];
			if(arr.length>1) {
				parsepame(arr[1]);				
			}
		}else {
			this.requestURI=this.url;
		}
		System.out.println(queryString);
		System.out.println(requestURI);
	}


	//解析消息头
	private void parseHears() {
		try {
			while(true) {
				String str=rendLing();
				System.out.println(str);
				if(str.equals("")) {
					break;
				}else {
					String[]arr= str.split(": ");
					headers.put(arr[0], arr[1]);
				}
			}
			System.out.println(headers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//解析消息正文
	private void parseConent() {
		try {
			if(headers.containsKey("Content-Length")) {
				int len= Integer.parseInt(headers.get("Content-Length"));
				byte[]data=new byte[len];
				in.read(data);
				String  type= headers.get("Content-Type");
				if("application/x-www-form-urlencoded".equals(type)) {
					String line=new String(data,"ISO8859-1");              
					parsepame(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
     //读方法
  private String rendLing() throws IOException {
	  StringBuilder sb=new StringBuilder();
	  int d=-1;
	  char a='a',a1='a'; 
	  while((d=in.read())!=-1) {
		  a1=(char)d;
		  if(a==HttpContext.CR&&a1==HttpContext.LR) {
			  break;
		  }
		  sb.append(a1);
		  a=a1;
	  }
	  return  sb.toString().trim();
  }
  
  
  //转换文字把参数放进queryString,拆分参数字符放进parmeter
  private void parsepame(String len) {
		try {
            queryString=URLDecoder.decode(len,ServerContext.URIEncoding); 
            String[]qs=queryString.split("&");
			for(String e:qs) {
				String[] arrs = e.split("=");
				if(arrs.length>1) {
					parameter.put(arrs[0], arrs[1]);
				}else {
					parameter.put(arrs[0], null);
				}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
  }
  
  
  
public String getMethod() {
	return method;
}
public String geturl() {
	return url;
}
public String getpotocol() {
	return potocol;
}
public String getRequestURI() {
	return requestURI;
}
public String getQueryString() {
	return queryString;
}
public String getParameter(String name) {
	return parameter.get(name);
}
  
  
  
}
