import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

public class EditFile {
	public static int findLine(String filename, String lookFor) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while(true) {
			try {
				System.out.println(bufferedReader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		return 1;
	}
	public static void editLine() {
		
	}
	public static void deleteLine() {
		
	}
}
