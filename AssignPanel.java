import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Color;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.*;
import java.awt.FlowLayout;
public class AssignPanel {

	private JFrame frame;
	private JTable table;
	private JTextField txtAssignmentName;
	private JTextField txtNumber;
	private JTextField txtDaysUntilDue;

	/**
	 * Launch the application.
	 */
	private static JMenu compM = null;
	private static JMenu progM = null;
	private static JMenu groupM = null;
	public static void addMen(JMenu comp, JMenu prog, AllData a) {
		comp.removeAll();
		prog.removeAll();
		ArrayList<Assignment> asis = a.getAssignments();
		ArrayList<ACheck> entries = new ArrayList<ACheck>(10);
		Date today = new Date();
		for (int i = 0; i < asis.size(); i++) {
			Assignment assign = asis.get(i);
			try {
				boolean b = today.before(new SimpleDateFormat("DDD/yyyy").parse(assign.getDate()));
				if (b) {
					JMenu j = new JMenu(assign.getName());
					JCheckBoxMenuItem chi = new JCheckBoxMenuItem("View");
					ACheck aC = new ACheck(chi, assign);
					entries.add(aC);
					JButton deleteButton = new JButton("Delete Assignment");
					j.add(chi);
					j.add(deleteButton);
					deleteButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							aC.delete(comp, prog, a);
							System.out.println("Assignmetn delted attempt");
							AssignPanel.addMen(compM, progM, a);
						}
					});
					prog.add(j);
				} else {
					JMenu j = new JMenu(assign.getName());
					JCheckBoxMenuItem chi = new JCheckBoxMenuItem("View");
					ACheck aC = new ACheck(chi, assign);
					entries.add(aC);
					JButton deleteButton = new JButton("Delete Assignment");
					j.add(chi);
					j.add(deleteButton);
					deleteButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							aC.delete(comp, prog, a);
							System.out.println("Assignmetn delted attempt");
							AssignPanel.addMen(compM, progM, a);
						}
					});
					comp.add(j);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static JPanel initView(AllData a) {

		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 417, 31);
		panel.add(menuBar);

		JMenu mnCompletedAssignments = new JMenu("Completed Assignments");
		menuBar.add(mnCompletedAssignments);

		JMenu mnInprogressAssignments = new JMenu("In-Progress Assignments");
		menuBar.add(mnInprogressAssignments);
		compM = mnCompletedAssignments;
		progM = mnInprogressAssignments;
		AssignPanel.addMen(mnCompletedAssignments, mnInprogressAssignments, a);
		JTextPane txtpnAssignmentNameAsdklkl = new JTextPane();
		JScrollPane scrollPanetxt = new JScrollPane(txtpnAssignmentNameAsdklkl);
		scrollPanetxt.setBounds(10, 34, 563, 80);
		JTable table = new JTable();
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBounds(0, 130, 563, 400);
		panel.add(tablePane);
		panel.add(scrollPanetxt);
		txtpnAssignmentNameAsdklkl.setText("");
		JButton btnNewButton = new JButton("Update View");
		btnNewButton.setBounds(432, 2, 141, 29);
		panel.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<Assignment> selAss = new ArrayList<Assignment>(10);
				for (Component mi : mnCompletedAssignments.getMenuComponents()) {
					JCheckBoxMenuItem checkB = (JCheckBoxMenuItem) ((JMenu) mi).getMenuComponent(0);
					if (checkB.isSelected()) {
						Assignment n = a.getAssignment(((JMenu) mi).getText());
						selAss.add(n);
					}
				}
				for (Component mi : mnInprogressAssignments.getMenuComponents()) {
					JCheckBoxMenuItem checkB = (JCheckBoxMenuItem) ((JMenu) mi).getMenuComponent(0);
					if (checkB.isSelected()) {
						Assignment n = a.getAssignment(((JMenu) mi).getText());
						selAss.add(n);
					}
				}
				txtpnAssignmentNameAsdklkl.setText("");
				for (Assignment k : selAss) {
					Group g = k.getGroup();
					if (g == null)
						System.out.println("Group is nullF\n\n\nFFFFFFFf");
					String m="";
					try {
						m = "Name: " + k.getName() + "  Number of bats: " + k.getNum() + "  Group: "
								+ k.getGroup().getName() + "  Due @: " + k.getViewableDate() + "\n";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					txtpnAssignmentNameAsdklkl.setText(txtpnAssignmentNameAsdklkl.getText() + m);

				}
				DefaultTableModel mod = new DefaultTableModel();
				mod.addColumn("A. Name");
				mod.addColumn("Student Name");
				mod.addColumn("ID");
				mod.addColumn("Score");
				mod.addColumn("Completed");
				for (Assignment b : selAss) {
					for (Person p : b.getGroup().getPeople()) {
						String[] data = new String[5];
						data[0] = b.getName();
						data[1] = p.getName();
						data[2] = p.getId();
						int c = p.getScore(p.numScores() - 1).getScores()[b.cat];
						data[3] = "" + c;
						data[4] = "" + (c >= b.getNum());
						mod.addRow(data);
					}
				}
				table.setModel(mod);
			}
		});

		return panel;
	}

	public static JPanel initAdd(AllData a) {

		JPanel pan = new JPanel();
		pan.setBounds(0, 0, 777, 468);
		pan.setLayout(null);
		pan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextField txtAssignmentName = new JTextField();
		txtAssignmentName.setText("Assignment Name");
		txtAssignmentName.setBounds(15, 16, 146, 26);
		pan.add(txtAssignmentName);
		txtAssignmentName.setColumns(10);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(167, 16, 139, 31);
		pan.add(menuBar);

		JMenu mnGroup = new JMenu("Group");
		mnGroup.setBackground(Color.WHITE);
		menuBar.add(mnGroup);

		JMenu mnCategory = new JMenu("Category");
		menuBar.add(mnCategory);

		JTextField txtNumber = new JTextField();
		txtNumber.setText("Number of 'bats");
		txtNumber.setBounds(318, 16, 146, 26);
		pan.add(txtNumber);
		txtNumber.setColumns(10);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		pan.add(datePicker);
		

		groupM = mnGroup;
		JButton btnAddGroup = new JButton("Add Assignment");
		btnAddGroup.setBounds(275, 63, 115, 29);
		pan.add(btnAddGroup);
		ButtonGroup gGroup = new ButtonGroup();
		for (Group g : a.getGroups()) {
			JRadioButtonMenuItem radioB = new JRadioButtonMenuItem(g.getName());
			gGroup.add(radioB);
			mnGroup.add(radioB);
		}
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i <= 13; i++) {
			JRadioButtonMenuItem rad = new JRadioButtonMenuItem(CategoryUtil.numToString(i));
			group.add(rad);
			mnCategory.add(rad);
		}
		btnAddGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String d = "";
				try {
//					d = Work.transformDate(txtDaysUntilDue.getText());
					Date selectedDate = (Date) datePicker.getModel().getValue();
					DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
					d = formatter.format(selectedDate);
				} catch (Exception e) {
					// System.out.println("Something's wrong with the date tranformation.");
				}
				
				if (d.equals("")) {
					JOptionPane.showMessageDialog(pan, "Something is wrong with the date you inputted.", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				String n = txtAssignmentName.getText();
				int ca = -1;
				for (Component c : mnCategory.getMenuComponents()) {
					JRadioButtonMenuItem radB = (JRadioButtonMenuItem) c;
					if (radB.isSelected()) {
						ca = CategoryUtil.stringToInt(radB.getText());
					}
				}
				if (ca == -1) {
					JOptionPane.showMessageDialog(pan, "You didn't select a category.", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				Group g = null;

				for (Component c : mnGroup.getMenuComponents()) {
					JRadioButtonMenuItem radB = (JRadioButtonMenuItem) c;
					if (radB.isSelected()) {
						g = a.getGroup(radB.getText());
						System.out.println("button Selected\n3" + radB.getText() + "3");
					}
				}
				// if(g)
				if (g == null) {
					JOptionPane.showMessageDialog(pan, "You did not select a group.", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				int numb = -999998;
				try {
					numb = Integer.parseInt(txtNumber.getText());
				} catch (Exception e) {

				}
				if (numb == -999998) {
					JOptionPane.showMessageDialog(pan, "Something is wrong with the number you inputted.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				Assignment assNew = new Assignment(d, n, "", ca, g, numb);
				a.addAssignment(assNew);
				AssignPanel.addMen(compM, progM, a);
				// for(Component )
				// String d, String n,String b, int ca,Group g,int numb
			}
		});

		// for()

		return pan;
	}

	public static void refreshAdd(AllData a) {
		groupM.removeAll();
		ButtonGroup bG = new ButtonGroup();
		for(Group g:a.getGroups()) {
			JRadioButtonMenuItem rB = new JRadioButtonMenuItem(g.getName());
			bG.add(rB);
			groupM.add(rB);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignPanel window = new AssignPanel();
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
	public AssignPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		Date d = datePicker.getJDateInstantPanel().getModel().ge
//		datePicker.setBounds(100, 100, 100, 300);
		 
		frame.getContentPane().add(datePicker);
	}
}
