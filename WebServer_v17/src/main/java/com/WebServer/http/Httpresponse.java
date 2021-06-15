package com.WebServer.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.WebServer.core.ServerContext;

public class Httpresponse {
	
	private Map<String, String>headers=new HashMap<String, String>();
	//通过socket来输入给客户端信息
	private Socket socket;
	private OutputStream out;
	//建立私有的file
	private File entity;
	
	
	private int statuCode=200;
	private String statuReason="OK";
	 
	
	public Httpresponse(Socket socket) {
		try {
			this.socket=socket;
			this.out=socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
   
	//响客户端发过来的请求
	public void flush() {
		sendstatusline();
		sendHeadrs();
		sendConent();
		
	}
	private void sendstatusline() {
		try {
			String line=ServerContext.protocl+" "+statuCode+" "+statuReason;
			println(line);
			System.out.println("发送头"+line);
		} catch (Exception e) {
			e.printStackTrace();  
		}
	}
	private void sendHeadrs() {
		try {
			Set<Entry<String,String>>entrySet=headers.entrySet();
			for(Entry<String,String> header:entrySet) {
				String key=header.getKey();
				String value=header.getValue();
				String line=key+": "+value;
				println(line);
				System.out.println("发送行"+line);
			}				

			out.write(HttpContext.CR);
			out.write(HttpContext.LR);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void sendConent(){
		if(entity!=null) {
			try (
					FileInputStream fi=new FileInputStream(entity);
					){
			
				int len=-1;
				byte[]data=new byte[1024*10];
				while((len=fi.read(data))!=-1) {
					out.write(data, 0, len);
					System.out.println("len"+len);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void println(String line) {
		  try {
			    out.write(line.getBytes("ISO8859-1"));
			    out.write(HttpContext.CR);
			    out.write(HttpContext.LR);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
	

	public File getFile() {
		return entity;
	}
	public void setEntry(File entity) {
		this.entity = entity;
		this.headers.put("Content-Length", entity.length()+"");
		String flieName=entity.getName();
		int index=flieName.lastIndexOf(".")+1;
		String ext=flieName.substring(index);
		String contentTye=HttpContext.getContentType(ext);
		this.headers.put("Content-Type", contentTye);
		System.out.println(entity);
		System.out.println(headers);
	}

    
	public int getStatuCode() {
		return statuCode;
	}
	
	public void setStatuCode(int statuCode) {
		this.statuCode = statuCode;
		this.statuReason=HttpContext.getStatus(statuCode);
	}
	
	public String getStatuReason() {
		return statuReason;
	}
	
	public void setStatuReason(String statuReason) {
		this.statuReason = statuReason;
	}
	
	public void putHread(String key,String value) {
		this.headers.put(key, value);
	}
}
