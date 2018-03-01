package url_inspector;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class URLInspector {
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss 'Uhr'");
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	void setUserInputAsUrlString(Scanner scanner) {
		System.out.println("Bitte geben Sie eine Url ein und bestätigen diese mit Enter: ");
		String url = scanner.next();
		if (isValidHTML(url)) {
			setUrl(url);
		} else {
			System.out.println("Achtung: Eingabe ung�ltig!");
			setUserInputAsUrlString(scanner);
		}
	}
		
	public boolean isValidHTML(String url) {
		return url.matches("^(https?|ftp)://.*$") && url.matches("(.*).html");
	}
	
	public boolean isHttpStatusCodeSuccess(String user_url) {
		try {
			URL url = new URL(user_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			int responseCode =  httpURLConnection.getResponseCode();
			String responseCodeAsString = Integer.toString(responseCode);
			return responseCodeAsString.startsWith("20");
		} catch (MalformedURLException e) {
			System.out.println("Warnung: Es konnte keine Url erstellt werden!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Warnung: Die Url konnte nicht überprüft werden!");
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

	void checkFileExists(String file_path) {
		//checks if file exists and creates file if not
		File file = new File(file_path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Warnung: Die Datei konnte nicht erstellt werden!");
				e.printStackTrace();
			}
		}
	}
}
