package com.WebServer.servlet;

import java.io.File;

import com.WebServer.http.HttpRequest;
import com.WebServer.http.Httpresponse;

/**
 * 抽象超类httpservlet
 * 所有需要办理业务可以看作一个业务超类,而业务不同所以定义为抽象类
 * 1:在定义一个抽象方法为service,这方法所有业务子类都实现,(不同业务方法不同)
 * 2:在定义一个普通方发forward,这方法提供跳转,写入方法,每个业务都需要跳转跟写入,故建立这么一个方法
 * @author soft01
 *
 */
public abstract class HttpServlet {
	
	
   public abstract void service(HttpRequest request,Httpresponse reponse);
   public void forward(String url,HttpRequest request,Httpresponse reponse) {
	   reponse.setEntry(new File("webapps"+url));
   }
}
