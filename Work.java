import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class Work {
	public static ArrayList<Group> allAddedGroups = new ArrayList<Group>(10);
	public static ArrayList<JMenu> allAssignments = new ArrayList<JMenu>(10);
	public static ArrayList<Person> allAddedPeople = new ArrayList<Person>(10);
	public static String transformDate(String s) throws NumberFormatException {
		//gets a form of -5 days, 5 days etc and turns it into day#/year#
		int sk = Integer.parseInt(s);
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, sk);
		dt = c.getTime();
		DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
		return formatter.format(dt);
	}
	private static int getCat(ArrayList<JCheckBox> boxes) {
		for(int i=0;i<boxes.size();i++) {
			JCheckBox b = boxes.get(i);
			if(b.isSelected())
				return i+1;
			
		}
		return -1;
	}
	//1 - group didnt work
	//2 - due date didn't work
	//3 - cat didnt' work
	
	public static void assign(AllData a,JMenu prog,JMenu comp) {
		prog.removeAll();
		comp.removeAll();
		ArrayList<Assignment> as=a.getAssignments();
		for(Assignment ass1:as) {
			boolean completed=false;
			System.out.println("tried to add");
			String s1=ass1.getDate();
			String s2=new SimpleDateFormat("DDD").format(new Date());
			s1=s1.split("/")[0];
			//CANNOT PARSE THE FORMAT 
			
			int sDue = Integer.parseInt(s1);
			int sToday = Integer.parseInt(s2);
			if(sDue<=sToday) {
				//comp
				completed=true;
				comp.add(new AMenu(ass1,completed,a).getMenu());
				System.out.print("Added to completed");
				//Add onclicks
			}else {
				//notcomp
				
//				completed=false;
				prog.add(new AMenu(ass1,completed,a).getMenu());
			}
			
		}
		prog.repaint();
		comp.repaint();
	}
	
	public static int addAssign(AllData a,JTextField due,JTextField name,ArrayList<JCheckBox> boxes,JTextField group,JTextField num) {
		String gname = group.getText();
		Group g = a.getGroup(gname);
		if(g==null)
			return 1;
		String setDate = new SimpleDateFormat("DDD/yyy").format(new Date());
		String dueDate = transformDate(due.getText()); 
		if(dueDate==null)
			return 2;
		int category = getCat(boxes)-2;
		if(category==-1)
			return 3;
		String nameN = name.getText();
		int number = Integer.parseInt(num.getText());
		
		Assignment one = new Assignment(dueDate,nameN,setDate,category,g,number);
		a.addAssignment(one);
		return 0;
	}
	public static boolean removeGroup(String s) {
		for(int i=0;i<allAddedGroups.size();i++) {
			Group g = allAddedGroups.get(i);
			if(g.getName().equals(s)) {
				allAddedGroups.remove(i);
				return true;
			}else {
			
			}
		}
		return false;
	}
	public static void refill(DefaultTableModel m, boolean[] select) {
		for(Group f:allAddedGroups) {
			addGroup(m,f,select);
		}
		for(Person k:allAddedPeople) {
			addPerson(m,k,select);
		}
			
	}
	public static boolean[] getBools(ArrayList<JCheckBox> allBoxes) {
		boolean[] all = new boolean[allBoxes.size()];
		for(int i=0;i<all.length;i++) {
			all[i]=allBoxes.get(i).isSelected();
		}
		return all;
	}
	public static String getTrueText(JTextField t) {
		return t.getText();
	}
	private static int numTrue(boolean[] b) {
		int i=0;
		for(boolean x:b) {
			if(x)
				i++;
		}
		return i;
	}
	public static void addGroup(DefaultTableModel m, Group g, boolean[] select) {
		allAddedGroups.add(g);
		ArrayList<Person> peopleInGroup = g.getPeople();
		for(Person p:peopleInGroup) {
			int filled=1;
			String[] results = new String[numTrue(select)+1];
			results[0]=p.getName();
			for(int i=0;i<select.length;i++) {
				String s="";
				//TODO add total on to the end of  scores[]
				if(i==13&&select[i]==true) {
					int k=0;
					int[] scores = p.getScore(p.numScores()-1).getScores();
					for(int l:scores) {
						k+=l;
					}
					s+=k;
					results[filled]=s;
					continue;
				}
				if(select[i]) {
					String s1 = "";
					int[] scores = p.getScore(p.numScores()-1).getScores();
					s1+=scores[i];
					results[filled]=s1;
					filled++;
				}
			}
			m.addRow(results);
		}
		
	}
	public static void addPerson(DefaultTableModel m, Person p, boolean[] select) {
		int filled=1;
		String[] results = new String[numTrue(select)+1];
		results[0]=p.getName();
		for(int i=0;i<select.length;i++) {
			String s="";
			//TODO add total on to the end of  scores[]
			if(i==13&&select[i]==true) {
				int k=0;
				int[] scores = p.getScore(p.numScores()-1).getScores();
				for(int l:scores) {
					k+=l;
				}
				s+=k;
				results[filled]=s;
				continue;
			}
			if(select[i]) {
				String s1 = "";
				int[] scores = p.getScore(p.numScores()-1).getScores();
				s1+=scores[i];
				results[filled]=s1;
				filled++;
			}
		}
		m.addRow(results);
		allAddedPeople.add(p);
	}
}
