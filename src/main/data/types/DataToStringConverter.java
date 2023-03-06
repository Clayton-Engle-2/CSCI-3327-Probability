package main.data.types;

public class DataToStringConverter {
	
	public DisplaySolution toDisplay(ShareData input) {
		DisplaySolution display = null;
		
		if(input instanceof BigString) {
			display = convert((BigString) input);
			
		}
		if(input instanceof DoubleAndString) {
			display = convert((DoubleAndString) input);
		}
		if(input instanceof DubArrAndString) {
			display = convert((DubArrAndString) input);
		}
		return display;
	}
	
	public DisplaySolution convert(BigString input) {
		return new DisplaySolution(input.getValue().toString(), input.getOperation()) ;
	}
	public DisplaySolution convert(DoubleAndString input) {
		return new DisplaySolution(doubleToString(input.getNum()), input.getOperation());
	}
	public DisplaySolution convert(DubArrAndString input) {
		return new DisplaySolution(doubleArrayToString(input.getArray()), input.getDescriptor());
	}
	
	public String doubleArrayToString(double[] array) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < array.length; i++) {
	        sb.append(Double.toString(array[i]));
	        if (i != array.length - 1) {
	            sb.append(", ");
	        }
	    }
	    return sb.toString();
	}
	
	public String doubleToString(double value) {
	    return Double.toString(value);
	}

}
