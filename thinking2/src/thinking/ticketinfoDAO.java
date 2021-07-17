package thinking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ticketinfoDAO {
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

	public ticketVO getStno(String idchk) {
		// ArrayList<studentVO> botari = new ArrayList<studentVO>();

		connectDB();
		String SQL = "select ename, gender, tel, email from passenger where passid=?; ";//
		ticketVO vo = null;// 결과를 담는 그릇 객체

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, idchk);
			rs = pstm.executeQuery(); // select 문 수행 결과가 rs 테이블에 다 담겨져 있음

			// rs 테이블의 모든 레코드를 botari에 담는다
			if (rs.next() == true) {
				vo = new ticketVO();// 1개의 레코드를 담을 빈그릇(vo)을 준비
				vo.setName(rs.getString("ename"));
				vo.setGender(rs.getString("gender"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return vo;
	}

	public boolean loginCheck(String id) {
		boolean check = false;
		connectDB();
		String SQL = "Select id from passenger where id= ?";

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, id);

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

	public testVO getDep(String idchk) {
		// ArrayList<studentVO> botari = new ArrayList<studentVO>();

		connectDB();
		String SQL = "select a.portname from schedule s , airport a, departure d ,totalbook t "
				+ "where s.depport_Code_fk = d.depcode " + "and d.dep_fk =a.portname "
				+ "and t.scheduleid_fk = s.scheduleid " + "and t.bookid_fk = ? ;";//
		testVO vo = null;// 결과를 담는 그릇 객체

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, idchk);
			rs = pstm.executeQuery(); // select 문 수행 결과가 rs 테이블에 다 담겨져 있음

			// rs 테이블의 모든 레코드를 botari에 담는다
			if (rs.next() == true) {
				vo = new testVO();// 1개의 레코드를 담을 빈그릇(vo)을 준비
				vo.setDepport(rs.getString("a.portname"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return vo;
	}

	public testVO getArrive(String idchk) {
		// ArrayList<studentVO> botari = new ArrayList<studentVO>();

		connectDB();
		String SQL = "select a.portname from schedule s , airport a, arrive ar ,totalbook t\r\n"
				+ "where s.arrport_Code_fk = ar.arrcode\r\n" + "and ar.arr_fk =a.portname \r\n"
				+ "and t.scheduleid_fk = s.scheduleid\r\n" + "and t.bookid_fk= ?  ;";//
		testVO vo = null;// 결과를 담는 그릇 객체

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, idchk);
			rs = pstm.executeQuery(); // select 문 수행 결과가 rs 테이블에 다 담겨져 있음

			// rs 테이블의 모든 레코드를 botari에 담는다
			if (rs.next() == true) {
				vo = new testVO();// 1개의 레코드를 담을 빈그릇(vo)을 준비
				vo.setArrivePort(rs.getString("a.portname"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return vo;
	}

	public testVO getTime(String idchk) {
		// ArrayList<studentVO> botari = new ArrayList<studentVO>();

		connectDB();
		String SQL = "select depdate from schedule s ,totalbook t\r\n" + "where t.scheduleid_fk = s.scheduleid\r\n"
				+ "and t.bookid_fk=? ;";//
		testVO vo = null;// 결과를 담는 그릇 객체

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, idchk);
			rs = pstm.executeQuery(); // select 문 수행 결과가 rs 테이블에 다 담겨져 있음

			// rs 테이블의 모든 레코드를 botari에 담는다
			if (rs.next() == true) {
				vo = new testVO();// 1개의 레코드를 담을 빈그릇(vo)을 준비
				vo.setDate(rs.getString("depdate"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return vo;
	}

	public testVO getFee(String idchk) {
		// ArrayList<studentVO> botari = new ArrayList<studentVO>();

		connectDB();
		String SQL = "select f.oneway from fares f , totalbook t , schedule s, departure d\r\n"
				+ "where  f.depcode_fk = d.depcode\r\n" + "and t.scheduleid_fk = s.scheduleid\r\n"
				+ "and s.depport_Code_fk = d.depcode\r\n" + "and  t.bookid_fk=? \r\n" + ";";//
		testVO vo = null;// 결과를 담는 그릇 객체

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, idchk);
			rs = pstm.executeQuery(); // select 문 수행 결과가 rs 테이블에 다 담겨져 있음

			// rs 테이블의 모든 레코드를 botari에 담는다
			if (rs.next() == true) {
				vo = new testVO();// 1개의 레코드를 담을 빈그릇(vo)을 준비
				vo.setFee(rs.getString("oneway"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return vo;
	}

}
