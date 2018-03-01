package url_inspector;

import java.util.Scanner;
import java.util.Timer;

public class LoggingThread implements Runnable{
	private Scanner scanner;
	private URLInspector urlInspector = new URLInspector();

	LoggingThread(Scanner scanner) {
		this.scanner = scanner;
		
	}
	@Override
	public void run() {
		//asks the user for input and starts logging
		urlInspector.setUserInputAsUrlString(scanner);
		String url = urlInspector.getUrl();
		Timer timer = new Timer();
		timer.schedule(new LogUrlStatus(url), 0, 30000); //logs every 30 seconds
		System.out.println(url + " wird �berwacht, \n Dr�cken Sie 's' und best�tigen Sie mit Enter zum Beenden.");
		while(true) {
			if (scanner.next().equalsIgnoreCase("s")) {
				timer.cancel();
				System.out.println("Die �berwachung wurde beendet.");
				break;
			}
		}
	} 	
}
