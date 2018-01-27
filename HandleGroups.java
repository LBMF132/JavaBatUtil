import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HandleGroups {
	public static void deleteGroup(String name) {
		
	}
	public static void addGroup(String name) throws FileNotFoundException {
		PrintStream writer = new PrintStream(new FileOutputStream("C:\\Users\\19lfreeman\\Desktop\\CSII\\IA\\Proj\\groups.txt", true));
		writer.println("@"+name);
		writer.println("^");
		writer.close();
	}
	public static void addStudent(Group g, String id) {
		
	}
	public static void removeStudent(Group g, String id) {
		
	}
}
