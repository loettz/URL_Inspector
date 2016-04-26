package url_inspector;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class LogUrlStatus extends TimerTask {
	
	String path = "C:/temp";
	String file_path = path + "/" + "URLInspector.log";
	String url;
	URLInspector insp = new URLInspector();
	
	public LogUrlStatus(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		//creates string with Date, Time, Url and accessibility and appends it to log file
		insp.checkDirectory(path, file_path);
		insp.checkFileExists(file_path);
		String accessibility = new String();
		StringBuilder sb = new StringBuilder(insp.sdf.format(new Date().getTime()));
		sb.append(" : ");
		sb.append(url);
		if (insp.checkHttpStatusCode(url)) {
			accessibility = " -> erreichbar";
		} else {
			accessibility = " -> nicht erreichbar";
		}
		sb.append(accessibility);
		insp.sdf.format(new Date().getTime());
		List<String> lines = Arrays.asList(sb.toString());

		Path file = Paths.get(file_path);

		try {
			Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
