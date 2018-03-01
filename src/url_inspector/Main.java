package url_inspector;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Thread thread = (new Thread(new LoggingThread(scanner)));
        thread.start();
    }
}
