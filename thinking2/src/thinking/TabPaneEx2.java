package thinking;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TabPaneEx2 extends JFrame {

	Container c;

	public TabPaneEx2() {
		super("매니저 관리창");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();

		JTabbedPane tp = makeTab();

		c.add(tp, BorderLayout.CENTER);
		// setLocationRelativeTo(null);
		setSize(1200, 1000);
		setVisible(true);
	}

	public JTabbedPane makeTab() {

		JTabbedPane tp = new JTabbedPane(JTabbedPane.LEFT);
		tp.addTab("항공사관리", new FlightManage());
		tp.addTab("비행기 스케쥴", new MyPanel());
		tp.addTab("공항 관리", new AirLineM1());
		tp.addTab("요금관리", new FeeManage());
		tp.addTab("예약관리", new ToTalbook());

		return tp;
	}

	class FlightManage extends JPanel {
		Vector v;
		Vector cols;
		DefaultTableModel model;
		JTable jTable;
		JScrollPane pane;

		FlightDAO fldao = new FlightDAO();

		public FlightManage() {

			setLayout(null);
			JLabel title = new JLabel("<비행기 관리창>");
			title.setFont(new Font("Serif", Font.BOLD, 25));
			title.setBounds(380, 0, 250, 50);
			//////////////////////////////////////////////////////
			JLabel laFlightCode = new JLabel("비행기관리코드");
			laFlightCode.setBounds(50, 90, 100, 20);

			JTextField tfFlightCode = new JTextField(10);
			tfFlightCode.setBounds(150, 90, 100, 20);

			//////////////////////////////////////////////////////
			JLabel laflightno = new JLabel("항공사이름");
			laflightno.setBounds(300, 90, 100, 20);

			JTextField tfFlightno = new JTextField(10);
			tfFlightno.setBounds(380, 90, 100, 20);
			//////////////////////////////////////////////////////

			searchDAO dao = new searchDAO();

			v = fldao.getAirType();

			System.out.println("v=" + v);
			cols = getAirtypeCol();

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

						int row = jTable.getSelectedRow();
						int col = jTable.getSelectedColumn();
						Object value = jTable.getValueAt(row, col);

						String code = value.toString();

						fldao.Delete(code);
						model.setRowCount(0);
						DefaultTableModel model = new DefaultTableModel(fldao.getAirType(), getAirtypeCol());

						jTable.setModel(model);

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

			JButton btnInsert = new JButton("등록");
			btnInsert.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String name = tfFlightno.getText();

					fldao.insertAirtype(name);

					model.setRowCount(0);
					DefaultTableModel model = new DefaultTableModel(fldao.getAirType(), getAirtypeCol());

					jTable.setModel(model);

				}
			});

			btnInsert.setBounds(600, 90, 100, 50);
			JButton btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// 가져온값을 텍스트필드에 출력하고 수정
					FlightDAO fdao = new FlightDAO();
					String airname = tfFlightno.getText();
					String code = tfFlightCode.getText();
					fdao.Update(airname, code);

					model.setRowCount(0);
					DefaultTableModel model = new DefaultTableModel(fldao.getAirType(), getAirtypeCol());

					jTable.setModel(model);

				}
			});

			btnUpdate.setBounds(750, 90, 100, 50);

			JButton btnExit = new JButton("종료");
			btnExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();

				}
			});
			btnExit.setBounds(900, 90, 100, 50);

			add(laFlightCode);
			add(laflightno);

			add(tfFlightCode);
			add(tfFlightno);

			add(btnInsert);
			add(btnUpdate);

			add(btnExit);

		}

	}



	/************************************************************************************/
	class AirLineM1 extends JPanel {

		Vector v;
		Vector cols;
		DefaultTableModel model1;
		DefaultTableModel model2;
		DefaultTableModel model3;
		JTable jTable1;
		JTable jTable2;
		JTable jTable3;

		JScrollPane pane;

		AirLineDAO adao = new AirLineDAO();

		/************************************************************************************/
		public AirLineM1() {
			setTitle("항공사 관리창");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(new BorderLayout());

			JPanel p1 = new JPanel();
			setLayout(null);
			JLabel title = new JLabel("항공사 관리창");
			title.setFont(new Font("Serif", Font.BOLD, 25));
			title.setBounds(120, 0, 250, 50);

			JLabel laportName = new JLabel("공항이름");
			laportName.setBounds(50, 90, 100, 20);

			JTextField tfportName = new JTextField(10);
			tfportName.setBounds(150, 90, 100, 20);

			JLabel laAirName = new JLabel("공항위치");
			laAirName.setBounds(300, 90, 100, 20);

			JTextField tfAirName = new JTextField(10);

			tfAirName.setBounds(380, 90, 100, 20);

			JLabel laDepcode = new JLabel("출발공항코드");
			laDepcode.setBounds(50, 130, 100, 20);

			JTextField tfDepcode = new JTextField(10);
			tfDepcode.setBounds(150, 130, 100, 20);

			JLabel ladepname = new JLabel("출발공항이름");
			ladepname.setBounds(300, 130, 100, 20);

			JTextField tfdepname = new JTextField(10);
			tfdepname.setBounds(380, 130, 100, 20);

			JLabel laArrCode = new JLabel("도착코드");
			laArrCode.setBounds(50, 170, 100, 20);

			JTextField tfArrCode = new JTextField(10);
			tfArrCode.setBounds(150, 170, 100, 20);

			JLabel ladonum = new JLabel("도착이름");
			ladonum.setBounds(300, 170, 100, 20);

			JTextField tfdonum = new JTextField(10);
			tfdonum.setBounds(380, 170, 100, 20);

			/*************************************************************************/
			v = adao.getList1();

			System.out.println("v=" + v);
			cols = getColumn2();

			model1 = new DefaultTableModel(v, cols) {

				public boolean isCellEditable(int i, int c) {
					return false;
				}

			};

			jTable1 = new JTable(model1);
			jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable1.getTableHeader().setReorderingAllowed(false);

			jTable1.addMouseListener(new MouseListener() {

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

					jTable1 = (JTable) e.getSource();

					if (e.getButton() == 1) {

					} // 클릭
					if (e.getClickCount() == 2) {
						
					} // 더블클릭
					if (e.getButton() == 3) {
					} // 오른쪽 클릭

				}
			});

			pane = new JScrollPane(jTable1);
			add(pane);

			jTable1.setModel(model1);
			JScrollPane pane1 = new JScrollPane(jTable1);
			add(pane1);

			pane1.setBounds(14, 250, 300, 600);

			add(title);

			/*******************************************************************************/

			v = adao.getList2();

			System.out.println("v=" + v);
			cols = getColumn1();

			// public DefaultTableModel()
			// public DefaultTableModel(int rowCount, int columnCount)
			// public DefaultTableModel(Vector columnNames, int rowCount)
			// public DefaultTableModel(Object[] columnNames, int rowCount)
			// public DefaultTableModel(Vector data,Vector columnNames)
			// public DefaultTableModel(Object[][] data,Object[] columnNames)

			model2 = new DefaultTableModel(v, cols) {

				public boolean isCellEditable(int i, int c) {
					return false;
				}

			};

			jTable2 = new JTable(model2);
			jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable2.getTableHeader().setReorderingAllowed(false);

			jTable2.addMouseListener(new MouseListener() {

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

					jTable2 = (JTable) e.getSource();

					if (e.getButton() == 1) {

					} // 클릭
					if (e.getClickCount() == 2) {
				
					} // 더블클릭
					if (e.getButton() == 3) {
					} // 오른쪽 클릭

				}
			});

			pane = new JScrollPane(jTable2);
			add(pane);

			jTable2.setModel(model2);
			JScrollPane pane2 = new JScrollPane(jTable2);
			add(pane2);
			pane2.setBounds(350, 250, 300, 600);

			add(title);

			/*******************************************************************************/

			v = adao.getList();

			System.out.println("v=" + v);
			cols = getColumn();

			model3 = new DefaultTableModel(v, cols) {

				public boolean isCellEditable(int i, int c) {
					return false;
				}

			};

			jTable3 = new JTable(model3);
			jTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable3.getTableHeader().setReorderingAllowed(false);

			jTable3.addMouseListener(new MouseListener() {

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

					jTable3 = (JTable) e.getSource();

					if (e.getButton() == 1) {

					} // 클릭
					if (e.getClickCount() == 2) {

					} // 더블클릭
					if (e.getButton() == 3) {
					} // 오른쪽 클릭

				}
			});

			pane = new JScrollPane(jTable3);
			add(pane);

			jTable3.setModel(model3);
			JScrollPane pane = new JScrollPane(jTable3);
			add(pane);

			pane.setBounds(680, 250, 300, 600);

			/********************************************************************************/

			/*******************************************************************************/

			JButton btnInsert = new JButton("등록");
			btnInsert.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String Airportname = tfportName.getText();
					String portLocation = tfAirName.getText();

				

					adao.insertAirportName(Airportname, portLocation);

					model1.setRowCount(0);
					DefaultTableModel model1 = new DefaultTableModel(adao.getList1(), getColumn2());

					jTable1.setModel(model1);

				}
			});

			btnInsert.setBounds(500, 90, 100, 20);

			JButton btnInsert1 = new JButton("등록1");
			btnInsert1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String portde = tfdepname.getText();
					adao.insertDe(portde);
					model2.setRowCount(0);
					DefaultTableModel model = new DefaultTableModel(adao.getList2(), getColumn1());

					jTable2.setModel(model);

				}
			});

			btnInsert1.setBounds(500, 130, 100, 20);

			v = adao.getList();

			System.out.println("v=" + v);
			cols = getColumn();

			JButton btnInsert2 = new JButton("등록2");
			btnInsert2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String portArrive = tfdonum.getText();
					adao.insertArr(portArrive);

					model3.setRowCount(0);
					DefaultTableModel model3 = new DefaultTableModel(adao.getList(), getColumn1());

					jTable3.setModel(model3);

				}
			});

			btnInsert2.setBounds(500, 170, 100, 20);

			JButton btnExit = new JButton("종료");
			btnExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();

				}
			});
			btnExit.setBounds(750, 150, 100, 50);

			add(laportName);
			add(laAirName);
			add(laDepcode);
			add(laArrCode);
			add(ladepname);
			add(ladonum);

			add(tfportName);
			add(tfAirName);
			add(tfArrCode);
			add(tfDepcode);
			add(tfdepname);
			add(tfdonum);

			add(btnInsert);
			add(btnInsert1);
			add(btnInsert2);
			

			add(btnExit);

			// TODO Auto-generated constructor stub
		}

	}

	/***************************************************************************************
	 * 요금관리
	 ***/

	class FeeManage extends JPanel {
		Vector v;
		Vector cols;
		DefaultTableModel model;
		JTable jTable;
		JScrollPane pane;

		FareDAO fdao = new FareDAO();

		public FeeManage() {

			setLayout(null);
			JLabel title = new JLabel("요금관리");
			title.setFont(new Font("Serif", Font.BOLD, 25));
			title.setBounds(380, 0, 250, 50);

			JLabel lafareType = new JLabel("요금타입코드");
			lafareType.setBounds(50, 90, 100, 20);

			JTextField tffareType = new JTextField(10);
			tffareType.setBounds(150, 90, 100, 20);

			JLabel ladepCode = new JLabel("출발코드");
			ladepCode.setBounds(50, 130, 100, 20);

			JTextField tfdepCode = new JTextField(10);

			tfdepCode.setBounds(150, 130, 100, 20);

			JLabel laArrCode = new JLabel("도착코드");
			laArrCode.setBounds(300, 130, 100, 20);

			JTextField tfArrCode = new JTextField(10);
			tfArrCode.setBounds(380, 130, 100, 20);

			JLabel laOneWay = new JLabel("편도");
			laOneWay.setBounds(300, 90, 100, 20);

			JTextField tfOneWay = new JTextField(10);
			tfOneWay.setBounds(380, 90, 100, 20);

			v = fdao.getFareAll();

			System.out.println("v=" + v);
			cols = getFareType();

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

						int row = jTable.getSelectedRow();
						int col = jTable.getSelectedColumn();
						Object value = jTable.getValueAt(row, col);

						String code = value.toString();

						FareDAO fdao = new FareDAO();
						fdao.Delete(code);

						model.setRowCount(0);
						DefaultTableModel model = new DefaultTableModel(fdao.getFareAll(), getFareType());

						jTable.setModel(model);

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
			pane.setBounds(14, 250, 900, 600);

			add(title);

			JButton btnInsert = new JButton("등록");
			btnInsert.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String DepCode = tfdepCode.getText();
					String ArrCode = tfArrCode.getText();
					String OneWay = tfOneWay.getText();

					fdao.insertFareCode(DepCode, ArrCode, OneWay);

					model.setRowCount(0);
					DefaultTableModel model = new DefaultTableModel(fdao.getFareAll(), getFareType());

					jTable.setModel(model);

				}
			});

			btnInsert.setBounds(600, 90, 100, 50);
			JButton btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// 가져온값을 텍스트필드에 출력하고 수정
					int row = jTable.getSelectedRow();
					int col = jTable.getSelectedColumn();
					Object value = jTable.getValueAt(row, col);

					String code = value.toString();
					String fee = tfOneWay.getText();

					FareDAO fdao = new FareDAO();
					fdao.Update(fee, code);

					model.setRowCount(0);
					DefaultTableModel model = new DefaultTableModel(fdao.getFareAll(), getFareType());

					jTable.setModel(model);

				}
			});

			btnUpdate.setBounds(730, 90, 100, 50);

			JButton btnExit = new JButton("종료");
			btnExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();

				}
			});
			btnExit.setBounds(860, 90, 100, 50);

			add(lafareType);
			add(ladepCode);
			add(laArrCode);
			add(laOneWay);

			add(tffareType);
			add(tfdepCode);
			add(tfArrCode);
			add(tfOneWay);

			add(btnInsert);
			add(btnUpdate);

			add(btnExit);

		}
		class FlightManage extends JPanel {
			Vector v;
			Vector cols;
			DefaultTableModel model;
			JTable jTable;
			JScrollPane pane;

			FlightDAO fldao = new FlightDAO();

			public FlightManage() {

				setLayout(null);
				JLabel title = new JLabel("<비행기 관리창>");
				title.setFont(new Font("Serif", Font.BOLD, 25));
				title.setBounds(380, 0, 250, 50);
				//////////////////////////////////////////////////////
				JLabel laFlightCode = new JLabel("비행기관리코드");
				laFlightCode.setBounds(50, 90, 100, 20);

				JTextField tfFlightCode = new JTextField(10);
				tfFlightCode.setBounds(150, 90, 100, 20);

				//////////////////////////////////////////////////////
				JLabel laflightno = new JLabel("항공사이름");
				laflightno.setBounds(300, 90, 100, 20);

				JTextField tfFlightno = new JTextField(10);
				tfFlightno.setBounds(380, 90, 100, 20);
				//////////////////////////////////////////////////////

				searchDAO dao = new searchDAO();

				v = fldao.getAirType();

				System.out.println("v=" + v);
				cols = getAirtypeCol();

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

							int row = jTable.getSelectedRow();
							int col = jTable.getSelectedColumn();
							Object value = jTable.getValueAt(row, col);

							String code = value.toString();

							fldao.Delete(code);
							model.setRowCount(0);
							DefaultTableModel model = new DefaultTableModel(fldao.getAirType(), getAirtypeCol());

							jTable.setModel(model);

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

				JButton btnInsert = new JButton("등록");
				btnInsert.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						String name = tfFlightno.getText();

						fldao.insertAirtype(name);

						model.setRowCount(0);
						DefaultTableModel model = new DefaultTableModel(fldao.getAirType(), getAirtypeCol());

						jTable.setModel(model);

					}
				});

				btnInsert.setBounds(600, 90, 100, 50);
				JButton btnUpdate = new JButton("수정");
				btnUpdate.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						// 가져온값을 텍스트필드에 출력하고 수정
						FlightDAO fdao = new FlightDAO();
						String airname = tfFlightno.getText();
						String code = tfFlightCode.getText();
						fdao.Update(airname, code);

						model.setRowCount(0);
						DefaultTableModel model = new DefaultTableModel(fldao.getAirType(), getAirtypeCol());

						jTable.setModel(model);

					}
				});

				btnUpdate.setBounds(750, 90, 100, 50);

				JButton btnExit = new JButton("종료");
				btnExit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();

					}
				});
				btnExit.setBounds(900, 90, 100, 50);

				add(laFlightCode);
				add(laflightno);

				add(tfFlightCode);
				add(tfFlightno);

				add(btnInsert);
				add(btnUpdate);

				add(btnExit);

			}

		}


	


	}
	class ToTalbook extends JPanel {
		Vector v;
		Vector cols;
		DefaultTableModel model;
		JTable jTable;
		JScrollPane pane;

		FlightDAO fldao = new FlightDAO();

		public ToTalbook() {

			setLayout(null);
			JLabel title = new JLabel("<비행기 관리창>");
			title.setFont(new Font("Serif", Font.BOLD, 25));
			title.setBounds(380, 0, 250, 50);
			//////////////////////////////////////////////////////
			JLabel laID = new JLabel("아이디");
			laID.setBounds(50, 90, 100, 20);

			JTextField tfID = new JTextField(10);
			tfID.setBounds(150, 90, 100, 20);

			//////////////////////////////////////////////////////
			JLabel laflightno = new JLabel("스케쥴번호");
			laflightno.setBounds(300, 90, 100, 20);

			JTextField tfSchnum = new JTextField(10);
			tfSchnum .setBounds(380, 90, 100, 20);
			//////////////////////////////////////////////////////

			searchDAO dao = new searchDAO();

			v = fldao.getBook();

			System.out.println("v=" + v);
			cols = getBookCol();

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

						int row = jTable.getSelectedRow();
						int col = jTable.getSelectedColumn();
						Object value = jTable.getValueAt(row, col);

						String code = value.toString();

						fldao.Delete(code);
						model.setRowCount(0);
						DefaultTableModel model = new DefaultTableModel(fldao.getBook(),getBookCol());

						jTable.setModel(model);

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

			
			JButton btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// 가져온값을 텍스트필드에 출력하고 수정
					FlightDAO fdao = new FlightDAO();
					int code= Integer.parseInt(tfSchnum .getText());
					String id = tfID.getText();
					fdao.Update1(code, id);

					model.setRowCount(0);
					DefaultTableModel model = new DefaultTableModel(fldao.getBook(), getBookCol());

					jTable.setModel(model);

				}
			});

			btnUpdate.setBounds(750, 90, 100, 50);

			JButton btnExit = new JButton("종료");
			btnExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();

				}
			});
			btnExit.setBounds(900, 90, 100, 50);

			add(laID);
			add(laflightno);

			add(tfSchnum);
			add(tfID);

			
			add(btnUpdate);

			add(btnExit);

		}

	}

//	public static void main(String[] args) {
//
//		new TabPaneEx2();
//	}

	public Vector getColumn1() {
		Vector col = new Vector();
		col.add("항공사번호");
		col.add("출발 공항이름");
		col.add("공항위치");

		return col;
	}// getColumn

	public Vector getColumn() {
		Vector col = new Vector();
		col.add("항공사번호");
		col.add("도착 공항이름");
		col.add("공항위치");

		return col;
	}

	public Vector getColumn2() {
		Vector col = new Vector();
		col.add("공항이름");
		col.add("공항위치");

		return col;
	}

	public Vector getFareType() {
		Vector col = new Vector();
		col.add("요금타입코드");
		col.add("출발공항");
		col.add("도착공항");
		col.add("편도");

		return col;
	}// getColumn

	public Vector getAirtypeCol() {
		Vector col = new Vector();
		col.add("항공사 코드");
		col.add("항공사 이름");

		return col;
	}// getColumn
	
	public Vector getBookCol() {
		Vector col = new Vector();
		col.add("예약번호");
		col.add("고객아이디");
		col.add("스케쥴번호");
		col.add("예약한 시간");
		col.add("자리");
		

		return col;
	}// getColumn

}
