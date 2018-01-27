import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

public class Test1 {
	public static void ra() {
		try{
		AllData ak = new AllData();
		String filename = "C:\\Users\\19lfreeman\\Desktop\\CSII\\IA\\store.ser";
		System.out.println(filename);
		ak = (AllData)SerializationUtil.deserialize(filename);
		File f = new File(filename);
		f.createNewFile();
		if(f.exists() && !f.isDirectory()) { 
			ak = (AllData)SerializationUtil.deserialize(filename);
		}
		System.out.println(ak.getGroups().size());
		SerializationUtil.serialize(ak, filename);
		}catch(Exception e) {
			
		}
	}
}

// DO ALL THE TYPES OF PROBLEMS AND HANDLE THAT