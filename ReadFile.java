import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;

public class ReadFile {
	private static String username = "";
	private static String password = "";
	private static final String fileName = "config.txt";

	public static void getUP() throws Exception {
		String path = Test1.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//		String decodedPath="";
		
		
		FileReader fileReader = new FileReader("config.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String one = bufferedReader.readLine();
		String two = bufferedReader.readLine();
		Scrape.setUsername(one);
		Scrape.setPassword(two);
		bufferedReader.close();
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ReadFile.password = password;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		ReadFile.username = username;
	}
}
