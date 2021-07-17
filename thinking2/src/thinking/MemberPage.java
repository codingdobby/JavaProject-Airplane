package thinking;

import static javax.swing.JOptionPane.showMessageDialog;

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

import thinking.SignUp.MyMouseListener;

public class MemberPage extends JFrame {
	signupDAO sdao = new signupDAO();

	public MemberPage() {
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
		JLabel lakfirst = new JLabel("한글이름");

		JLabel laefirst = new JLabel("영문 성");

		JLabel labirth = new JLabel("생년월일");
		JLabel latel = new JLabel("전화번호");
		JLabel lagender = new JLabel("성별");
		JLabel laemail = new JLabel("이메일");

		JLabel laaddress = new JLabel("주소");
		JLabel lapwdck = new JLabel("비번 확인");
		JButton btnUpdate = new JButton("수정");
		JButton btnCancel = new JButton("취소");
		JButton btnOut = new JButton("탈퇴하기");
		JButton btnClose = new JButton("종료");
		JButton btnidck = new JButton("아이디 입력");

		JTextField tfid = new JTextField(10);
		JTextField tfpwd = new JTextField(10);
		JTextField tfpwdck = new JTextField(10);
		JTextField tfkfirst = new JTextField(5);

		JTextField tfefirst = new JTextField(5);

		JTextField tfbirth = new JTextField(10);
		JTextField tftel = new JTextField(10);

		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbmale = new JRadioButton("남자");
		JRadioButton rbfemale = new JRadioButton("여자");
		bg.add(rbmale);
		bg.add(rbfemale);

		JTextField tfemail = new JTextField(30);

		JTextField tfaddress = new JTextField(40);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tfid.setText(" ");
				tfpwd.setText(" ");
				tfpwdck.setText(" ");
				tfkfirst.setText(" ");

				tfefirst.setText(" ");
				rbfemale.setSelected(false);
				rbmale.setSelected(false);
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
				if (id == null || id.equals("")) {
					showMessageDialog(null, "아이디를 입력하세요!");
					return;
				}
				else {
					signupDAO dao = new signupDAO();
					result = dao.getid(id);

					if (result == true) {

					} else {
						showMessageDialog(null, "존재하지 않는 아이디입니다!");
						return;
					}

				}

				signupDAO sdao = new signupDAO();

				signUpVO vo = sdao.getInfo(id);
				sdao.getInfo(id);

				tfpwd.setText(vo.getPwd());
				tfpwdck.setText(vo.getPwdck());
				tfkfirst.setText(vo.getKfirst());
				tfefirst.setText(vo.getEfirst());
				tfbirth.setText(vo.getBirth());
				tftel.setText(vo.getTel());

				if (vo.getGender().equals("여자")) {
					rbfemale.setSelected(true);

				} else if (vo.getGender().equals("남자")) {

					rbmale.setSelected(true);
				}

				tfemail.setText(vo.getEmail());

				tfaddress.setText(vo.getAddress());

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

		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FirstMain();
				dispose();
			}
		});

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String passid = tfid.getText();
				String passpwd = tfpwd.getText();
				String pwdck = tfpwdck.getText();
				String hname = tfkfirst.getText();
				String ename = tfefirst.getText();
				String birth = tfbirth.getText();
				String tel = tftel.getText();
				String gender;

				if (rbfemale.isSelected()) {
					gender = rbfemale.getText();
				} else {
					gender = rbmale.getText();
				}

				String email = tfemail.getText();
				String address = tfaddress.getText();
				sdao.Update(passpwd, pwdck, tel, gender, email, address, passid);
				showMessageDialog(null, "수정되었습니다.");

			}
		});

		btnOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String passid = tfid.getText();
				sdao.Delete(passid);
				showMessageDialog(null, "탈퇴되었습니다");

			}
		});
		JButton btnPage = new JButton("예약확인");
		btnPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ticketinfoPage();
				dispose();

			}
		});

		btnPage.setBounds(350, 0, 100, 20);
		lapwd.setBounds(48, 120, 100, 20);
		tfpwd.setBounds(120, 120, 100, 20);

		lapwdck.setBounds(250, 120, 100, 20);
		tfpwdck.setBounds(330, 120, 100, 20);

		lakfirst.setBounds(48, 160, 100, 20);
		tfkfirst.setBounds(120, 160, 100, 20);

		laefirst.setBounds(48, 200, 100, 20);
		tfefirst.setBounds(120, 200, 100, 20);

		labirth.setBounds(48, 240, 100, 20);
		tfbirth.setBounds(120, 240, 100, 20);

		latel.setBounds(48, 280, 100, 20);
		tftel.setBounds(120, 280, 100, 20);

		lagender.setBounds(48, 320, 100, 20);
		rbmale.setBounds(120, 320, 60, 20);
		rbfemale.setBounds(210, 320, 60, 20);

		laemail.setBounds(48, 360, 100, 20);
		tfemail.setBounds(120, 360, 200, 20);

		laaddress.setBounds(48, 400, 100, 20);
		tfaddress.setBounds(120, 400, 300, 20);

		btnUpdate.setBounds(20, 450, 100, 30);
		btnCancel.setBounds(130, 450, 100, 30);
		btnOut.setBounds(240, 450, 100, 30);
		btnClose.setBounds(350, 450, 100, 30);

		p1.add(tfid);
		p1.add(tfpwd);
		p1.add(title);
		p1.add(tfkfirst);

		p1.add(tfefirst);

		p1.add(tfbirth);
		p1.add(tftel);
		p1.add(rbmale);
		p1.add(rbfemale);
		p1.add(tfemail);

		p1.add(laaddress);

		p1.add(laid);
		p1.add(lapwd);
		p1.add(lakfirst);

		p1.add(laefirst);

		p1.add(labirth);
		p1.add(latel);
		p1.add(lagender);
		p1.add(laemail);

		p1.add(btnidck);
		p1.add(lapwdck);
		p1.add(tfpwdck);
		p1.add(tfaddress);

		p1.add(btnPage);
		p1.add(btnUpdate);
		p1.add(btnCancel);
		p1.add(btnOut);
		p1.add(btnClose);
		c.add(p1);

		/*******************************************************************************************************/
		setSize(500, 600);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		new MemberPage();
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
