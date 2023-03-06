package main.data.types;

public class DisplayTwoDoubles extends DisplayData{
	
	private String double1;
	private String double2;
	private String opperation;
	
	public DisplayTwoDoubles(String double11, String double2, String opperation) {
		this.setDouble1(double1);
		this.setDouble2(double2);
		this.setOpperation(opperation);
	}

	public String getOpperation() {
		return opperation;
	}

	public void setOpperation(String opperation) {
		this.opperation = opperation;
	}

	public String getDouble1() {
		return double1;
	}

	public void setDouble1(String double1) {
		this.double1 = double1;
	}

	public String getDouble2() {
		return double2;
	}

	public void setDouble2(String double2) {
		this.double2 = double2;
	}

}

