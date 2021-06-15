package com.WebServer.servlet;

import java.io.RandomAccessFile;
import java.util.Arrays;

import com.WebServer.core.ServerContext;
import com.WebServer.http.HttpRequest;
import com.WebServer.http.Httpresponse;

public class usderservlet extends HttpServlet{
	public void service(HttpRequest request,Httpresponse reponse) {
		String username = request.getParameter("username");
		String passwork = request.getParameter("passwork");
		System.out.println("开始修改");
		try(
				RandomAccessFile raf=new RandomAccessFile("urt.data", "rw");
				) {
			boolean falt=false;
			for(int i=0;i<raf.length()/100;i++) {
				raf.seek(i*100);
				byte[]data=new byte[32];
				raf.read(data);
				String lusername=new String(data,ServerContext.URIEncoding).trim();
				if(lusername.equals(username)) {
					raf.seek(i*100+32);
					byte[]usdata=passwork.getBytes();
					usdata=Arrays.copyOf(usdata, 32);
					raf.write(usdata);
					falt=true;
					forward("/myweb/usder_sucess.html", request, reponse);
					
					System.out.println("修改成功");
					break;
				}
			}
			if(!falt) {
				forward("/myweb/loginusernamefales.html", request, reponse);
				System.out.println("修改失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}         
	}
}
