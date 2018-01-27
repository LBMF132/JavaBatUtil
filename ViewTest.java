import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewTest {

	private JFrame frame;
	private JTextField txtStudentId;
	private static JMenu gMenu;
	private static void addBoxes(JMenu categoryMenu) {
		JCheckBoxMenuItem chTotal = new JCheckBoxMenuItem("Total");
		categoryMenu.add(chTotal);
		JCheckBoxMenuItem chWarmup1 = new JCheckBoxMenuItem("Warmup 1");
		categoryMenu.add(chWarmup1);
		JCheckBoxMenuItem chWarmup2 = new JCheckBoxMenuItem("Warmup 2");
		categoryMenu.add(chWarmup2);
		JCheckBoxMenuItem chLogic1 = new JCheckBoxMenuItem("Logic 1");
		categoryMenu.add(chLogic1);
		JCheckBoxMenuItem chString1 = new JCheckBoxMenuItem("String 1");
		categoryMenu.add(chString1);
		JCheckBoxMenuItem chArray1 = new JCheckBoxMenuItem("Array 1");
		categoryMenu.add(chArray1);
		JCheckBoxMenuItem chLogic2 = new JCheckBoxMenuItem("Logic 2");
		categoryMenu.add(chLogic2);
		JCheckBoxMenuItem chString2 = new JCheckBoxMenuItem("String 2");
		categoryMenu.add(chString2);
		JCheckBoxMenuItem chArray2 = new JCheckBoxMenuItem("Array 2");
		categoryMenu.add(chArray2);
		JCheckBoxMenuItem chAP1 = new JCheckBoxMenuItem("AP 1");
		categoryMenu.add(chAP1);
		JCheckBoxMenuItem chString3 = new JCheckBoxMenuItem("String 3");
		categoryMenu.add(chString3);
		JCheckBoxMenuItem chArray3 = new JCheckBoxMenuItem("Array 3");
		categoryMenu.add(chArray3);
		JCheckBoxMenuItem chRecursion1 = new JCheckBoxMenuItem("Recursion 1");
		categoryMenu.add(chRecursion1);
		JCheckBoxMenuItem chRecursion2 = new JCheckBoxMenuItem("Recursion 2");
		categoryMenu.add(chRecursion2);
	}

	/**
	 * Launch the application.
	 */
	public static void refreshView(AllData a) {
		ArrayList<Group> groups = a.getGroups();
		ArrayList<Combo> allCombo = new ArrayList<Combo>(10);
		gMenu.removeAll();
		for (Group g : groups) {
			allCombo.add(new Combo(g));
		}
		for (Combo c : allCombo) {
			gMenu.add(c.chBox);
		}
	}
	public static JPanel initView(AllData a) {
		ArrayList<String> addedIDs = new ArrayList<String>(10);
		JMenu categoryMenu = new JMenu("Categories");
		addBoxes(categoryMenu);
		JPanel view = new JPanel();
		view.setLayout(null);
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);

		view.setBounds(0, 0, 1350, 1016);
		ArrayList<Group> groups = a.getGroups();
		ArrayList<Combo> allCombo = new ArrayList<Combo>(10);
		for (Group g : groups) {
			allCombo.add(new Combo(g));
		}

		JMenuBar options = new JMenuBar();
		JMenu groupMenu = new JMenu("Select group");
		gMenu = groupMenu;
		for (Combo c : allCombo) {
			groupMenu.add(c.chBox);
		}

		JButton loadView = new JButton("Update View");
		loadView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.setColumnCount(0);
				model.setRowCount(0);
				model.addColumn("Name");
				model.addColumn("ID");
				// System.out.println("KaOkTTT__--"+categoryMenu.getMenuComponentCount());
				int smc = 0;
				for (int i = 0; i < categoryMenu.getMenuComponentCount(); i++) {
					JCheckBoxMenuItem chbm = (JCheckBoxMenuItem) categoryMenu.getMenuComponent(i);
					if (chbm.isSelected()) {
						model.addColumn(chbm.getText());
						smc++;
					}

				}
				for(String s: addedIDs) {
					Person p = a.getGroups().get(0).getPerson(s);
					String[] data = new String[smc + 2];
					int filled = 2;
					data[0] = p.getName();
					data[1] = p.getId();
					for (int i = 0; i < categoryMenu.getMenuComponentCount(); i++) {
						JCheckBoxMenuItem chbm = (JCheckBoxMenuItem) categoryMenu.getMenuComponent(i);
						if (chbm.isSelected()) {
							String cat = chbm.getText();
							switch (cat) {
							case "Total":
								data[filled] = "" + p.getScore(p.numScores() - 1).sum();
								filled++;
								break;
							case "Warmup 1":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[0];
								filled++;
								break;
							case "Warmup 2":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[1];
								filled++;
								break;
							case "Logic 1":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[2];
								filled++;
								break;
							case "String 1":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[3];
								filled++;
								break;
							case "Array 1":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[4];
								filled++;
								break;
							case "Logic 2":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[5];
								filled++;
								break;
							case "String 2":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[6];
								filled++;
								break;
							case "Array 2":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[7];
								filled++;
								break;
							case "AP 1":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[8];
								filled++;
								break;
							case "String 3":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[9];
								filled++;
								break;
							case "Array 3":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[10];
								filled++;
								break;
							case "Recursion 1":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[11];
								filled++;
								break;
							case "Recursion 2":
								data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[12];
								filled++;
								break;

							}
						}

					}
					model.addRow(data);
				}
				for (int k = 0; k < groupMenu.getMenuComponentCount(); k++) {
					JCheckBoxMenuItem chb = (JCheckBoxMenuItem) groupMenu.getMenuComponent(k);
					if (chb.isSelected()) {
						Group g = a.getGroup(chb.getText());
						for (Person p : g.getPeople()) {
							String[] data = new String[smc + 2];
							int filled = 2;
							data[0] = p.getName();
							data[1] = p.getId();
							for (int i = 0; i < categoryMenu.getMenuComponentCount(); i++) {
								JCheckBoxMenuItem chbm = (JCheckBoxMenuItem) categoryMenu.getMenuComponent(i);
								if (chbm.isSelected()) {
									String cat = chbm.getText();
									switch (cat) {
									case "Total":
										data[filled] = "" + p.getScore(p.numScores() - 1).sum();
										filled++;
										break;
									case "Warmup 1":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[0];
										filled++;
										break;
									case "Warmup 2":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[1];
										filled++;
										break;
									case "Logic 1":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[2];
										filled++;
										break;
									case "String 1":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[3];
										filled++;
										break;
									case "Array 1":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[4];
										filled++;
										break;
									case "Logic 2":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[5];
										filled++;
										break;
									case "String 2":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[6];
										filled++;
										break;
									case "Array 2":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[7];
										filled++;
										break;
									case "AP 1":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[8];
										filled++;
										break;
									case "String 3":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[9];
										filled++;
										break;
									case "Array 3":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[10];
										filled++;
										break;
									case "Recursion 1":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[11];
										filled++;
										break;
									case "Recursion 2":
										data[filled] = "" + p.getScore(p.numScores() - 1).getScores()[12];
										filled++;
										break;

									}
								}

							}
							model.addRow(data);
						}
					}
				}
			}
		});
		loadView.setBounds(25, 45, 150, 20);
		view.add(loadView);
		JTextField txtStudentID = new JTextField("Student ID - for selecting one student");
		JButton butStudentSel = new JButton("Add Student");
		butStudentSel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(a.getGroups().get(0).hasPerson(txtStudentID.getText())) {
					addedIDs.add(txtStudentID.getText());
				}else {
					txtStudentID.setText("Couldn't find student");
				}
			}
		});
		
		options.add(groupMenu);
		view.add(options);
		options.add(txtStudentID);
		options.add(butStudentSel);
		options.add(categoryMenu);
		options.setBounds(0, 0, 626, 31);

		JScrollPane scr = new JScrollPane(table);
		scr.setBounds(0, 99, 589, 436);
		view.add(scr);
		return view;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTest window = new ViewTest();
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
	public ViewTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 762, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(ViewTest.initView(null));

		/*
		 * JMenuBar menuBar = new JMenuBar(); menuBar.setBounds(0, 0, 536, 31);
		 * frame.getContentPane().add(menuBar);
		 * 
		 * JMenu mnGroups = new JMenu("Groups"); menuBar.add(mnGroups);
		 * 
		 * JCheckBoxMenuItem chckbxmntmGr = new JCheckBoxMenuItem("Gr1");
		 * mnGroups.add(chckbxmntmGr);
		 * 
		 * txtStudentId = new JTextField(); txtStudentId.setText("Student ID");
		 * menuBar.add(txtStudentId); txtStudentId.setColumns(10);
		 * txtStudentId.setBounds(0, 0, 10, 10);
		 * 
		 * JButton btnAddStudentFrom = new JButton("Add student from ID");
		 * menuBar.add(btnAddStudentFrom);
		 * 
		 * JMenu mnCategories = new JMenu("Categories"); menuBar.add(mnCategories);
		 * 
		 * JCheckBoxMenuItem chckbxmntmTotal = new JCheckBoxMenuItem("Total");
		 * mnCategories.add(chckbxmntmTotal);
		 * 
		 * JButton btnUpdateView = new JButton("Update View");
		 * btnUpdateView.setBounds(291, 60, 149, 29);
		 * frame.getContentPane().add(btnUpdateView);
		 */
	}
}
