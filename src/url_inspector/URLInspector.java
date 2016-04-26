package url_inspector;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class URLInspector {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss 'Uhr'");
	String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUserInput(Scanner reader) {
		//sets user input as url string if valid
		System.out.println("Bitte geben Sie eine Url ein und best�tigen diese mit Enter: ");
		String url = reader.next();
		if (verifyInput(url)) {
			setUrl(url);
		} else {
			System.out.println("Achtung: Eingabe ung�ltig!");
			setUserInput(reader);
		}
	}
		
	public boolean verifyInput(String url) {
		//checks if input is valid html site
		if  (url.matches("^(https?|ftp)://.*$")) {
			 return url.matches("(.*).html");		
		} else {
			return false;
		}	
	}
	
	public boolean checkHttpStatusCode(String user_url) {
		//returns true if http status code starts with 2 (success)
		try {
			URL url = new URL(user_url);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			int i =  conn.getResponseCode();
			String s = Integer.toString(i);
			return s.startsWith("2");
		} catch (MalformedURLException e) {
			System.out.println("Warnung: Es konnte keine Url erstellt werden!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Warnung: Die Url konnte nicht �berpr�ft werden!");
			e.printStackTrace();
		}
		return false;
	}
	
	public void checkDirectory(String path, String file_path) {
		//checks if directory exists and creates dir if not
		File theDir = new File(path); 
		if (!theDir.exists()) {
			theDir.mkdirs();
			checkFileExists(file_path);
			
		} else {
			checkFileExists(file_path);
		}			
	}

	public void checkFileExists(String file_path) {
		//checks if file exists and creates file if not
		File f = new File(file_path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Warnung: Die Datei konnte nicht erstellt werden!");
				e.printStackTrace();
			}
		}
	}
}
