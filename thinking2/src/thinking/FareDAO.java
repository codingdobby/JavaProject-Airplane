package thinking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class FareDAO {
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
	
	
	//목록 가져오기
	/****************************************************************************************************/
	public Vector getFareAll() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		connectDB();
		String SQL = "select * from fares" ;

		try {
			pstm = conn.prepareStatement(SQL);

			rs = pstm.executeQuery();

			while (rs.next()) {

				String farecode = rs.getString("farecode");
				String depcode = rs.getString("depcode_fk");
				String arrcode = rs.getString("arrcode_fk");
				String oneway = rs.getString("oneway");
			
				
			
			

				Vector row = new Vector();

				row.add(farecode);
				row.add(depcode);
				row.add(arrcode);
				row.add(oneway);
				
			
	

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return data;
	}// getMemberList()
	
	/****************************************************************************************************/
	
	
		

	/****************************************************************************************************/
	//값 넣기 
	
		public void insertFareCode (String DepCode, String Arrcode, String oneway) {

			connectDB();
			String SQL = "insert into fares(depcode_fk, arrcode_fk, oneway) values(?,?,?)";
			try {

				pstm = conn.prepareStatement(SQL);

			
				pstm.setString(1,DepCode);
				pstm.setString(2,Arrcode);
				pstm.setString(3,oneway);
			
				

			

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
		public void Update (String fee, String code){

			connectDB();
			String SQL = "update fares set oneway=? where farecode =?";
	        try{


	            pstm = conn.prepareStatement(SQL);      

	            pstm.setString(1,fee);

	            pstm.setString(2,code);
	      
	   

	       

	            pstm.executeUpdate(); 

	        }catch(SQLException se){

	            se.printStackTrace();    

	        }catch(Exception e){

	            e.printStackTrace();

	        }finally{

	        	closeDB();
	        }

	    }     
		/****************************************************************************************************/
		public void Delete (String code){

			connectDB();
			String SQL = "delete from fares where farecode=?";
	        try{


	            pstm = conn.prepareStatement(SQL);      

	    
	            pstm.setString(1,code);

	       

	            pstm.executeUpdate(); 

	        }catch(SQLException se){

	            se.printStackTrace();    

	        }catch(Exception e){

	            e.printStackTrace();

	        }finally{

	        	closeDB();
	        }

	    }     
		
	/****************************************************************************************************/

	/****************************************************************************************************/
	
	
	
	
}
