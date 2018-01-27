import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5572175111590071052L;
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
	private String id;
	private String name;
	public ArrayList<Score> scores = new ArrayList<Score>(10);
	public Person(String ids,String names) {
		id = ids;
		name = names;
	}
	public void addScore(Score s) {
		scores.add(s);
	}
	public int numScores() {
		return scores.size();
	}
	public Score getScore(int i) {
		if(i<0) {
			return scores.get(0);
		}else {
			return scores.get(i);
		}
	}
	public Score getScore(String d) {
		for(Score s:scores) {
			if(s.getDate().equals(d)) {
				return s;
			}
		}
		return null;
		
	}
	public String toString() {
		return name;
	}	public Person() {
		
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
}
