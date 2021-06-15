package com.WebServer.core;

import java.io.IOException;
import java.io.RandomAccessFile;

public class showAllUserDemo {
    public static void main(String[] args) throws IOException {
		RandomAccessFile raf= new RandomAccessFile("urt.data","rw");
		
		for(int i=0;i<raf.length()/100;i++) {
			raf.seek(i*100);
       byte[]data=new byte[32];
       //读取用户
         raf.read(data);
       String name=new String(data,"UTF-8").trim();
       //读取密码
       raf.read(data);
       String password=new String(data,"UTF-8").trim();
       //读取昵称
       raf.read(data);
       String nickname=new String(data,"UTF-8").trim();
       //读年龄
       
       int age=raf.readInt();
			System.out.println(name+" "+password+" "+nickname+" "+age);
		}
    	raf.close();
	}
}
