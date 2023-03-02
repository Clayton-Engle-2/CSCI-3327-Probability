package statlib;

import java.util.Arrays;

public class StatLibrary {
	
	/**
	 * this method finds the sum of all elements in an array then divides by the number of elements
	 * @param data: the set of numbers from which the average will be calculated
	 * @return the average of all elements in the array
	 */
	public double mean(double[] data) {
		// Validate that data array is not null or empty
		ToolBox.validateArray(data);
		// Find sum of every element in array
		double sum = ToolBox.sum(data);
		//return average, sum of elements/ number of elements
		return sum / data.length;
	}

	/**
	 * This method calculates the median of a given double array.
	 * It uses the validateArray method from ToolBox to check if the array is valid.
	 * It sorts the input array using the mergeSort method from ToolBox.
	 * If the length of the data is even, the median is calculated as the average of the two middle values.
	 * If the length of the data is odd, the median is calculated as the middle value.
	 * 
	 * @param data - the input double array
	 * @return the median value
	 */
	public double median(double[] data) {
		// Validate that data array is not null or empty
		ToolBox.validateArray(data);
		// Sort array with merge sort
		ToolBox.mergeSort(data);
		//if number of elements is even, return average of middle 2 values
		if (data.length % 2 == 0)
			return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
		else
			// If number of elements is odd return value of the middle element
			return data[data.length / 2];
	}
	
	/**
	 * This method calculates the mode (most frequently occurring value) in an array of doubles. 
	 * This method merge sorts the array, which has a worst case time complexity of O(n log n), 
	 * and the rest of the method has a time complexity of O(n).  When the list is sorted, a loop 
	 * can run one pass on the array, keeping track of the highest number of consecutive values 
	 * it finds, and the value itself, in this case, the mode
	 * 
	 * @param data: The set of data from which a mode will be found
	 * @return: the mode of the data
	 */
	public double mode(double[] data) {
		// First, merge sort the array. 
		ToolBox.mergeSort(data);
		
		// Initialize the mode and the maximum count to keep track of the mode
		double mode = 0;
		int maxCount = 0;
		
		// Initialize a count for the current value
		int currentCount = 1;

		// Loop through the array, starting from the second value (data[1])
		for (int i = 1; i < data.length; i++) {
			
			 // If the current value is equal to the previous value
			if (data[i] == data[i - 1]) {
				 // Increment the count for the current value
				currentCount++;
			} else {
				 // If the current count is greater than the maximum count, update the mode and the maximum count
				if (currentCount > maxCount) {
					mode = data[i - 1];
					maxCount = currentCount;
				}
				 // Reset the count for the current value
				currentCount = 1;
			}
		}
		// Check the count for the last value, in case it is the mode
		if (currentCount > maxCount) {
			mode = data[data.length - 1];
		}
		return mode;
	}

     
	/**
	 * This method calculates the variance of a given array of doubles.
	 * Variance is a measure of the spread of the data in the array.
	 *
	 * @param data - The input array of doubles.
	 *
	 * @return The variance of the input data.
	 */
	public double variance(double[] data) {
	    // Validate the input array
	    ToolBox.validateArray(data);
	    // Calculate the mean of the data
	    double mean = mean(data);
	    // Initialize a variable to store the temporary sum
	    double temp = 0;
	    // Loop through the data array
	    for(double a : data) {
	        // Add the square of the difference between the current element and the mean to the temporary sum
	        temp += (a-mean)*(a-mean);
	    }
	    // Return the variance by dividing the temporary sum by the number of elements in the data array
	    return temp/data.length;
	}

	/**
	 * This method calculates the standard deviation of a set of numbers stored in an array.
	 * The standard deviation is a measure of how spread out the numbers are.
	 *
	 * @param data an array of numbers to calculate the standard deviation
	 * @return the standard deviation of the given data
	 */
	public double standardDeviation(double[] data) {
		return ToolBox.squareRoot(variance(data), 0.0001);
	}
     
