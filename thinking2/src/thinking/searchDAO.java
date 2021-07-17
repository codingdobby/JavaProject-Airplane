package thinking;

//조회기능 저장 긴응 전체 보여주기
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class searchDAO {
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

	public Vector getList() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select s.scheduleid, d.dep_fk,s.depdate,a.arr_fk, s.arrdate from schedule s , departure d , arrive a\r\n"
				+ "where s.depport_Code_fk = d.depcode\r\n" + "and s.arrport_Code_fk = a.arrcode;";

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String scheduleid = rs.getString("s.scheduleid");
				String departure = rs.getString("d.dep_fk");
				String deptime = rs.getString("s.depdate");
				String destination = rs.getString("a.arr_fk");
				String arrtime = rs.getString("s.arrdate");

				Vector row = new Vector();

				row.add(scheduleid);
				row.add(departure);
				row.add(deptime);
				row.add(destination);
				row.add(arrtime);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()

	public Vector test(String Depname, String Depdate, String Arrname) {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select s.scheduleid, d.dep_fk, s.depdate, a.arr_fk, s.arrdate from schedule s , departure d , arrive a\r\n"
				+ "where s.depport_Code_fk = d.depcode\r\n" + "and s.arrport_Code_fk = a.arrcode\r\n"
				+ "and d.dep_fk=? and s.depdate =? and  a.arr_fk=? order by s.scheduleid;";
		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, Depname);
			pstm.setString(2, Depdate);
			pstm.setString(3, Arrname);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String schedule = rs.getString("s.scheduleid");
				String departure = rs.getString("d.dep_fk");
				String deptime = rs.getString("s.depdate");
				String destination = rs.getString("a.arr_fk");
				String arrtime = rs.getString("s.arrdate");

				Vector row = new Vector();

				row.add(schedule);
				row.add(departure);
				row.add(deptime);
				row.add(destination);
				row.add(arrtime);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()

	public void insertA(String id, String scheduleid) {

		connectDB();
		String SQL = "insert into totalbook(bookid_fk, scheduleid_fk, bookdate, seat) values(?,?,now(),?)";
		try {

			int[] arr = new int[100];

			for (int i = 0; i < 100; i++) {

				arr[i] = i + 1;

			}

			searchVO vo = new searchVO();

			int seat = 0;
			for (int i = 0; i < arr.length; i++) {

				seat = arr[i];
			}

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, id);
			pstm.setString(2, scheduleid);
			pstm.setInt(3, seat);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	public Vector search(String Depname, String Depdate, String Arrname) {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select s.scheduleid from schedule s , departure d , arrive a\r\n"
				+ "where s.depport_Code_fk = d.depcode\r\n" + "and s.arrport_Code_fk = a.arrcode\r\n"
				+ "and d.dep_fk=? and s.depdate =? and  a.arr_fk='?';";

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();
			pstm.setString(1, Depname);
			pstm.setString(2, Depdate);
			pstm.setString(3, Arrname);

			while (rs.next()) {

				String departure = rs.getString("scheduleid");

				Vector row = new Vector();

				row.add(departure);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()

	public void Leg(String Departure, String depdate, String arrive) {

		connectDB();
		String SQL = "select s.scheduleid from schedule s , departure d , arrive a\r\n"
				+ "where s.depport_Code_fk = d.depcode\r\n" + "and s.arrport_Code_fk = a.arrcode\r\n"
				+ "and d.dep_fk=''and s.depdate ='2018-01-01' and  a.arr_fk='인천공항';";
		try {

			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, Departure);
			pstm.setString(2, depdate);
			pstm.setString(3, arrive);

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
