package url_inspector;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class LogUrlStatus extends TimerTask {

    private String directory = "C:/temp";
    private String file_path = directory + "/" + "URLInspector.log";
    private String url;
    private URLInspector urlInspector = new URLInspector();

    LogUrlStatus(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        //creates string with Date, Time, Url and accessibility and appends it to log file
        urlInspector.checkDirectory(directory, file_path);
        String accessibility;
        StringBuilder stringBuilder = new StringBuilder(urlInspector.simpleDateFormat.format(new Date().getTime()));
        stringBuilder.append(" : ");
        stringBuilder.append(url);
        if (urlInspector.isHttpStatusCodeSuccess(url)) {
            accessibility = " -> erreichbar";
        } else {
            accessibility = " -> nicht erreichbar";
        }
        stringBuilder.append(accessibility);
        urlInspector.simpleDateFormat.format(new Date().getTime());
        List<String> loggingMessages = Collections.singletonList(stringBuilder.toString());

        Path file = Paths.get(file_path);

        try {
            Files.write(file, loggingMessages, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
