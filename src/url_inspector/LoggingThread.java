package url_inspector;

import java.util.Scanner;
import java.util.Timer;


public class LoggingThread implements Runnable{
	Scanner in;
	URLInspector insp = new URLInspector();

	public LoggingThread(Scanner in) {
		this.in = in;
		
	}
	@Override
	public void run() {
		//asks the user for input and starts logging
		insp.setUserInput(in);
		String url = insp.getUrl();
		Timer timer = new Timer();
		timer.schedule(new LogUrlStatus(url), 0, 30000); //logs every 30 seconds
		System.out.println(url + " wird �berwacht, \n Dr�cken Sie 's' und best�tigen Sie mit Enter zum Beenden.");
		while(true) {
			if (in.next().equalsIgnoreCase("s")) {
				timer.cancel();
				System.out.println("Die �berwachung wurde beendet.");
				break;
			}
		}
	} 	
}
