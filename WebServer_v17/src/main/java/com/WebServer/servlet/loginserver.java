package com.WebServer.servlet;

import java.io.RandomAccessFile;

import com.WebServer.core.ServerContext;
import com.WebServer.http.HttpRequest;
import com.WebServer.http.Httpresponse;

/**
 * 登陆业务
 * @author soft01
 *
 */
public class loginserver  extends HttpServlet{
	public loginserver() {
		System.out.println();
     System.out.println("构造器");
     System.out.println();
	}
	
    public void service(HttpRequest request,Httpresponse reponse) {
    	
    	String  lusername=request.getParameter("username");
    	String  lpassword=request.getParameter("password");

    	boolean falt=false;
    	try (
    			RandomAccessFile raf=new RandomAccessFile("urt.data", "rw");
    			){
    		for(int i=0;i<raf.length()/100;i++){
    			raf.seek(i*100);
    			byte[]data=new byte[32];
    			raf.read(data);
    			String username=new String(data,ServerContext.URIEncoding).trim();
    			if(username.equals(lusername)) {
    				raf.seek(i*100+32);     
    				data=new byte[32];
    				raf.read(data);
    				String password=new String(data,ServerContext.URIEncoding).trim(); 
    				if(password.equals(lpassword)) {
    				       falt=true;
    				      forward("/myweb/login_sucess.html",request, reponse);
    					break;
    				}else {
    					falt=true;
    					forward("/myweb/login_false.html", request, reponse);
    					break;
    				}
    			}
		    }
    		if(!falt) {
    			System.out.println("用户不存在");
    			forward("/myweb/loginusernamefales.html", request, reponse);
    		}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


}
