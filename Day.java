import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Day {
	private String date = "";
	private ArrayList<Person> people = new ArrayList<Person>(100);
	public void addPerson(Person p) {
		people.add(p);
	}
	public Day() {
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
		date = (formatter.format(now));
	}
	
}
