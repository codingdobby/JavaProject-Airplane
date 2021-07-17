package thinking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class signupDAO {
//Data Access Object
	// 공식처럼 사용,사용 많이 함

	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null; // select문 수행 후 결과를 저장하는 객체

	public void connectDB() {

		try {
			// 자기 url사용 로칼호스트 자기 콤퓨타 사용시 필요
			// jdbc:mysql://localhost:3306/BBS
			String dbURL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "cp1234";

			// jdbc 드라이브 로딩
			// 5.xx버전은 cj없음 com.mysql.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// DB연결 conn 다리 이름
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println("DB연결 OK");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("DB연결 실패");
		}

	}

	public void closeDB() {
		try {
			if (pstm != null)
				pstm.close();
			if (rs != null)
				rs.close();
			conn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

	/*****************************************************************************************/
	public void insertOne(String id, String pwd, String pwdck, String hname, String ename, String birth, String tel,
			String gender, String email, String address) {
		connectDB();

		String SQL = "insert into passenger values(?,?,?,?,?,?,?,?,?,?)";
		
		try { // 안쓰면 에러남
			pstm = conn.prepareStatement(SQL);// 공식
			pstm.setString(1, id);// 첫번째 물음표
			pstm.setString(2, pwd);// 두번째 물음표
			pstm.setString(3, pwdck);
			pstm.setString(4, hname);
			pstm.setString(5, ename);
			pstm.setString(6, birth);
			pstm.setString(7, tel);
			pstm.setString(8, gender);
			pstm.setString(9, email);
			pstm.setString(10, address);

			pstm.executeUpdate();// insert, delete, update문에 사용=>수행 결과가 없기 때문
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		closeDB();// 연결 끊기

	}

	/*****************************************************************************************/

	public boolean getid(String id) {//
		boolean result = false;
		ArrayList<signUpVO> botari = new ArrayList<signUpVO>();

		connectDB();
		String SQL = "select passid from passenger where passid=?";

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, id);

			rs = pstm.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}

			// rs테이블의 모든 레코드를 botari에 담는 과정

		} catch (SQLException e) {
			e.printStackTrace();
		} // 공식

		closeDB();
		return result;
	}

	/*****************************************************************************************/

	public void Update(String passpwd, String pwdck, String tel, String gender, String email, String address,
			String passid) {

		connectDB();
		String SQL = "update passenger set passpwd=?, pwdck=? ,tel=?,gender=?, email=?,address=? where passid=?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, passpwd);

			pstm.setString(2, pwdck);
			pstm.setString(3, tel);

			pstm.setString(4, gender);
			pstm.setString(5, email);

			pstm.setString(6, address);
			pstm.setString(7, passid);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	/****************************************/
	public signUpVO getInfo(String idchk) {
		// ArrayList<studentVO> botari = new ArrayList<studentVO>();

		connectDB();
		String SQL = "select   passpwd, pwdck, hname, ename,birth,tel,gender, email,address from passenger "
				+ "where passid=?; ";//
		signUpVO vo = null;// 결과를 담는 그릇 객체

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, idchk);
			rs = pstm.executeQuery(); // select 문 수행 결과가 rs 테이블에 다 담겨져 있음

			// rs 테이블의 모든 레코드를 botari에 담는다
			if (rs.next() == true) {
				vo = new signUpVO();// 1개의 레코드를 담을 빈그릇(vo)을 준비
				vo.setPwd(rs.getString("passpwd"));
				vo.setPwdck(rs.getString("pwdck"));
				vo.setKfirst(rs.getString("hname"));
				vo.setEfirst(rs.getString("ename"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setGender(rs.getString("gender"));

				vo.setEmail(rs.getString("email"));
				vo.setAddress(rs.getString("address"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return vo;
	}

	/*****************************************************************************************/
	//
	public boolean loginCheck(String id, String pwd) {
		boolean check = false;
		connectDB();
		String SQL = "Select * from passenger where passid= ? and passpwd =?";

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, id);
			pstm.setString(2, pwd);

			rs = pstm.executeQuery();// select문 수행시 사용 => table결과 보여줌

			// 결과출력
			if (rs.next() == true) {// rs.next() => 커서 한칸 밑으로 옮김

				check = true;

			} else {
				check = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} // 공식

		closeDB();
		return check;
	}

	/*****************************************************************************************/
	public void Delete(String passid) {

		connectDB();
		String SQL = "delete from passenger where passid=?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, passid);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

}