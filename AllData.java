import java.util.ArrayList;
import java.io.Serializable;
public class AllData implements Serializable{
	/**
	 * 
	 */
	public ArrayList<AMenu> AMenus = new ArrayList<AMenu>(10);
	public boolean hasAll = false;
	private static final long serialVersionUID = 1L;
	public void addAssignment(String cat, Group g, String name,String begin,String end,int num) {
		int c=Parse.convert(cat);
		Assignment af = new Assignment(end,name,begin,c,g,num);
		addAssignment(af);
	}
	public AllData() {

	}
	public boolean deleteAssignment(Assignment a) {
		for(int i=0;i<assignments.size();i++) {
			Assignment as = assignments.get(i);
			if(a.getName().equals(as.getName())) {
				assignments.remove(i);
				return true;
			}
				
		}
		return false;
	}
	private ArrayList<Group> groups = new ArrayList<Group>(10);
	public boolean removeGroup(String s) {
		for(int i=0;i<groups.size();i++) {
			Group current = groups.get(i);
			if(current.getName().equals(s)) {
				groups.remove(i);
				return true;
			}
		}
		return false;
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void addGroup(Group g) {
		groups.add(g);
	}

	private ArrayList<Assignment> assignments = new ArrayList<Assignment>(10);

	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}

	public void addAssignment(Assignment a) {
		assignments.add(a);
	}
	public Group getGroup(String name) {
		for(Group g:groups) {
			if(g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}
	public Assignment getAssignment(String n) {
		for(Assignment a:assignments) {
			if(a.getName().equals(n))
				return a;
		}
		return null;
	}
	private ArrayList<Day> history = new ArrayList<Day>(10);
	public ArrayList<Day> getDays(){
		return history;
	}
	public void addDay(Day d) {
		history.add(d);
	}
}
