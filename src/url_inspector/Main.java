package url_inspector;


import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Thread t = (new Thread(new LoggingThread(in)));
		t.start();
		}	
}
