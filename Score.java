import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Score implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4925191446934693954L;
	/*
	 * Warmup 1
	 * Warmup 2
	 * Logic 1
	 * String 1
	 * Array 1
	 * Logic 2
	 * String 2
	 * Array 2
	 * AP 1
	 * String 3
	 * Array 3
	 * Recursion 1
	 * Recursion 2 
	 */
	public Score() {
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
		date = (formatter.format(now));
	}
	private int[] scores = new int[13];
	@SuppressWarnings("unused")
	private String date="";
	public String getDate() {
		return date;
	}
	private String name = "";
	private String id = "";
	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public int len() {
		return scores.length;
	}
	public int sum() {
		int s=0;
		for(int i:scores) {
			s+=i;
		}
		return s;
	}
}
