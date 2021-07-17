package thinking;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class FirstMain extends JFrame {
	JTextField tfid;
	String id = "0";

	public FirstMain() {
		setTitle("항공 예약 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		/*******************************************************************************************************/
		// 컴포터넌트 변수 선언
		JLabel top = new JLabel("항공예약시스템");

		JLabel laid = new JLabel("아이디  :  ");
		JTextField tfid = new JTextField(10);

		JLabel lapwd = new JLabel("비밀번호 : ");
		JTextField tfpwd = new JTextField(10);

		JButton btnLogin = new JButton("로그인");
		JButton btnBeCus = new JButton("회원정보");
		
		JButton btnSign = new JButton("회원가입");
		btnSign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUp();

			}
		});

		// 좌표알아내기
		c.addMouseListener(new MyMouseListener());

		//

		JPanel p1 = new JPanel();
		p1.setLayout(null);
		top.setBounds(119, 0, 180, 100);
		top.setFont(new Font("Serif", Font.BOLD, 25));
		top.setForeground(Color.RED);

		laid.setBounds(61, 100, 80, 20);
		lapwd.setBounds(61, 138, 80, 20);
		tfid.setBounds(140, 102, 150, 20);
		tfpwd.setBounds(140, 138, 150, 20);

		p1.add(top);
		p1.add(laid);
		p1.add(lapwd);
		p1.add(tfid);
		p1.add(tfpwd);

		p1.add(btnLogin);
		btnLogin.setBounds(331, 100, 100, 60);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signupDAO sdao = new signupDAO();
				ManagerDAO dao = new ManagerDAO();
				String id = tfid.getText();
				String pwd = tfpwd.getText();
				boolean check = dao.loginCheck(id, pwd);
				if (check == true) { // 관리자가 로그인 성공 후 관리 창 띄우기
					new TabPaneEx2();
					dispose(); // 현재 창만 종료

				} else { // 로그인 실패할 경우
					System.out.println("잘못입력");
				}

				boolean cuscheck = sdao.loginCheck(id, pwd);
				if (cuscheck == true) {
					// 고객이 로그인 성공 후 조회하는 searchPage 창 띄우기
					new searchPage();
					dispose();// 현재 창을 종료 순서 상관 없음 하나 없앨때 사용

				} else {// 로그인 실패
					System.out.println("잘못입력");
				}

			}
		});
		p1.add(btnBeCus);
		btnBeCus.setBounds(94, 193, 130, 40);
		btnBeCus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MemberPage();
			}
		});

		p1.add(btnSign);
		btnSign.setBounds(259, 193, 130, 40);
		c.add(p1);

		/*******************************************************************************************************/
		setSize(500, 320);
		setVisible(true);

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

	public static void main(String[] args) {
		new FirstMain();
	}

}