package thinking;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import thinking.searchPage.MyMouseListener;

//티켓 요금 비행기 이름 출발시간 도착시간 등 출력
public class ticketinfoPage extends JFrame {

	public ticketinfoPage() {

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
		JPanel p1 = new JPanel();
		p1.setLayout(null);

		JLabel info = new JLabel("탑승객 정보");
		info.setBounds(50, 80, 400, 50);
		info.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel laidcheck = new JLabel("아이디");
		laidcheck.setBounds(50, 150, 100, 20);

		JTextField tfidcheck = new JTextField(10);
		tfidcheck.setBounds(170, 150, 100, 20);

		JButton btnidcheck = new JButton("아이디체크");
		btnidcheck.setBounds(290, 150, 100, 20);

		JLabel ename = new JLabel("이름");
		ename.setBounds(55, 200, 100, 20);

		JTextField tfename = new JTextField(10);
		tfename.setBounds(170, 200, 100, 20);

		ButtonGroup bg = new ButtonGroup();
		JLabel lagender = new JLabel("승객 구분");
		lagender.setBounds(55, 250, 100, 20);

		JRadioButton rbtn1 = new JRadioButton("여자");
		JRadioButton rbtn2 = new JRadioButton("남자");
		bg.add(rbtn1);
		bg.add(rbtn2);
		rbtn1.setBounds(170, 250, 100, 20);
		rbtn2.setBounds(270, 250, 100, 20);

		JLabel latel = new JLabel("전화번호");
		latel.setBounds(55, 300, 100, 20);

		JTextField tftel = new JTextField(10);
		tftel.setBounds(170, 300, 150, 20);

		JLabel laemail = new JLabel("이메일");
		laemail.setBounds(55, 350, 100, 20);

		JTextField tfemail = new JTextField(10);
		tfemail.setBounds(170, 350, 150, 20);

		/*****************************************************************************************/
		c.add(p1);
		p1.add(title);
		p1.add(lagender);
		p1.add(laidcheck);
		p1.add(tfidcheck);
		p1.add(btnidcheck);

		p1.add(info);
		p1.add(lagender);
		p1.add(rbtn1);
		p1.add(rbtn2);

		p1.add(ename);
		p1.add(tfename);
		p1.add(latel);
		p1.add(tftel);
		p1.add(laemail);
		p1.add(tfemail);
		/*****************************************************************************************/
		// 요금 기타등등 정보
		JLabel laFeeTitle = new JLabel("요금");
		p1.add(laFeeTitle);
		laFeeTitle.setFont(new Font("Serif", Font.BOLD, 20));
		laFeeTitle.setBounds(500, 80, 400, 50);

		JLabel laDepname = new JLabel("");
		laDepname.setBounds(520, 150, 150, 20);
		laDepname.setFont(new Font("Serif", Font.BOLD, 18));

		JLabel laArrow = new JLabel("▶");
		laArrow.setBounds(650, 150, 150, 20);

		JLabel laArrname = new JLabel("");

		laArrname.setBounds(700, 150, 150, 20);
		laArrname.setFont(new Font("Serif", Font.BOLD, 18));

		JLabel laArrDate = new JLabel();
		laArrDate.setText("");
		laArrDate.setBounds(520, 200, 150, 20);

		JLabel laFee = new JLabel("총 운행요금 : ");
		laFee.setBounds(520, 250, 150, 20);
		laFee.setFont(new Font("Serif", Font.BOLD, 18));
		JLabel lafee = new JLabel("");
		lafee.setBounds(700, 250, 100, 20);
		lafee.setFont(new Font("Serif", Font.BOLD, 18));
		JButton btn = new JButton("확인");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showMessageDialog(null, "첫 화면으로 전환합니다");
				new FirstMain();
				dispose();
			}
		});
		p1.add(btn);
		btn.setBounds(860, 160, 100, 30);

		p1.add(laFeeTitle);
		p1.add(laDepname);

		p1.add(laArrname);
		p1.add(laArrDate);

		p1.add(lafee);
		p1.add(laFee);
		p1.add(laArrow);

		/*****************************************************************************************/
		btnidcheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String idchk = tfidcheck.getText();
				ticketinfoDAO tdao = new ticketinfoDAO();
				tdao.getStno(idchk);

				ticketVO vo = tdao.getStno(idchk);

				tfename.setText(vo.getName());
				if (vo.getGender().equals("여자")) {
					rbtn1.setSelected(true);

				} else if (vo.getGender().equals("남자")) {

					rbtn2.setSelected(true);
				}

				tfemail.setText(vo.getEmail());
				tftel.setText(vo.getTel());

				tdao.getDep(idchk);
				testVO vo1 = tdao.getDep(idchk);
				laDepname.setText(vo1.getDepport());

				tdao.getArrive(idchk);
				vo1 = tdao.getArrive(idchk);

				laArrname.setText(vo1.getArrivePort());

				tdao.getTime(idchk);
				vo1 = tdao.getTime(idchk);

				laArrDate.setText(vo1.getDate());

				tdao.getFee(idchk);
				vo1 = tdao.getFee(idchk);

				lafee.setText(vo1.getFee());
			}
		});

		/*****************************************************************************************/

		/*****************************************************************************************/
		/*****************************************************************************************/
		/*****************************************************************************************/

		/*****************************************************************************************/
		setSize(1000, 600);
		setVisible(true);
		setLocationRelativeTo(null);

	}

//	public static void main(String[] args) {
//
//		new ticketinfoPage();
//
//	}

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