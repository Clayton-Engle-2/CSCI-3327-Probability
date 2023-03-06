package main.data.types;

public class DisplayTwoArrays extends DisplayData{
	
	private String array1;
	private String array2;
	private String opperation;
	
	public DisplayTwoArrays(String array1, String array2, String opperation) {
		this.setArray1(array1);
		this.setArray2(array2);
		this.setOpperation(opperation);
	}

	public String getArray1() {
		return array1;
	}

	public void setArray1(String array1) {
		this.array1 = array1;
	}

	public String getArray2() {
		return array2;
	}

	public void setArray2(String array2) {
		this.array2 = array2;
	}

	public String getOpperation() {
		return opperation;
	}

	public void setOpperation(String opperation) {
		this.opperation = opperation;
	}

}
