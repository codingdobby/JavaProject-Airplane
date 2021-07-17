package thinking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;

public class FlightDAO {
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

	/****************************************************************************************************/
	public Vector getSchedule() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select * from schedule";

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String scheduleid = rs.getString("scheduleid");
				String flightnum = rs.getString("flightnumber");
				String depdate = rs.getString("depdate");
				String deptime = rs.getString("deptime");
				String arrdate = rs.getString("arrdate");
				String arrtime = rs.getString("arrtime");
				String type_fk = rs.getString("airtypecode_fk");
				String deport_fk = rs.getString("depport_Code_fk");
				String arr_fk = rs.getString("arrport_Code_fk");

				Vector row = new Vector();

				row.add(scheduleid);
				row.add(flightnum);
				row.add(depdate);
				row.add(deptime);

				row.add(arrdate);
				row.add(arrtime);

				row.add(type_fk);
				row.add(deport_fk);

				row.add(arr_fk);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()

	/****************************************************************************************************/
	public Vector getAirType() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select * from airtype";

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String AirCode = rs.getString("airtypecode");
				String Airname = rs.getString("airname");

				Vector row = new Vector();

				row.add(AirCode);
				row.add(Airname);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()
	
	
	
	public Vector getBook() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select * from totalbook";

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				int totBookid = rs.getInt("totBookid");
				String bookid = rs.getString("bookid_fk");
				int scheduleid = rs.getInt("scheduleid_fk");
				Timestamp bookdate = rs.getTimestamp("bookdate");
				int seat = rs.getInt("seat");
				

				Vector row = new Vector();

				row.add(totBookid);
				row.add(bookid);
				row.add(scheduleid);
				row.add(bookdate);
				row.add(seat);
				

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()
	

	/****************************************************************************************************/
	public void insertAirtype(String name) {

		connectDB();
		String SQL = "insert into airtype(airname) values(?)";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, name);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	/****************************************************************************************************/

	public void Update(String airname, String code) {

		connectDB();
		String SQL = "update airtype set airname=? where airtypecode =?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, airname);

			pstm.setString(2, code);
			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	
	
	
	
	public void Update1(int schcode,String id ) {

		connectDB();
		String SQL = "update totalbook set scheduleid_fk=? where bookid_fk =?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setInt(1, schcode);

			pstm.setString(2,id);
			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	/****************************************************************************************************/
	public void Delete(String code) {

		connectDB();
		String SQL = "delete from airtype where airtypecode=?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, code);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	/****************************************************************************************************/
	public void insertSchedule(String FlightName, String depdate, String depTime, String Arrdate, String ArrTime,
			int airtype_fk, int Dep_fk, int Arr_fk) {

		connectDB();
		String SQL = "insert into schedule(flightnumber, depdate, deptime, arrdate, arrtime, "
				+ "airtypecode_fk , depport_Code_fk, arrport_Code_fk ) values(?,?,?,?,?,?,?,?);";
		try {
			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, FlightName);

			pstm.setString(2, depdate);
			pstm.setString(3, depTime);

			pstm.setString(4, Arrdate);
			pstm.setString(5, ArrTime);

			pstm.setInt(6, airtype_fk);
			pstm.setInt(7, Dep_fk);

			pstm.setInt(8, Arr_fk);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} finally {

			closeDB();
		}

	}

	/****************************************************************************************************/

	public void Updatesch(String depdate, String deptime, String arrdate, String arrtime, String scheduleid) {

		connectDB();
		String SQL = "update schedule set depdate=?, deptime=?, arrdate=?, arrtime=? where scheduleid =?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, depdate);
			pstm.setString(2, deptime);
			pstm.setString(3, arrdate);
			pstm.setString(4, arrtime);
			pstm.setString(5, scheduleid);

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}

	/****************************************************************************************************/
	public void Deletesch(String code) {

		connectDB();
		String SQL = "delete from schedule where scheduleid=?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, code);

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
