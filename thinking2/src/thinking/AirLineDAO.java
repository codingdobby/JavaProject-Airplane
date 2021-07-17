package thinking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class AirLineDAO {
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
	
	
	
	
	
	public void insertAirportName(String Airportname,String AirportLocation) {

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
	
	
	
	
//	public void delete(String arrcode) {
//
//		connectDB();
//		String SQL = "delete from arrive where arrcode=? ";
//		try {
//
//			pstm = conn.prepareStatement(SQL);
//
//			pstm.setString(1, arrcode);
//
//			pstm.executeUpdate();
//
//		} catch (SQLException se) {
//
//			se.printStackTrace();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			closeDB();
//		}
//
//	}

	/*****************************************************************************************/
	
	public void insertDe(String depname) {

		connectDB();
		String SQL = "insert into departure(dep_fk) values(?)";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1,depname);
		

		

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			closeDB();
		}

	}
	
	
	public void insertArr( String doname) {

		connectDB();
		String SQL = "insert into arrive(arr_fk) values(?)";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1,doname);
		

		

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
		String SQL = "select ar.arrcode, ar.arr_fk , po.portlocation from arrive ar ,airport po "
				+ "where ar.arr_fk=po.portname" ;

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String AirNum = rs.getString("arrcode");
				String AirName = rs.getString("arr_fk");
			
				String ReSeat = rs.getString("portlocation");
			

				Vector row = new Vector();

				row.add(AirNum);
				row.add(AirName);
			
				row.add(ReSeat);
			

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()
	
	
	//공항조회
	public Vector getList1() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select * from airport;" ;

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String AirNum = rs.getString("portname");
				String Location = rs.getString("portlocation");
			

				Vector row = new Vector();

				row.add(AirNum);
				row.add(Location);
			
				
			

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()
	
	
	
	
	//출발지
	public Vector getList2() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select de.depcode,de.dep_fk , po.portlocation from departure de ,airport po\r\n" + 
				" where de.dep_fk =po.portname ;" ;

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String AirNum = rs.getString("depcode");
				String AirName = rs.getString("dep_fk");
			
				String ReSeat = rs.getString("portlocation");
			

				Vector row = new Vector();

				row.add(AirNum);
				row.add(AirName);
			
				row.add(ReSeat);
			

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()
	
	

	public void Update( String AirName, String Kind, String ReSeat, String AirNum) {

		connectDB();
		String SQL = "update air set company=?,kind=?,seat=?  where airplaneID=?";
		try {

			pstm = conn.prepareStatement(SQL);

			pstm.setString(1, AirName);

			pstm.setString(2, Kind);
			
			pstm.setString(3,ReSeat);
			pstm.setString(4, AirNum);
			
	

			pstm.executeUpdate();

		} catch (SQLException se) {

			se.printStackTrace();

	

		} finally {

			closeDB();
		}

	}
	
	
	
	public boolean getnum(String airplaneID ) {// namecard 테이블에 있는 모든 레코드를 넘겨줌
		boolean result = false;
		ArrayList<AirLineVO> botari = new ArrayList<AirLineVO>();

		connectDB();
		String SQL = "select company from air where airplaneID=?";
		
		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, airplaneID);
			
			rs = pstm.executeQuery();// select문 수행시 사용 => table결과 보여줌 수행결과가 rs테이블에 들어감
			if(rs.next()) {result = true;}
			else {result = false;}
			
			
			// rs테이블의 모든 레코드를 botari에 담는 과정
			
			}
		 catch (SQLException e) {
			e.printStackTrace();
		} // 공식

		closeDB();
		return result;
	}
	

	
	
	
	
	
	
	
	
	
}
