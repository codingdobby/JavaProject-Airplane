package thinking;

//등록 기능 넣고 곰보박스 만들거면 만들기
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.JOptionPane.*;

import thinking.FirstMain.MyMouseListener;

public class SignUp extends JFrame {
	signupDAO sdao = new signupDAO();

	public SignUp() {
		setTitle("회원 가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.addMouseListener(new MyMouseListener());
		/*******************************************************************************************************/
		// 컴포터넌트 선언
		JPanel p1 = new JPanel();
		p1.setLayout(null);

		JLabel title = new JLabel("<회원 관리 창>");
		title.setBounds(158, 0, 180, 50);
		title.setFont(new Font("Serif", Font.BOLD, 25));
		JLabel laid = new JLabel("아이디");
		JLabel lapwd = new JLabel("비밀번호");
		JLabel lakfirst = new JLabel("한글 성");
		JLabel laklast = new JLabel("한글 이름");
		JLabel laefirst = new JLabel("영문 성");
		JLabel laelast = new JLabel("영문 이름");
		JLabel labirth = new JLabel("생년월일");
		JLabel latel = new JLabel("전화번호");
		JLabel lagender = new JLabel("성별");
		JLabel laemail = new JLabel("이메일");

		JLabel laaddress = new JLabel("주소");
		JLabel lapwdck = new JLabel("비번 확인");
		JButton btnReg = new JButton("등록");
		JButton btnCancel = new JButton("취소");
		JButton btnClose = new JButton("종료");
		JButton btnidck = new JButton("아이디 중복 확인");

		JTextField tfid = new JTextField(10);
		JTextField tfpwd = new JTextField(10);
		JTextField tfpwdck = new JTextField(10);
		JTextField tfkfirst = new JTextField(10);
		JTextField tfklast = new JTextField(10);
		JTextField tfefirst = new JTextField(10);
		JTextField tfelast = new JTextField(10);
		JTextField tfbirth = new JTextField(10);
		JTextField tftel = new JTextField(10);

		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbmale = new JRadioButton("남자");
		JRadioButton rbfemale = new JRadioButton("여자");
		bg.add(rbmale);
		bg.add(rbfemale);

		JTextField tfemail = new JTextField(10);

		JTextField tfaddress = new JTextField(20);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tfid.setText(" ");
				tfpwd.setText(" ");
				tfpwdck.setText(" ");
				tfkfirst.setText(" ");
				tfklast.setText(" ");
				tfefirst.setText(" ");
				tfelast.setText(" ");
				tfbirth.setText(" ");
				tftel.setText(" ");

				tfemail.setText(" ");

				tfaddress.setText("");

			}
		});

		laid.setBounds(48, 80, 100, 20);
		tfid.setBounds(120, 80, 100, 20);
		btnidck.setBounds(250, 80, 180, 20);

		btnidck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfid.getText();
				boolean result = false;
				if (id == null || id.equals("")) { // 텍스트필드가 빈칸일때
					showMessageDialog(null, "아이디를 입력하세요!");
				} else {
					signupDAO dao = new signupDAO();
					result = dao.getid(id);

					if (result == true) { // 결과값이 이미 있을 때
						showMessageDialog(null, "중복된 아이디입니다.");

					} else { // 결과값이 없을 때
						showMessageDialog(null, "사용가능한 아이디입니다.");
					}
				}
			}
		});

//		JButton df = new JButton("test");
//		df.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String hname = tfkfirst.getText()+tfklast.getText();
//				System.out.println(hname);
//			}
//		});
//		p1.add(df);
//		df.setBounds(100, 100, 50, 50);
		btnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfpwd.getText().equals(tfpwdck.getText())) {
					//비밀번호와 비밀번호 확인 값이 일치하지 않을때 메세지뜸
					showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					return;
				}
				String id = tfid.getText();
				String pwd = tfpwd.getText();
				String pwdck = tfpwdck.getText();
				String hname = tfkfirst.getText() + tfklast.getText();
				String ename = tfefirst.getText() + tfelast.getText();

				String birth = tfbirth.getText();
				String tel = tftel.getText();

				String gender;

				if (rbfemale.isSelected()) { //여자 라디오버튼이 선택 되어있으면 gender가 여자
					gender = rbfemale.getText();
				} else {
					gender = rbmale.getText(); //남자 라디오버튼이 선택 되어있으면 gender가 남자
				}
				String email = tfemail.getText();
				String address = tfaddress.getText();
				sdao.insertOne(id, pwd, pwdck, hname, ename, birth, tel, gender, email, address);
			}
		});
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		lapwd.setBounds(48, 120, 100, 20);
		tfpwd.setBounds(120, 120, 100, 20);

		lapwdck.setBounds(250, 120, 100, 20);
		tfpwdck.setBounds(330, 120, 100, 20);

		lakfirst.setBounds(48, 160, 100, 20);
		tfkfirst.setBounds(120, 160, 100, 20);

		laklast.setBounds(250, 160, 100, 20);
		tfklast.setBounds(330, 160, 100, 20);

		laefirst.setBounds(48, 200, 100, 20);
		tfefirst.setBounds(120, 200, 100, 20);

		laelast.setBounds(250, 200, 100, 20);
		tfelast.setBounds(330, 200, 100, 20);

		labirth.setBounds(48, 240, 100, 20);
		tfbirth.setBounds(120, 240, 100, 20);

		latel.setBounds(48, 280, 100, 20);
		tftel.setBounds(120, 280, 100, 20);

		lagender.setBounds(48, 320, 100, 20);
		rbmale.setBounds(120, 320, 60, 20);
		rbfemale.setBounds(210, 320, 60, 20);

		laemail.setBounds(48, 360, 100, 20);
		tfemail.setBounds(120, 360, 150, 20);

		laaddress.setBounds(48, 400, 100, 20);
		tfaddress.setBounds(120, 400, 250, 20);

		btnReg.setBounds(48, 480, 120, 50);
		btnCancel.setBounds(200, 480, 120, 50);
		btnClose.setBounds(350, 480, 120, 50);

		p1.add(tfid);
		p1.add(tfpwd);
		p1.add(title);
		p1.add(tfkfirst);
		p1.add(tfklast);
		p1.add(tfefirst);
		p1.add(tfelast);
		p1.add(tfbirth);
		p1.add(tftel);
		p1.add(rbmale);
		p1.add(rbfemale);
		p1.add(tfemail);

		p1.add(laaddress);

		p1.add(laid);
		p1.add(lapwd);
		p1.add(lakfirst);
		p1.add(laklast);
		p1.add(laefirst);
		p1.add(laelast);
		p1.add(labirth);
		p1.add(latel);
		p1.add(lagender);
		p1.add(laemail);

		p1.add(btnidck);
		p1.add(lapwdck);
		p1.add(tfpwdck);
		p1.add(tfaddress);
		p1.add(btnReg);
		p1.add(btnCancel);
		p1.add(btnClose);
		c.add(p1);

		/*******************************************************************************************************/
		setSize(500, 700);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		new SignUp();
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
