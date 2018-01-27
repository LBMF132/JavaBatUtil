import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

public class ACheck {
	public JCheckBoxMenuItem chb;
	public Assignment aS;
	public ACheck(JCheckBoxMenuItem c,Assignment a) {
		chb=c;
		aS=a;
	}
	public void delete(JMenu comp,JMenu prog,AllData a) {
		for(int i=0;i<a.getAssignments().size();i++) {
			Assignment f = a.getAssignments().get(i);
			if(f.getName().equals(aS.getName())) {
				a.getAssignments().remove(f);
			}
		}
		AssignPanel.addMen(comp, prog, a);
	}
}
