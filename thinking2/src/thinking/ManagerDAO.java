package thinking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.print.attribute.standard.Destination;

public class ManagerDAO {
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
	// 로그인 체크
	public boolean loginCheck(String id, String pwd) {
		boolean check = false;
		connectDB();
		String SQL = "select * from manager where manid=? and manpwd=?";

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, id); //manid
			pstm.setString(2, pwd);//manpwd

			rs = pstm.executeQuery();// select문 수행시 사용 => table결과 보여줌

			// 결과출력
			if (rs.next() == true) {// rs.next() => 커서 한칸 밑으로 옮김
				check = true;  //일치하는 값이 존재하면  true 

			} else {
				check = false;  //일치하는 값이 없으면  false 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} // 공식

		closeDB();
		return check;//check값 반환
	}

	/*****************************************************************************************/
	public void delete(String flightno) {

		connectDB();
		String SQL = "delete from air where flightno=? ";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, flightno);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	/*****************************************************************************************/
	public void insert(String flightno, String departure, String departT, String Des) {

		connectDB();
		String SQL = "insert into air values(?,?,?,?)";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, flightno);

			pstm.setString(2, departure);
			pstm.setString(3, departT);
			pstm.setString(4, Des);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	public void insertAirportName(String Airportname, String AirportLocation) {

		connectDB();
		String SQL = "insert into airport values(?,?)";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, Airportname);
			pstm.setString(2, AirportLocation);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	public Vector getList() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select * from air";

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String flightno = rs.getString("flightno");
				String departure = rs.getString("departure");
				String chul = rs.getString("chul");
				String destination = rs.getString("destination");

				Vector row = new Vector();

				row.add(flightno);
				row.add(departure);
				row.add(chul);
				row.add(destination);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()

	public void Update(String departure, String chul, String Des, String flightno) {

		connectDB();
		String SQL = "update air set departure=? ,chul=?,destination=?  where flightno=?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, departure);

			pstm.setString(2, chul);

			pstm.setString(3, Des);

			pstm.setString(5, flightno);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} finally {

			closeDB();
		}

	}

	public boolean getnum(String flightno) {// namecard 테이블에 있는 모든 레코드를 넘겨줌
		boolean result = false;
		ArrayList<signUpVO> botari = new ArrayList<signUpVO>();

		connectDB();
		String SQL = "select flightno from air where flightno=?";

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, flightno);

			rs = pstm.executeQuery();// select문 수행시 사용 => table결과 보여줌 수행결과가 rs테이블에 들어감
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

}