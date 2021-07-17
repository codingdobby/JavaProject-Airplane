package thinking;

import java.sql.Timestamp;

public class searchVO {
	private String flightno;
	private String airLine;
	private String dep;// 출발지역
	private String depT;// 출발시간
	private String Do;// 도착지역
	private String DoT;// 도착시간

	private Timestamp bookday;
	
	
	public Timestamp getBookday() {
		return bookday;
	}

	public void setBookday(Timestamp bookday) {
		this.bookday = bookday;
	}

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getDepT() {
		return depT;
	}

	public void setDepT(String depT) {
		this.depT = depT;
	}

	public String getDo() {
		return Do;
	}

	public void setDo(String do1) {
		Do = do1;
	}

	public String getDoT() {
		return DoT;
	}

	public void setDoT(String doT) {
		DoT = doT;
	}

}
