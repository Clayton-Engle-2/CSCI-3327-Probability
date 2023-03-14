package main.data.types.display;

public class DisplayTwoDoubles extends DisplayData{
	
	private String dub1;
	private String dub2;
	private String opperation;
	
	public DisplayTwoDoubles(String d1, String d2, String opperation) {
		this.setDub1(d1);
		this.setDub2(d2);
		this.setOpperation(opperation);
	}

	public String getOpperation() {
		return opperation;
	}

	public void setOpperation(String opperation) {
		this.opperation = opperation;
	}

	public String getDub1() {
		return dub1;
	}

	public void setDub1(String dub1) {
		this.dub1 = dub1;
	}

	public String getDub2() {
		return dub2;
	}

	public void setDub2(String dub2) {
		this.dub2 = dub2;
	}

}

