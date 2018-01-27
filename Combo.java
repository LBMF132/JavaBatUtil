import javax.swing.JCheckBoxMenuItem;

public class Combo {
	public JCheckBoxMenuItem chBox;
	public Group group;
	public String gName;
	public Combo(Group g) {
		gName = g.getName();
		group=g;
		chBox = new JCheckBoxMenuItem(g.getName());
	}
}
