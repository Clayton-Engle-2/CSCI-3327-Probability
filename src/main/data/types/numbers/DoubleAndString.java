package main.data.types.numbers;

public class DoubleAndString extends ShareData{
	
	private double num;
	private String operation;
	
	public DoubleAndString(double number, String meaning) {
		super(number, meaning);
		setNum(number);
		setOperation(meaning);
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
