package com.WebServer.servlet;

import com.WebServer.antisopt.antisoptcContext;
import com.WebServer.http.HttpRequest;
import com.WebServer.http.Httpresponse;

public class indexservlet extends HttpServlet{
	public void service(HttpRequest request, Httpresponse reponse) {
		String lantispot=request.getParameter("anitspots");
		String web= antisoptcContext.getAntispot(lantispot);
		if(web!=null) {
			forward(web, request, reponse);

		}else {
			forward("/root/404.html", request, reponse);			
		}
	}
}
