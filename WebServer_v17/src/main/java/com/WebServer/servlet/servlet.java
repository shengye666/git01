package com.WebServer.servlet;

import java.io.RandomAccessFile;
import java.util.Arrays;

import com.WebServer.http.HttpRequest;
import com.WebServer.http.Httpresponse;

/**
 * 注册业务
 * @author soft01
 *
 */
public class servlet extends HttpServlet{
            
	public void service(HttpRequest request,Httpresponse reponse) {
		System.out.println("注册开始");                   
		String name=request.getParameter("username");
		String passwork=request.getParameter("passwork");
		String nickname=request.getParameter("nickname");
		int age=Integer.parseInt(request.getParameter("age"));
		try(
				RandomAccessFile raf=new RandomAccessFile("urt.data", "rw");
	 			) {
			raf.seek(raf.length());
			
			byte[]data=name.getBytes();
			data=Arrays.copyOf(data, 32);
			raf.write(data);
			
			data=passwork.getBytes();
			data=Arrays.copyOf(data, 32);
			raf.write(data);
           
            
			data=nickname.getBytes();
			data=Arrays.copyOf(data, 32);
			raf.write(data);
			
			raf.writeInt(age);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("注册完毕");
		
		forward("/myweb/success.html", request, reponse);
		
	}
}
