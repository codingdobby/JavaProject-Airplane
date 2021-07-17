package thinking;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.DefaultTableModel;

import thinking.SignUp.MyMouseListener;

public class searchPage extends JFrame {

	Vector v;
	Vector cols;
	DefaultTableModel model;
	JTable jTable;
	JScrollPane pane;
	ManagerDAO mdao = new ManagerDAO();
	JTextField tfDoD;

	public searchPage() {

		setTitle("항공 예약 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.addMouseListener(new MyMouseListener());
		/*****************************************************************************************/
		JLabel title = new JLabel("<항공 예약 시스템 페이지>");
		title.setBounds(348, 0, 400, 50);
		title.setFont(new Font("Serif", Font.BOLD, 25));
		/*****************************************************************************************/

		// 편도 왕복 선택
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbpyeon = new JRadioButton("편도");
		rbpyeon.setSelected(true);

		JRadioButton rbwang = new JRadioButton("왕복");

		bg.add(rbwang);
		bg.add(rbpyeon);

		/*****************************************************************************************/
		// 좌석 타입 선택 이코노미 비지니수
		JPanel p1 = new JPanel();
		p1.setLayout(null);

		ButtonGroup bg1 = new ButtonGroup();
		JRadioButton rbECO = new JRadioButton("이코노미");
		rbECO.setSelected(true);
		JRadioButton rbBUS = new JRadioButton("비즈니스");

		JLabel laChul = new JLabel("출발");
		laChul.setBounds(240, 90, 100, 20);

		JTextField tfChulport = new JTextField(10);

		p1.add(tfChulport);

		tfChulport.setBounds(300, 90, 100, 20);

		JLabel login = new JLabel("");
		login.setText("");

//		JTextField tfChul = new JTextField(10);
//		tfChul.setBounds(300, 90, 100, 20);

		JLabel laDo = new JLabel("도착");
		laDo.setBounds(450, 90, 100, 20);

		JTextField tfDo = new JTextField(10);
		tfDo.setBounds(510, 90, 100, 20);

		p1.add(tfDo);
		JLabel lachulD = new JLabel("가는날");
		lachulD.setBounds(240, 140, 100, 20);

		JTextField tfchulD = new JTextField(10);
		tfchulD.setBounds(300, 140, 100, 20);

		JLabel laDoD = new JLabel("오는날");
		laDoD.setBounds(450, 140, 100, 20);

		tfDoD = new JTextField(10);
		tfDoD.setBounds(510, 140, 100, 20);
		tfDoD.setEnabled(false);

		JLabel laAdult = new JLabel("성인");
		laAdult.setBounds(50, 190, 100, 20);

		rbwang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rbwang.isSelected()) {
					tfDoD.setEnabled(true);
				}

			}
		});
		rbpyeon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rbpyeon.isSelected()) {
					tfDoD.setEnabled(false);
				}

			}
		});

		String[] su = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		JComboBox comboAd = new JComboBox(su);
		JComboBox combokid = new JComboBox(su);
		JComboBox combobaby = new JComboBox(su);

		comboAd.setBounds(90, 190, 100, 20);
		p1.add(comboAd);

		JLabel laKid = new JLabel("2-11");
		laKid.setBounds(200, 190, 100, 20);

		combokid.setBounds(235, 190, 100, 20);
		p1.add(combokid);

		JLabel laBaby = new JLabel("2세미만");
		laBaby.setBounds(350, 190, 100, 20);

		combobaby.setBounds(415, 190, 100, 20);
		p1.add(combobaby);

		JLabel id = new JLabel("아이디");
		id.setBounds(550, 190, 100, 20);
		p1.add(id);
		JTextField tfid = new JTextField(10);
		
		p1.add(tfid);
		tfid.setBounds(600, 190, 100, 20);
		
	

		JButton btnSearch = new JButton("조회");
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				String departure = combo1.getSelectedItem().toString();
//				String destination = combo2.getSelectedItem().toString();
//				System.out.println(departure + destination);
				searchDAO dao = new searchDAO();
				String adultcl = comboAd.getSelectedItem().toString();
				String babycl = combobaby.getSelectedItem().toString();
				String kid = combokid.getSelectedItem().toString();

				if (adultcl == "0" && Integer.parseInt(babycl) > 0) {
					showMessageDialog(null, "보호자가 동반해야함");
					return;
				}

				if (adultcl == "0" && babycl == "0" && kid == "0") {
					showMessageDialog(null, "탑승자를 선택하여 주십시오");
					return;

				}

				// 조인해서 왕복, 편도 //자리 남은수 //air 테이블 전부 다 , 이코노미 비지니스 구분 조회

				String Depname = tfChulport.getText();
				String Depdate = tfchulD.getText();
				String Arrname = tfDo.getText();

				if (Depname.equals("") && Depdate.equals("") && Arrname.equals("")) {
					showMessageDialog(null, "출발지를 입력해주세요");
					return;

				}

				else if (Depdate.equals("")) {
					showMessageDialog(null, "출발일자를 입력해주세요");
					return;

				} else if (Arrname.equals("")) {
					showMessageDialog(null, "도착지를 입력해주세요");
					return;

				}
				dao.test(Depname, Depdate, Arrname);
				
				
				
				
				
				
				model.setRowCount(0);
				DefaultTableModel model = new DefaultTableModel(dao.test(Depname, Depdate, Arrname), getColumn());

				jTable.setModel(model);

				// if(남은 자리는 0이면 경고창)

			}
		});

		btnSearch.setBounds(750, 90, 100, 80);


		rbECO.setBounds(50, 140, 80, 20);
		rbBUS.setBounds(130, 140, 80, 20);

		bg1.add(rbECO);
		bg1.add(rbBUS);

		rbpyeon.setBounds(50, 90, 80, 20);

		rbwang.setBounds(130, 90, 100, 20);

		searchDAO dao = new searchDAO();

		v = dao.getList();

		System.out.println("v=" + v);
		cols = getColumn();

		// public DefaultTableModel()
		// public DefaultTableModel(int rowCount, int columnCount)
		// public DefaultTableModel(Vector columnNames, int rowCount)
		// public DefaultTableModel(Object[] columnNames, int rowCount)
		// public DefaultTableModel(Vector data,Vector columnNames)
		// public DefaultTableModel(Object[][] data,Object[] columnNames)

		model = new DefaultTableModel(v, cols) {

			public boolean isCellEditable(int i, int c) {
				return false;
			}

		};

		// JTable()
		// JTable(int numRows, int numColumns)
		// JTable(Object[][] rowData, Object[] columnNames)
		// JTable(TableModel dm)
		// JTable(TableModel dm, TableColumnModel cm)
		// JTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
		// JTable(Vector rowData, Vector columnNames)

		// jTable = new JTable(v,cols);
		jTable = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable.getTableHeader().setReorderingAllowed(false);
		// jTable.setFillsViewportHeight(true);

	


		jTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				jTable = (JTable) e.getSource();

				if (e.getButton() == 1) {

				} // 클릭
				if (e.getClickCount() == 2) {
//					int index = jTable.getSelectedRow();
//					Vector in = (Vector)v.get(index);

					int row = jTable.getSelectedRow();
					int col = jTable.getSelectedColumn();
					Object value = jTable.getValueAt(row, col);

					searchDAO dao = new searchDAO();
					String id = tfid.getText();
					String scheduleid = value.toString();
				
						
					
					
					dao.insertA(id, scheduleid);
					showMessageDialog(null, "예약이 완료되었습니다.");
					
					new ticketinfoPage();
					
					dispose();

				} // 더블클릭
				if (e.getButton() == 3) {
				} // 오른쪽 클릭

			}
		});

		pane = new JScrollPane(jTable);
		add(pane);

		jTable.setModel(model);
		JScrollPane pane = new JScrollPane(jTable);
		p1.add(pane);
		pane.setBounds(14, 250, 900, 600);

		p1.add(title);
		p1.add(rbpyeon);
		p1.add(rbwang);
		p1.add(rbECO);
		p1.add(rbBUS);
		p1.add(laChul);

		p1.add(lachulD);
		p1.add(tfchulD);
		p1.add(laDo);


		p1.add(laDoD);
		p1.add(tfDoD);
		p1.add(btnSearch);
	
		p1.add(laAdult);
		p1.add(laKid);
		p1.add(laBaby);

		c.add(p1);

		/*****************************************************************************************/
		setSize(1000, 1000);
		setVisible(true);
		setLocationRelativeTo(null);

	}

//	public static void main(String[] args) {
//		new searchPage();
//	}

	// JTable의 컬럼
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("순번");
		col.add("출발지역");
		col.add("가는날짜");
		col.add("도착지역");
		col.add("도착시간");

		return col;
	}// getColumn

	// Jtable 내용 갱신 메서드
	public void jTableRefresh() {

		searchDAO dao = new searchDAO();
		model.setRowCount(0);
		DefaultTableModel model = new DefaultTableModel(dao.getList(), getColumn());

		jTable.setModel(model);

	}

	class MyMouseListener implements MouseListener {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			System.out.println(x);
			System.out.println("y 좌표" + y);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
