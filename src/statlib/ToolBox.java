package statlib;

public class ToolBox {
	
	public static double sum(double[] arr) {
	    double sum = 0;
	    for (double element : arr) {
	        sum += element;
	    }
	    return sum;
	}
	
	public static double squareRoot(double num, double precision) {
	    double approx = (num + 1) / 2;
	    double difference = Math.abs(approx * approx - num);
	    if (difference <= precision) {
	        return approx;
	    }
	    return squareRoot((approx + num / approx) / 2, precision);
	}
	
	public static double exponent(double base, int exponent) {
		double result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
	
	public static double factorial(double num) {
		if (num < 0) {
			throw new IllegalArgumentException("Number must be non-negative");
		}
		double factorial = 1;
		for (double i = 1; i <= num; i++) {
			factorial *= i;
		}
		return factorial;
	}
	
	public static void validCombo(int n, int r) {
		if (n < 0 || r < 0) {
			throw new IllegalArgumentException("Both n and r must be non-negative");
		}
		if (r > n) {
			throw new IllegalArgumentException("r must be less than or equal to n");
		}
	}
}