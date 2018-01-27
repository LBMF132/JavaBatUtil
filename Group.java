import java.io.Serializable;
import java.util.ArrayList;

//ALL is a group, then you have others after that
public class Group implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -61425894457261729L;
	private ArrayList<Person> group = new ArrayList<Person>(10);
	private int number = 0;
	private String name = "";

	public Group(String nae) {
		name = nae;
		// Add new group to file
	}
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	public boolean hasPerson(String id) {
		for (Person p : group) {
			if (p.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public Person getPerson(String id) {
		for (Person p : group) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}

	public boolean add(Person p) {
		group.add(p);
		number++;
		return true;
	}

	public boolean remove(Person p) {
		group.remove(p);
		number--;
		return true;
	}
	public ArrayList<Person> getPeople(){
		return group;
	}

}
