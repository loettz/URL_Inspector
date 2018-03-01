package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import url_inspector.URLInspector;

public class URLInspectorTest {
	
	private String url = "http://www.stadtbibliothek.luebeck.de/katalog/index.html"; 
	private String path = "C:/temp";
	private String file_path = path + "/"  +  "URLInspector.log";
	private URLInspector urlInspector = new URLInspector();
	
	@Test
	public void testUrlString() {
		urlInspector.setUrl(url);
		assertEquals(urlInspector.getUrl(), url);
	}
	
	@Test
	public void testVerifyInput() {

		assertTrue(urlInspector.isValidHTML(url));
		assertFalse(urlInspector.isValidHTML("test"));
	}
	
	@Test
	public void testCheckHttpStatusCode() {
		
		assertTrue(urlInspector.isHttpStatusCodeSuccess(url));
		
	}
	
	@Test
	public void testCheckDirectory() {
		urlInspector.checkDirectory(path, file_path);
		File thedir = new File(path);
		File f = new File(file_path);
		assertTrue(thedir.isDirectory());
		assertTrue(f.isFile());
	}
	
}
