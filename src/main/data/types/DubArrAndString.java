package main.data.types;

public class DubArrAndString extends ShareData{
	private double[] arr;
	private String descriptor;
	
	
	public DubArrAndString(double[] array, String meaning) {
		super(array, meaning);
		setArray(array);
		setDescriptor(meaning);
	}

	public double[] getArray() {
		return arr;
	}

	public void setArray(double[] array) {
		this.arr = array;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

}