	/**
	 * This method takes two arrays and returns 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public double[] intersection(double[] arr1, double[] arr2) {

		// First, both arrays are sorted.
		arr1 = ToolBox.mergeSort(arr1);
		arr2 = ToolBox.mergeSort(arr2);

		// Initialize pointers for both arrays
		int i = 0;
		int j = 0;

		// Initialize an index for the result array
		int index = 0;

		// Create a result array with the length of the minimum length of the two input arrays
		double[] result = new double[Math.min(arr1.length, arr2.length)];

		// Keep looping while both pointers are within their respective arrays
		while (i < arr1.length && j < arr2.length) {
		  
			// If the elements at the pointers are equal, add the value to the result array
			if (arr1[i] == arr2[j]) {
		    
				// Check if the current value is not equal to the previous value in the result array
				if (index == 0 || result[index - 1] != arr1[i]) {
					result[index++] = arr1[i];
				}
				i++;
				j++;
		    
				// If the element at the first pointer is less than the second pointer, increment the first pointer
			} else if (arr1[i] < arr2[j]) {
				i++;
		    
			// If the element at the second pointer is less than the first pointer, increment the second pointer
			} else {
				j++;
			}
		}

		// Return a copy of the result array with the correct length (up to the index)
		return Arrays.copyOf(result, index);
	}

	/**
	 * This method returns the union of two arrays, arr1 and arr2, by combining all elements
	 * from both arrays and removing duplicates.
	 *
	 * @param arr1 the first array
	 * @param arr2 the second array
	 * @return an array containing all elements from both input arrays with duplicates removed
	 */
	public double[] union(double[] arr1, double[] arr2) {
		
		// Sorting the arrays
		arr1 = ToolBox.mergeSort(arr1);
		arr2 = ToolBox.mergeSort(arr2);
		
		//index will keep track of where to put new elements in result array
		int index = 0;
		
		// Creating a new result array with length equal to sum of lengths of arr1 and arr2
		double[] result = new double[arr1.length + arr2.length];
		
		// Looping through arr1 to add unique elements to the result array
		for(int i = 0; i < arr1.length - 1; i++) {
			// Adding elements to result array only if the current element is not equal to its next element
			if(arr1[i] != arr1[i + 1]) {
				result[index] = arr1[i];
				index += 1;
			}
		}
		
		// Adding last element of arr1 to result array if it's not equal to the second last element
		//this is because the for loop does not check the last element
		if(arr1[arr1.length - 1] != arr1[arr1.length - 2] ) {
			result[index] = arr1[arr1.length - 1];
	        index +=1;
		}
		
		// Looping through arr2 to add unique elements to the result array
		for(int i = 0; i < arr2.length - 1; i++) {
			// Adding elements to result array only if it's not present in arr1 and not equal to its next element
			if (Arrays.binarySearch(arr1, arr2[i]) < 0 && arr2[i] != arr2[i + 1]) {
	            result[index] = arr2[i];
	            index +=1;
	        }
		}

		// Adding last element of arr2 to result array if it's not equal to the second last element
		// again checking the last element missed by for loop
		if(arr2[arr2.length - 1] != arr2[arr1.length - 2] ) {
			result[index] = arr2[arr2.length - 1];
	        index +=1;
		}
		
		// Returning a copy of result array with size equal to the number of unique elements added
	    return Arrays.copyOf(result, index);
	}

	/**
	 * This method returns the complement of two arrays. 
	 * The complement of two arrays is the set of elements that are in arr1 but not in arr2.
	 * 
	 * @param arr1 - The first array to compare.
	 * @param arr2 - The second array to compare.
	 * 
	 * @return The complement of the two arrays.
	 */
	public double[] complement(double[] arr1, double[] arr2) {
		
	    // Create a new array with the same length as arr1 to store the result
	    double[] result = new double[arr1.length];
	    // Keep track of the current index in the result array
	    int index = 0;
	    // Sort the second array (arr2)
	    Arrays.sort(arr2);

	    // Loop through arr1
	    for (int i = 0; i < arr1.length; i++) {
	    	
	        // Use binary search to see if the current element from arr1 is in arr2
	        if (Arrays.binarySearch(arr2, arr1[i]) < 0) {
	        	
	            // If it's not in arr2, add it to the result array
	            result[index++] = arr1[i];
	        }
	    }
	    // Return a copy of the result array with the length equal to the number of elements that were added to the result
	    return Arrays.copyOf(result, index);
	}

