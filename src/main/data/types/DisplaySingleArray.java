package main.data.types;

public class DisplaySingleArray extends DisplayData{
	
	private String array;
	private String opperation;
	
	public DisplaySingleArray(String array, String opperation) {
		this.setArray(array);
		this.setOpperation(opperation);
	}

	public String getOpperation() {
		return opperation;
	}

	public void setOpperation(String opperation) {
		this.opperation = opperation;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}

}

