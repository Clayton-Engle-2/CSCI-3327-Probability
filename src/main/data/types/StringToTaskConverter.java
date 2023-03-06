package main.data.types;

import control.Pipe;
import control.tasks.client.TaskClient;
import control.tasks.tasksuper.Task;
import main.data.structures.LinkedStack;

public class StringToTaskConverter {
	
	private TaskClient factory;
	
	public StringToTaskConverter() {
		factory = new TaskClient();
	}
	
	/**
	 * These methods take a variety of data types and a reference to the main thread
	 * and uses the TaskClient class to create a new Task through the factory.
	 * @param opperation
	 * @param array
	 * @param pipe
	 * @return
	 */
	public Task createTask(String opperation, double[] array, Pipe<ShareData> pipe) {
		return factory.newTask(opperation, array, pipe);
	}
	public Task createTask(String opperation, double[] array1, double[] array2, Pipe<ShareData> pipe) {
		return factory.newTask(opperation, array1, array2, pipe);
	}
	public Task createTask(String opperation, double number1, double number2, Pipe<ShareData> pipe) {
		return factory.newTask(opperation, number1, number2, pipe);
	}
	public Task createTask(String opperation, double number1, Pipe<ShareData> pipe) {
		return factory.newTask(opperation, number1, pipe);
	}
	
	/**
	 * This method 
	 * @param input
	 * @param pipe
	 * @return
	 */
	
	public Task ToTask(DisplayData input, Pipe<ShareData> pipe) {
		Task task = null;
		if(input instanceof DisplayTwoArrays) {
			task = convert((DisplayTwoArrays) input, pipe);
		}
		if(input instanceof DisplayTwoDoubles) {
			task = convert((DisplayTwoDoubles) input, pipe);
		}
		if(input instanceof DisplaySingleArray) {
			task = convert((DisplaySingleArray) input, pipe);
		}
		if(input instanceof DisplaySingleDouble) {
			task = convert((DisplaySingleDouble) input, pipe);
		}
		return task;
	}
	
	public Task convert(DisplayTwoArrays input, Pipe<ShareData> pipe) {
		Task task = null;
		task = createTask(input.getOpperation(), stringToDoubleArray(input.getArray1()), stringToDoubleArray(input.getArray2()), pipe );
		return task;
	}
	
	public Task convert(DisplaySingleArray input, Pipe<ShareData> pipe) {
		Task task = null;
		task = createTask(input.getOpperation(), stringToDoubleArray(input.getArray()), pipe );
		return task;
	}
	
	public Task convert(DisplayTwoDoubles input, Pipe<ShareData> pipe) {
		Task task = null;
		task = createTask(input.getOpperation(), stringToDouble(input.getDouble1()), stringToDouble(input.getDouble1()), pipe );
		return task;
	}
	public Task convert(DisplaySingleDouble input, Pipe<ShareData> pipe) {
		Task task = null;
		task = createTask(input.getOpperation(), stringToDouble(input.getDub()), pipe );
		return task;
	}
	
	
	public double stringToDouble(String input) {
	    LinkedStack doubles = new LinkedStack();
	    int start = 0;

	    // Iterate over the input string, looking for ", " occurrences
	    for (int i = 0; i < input.length(); i++) {
	        if (input.charAt(i) == ',' && input.charAt(i + 1) == ' ') {
	            // If we find a ", ", extract the substring between the start index and the current index
	        	
	        	int length = i - start;
	        	char[] subChars = new char[length];
	        	for (int j = 0; j < length; j++) {
	        		subChars[j] = input.charAt( start + j);
	        	} 
	        	doubles.push(parseDouble(subChars));
	            start = i + 2; // Move the start index to the next character after the ", "
	            i++; // Skip the " " character
	        }
	    }

	    // Add the last substring, which may not have a ", " after it
	   
    	char[] subChars = new char[input.length() - start];
    	for (int i = start; i < input.length(); i++) {
            subChars[i - start] = input.charAt(i);
        }    
    	doubles.push(parseDouble(subChars));

    	 double[] number = doubles.popAll();
	    // Convert the List to an array and return it
	    return number[0];
	}
	
	public double[] stringToDoubleArray(String input) {
	    LinkedStack doubles = new LinkedStack();
	    int start = 0;

	    // Iterate over the input string, looking for ", " occurrences
	    for (int i = 0; i < input.length(); i++) {
	        if (input.charAt(i) == ',' && input.charAt(i + 1) == ' ') {
	            // If we find a ", ", extract the substring between the start index and the current index
	        	
	        	int length = i - start;
	        	char[] subChars = new char[length];
	        	for (int j = 0; j < length; j++) {
	        		subChars[j] = input.charAt( start + j);
	        	} 
	        	doubles.push(parseDouble(subChars));
	            start = i + 2; // Move the start index to the next character after the ", "
	            i++; // Skip the " " character
	        }
	    }

	    // Add the last substring, which may not have a ", " after it
	   
    	char[] subChars = new char[input.length() - start];
    	for (int i = start; i < input.length(); i++) {
            subChars[i - start] = input.charAt(i);
        }    
    	doubles.push(parseDouble(subChars));

	    // Convert the List to an array and return it
	    return doubles.popAll();
	}
	
	public double parseDouble(char[] input) {
        double result = 0.0;
        int sign = 1;
        int decimalIndex = -1;
        boolean hasDecimal = false;
        int digitsAfterDecimal = 0;

        // Handle leading sign character, if present
        if (input[0] == '-') 
            sign = -1;

        // Iterate over the input string, handling each character
        for (int i = 0; i < input.length; i++) {
            char c = input[i];

            if (c >= '0' && c <= '9') {
                // Handle digits
                if (hasDecimal) {
                    // We've already seen the decimal point, so this is a digit after the decimal
                    result = result + (c - '0') * Math.pow(10, -digitsAfterDecimal);
                    digitsAfterDecimal++;
                } else {
                    // This is a digit before the decimal
                    result = result * 10 + (c - '0');
                }
            } else if (c == '.') {
                // Handle decimal point
                if (hasDecimal) {
                    // We've already seen a decimal point, so this is an error
                    throw new NumberFormatException("Invalid input: " + input);
                } else {
                    // This is the first decimal point we've seen
                    hasDecimal = true;
                    decimalIndex = i;
                }
            } else {
                // Handle other characters (error)
                throw new NumberFormatException("Invalid input: " + input[i]);
            }
        }

        // Apply sign and return result
        result = sign * result;

        return result;
    }

}
