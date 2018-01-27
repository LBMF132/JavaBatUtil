import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Assignment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7139810777118213042L;
	private String due;
	private String set;
	private String name;
	private Group group;
	private int num;
	public int cat=0;
	public int getNum() {
		return num;
	}
	public String getName() {
		return name;
	}
	public String getViewableDate() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
		Date d = formatter.parse(due);
		DateFormat formatter2 = new SimpleDateFormat("DDD/MMM/yyyy");
		return formatter2.format(d);

	}
	public String getDate() {
		return due;
	}
	public Group getGroup() {
		return group;
	}
	public Assignment(String d,String n,String b,int ca,ArrayList<Group> gs) {
		
	}
	public Assignment(String d, String n,String b, int ca,Group g,int numb) {
		due=d;
		//TODO date set
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
		//set = (formatter.format(now));
		name=n;
		cat=ca;
		set=b;
		group=g;
		set=formatter.format(now);
		num=numb;
	}
	public Assignment(String d,String s,int cat) {
		
	}
	
	
}
