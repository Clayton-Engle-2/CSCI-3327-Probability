package main.data.types.numbers;

import java.math.BigInteger;

public class BigString extends ShareData{
	private String operation;
	private BigInteger value;
	
	public BigString(BigInteger value, String operation) {
		super(value, operation);
		this.operation = operation;
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}

}
