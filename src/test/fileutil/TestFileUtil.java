package test.fileutil;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import fileutil.FileUtil;

public class TestFileUtil {
	@Test
	public void testDoCopyWithChar() throws IOException{
		FileUtil.doCopyWithChar("G:\\test\\src\\新建文本文档1.txt","G:\\test\\des", null);
	}
	
	@Test
	public void testDoCopyWithStream() throws IOException{
		FileUtil.doCopyWithStream("G:\\test\\src\\新建文本文档2.txt","G:\\test\\des", null);
	}
	
	@Test
	public void testDoCopyNIO() throws IOException{
		FileUtil.doCopyNIO("G:\\test\\src\\新建文本文档3.txt","G:\\test\\des", null);
	}
	
	@Test
	public void testTransStringAndFile() throws IOException {
		String content = "This is a test\nThis is a test\nThis is a test\nThis is a test\n This is a test!";
		System.out.println(content);
		String temp = FileUtil.transString2FileWithChar(content, "G:\\test\\des", "test.txt");
		String temp2 = FileUtil.transString2FileWithStream(content, "G:\\test\\des", "test1.txt");
		System.out.println(temp);
		System.out.println(temp2);
		String content1 = FileUtil.tansFile2String(temp);
		System.out.println(content1);
		String content2 = FileUtil.tansFile2String(temp2);
		System.out.println(content2);
	}
	
	
}
