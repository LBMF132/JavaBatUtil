import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.table.DefaultTableModel;

public class Work2 {
	public static String reverse(int i) {
		return "Needs work";
	}
	public static ArrayList<JCheckBox> addGroupMenu(JMenu menu,AllData a) {
		ArrayList<Group> gs = a.getGroups();
		ArrayList<JCheckBox> list = new ArrayList<JCheckBox>(10);
		for(Group g:gs) {
			JCheckBox c = new JCheckBox(g.getName());
			menu.add(c);
			list.add(c);
		}
		return list;
	}
	public static ArrayList<Group> getGroupBox(ArrayList<JCheckBox> arr,AllData a){
		System.out.println("getting group box x41"+arr.size()+"this was size");
		ArrayList<Group> gs = new ArrayList<Group>(10);
		for(JCheckBox box:arr) {
			System.out.println(box.getText());
			if(box.isSelected()) {
				System.out.print(box.getText());	
				String s = box.getText();
				Group g = a.getGroup(s);
				gs.add(g);
			}
		}
		return gs;
	}
	public static boolean view(Assignment a) {
		Strs.modelAssignment.setRowCount(0);
		Strs.modelAssignment.setColumnCount(0);
//		DefaultTableModel mod = Strs.modelAssignment;
		Group toDisplay = a.getGroup();
		ArrayList<Person> people = toDisplay.getPeople();
		String name = "";
		String progress = "";
		String complete = "";
		Strs.modelAssignment.addColumn("Name");
		Strs.modelAssignment.addColumn("" + reverse(a.cat) + "," + a.getNum());
		Strs.modelAssignment.addColumn("Completed");
//		Strs.modelAssignment.addRow(new String[]{"Name", "" + reverse(a.cat) + "," + a.getNum(), "Completed"});
		for(Person p:people) {
			name=p.getName();
			int s = p.getScore(p.numScores()-1).getScores()[a.cat];
			progress=""+s+"/"+a.getNum();
			if(s>=a.getNum()) {
				complete="Done";
			}else {
				complete="X";
			}
			String[] arr = {name,progress,complete};
			Strs.modelAssignment.addRow(arr);
		}
		// for()
		return true;
	}

}