	 /**
	  The multinomialCoefficient method takes two arguments: n and an array of k values, 
	  where n is the total number of objects and k is the number of objects in each category. 
	  The method first calculates the factorial of n and the product of the factorials of 
	  the k values, and then returns the ratio of these values as the result.
	**/
	 
	 public static long multinomialCoefficient(int n, int[] k) {
		    long numerator = ToolBox.factorial(n);
		    long denominator = 1;
		    for (int i = 0; i < k.length; i++) {
		        denominator *= ToolBox.factorial(k[i]);
		    }
		    return numerator / denominator;
		}

	public BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
	
	public BigInteger combination(int n, int k) {
	    BigInteger numerator = factorial(n);
	    BigInteger denominator = factorial(k).multiply(factorial(n - k));
	    return numerator.divide(denominator);
	}

	public BigInteger permutation(int n, int k) {
	    BigInteger numerator = factorial(n);
	    BigInteger denominator = factorial(n - k);
	    return numerator.divide(denominator);
	}
	
/**
 * The binomialDistribution method takes three parameters: n is the number of trials, k is the 
 * number of successes, and p is the probability of success for each trial. The method first 
 * calculates q = 1 - p, which is the probability of failure for each trial. It then calculates 
 * the probability of getting exactly k successes in n trials using the formula 
 * P(k) = C(n, k) * p^k * q^(n-k), where C(n, k) is the number of combinations of k successes in 
 * n trials.

The method uses the combination method provided to calculate C(n, k), and it uses BigDecimal to 
perform the calculations with high precision. The numerator of the result is C(n, k) * p^k * q^(n-k), 
and the denominator is 2^n. The result is returned as a BigDecimal.
 * @param n
 * @param k
 * @param p
 * @return
 */
	public BigDecimal binomialDistribution(int n, int k, double p) {
	    BigDecimal q = BigDecimal.valueOf(1 - p);
	    BigInteger numerator = combination(n, k);
	    BigDecimal probability = BigDecimal.valueOf(p).pow(k).multiply(q.pow(n - k));
	    return new BigDecimal(numerator).multiply(probability, MathContext.DECIMAL128);
	}
	
/**
 * This method works by adding up the probabilities of getting k, k + 1, k + 2, ..., n 
 * successes, which is the same as the probability of getting at least k successes. We 
 * don't need to calculate the probability of getting at most k successes, because we 
 * can use the complement rule: the probability of getting at least k successes is 1 
 * minus the probability of getting fewer than k successes.
 * @param n
 * @param k
 * @param p
 * @return
 */
	
	public BigDecimal binomialDistributionAtLeast(int n, int k, double p) {
	    BigDecimal probability = BigDecimal.ZERO;
	    for (int i = k; i <= n; i++) {
	        probability = probability.add(binomialDistribution(n, i, p));
	    }
	    return probability;
	}

	/**
	 * This method works by adding up the probabilities of getting 0, 1, 2, ..., k successes, which is 
	 * the same as the probability of getting at most k successes. We don't need to calculate the 
	 * probability of getting more than k successes, because we can use the complement rule: the probability 
	 * of getting at most k successes is 1 minus the probability of getting more than k successes.
	 * @param n
	 * @param k
	 * @param p
	 * @return
	 */
	public BigDecimal binomialDistributionAtMost(int n, int k, double p) {
	    BigDecimal probability = BigDecimal.ZERO;
	    for (int i = 0; i <= k; i++) {
	        probability = probability.add(binomialDistribution(n, i, p));
	    }
	    return probability;
	}
	
	public double binomialVariance(int n, double p) {
	    return n * p * (1 - p);
	}
	
	public double geometricExpectedValue(double p) {
	    return 1 / p;
	}
	public double geometricProbOnTrial(double p, int k) {
	    return Math.pow(1 - p, k - 1) * p;
	}

		
}

