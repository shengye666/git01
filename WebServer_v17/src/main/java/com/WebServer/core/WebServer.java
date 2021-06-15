
package com.WebServer.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	private ServerSocket server;
	private 	ExecutorService threadpool;
	public WebServer(){
		try {
		
			System.out.println("启动服务器....");
			server=new ServerSocket(ServerContext.port);
			System.out.println("以连接.....");
		 threadpool=Executors.newFixedThreadPool(ServerContext.maxthreads);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
public void start() {
	 try {
		 while(true) {
		 System.out.println("等待连接客户端....");
		 Socket  socket =server.accept();
		 System.out.println("客户端以连接");
		 
			//交给线程池操作
			ClientHand clid=new ClientHand(socket);
			threadpool.execute(clid);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {
	WebServer web=new WebServer();
	web.start();
}
}
