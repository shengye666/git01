package com.WebServer.core;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.WebServer.http.EmptyRequestExtion;
import com.WebServer.http.HttpRequest;
import com.WebServer.http.Httpresponse;
import com.WebServer.servlet.HttpServlet;
import com.WebServer.servlet.ServletContext;

public class ClientHand implements Runnable{
	private Socket socket;
	
public ClientHand(Socket socket){
		this.socket=socket;
	}
public void run() {

		try {
			HttpRequest request=new HttpRequest(socket);
			Httpresponse reponse=new Httpresponse(socket);
			ServletContext servlet=new ServletContext();
			String url= request.getRequestURI();
			File file=new File("webapps"+url);
			System.out.println(url);
			if(servlet.getservletmapping(url)!=null) {
				System.out.println("开始处理业务");
				Class cls=Class.forName(servlet.getservletmapping(url));
				System.out.println("类名:"+cls);
				HttpServlet httpservlet=(HttpServlet) cls.newInstance();
				System.out.println("对象生成:"+httpservlet);
				/*在tomcat中还有调用init方法还有detory()方法,这里的httpservlet类似与tomcat的GnericsSerlerver类*/
				httpservlet.service(request, reponse);
				
				
			}else {
				if(file.exists()) {
					System.out.println("该资源存在");
					System.out.println("文件名"+file.getName());
					reponse.setEntry(file);
				}else {
					System.out.println("该资源不存在");
					reponse.setStatuCode(404);
					reponse.setEntry(new File("webapps/root/404.html"));
				}
			}
			reponse.flush();
		} catch(EmptyRequestExtion e){
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    

}
