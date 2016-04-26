package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import url_inspector.URLInspector;

public class URLInspectorTest {
	
	private String url = "http://www.stadtbibliothek.luebeck.de/katalog/index.html"; 
	private String path = "C:/temp";
	private String file_path = path + "/"  +  "URLInspector.log";
	URLInspector insp = new URLInspector();
	
	@Test
	public void testUrlString() {
		insp.setUrl(url);
		assertEquals(insp.getUrl(), url);
	}
	
	@Test
	public void testVerifyInput() {

		assertTrue(insp.verifyInput(url));
		assertFalse(insp.verifyInput("test"));
	}
	
	@Test
	public void testCheckHttpStatusCode() {
		
		assertTrue(insp.checkHttpStatusCode(url));
		
	}
	
	@Test
	public void testCheckDirectory() {
		insp.checkDirectory(path, file_path);
		File thedir = new File(path);
		File f = new File(file_path);
		assertTrue(thedir.isDirectory());
		assertTrue(f.isFile());
	}
	
}
