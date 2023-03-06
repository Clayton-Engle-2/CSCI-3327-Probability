package main.data.types;

public class DisplaySingleDouble extends DisplayData{
	
	private String dub;
	private String opperation;
	
	public DisplaySingleDouble(String dub, String opperation) {
		this.setDub(dub);
		this.setOpperation(opperation);
	}

	public String getOpperation() {
		return opperation;
	}

	public void setOpperation(String opperation) {
		this.opperation = opperation;
	}

	public String getDub() {
		return dub;
	}

	public void setDub(String dub) {
		this.dub = dub;
	}

}
