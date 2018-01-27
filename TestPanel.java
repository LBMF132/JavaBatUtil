import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
/*
  button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
 */
public class TestPanel {
	public static JPanel initGroupAdd(AllData a) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		ArrayList<GroupPerson> allSel = new ArrayList<GroupPerson>(10);
		ArrayList<Person> alls = null;
		System.out.println("\n\n\n\n\nALLSIZE\n"+a.getGroups().size());
		alls = a.getGroups().get(0).getPeople();
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 2)
					return Boolean.class;
				return super.getColumnClass(columnIndex);
			}
		};
		JTextField txtGroupName = new JTextField("Group Name");

		panel.setBounds(0, 0, 1350, 1016);
		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.setBounds(173, 15, 111, 29);
		panel.add(btnAddGroup);
		btnAddGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String gName = txtGroupName.getText();
				a.addGroup(new Group(gName));
				System.out.println("Group added "+a.getGroups().size());
				ViewTest.refreshView(a);
				AssignPanel.refreshAdd(a);
			}
		});

		JButton btnRemoveGroup = new JButton("Remove Group");
		btnRemoveGroup.setBounds(299, 15, 139, 29);
		panel.add(btnRemoveGroup);
		btnRemoveGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Implement this");
			}
		});

		txtGroupName.setColumns(10);
		txtGroupName.setBounds(15, 16, 146, 26);
		panel.add(txtGroupName);

		JButton btnAddSelectedStudents = new JButton("Add selected students");
		btnAddSelectedStudents.setBounds(447, 15, 211, 29);
		panel.add(btnAddSelectedStudents);
		btnAddSelectedStudents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//iterate over rows checking for the selected ones. when we find one get the corresponding name/id(unique?) and add that Person to the group.
				//col 2 is id - getValueAt(row #, 3)
				System.out.println("tryna get students");
				for(int i=0;i<model.getRowCount();i++) {
					Boolean sel = (Boolean)model.getValueAt(i, 2);
					System.out.println(sel);
					if(sel.booleanValue()==true) {
						String sid =  (String)model.getValueAt(i, 1);
						Person perSel = a.getGroups().get(0).getPerson(sid);
						Group toAdd = a.getGroup(txtGroupName.getText());
						if(toAdd==null) {
							txtGroupName.setText("Invalid Group Name");
							return;
						}
						toAdd.add(perSel);
					}
				}
				System.out.println("The number of students in the group is "+a.getGroup(txtGroupName.getText()).getPeople().size()+".");
			}
		});
		JTable allT = new JTable(model);
		model.addColumn("Name");
		model.addColumn("ID");
		model.addColumn("Selected");
		for (int i = 0; i < alls.size(); i++) {
			Person p = alls.get(i);
			GroupPerson g = new GroupPerson(p.getName(), new Boolean(false), p.getId());
			allSel.add(g);
		}
		for (int i = 0; i < allSel.size(); i++) {
			GroupPerson g = allSel.get(i);
			model.addRow(new Object[] { g.name, g.id, g.selected });
		}
		JScrollPane scP = new JScrollPane(allT);
		scP.setBounds(0, 100, 600, 400);
		panel.add(scP);
		// JButton btnAdd
		return panel;
	}

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPanel window = new TestPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static final DefaultTableModel model = new DefaultTableModel() {
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 1)
				return Boolean.class;
			return super.getColumnClass(columnIndex);
		}
	};
	private JPanel panel_1;
	private JTextField txtSalkjfdslksjdflsdkfjlskdjflkk;

	private void initialize() {
		Object[] arr = { "Name", new Boolean(true) };
		model.addColumn("nem");
		model.addColumn("select");
		model.addRow(arr);

		frame = new JFrame();
		frame.setBounds(500, 500, 999, 575);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		for (int i = 0; i < 30; i++) {
			model.addRow(arr);
		}

		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1197, 647);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 626, 31);
		panel_1.add(menuBar);
		
		JButton btnNewButton = new JButton("New button");
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		menuBar.add(btnNewButton_1);
		
		txtSalkjfdslksjdflsdkfjlskdjflkk = new JTextField();
		txtSalkjfdslksjdflsdkfjlskdjflkk.setText("SALKJFDSLKSJDFLSDKFJLSKDJFLKK");
		menuBar.add(txtSalkjfdslksjdflsdkfjlskdjflkk);
		txtSalkjfdslksjdflsdkfjlskdjflkk.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 99, 589, 436);
		panel_1.add(scrollPane);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(209, 54, 115, 29);
		panel_1.add(btnNewButton_2);
		// JCheckBox checkBox = new JCheckBox();

	}
}
