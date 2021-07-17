package thinking;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

class MyPanel extends JPanel {
	Vector v;
	Vector cols;
	DefaultTableModel model;
	JTable jTable;
	JScrollPane pane;

	FlightDAO fldao = new FlightDAO();

	public MyPanel() {

		setLayout(null);
		JLabel title = new JLabel("<비행기 관리창>");
		title.setFont(new Font("Serif", Font.BOLD, 25));
		title.setBounds(380, 0, 250, 50);
		//////////////////////////////////////////////////////
		JLabel lascheduleID = new JLabel("스케쥴 아이디");
		lascheduleID.setBounds(50, 90, 100, 20);

		JTextField tfscheduleID = new JTextField(10);
		tfscheduleID.setBounds(150, 90, 100, 20);
		//////////////////////////////////////////////////////
		JLabel laflightno = new JLabel("비행기 번호");
		laflightno.setBounds(300, 90, 100, 20);

		JTextField tfFlightno = new JTextField(10);
		tfFlightno.setBounds(380, 90, 100, 20);
		//////////////////////////////////////////////////////

		JLabel ladep = new JLabel("출발날");
		ladep.setBounds(50, 130, 100, 20);

		JTextField tfDep = new JTextField(10);
		tfDep.setBounds(150, 130, 100, 20);

		JLabel ladepTime = new JLabel("출발시간");
		ladepTime.setBounds(300, 130, 100, 20);

		JTextField tfDepTime = new JTextField(10);

		tfDepTime.setBounds(380, 130, 100, 20);

		JLabel laDes = new JLabel("도착날");
		laDes.setBounds(50, 170, 100, 20);

		JTextField tfDes = new JTextField(10);
		tfDes.setBounds(150, 170, 100, 20);

		JLabel laDesTime = new JLabel("도착시간");
		laDesTime.setBounds(300, 170, 100, 20);

		JTextField tfDesT = new JTextField(10);
		tfDesT.setBounds(380, 170, 100, 20);

		JLabel laTypefk = new JLabel("비행기타입 외래키");
		laTypefk.setBounds(500, 90, 130, 20);

		JTextField tfTypefk = new JTextField(10);
		tfTypefk.setBounds(630, 90, 100, 20);

		JLabel laDepFk = new JLabel("출발코드 외래키");
		laDepFk.setBounds(500, 130, 100, 20);

		JTextField tfDepFk = new JTextField(10);
		tfDepFk.setBounds(630, 130, 100, 20);

		JLabel laArrFk = new JLabel("도착코드 외래키");
		laArrFk.setBounds(500, 170, 100, 20);

		JTextField tfArrFk = new JTextField(10);
		tfArrFk.setBounds(630, 170, 100, 20);

		searchDAO dao = new searchDAO();

		v = fldao.getSchedule();

		System.out.println("v=" + v);
		cols = getScheduleColum();

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
		jTable = new JTable(model);
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable.getTableHeader().setReorderingAllowed(false);

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

				} // 더블클릭
				if (e.getButton() == 3) {
				} // 오른쪽 클릭

			}
		});

		pane = new JScrollPane(jTable);
		add(pane);

		jTable.setModel(model);
		JScrollPane pane = new JScrollPane(jTable);
		add(pane);
		pane.setBounds(14, 250, 1000, 600);

		add(title);

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		System.out.println(df.format(date));

		JButton btnInsert = new JButton("등록");
		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String FlightName = tfFlightno.getText();
				String depdate = tfDep.getText();
				String depTime = tfDepTime.getText().toString();
				String Arrdate = tfDes.getText();
				String ArrTime = tfDesT.getText();
				int airtype_fk = Integer.parseInt(tfTypefk.getText());
				int Dep_fk = Integer.parseInt(tfDepFk.getText());
				int Arr_fk = Integer.parseInt(tfArrFk.getText());

				fldao.insertSchedule(FlightName, depdate, depTime, Arrdate, ArrTime, airtype_fk, Dep_fk, Arr_fk);

				model.setRowCount(0);
				DefaultTableModel model = new DefaultTableModel(fldao.getSchedule(), getScheduleColum());

				jTable.setModel(model);

			}
		});

		btnInsert.setBounds(750, 90, 100, 50);
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 가져온값을 텍스트필드에 출력하고 수정
						
				String depdate = tfDep.getText();
				String deptime = tfDepTime.getText();
				String arrdate = tfDes.getText();
				String arrtime = tfDesT.getText();
				String scheduleid = tfscheduleID.getText();
				
				
				fldao.Updatesch(depdate, deptime, arrdate, arrtime, scheduleid);
				model.setRowCount(0);
				DefaultTableModel model = new DefaultTableModel(fldao.getSchedule(), getScheduleColum());

				jTable.setModel(model);

			}
		});

		btnUpdate.setBounds(900, 90, 100, 50);

		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String code = tfscheduleID.getText();

				fldao.Deletesch(code);

				model.setRowCount(0);
				DefaultTableModel model = new DefaultTableModel(fldao.getSchedule(), getScheduleColum());

				jTable.setModel(model);

			}
		});

		btnDelete.setBounds(750, 150, 100, 50);

		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		btnExit.setBounds(900, 150, 100, 50);

		add(lascheduleID);
		add(laflightno);
		add(ladep);
		add(laDesTime);
		add(laDes);
		add(ladepTime);

		add(tfscheduleID);
		add(tfFlightno);
		add(tfDep);
		add(tfDepTime);
		add(tfDepTime);
		add(tfDes);
		add(tfDesT);

		add(laArrFk);
		add(laDepFk);
		add(laTypefk);

		add(tfArrFk);
		add(tfDepFk);
		add(tfTypefk);
		add(btnInsert);
		add(btnUpdate);
		add(btnDelete);
		add(btnExit);

	}

	public Vector getScheduleColum() {
		Vector col = new Vector();
		col.add("스케쥴아이디");
		col.add("비행기 번호");
		col.add("출발날");
		col.add("출발시간");
		col.add("도착날");
		col.add("도착시간");
		col.add("비행기타입 외래키");
		col.add("출발코드외래키");
		col.add("도착코드외래키");

		return col;
	}// getColumn

}
