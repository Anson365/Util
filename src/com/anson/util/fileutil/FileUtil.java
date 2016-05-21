package com.anson.util.fileutil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;

public class FileUtil {
	/**
	 *do copy whith char
	 * @param src source file path
	 * @param des destination file path
	 * @param fileName file name
	 * @throws IOException
	 */
	public static void doCopyWithChar(String src,String des,String fileName) throws IOException{
		src = src.replaceAll("\\\\", "/");
		des = des.replaceAll("\\\\", "/");
		des = des.endsWith("/")?des:des+"/";
		fileName= fileName==null?src.substring(src.lastIndexOf("/")+1):fileName;
		File desFile = new File(des);
		if(!desFile.exists()){
			desFile.mkdirs();
		}
		BufferedReader br = new BufferedReader(new FileReader(src));
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(des+fileName));
		char[] c = new char[1024];
		int len = 0;
		while((len=br.read(c))>-1){
			bw.write(c,0,len);
		}
		br.close();
		bw.flush();
		bw.close();
	}
	
	/**
	 *do copy with stream
	 * @param src source file path
	 * @param des destination file path
	 * @param fileName file name
	 * @throws IOException
	 */
	public static void doCopyWithStream(String src,String des,String fileName) throws IOException{
		src = src.replaceAll("\\\\", "/");
		des = des.replaceAll("\\\\", "/");
		des = des.endsWith("/")?des:des+"/";
		fileName= fileName==null?src.substring(src.lastIndexOf("/")+1):fileName;
		File desFile = new File(des);
		if(!desFile.exists()){
			desFile.mkdirs();
		}
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des+fileName));
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = bis.read(buffer))>-1){
			bos.write(buffer,0,len);
		}
		bis.close();
		bos.flush();
		bos.close();
	}
	
	/**
	 * do copy whith file channel NIO
	 * @param src source file path
	 * @param des destination file path
	 * @param fileName file name
	 * @throws IOException
	 */
	public static void doCopyNIO(String src,String des,String fileName) throws IOException{
		src = src.replaceAll("\\\\", "/");
		des = des.replaceAll("\\\\", "/");
		des = des.endsWith("/")?des:des+"/";
		fileName= fileName==null?src.substring(src.lastIndexOf("/")+1):fileName;
		File desFile = new File(des);
		if(!desFile.exists()){
			desFile.mkdirs();
		}
		FileChannel ic = new FileInputStream(src).getChannel();
		FileChannel io = new FileOutputStream(des+fileName).getChannel();
		ic.transferTo(0,ic.size(), io);
		ic.close();
		io.close();
		
	}
	
	/**
	 * translate string to file with char
	 * @param content string content
	 * @param des destination file path
	 * @param fileName destination file name
	 * @return Absolute Path
	 * @throws IOException
	 */
	public static String transString2FileWithChar(String content,String des,String fileName) throws IOException{
		des = des.replaceAll("\\\\", "/");
		des = des.endsWith("/")?des:des+"/";
		File desFile = new File(des);
		if(!desFile.exists()){
			desFile.mkdirs();
		}
		Reader is = new BufferedReader(new CharArrayReader(content.toCharArray()));
		Writer os = new BufferedWriter(new FileWriter(des+fileName));
		int len = 0;
		char[] bf = new char[1024];
		while((len=is.read(bf))>-1){
			os.write(bf, 0, len);
		}
		os.flush();
		os.close();
		return des+fileName;
	}
	

	/**
	 * translate string to file with stream
	 * @param content string content
	 * @param des destination file path
	 * @param fileName new file name
	 * @return absolute path
	 * @throws IOException
	 */
	public static String transString2FileWithStream(String content,String des,String fileName) throws IOException{
		des = des.replaceAll("\\\\", "/");
		des = des.endsWith("/")?des:des+"/";
		File desFile = new File(des);
		if(!desFile.exists()){
			desFile.mkdirs();
		}
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(content.getBytes("utf-8")));
		OutputStream os = new BufferedOutputStream(new FileOutputStream(des+fileName));
		int len = 0;
		byte[] bf = new byte[1024];
		while((len=is.read(bf))>-1){
			os.write(bf, 0, len);
		}
		os.flush();
		os.close();
		return des+fileName;
	}
	
	/**
	 * translate file to string
	 * @param src source file
	 * @return string result
	 * @throws IOException
	 */
	public static String tansFile2String(String src) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(src));
		StringBuilder sb = new StringBuilder();
		String temp;
		while((temp=br.readLine())!=null){
			sb.append(temp+"\n");
		}
		br.close();
		return sb.toString();
	}
		
		
}
