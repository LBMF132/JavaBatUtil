import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JMenu;

public class AMenu {
	private Assignment assignment;
	private JButton delete;
	private JButton view;
	private JMenu myMenu;
	public boolean comp;
	private AllData data;
	public AMenu(Assignment a,boolean b,AllData d) {
		assignment = a;
		myMenu=new JMenu();
		comp=b;
		data=d;
		if(b) {
			myMenu.setForeground(new Color(0,128,0));

		}else {
			myMenu.setForeground(new Color(0, 0, 255));

		}
		addButtons();
	}
	public void addButtons() {
//		delete.add	
		
		delete=new JButton();
		delete.setText("Delete");
		view=new JButton();
		view.setText("View");
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				data.deleteAssignment(assignment);
				Work.assign(Strs.a, Strs.mnProg, Strs.mnComp);
				System.out.print("AMENU delted assignment");
			}
		});
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Work2.view(assignment);
				System.out.print("AMENU viewed assignment");
			}
		});
		myMenu.add(delete);
		myMenu.add(view);
		myMenu.setText(assignment.getName());
	}
	public void setClick() {
		
	}
	public JMenu getMenu() {
		return myMenu;
	}
}

