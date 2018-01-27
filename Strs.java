import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Strs {
	private JFrame frame;
	private JTextField txtGroupView;
	private JTextField txtViewStudent;
	private JTextField textField_2;
	private JTextField txtStudentId;
	private JTextField txtGroupName;
	private static boolean deployed = false;
	public static AllData a = new AllData();
	private String fileName = "./store.ser";
	private static JTable table;
	private ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>(12);
	private ArrayList<JCheckBox> boxes2 = new ArrayList<JCheckBox>(12);
	private final DefaultTableModel model = new DefaultTableModel();
	private JTextField txtStudentGroup;
	private JTextField txtAssignmentName;
	private JTextField txtEndsInX;
	private JTextField txtGroupNameTo;
	private JPanel testPan = null;
	public static ArrayList<JCheckBox> groupViewMenuBoxes = new ArrayList<JCheckBox>(10);
	private JTable tableAssignment;
	public static JMenu mnComp;
	public static JMenu mnProg;
	public static final DefaultTableModel modelAssignment = new DefaultTableModel();
	private JTextField txtNumBats;
	private String patha;

	private void updateTable(ArrayList<JCheckBox> f) {
		model.setRowCount(0);
		model.setColumnCount(0);
		model.addColumn("Name");
		for (int i = 0; i < f.size(); i++) {
			JCheckBox j = f.get(i);
			if (j.isSelected()) {
				model.addColumn(change(i));
			}
		}
		Work.refill(model, Work.getBools(f));
		table.repaint();
	}

	private String change(int i) {
		switch (i) {
		case 0:
			return "Warmup 1";
		case 1:
			return "Warmup 2";
		case 2:
			return "Logic 1";
		case 3:
			return "String 1";
		case 4:
			return "Array 1";
		case 5:
			return "Logic 2";
		case 6:
			return "String 2";
		case 7:
			return "Array 2";
		case 8:
			return "AP 1";
		case 9:
			return "String 3";
		case 10:
			return "Array 3";
		case 11:
			return "Recursion 1";
		case 12:
			return "Recursion 2";
		case 13:
			return "Total";
		default:
			return "BRokE";
		}
	}
 
	/**
	 * Launch the application.
	 */
	;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Strs window = new Strs();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**x	
	 * Create the application.
	 */
	public Strs() {
		System.out.print("Init class strs\n");
		String path = Test1.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//		String decodedPath="";
		
		try {
			a = (AllData) SerializationUtil.deserialize("store.ser");
			System.out.println("#Ass"+a.getAssignments().size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Scrape.getData();
			Parse.parse(Scrape.handleData(), a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Pre - init");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1350, 1016);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					SerializationUtil.serialize(a, "store.ser");
					System.out.print("saving XXXX"+fileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(0, 0, 1280, 812);
		frame.getContentPane().add(layeredPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1207, 715);
		layeredPane.add(tabbedPane);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Group", null, tabbedPane_1, null);

		JPanel panel_2 = new JPanel();
//		tabbedPane_1.addTab("Manage", null, panel_2, null);
		tabbedPane_1.addTab("Manage",null, TestPanel.initGroupAdd(a),null);
//		tabbedPane_1.addTab("Test", AssignPanel.initView(a));
//		tabbedPane_1.addTab("Test2", AssignPanel.initAdd(a));
//		tabbedPane_1.addTab("Test2", ViewTest.initView(a));
		panel_2.setLayout(null);

		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String gName = txtGroupName.getText();
				Group gNew = new Group(gName);
				a.addGroup(gNew);
				System.out.println(txtGroupName.getText());
				txtGroupName.setText("Added Group!   " + a.getGroups().size());
			}
		});
		btnAddGroup.setBounds(178, 67, 111, 29);
		panel_2.add(btnAddGroup);

		JButton btnRemoveGroup = new JButton("Remove Group");
		btnRemoveGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				boolean b = a.removeGroup(txtGroupName.getText());
				if (b) {
					txtGroupName.setText("Group Removed!");
				} else {
					txtGroupName.setText("Could not remove group.");
				}
			}
		});
		btnRemoveGroup.setBounds(316, 67, 139, 29);
		panel_2.add(btnRemoveGroup);

		txtStudentId = new JTextField();
		txtStudentId.setBounds(15, 17, 146, 26);
		txtStudentId.setText("Student ID");
		txtStudentId.setColumns(10);
		panel_2.add(txtStudentId);

		txtGroupName = new JTextField();
		txtGroupName.setBounds(15, 68, 146, 26);
		txtGroupName.setText("Group Name");
		txtGroupName.setColumns(10);
		panel_2.add(txtGroupName);

		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String gName = txtStudentGroup.getText();
				String sName = txtStudentId.getText();
				Group g = a.getGroup(gName);
				Person p = a.getGroup("All").getPerson(sName);
				g.add(p);
			}
		});

		btnAddStudent.setBounds(339, 16, 121, 29);
		panel_2.add(btnAddStudent);

		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.setBounds(475, 16, 149, 29);
		panel_2.add(btnRemoveStudent);

		txtStudentGroup = new JTextField();
		txtStudentGroup.setText("Group Name");
		txtStudentGroup.setBounds(178, 17, 146, 26);
		panel_2.add(txtStudentGroup);
		txtStudentGroup.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		tabbedPane.addTab("View", null, ViewTest.initView(a), null);

		txtGroupView = new JTextField();
		txtGroupView.setText("Group Name");
		txtGroupView.setColumns(10);
		txtGroupView.setBounds(15, 16, 146, 26);
		panel_3.add(txtGroupView);

		JButton btnGroupView = new JButton("Add to view");
		btnGroupView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnGroupView.setBounds(191, 15, 158, 29);
		panel_3.add(btnGroupView);

		JButton btnRemoveGroupView = new JButton("Remove from view");
		btnRemoveGroupView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String rGroup = txtGroupView.getText();
				boolean rdGroup = Work.removeGroup(rGroup);
				if (rdGroup) {
					txtGroupView.setText("removed group!");
				} else {
					txtGroupView.setText("could not remove group");
					updateTable(boxes);
				}
				table.repaint();
			}
		});
		btnRemoveGroupView.setBounds(366, 15, 185, 29);
		panel_3.add(btnRemoveGroupView);

		JCheckBox checkBox = new JCheckBox("All");
		checkBox.setBounds(11, 114, 62, 29);
		panel_3.add(checkBox);

		JCheckBox check1 = new JCheckBox("Warmup 1");
		check1.setBounds(80, 114, 113, 29);
		panel_3.add(check1);

		JCheckBox check2 = new JCheckBox("Warmup 2");
		check2.setBounds(210, 114, 139, 29);
		panel_3.add(check2);

		JCheckBox check3 = new JCheckBox("Logic 1");
		check3.setBounds(322, 114, 92, 29);
		panel_3.add(check3);

		JCheckBox check4 = new JCheckBox("String 1");
		check4.setBounds(433, 114, 97, 29);
		panel_3.add(check4);

		JCheckBox check5 = new JCheckBox("Array 1");
		check5.setBounds(547, 114, 92, 29);
		panel_3.add(check5);

		JCheckBox check6 = new JCheckBox("Logic 2");
		check6.setBounds(635, 114, 92, 29);
		panel_3.add(check6);

		JCheckBox check7 = new JCheckBox("String 2");
		check7.setBounds(735, 114, 92, 29);
		panel_3.add(check7);

		JCheckBox check8 = new JCheckBox("Array 2");
		check8.setBounds(834, 114, 92, 29);
		panel_3.add(check8);

		JCheckBox check9 = new JCheckBox("AP 1");
		check9.setBounds(11, 151, 72, 29);
		panel_3.add(check9);

		JCheckBox check10 = new JCheckBox("String 3");
		check10.setBounds(90, 151, 92, 29);
		panel_3.add(check10);

		JCheckBox check11 = new JCheckBox("Array 3");
		check11.setBounds(191, 151, 92, 29);
		panel_3.add(check11);

		JCheckBox check12 = new JCheckBox("Recursion 1");
		check12.setBounds(290, 151, 123, 29);
		panel_3.add(check12);

		JCheckBox check13 = new JCheckBox("Recursion 2");
		check13.setBounds(427, 151, 139, 29);
		panel_3.add(check13);

		JCheckBox check14 = new JCheckBox("Total");
		check14.setBounds(573, 151, 139, 29);
		panel_3.add(check14);
		boxes.add(check1);
		boxes.add(check2);
		boxes.add(check3);
		boxes.add(check4);
		boxes.add(check5);
		boxes.add(check6);
		boxes.add(check7);
		boxes.add(check8);
		boxes.add(check9);
		boxes.add(check10);
		boxes.add(check11);
		boxes.add(check12);
		boxes.add(check13);
		boxes.add(check14);

		txtViewStudent = new JTextField();
		txtViewStudent.setText("Student ID");
		txtViewStudent.setColumns(10);
		txtViewStudent.setBounds(566, 16, 146, 26);
		panel_3.add(txtViewStudent);

		JButton button_2 = new JButton("Add Student");

		button_2.setBounds(731, 15, 139, 29);
		panel_3.add(button_2);

		JButton btnRemoveStudentView = new JButton("Remove Student");
		btnRemoveStudentView.setBounds(887, 15, 168, 29);
		panel_3.add(btnRemoveStudentView);

		JCheckBox check15 = new JCheckBox("Since \"x\" days");
		check15.setBounds(11, 66, 139, 29);
		panel_3.add(check15);
		table = new JTable();
		table.setModel(model);
		JScrollPane tableContainer = new JScrollPane(table);
		tableContainer.setBounds(0, 348, 1167, 581);
		panel_3.add(tableContainer);
		JScrollPane scrollPane = new JScrollPane((Component) null);
		tableContainer.setColumnHeaderView(scrollPane);
		textField_2 = new JTextField();
		textField_2.setText("X days");
		textField_2.setColumns(10);
		textField_2.setBounds(161, 67, 146, 26);
		panel_3.add(textField_2);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(941, 186, 139, 31);
		panel_3.add(menuBar);

		JMenu mnGroupView = new JMenu("Group");
		menuBar.add(mnGroupView);
		groupViewMenuBoxes = Work2.addGroupMenu(mnGroupView, a);
		System.out.println("boxes lentgt" + groupViewMenuBoxes.size());
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Assignments", null, tabbedPane_2, null);

		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.BLACK);
		tabbedPane_2.addTab("Manage/View", null, AssignPanel.initView(a), null);
		panel_4.setLayout(null);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(10, 16, 666, 31);
		panel_4.add(menuBar_1);

		mnProg = new JMenu("In-progress Assignments");
		menuBar_1.add(mnProg);
		mnProg.setForeground(Color.BLUE);

		mnComp = new JMenu("Completed Assignments");
		mnComp.setForeground(new Color(0, 128, 0));
		menuBar_1.add(mnComp);

		JPanel panel_1 = new JPanel();
		tabbedPane_2.addTab("Add", null, AssignPanel.initAdd(a), null);
		panel_1.setLayout(null);

		JLabel lblPleaseFillAll = new JLabel("Please fill all these fields before adding! Only 1 Category also.");
		lblPleaseFillAll.setBounds(345, 58, 479, 20);
		panel_1.add(lblPleaseFillAll);

		txtAssignmentName = new JTextField();
		txtAssignmentName.setText("Assignment Name");
		txtAssignmentName.setBounds(15, 16, 146, 26);
		panel_1.add(txtAssignmentName);
		txtAssignmentName.setColumns(10);

		txtEndsInX = new JTextField();
		txtEndsInX.setText("Ends in x days(number)");
		txtEndsInX.setBounds(176, 16, 193, 26);
		panel_1.add(txtEndsInX);
		txtEndsInX.setColumns(10);

		txtGroupNameTo = new JTextField();
		txtGroupNameTo.setText("group name goes here");
		txtGroupNameTo.setBounds(372, 16, 488, 26);
		panel_1.add(txtGroupNameTo);
		txtGroupNameTo.setColumns(10);

		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBounds(867, 16, 179, 31);
		panel_1.add(menuBar_2);

		JMenu mnNewMenu_1 = new JMenu("Assignment Category");
		menuBar_2.add(mnNewMenu_1);
		Work.assign(a, mnProg, mnComp);

		tableAssignment = new JTable();
		tableAssignment.setBackground(Color.WHITE);
		// panel_4.add(tableAssignment);
		tableAssignment.setModel(modelAssignment);
		model.addColumn("Completed");
		model.addColumn("Progress");
		model.addColumn("");
		JScrollPane scrollAssignment = new JScrollPane(tableAssignment);
		panel_4.add(scrollAssignment);
		scrollAssignment.setBounds(10, 100, 1000, 1000);
		scrollAssignment.setColumnHeaderView(new JScrollPane((Component) null));

		JCheckBox chAll = new JCheckBox("All");
		mnNewMenu_1.add(chAll);

		JCheckBox ch1 = new JCheckBox("Warmup 1");
		mnNewMenu_1.add(ch1);

		JCheckBox ch2 = new JCheckBox("Warmup 2");
		mnNewMenu_1.add(ch2);

		JCheckBox ch3 = new JCheckBox("Logic 1");

		mnNewMenu_1.add(ch3);

		JCheckBox ch4 = new JCheckBox("String 1");
		mnNewMenu_1.add(ch4);

		JCheckBox ch5 = new JCheckBox("Array 1");
		mnNewMenu_1.add(ch5);

		JCheckBox ch6 = new JCheckBox("Logic 2");
		mnNewMenu_1.add(ch6);

		JCheckBox ch7 = new JCheckBox("String 2");
		mnNewMenu_1.add(ch7);

		JCheckBox ch8 = new JCheckBox("Array 2");
		mnNewMenu_1.add(ch8);

		JCheckBox ch9 = new JCheckBox("AP 1");
		mnNewMenu_1.add(ch9);

		JCheckBox ch10 = new JCheckBox("String 3");
		mnNewMenu_1.add(ch10);

		JCheckBox ch11 = new JCheckBox("Array 3");
		mnNewMenu_1.add(ch11);

		JCheckBox ch12 = new JCheckBox("Recursion 1");
		mnNewMenu_1.add(ch12);

		JCheckBox ch13 = new JCheckBox("Recursion 2");
		mnNewMenu_1.add(ch13);
		boxes2.add(chAll);
		boxes2.add(ch1);
		boxes2.add(ch2);
		boxes2.add(ch3);
		boxes2.add(ch4);
		boxes2.add(ch5);
		boxes2.add(ch6);
		boxes2.add(ch7);
		boxes2.add(ch8);
		boxes2.add(ch9);
		boxes2.add(ch10);
		boxes2.add(ch11);
		boxes2.add(ch12);
		boxes2.add(ch13);
		for (JCheckBox c : boxes2) {
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					for (JCheckBox cx : boxes2) {
						cx.setSelected(false);
					}
					c.setSelected(true);
				}
			});
		}

		JButton btnAddAssignment = new JButton("Add Assignment");
		btnAddAssignment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int added = Work.addAssign(a, txtEndsInX, txtAssignmentName, boxes2, txtGroupNameTo, txtNumBats);
				switch (added) {
				case 0:
					break;
				case 1:
					txtGroupNameTo.setText("Something's wrong - does this group exist?");
					break;
				case 2:
					txtEndsInX.setText("Should be a #");
					break;
				case 3:
					btnAddAssignment.setText("NOTIFY DEVS");

				}
				Work.assign(a, mnProg, mnComp);
				// Work.
			}
		});
		btnAddAssignment.setBounds(507, 81, 115, 29);
		panel_1.add(btnAddAssignment);

		txtNumBats = new JTextField();
		txtNumBats.setText("How many bats?");
		txtNumBats.setBounds(1061, 21, 146, 26);
		panel_1.add(txtNumBats);
		txtNumBats.setColumns(10);
		for (JCheckBox j : boxes) {
			j.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					updateTable(boxes);
					table.repaint();
				}
			});
		}
		btnGroupView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<JCheckBox> boxesz = groupViewMenuBoxes;
				ArrayList<Group> groupsc = Work2.getGroupBox(boxesz, a);
				System.out.println(groupsc.size());
				boolean[] allBool = Work.getBools(boxes);
				String addName = txtGroupView.getText();
				for (Group gG : groupsc) {
					if (gG == null) {
//						txtGroupView.setText("Could not add group.");
					} else {
						System.out.println("Tried to add group to view");
						Work.addGroup(model, gG, allBool);
					}
				}
			}
		});
		checkBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.addColumn("addedd");
				boolean b = checkBox.isSelected();
				check1.setSelected(b);
				check2.setSelected(b);
				check3.setSelected(b);
				check4.setSelected(b);
				check5.setSelected(b);
				check6.setSelected(b);
				check7.setSelected(b);
				check8.setSelected(b);
				check9.setSelected(b);
				check10.setSelected(b);
				check11.setSelected(b);
				check12.setSelected(b);
				check13.setSelected(b);
				check14.setSelected(b);
				updateTable(boxes);
				table.repaint();
				System.out.print("added all");
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// ADD STUDENT TO VIEW
				String sName = txtViewStudent.getText();
				Person addS = a.getGroup("All").getPerson(sName);
				Group temp = new Group("Temp");
				boolean[] allBool = Work.getBools(boxes);

				temp.add(addS);
				Work.addGroup(model, temp, allBool);
				if (addS == null) {
					txtViewStudent.setText("Could not find student.");
				} else {
					Work.addPerson(model, addS, Work.getBools(boxes));
					txtViewStudent.setText("Added student!");
				}
			}
		});

	}
}
