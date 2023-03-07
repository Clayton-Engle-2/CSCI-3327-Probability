package model.statslibrary;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import main.data.structures.HashTable;
import main.data.structures.LinkedStack;
/*
 
 */
public class StatLibrary {
	
	/**
	 * this method finds the sum of all elements in an array then divides by 
	 * the number of elements
	 * 
	 * @param data: the set of numbers from which the average will be calculated
	 * @return the average of all elements in the array
	 * 
	 * @complexity O(n) in the worst case, where n is the length of the input array
	 * @memory O(n), constant memory, independent of array size
	 */
	public double mean(double[] data) {
		validateArray(data);
		double suM = sum(data);
		return suM / ((double) data.length);
	}

	/**
	 * This method calculates the median of a given double array by sorting the elements
	 * with the Tim Sort algorithm (combination of merge and insertion sort)
	 * If the length of the data is even, the median is calculated as the average of the two middle values.
	 * If the length of the data is odd, the median is calculated as the middle value.
	 * 
	 * @param data - the input double array
	 * @return the median value
	 * 
	 * @complexity O(n log(n)), the worst case time complexity of Tim Sort algorithm
	 * @memory O(n), memory needed increases linearly with array size
	 */
	public double median(double[] data) {
		validateArray(data);
		
		timSort(data);
		
		if (data.length % 2 == 0)
			return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
		else	
			return data[data.length / 2];
	}
	/*
	 * The mode method implements a hash table to hold all the values of the array.
	 * When the hash table adds an element, it returns the frequency of the element.
	 * The element with the highest frequency is the mode.
	 * 
	 * 
	 * 
	 * @param array the array of doubles to compute the mode of.
	 * @return the mode of the given array, or 0 if the array is empty.
	 * 
	 * @complexity: O(n), where n is the length of the array.
	 * @memory: O(n) (in the worst case, when all elements in the array are distinct).
	 */
	public double mode(double[] array) {
		
		HashTable ht = new HashTable(array.length*2);
		  
		double maxCount = 0;
		double count = 0;
		double mode = 0;
		  
		for (double value : array) {
			count  = ht.add(value, value);
		    
			if(Double.compare(count, maxCount) > 0) {
				maxCount = count;
				mode = value;
			}
		} 
		return mode;
	}
	
	/**
	 * This method calculates the variance of a given array of doubles.
	 * Variance is a measure of the spread of the data in the array.
	 * First, the arithmetic mean of the data is calculated
	 * Then an iterator (called by enhanced for loop) loops through the 
	 * array and  adds the square of the difference between the current 
	 * element and the mean to the temporary sum
	 *
	 *@complexity O(n), the loop iterates through every element of the array
	 * @param data - The input array of doubles.
	 * @return The variance of the input data.
	 */
	public double variance(double[] data) {
		validateArray(data);
	    
		double mean = mean(data);
		double temp = 0;
	    
		for(double a : data) {	       
			temp += (a-mean)*(a-mean);
		}	    
		return temp/((double)data.length - 1);
	}
	
	public double calculateVarianceParallel(double[] values, int numThreads) throws InterruptedException {
	    int n = values.length;
	    double sum = 0;
	    double[] partialSums = new double[numThreads];
	    int chunkSize = n / numThreads;
	    Thread[] threads = new Thread[numThreads];
	    for (int i = 0; i < numThreads; i++) {
	        int start = i * chunkSize;
	        int end = (i == numThreads - 1) ? n : (i + 1) * chunkSize;
	        threads[i] = new Thread(() -> {
	            double partialSum = 0;
	            for (int j = start; j < end; j++) {
	                partialSum += values[j];
	            }
	     //       partialSums[i] = partialSum;
	        });
	        threads[i].start();
	    }
	    for (int i = 0; i < numThreads; i++) {
	        threads[i].join();
	        sum += partialSums[i];
	    }
	    double mean = sum / n;
	    sum = 0;
	    for (int i = 0; i < numThreads; i++) {
	        int start = i * chunkSize;
	        int end = (i == numThreads - 1) ? n : (i + 1) * chunkSize;
	        threads[i] = new Thread(() -> {
	            double partialSum = 0;
	            for (int j = start; j < end; j++) {
	                partialSum += (values[j] - mean) * (values[j] - mean);
	            }
	           // partialSums[i] = partialSum;
	        });
	        threads[i].start();
	    }
	    for (int i = 0; i < numThreads; i++) {
	        threads[i].join();
	        sum += partialSums[i];
	    }
	    return sum / (n - 1);
	}

	/**
	 * This method calculates the standard deviation of a set of numbers stored in an array.
	 * The standard deviation is a measure of how spread out the numbers are.
	 *
	 *@complexity O(n)
	 *@memory O9n0
	 *
	 * @param data an array of numbers to calculate the standard deviation
	 * @return the standard deviation of the given data
	 */
	public double standardDeviation(double[] data) {
		return Math.sqrt(variance(data));
	}
     
	/**
	 * This method returns an array containing all elements found in both input arrays. After elements from array1 are added to the hash table
	 * elements from array2 are added. Remember the HashTable add() returns the frequency of the element in the hash table. If the frequency of
	 * the element is 1, this means the 
	 * 
	 * @param arr1 
	 * @param arr2
	 * @return
	 * @throws Exception 
	 */
	public double[] intersection(double[] array1, double[] array2) {
		HashTable setA = new HashTable((array1.length + array2.length)*2);
		HashTable setB = new HashTable((array1.length + array2.length)*2);
        LinkedStack intersect = new LinkedStack();

        for (double num : array1) {
            setA.add(num, num);
        }

        for (double num : array2) {
        	double inCurrent = setB.add(num, num);
            boolean inSetA = setA.contains(num, num);
            
            if(inSetA == true &&  Double.compare(inCurrent, 1.0) == 0) {
            	intersect.push(num);
            }
        }
        
        double[] result = intersect.popAll();      
        return result;
    }
	/**
	 * This method returns the union of two arrays, arr1 and arr2, by combining all elements
	 * from both arrays and removing duplicates.
	 *
	 * @param arr1 the first array
	 * @param arr2 the second array
	 * @return an array containing all elements from both input arrays with duplicates removed
	 */
	public double[] union(double[] array1, double[] array2) {
		HashTable alreadyIn = new HashTable((array1.length + array2.length)*2);
        LinkedStack union = new LinkedStack();
	    for (double value : array1) {
	      alreadyIn.add(value, value);
	      union.push(value);
	    }
	    for (double value : array2) {
	    	boolean exists = alreadyIn.contains(value, value);
	    	if(exists == false) {
	    		alreadyIn.add(value, value);
	    		union.push(value);
	    	}
	      
	    }

	    double[] result = union.popAll();
	    return result;
	  }

	/**
	 * This method returns the complement of two arrays. 
	 * The complement of two arrays is the set of elements that are in arr1 but not in arr2.
	 * 
	 * @param arr1 - The first array to compare.
	 * @param arr2 - The second array to compare.
	 * 
	 * @return The complement of the two arrays.
	 * @throws Exception 
	 */
	public double[] compliment(double[] a, double[] b) {
		HashTable setB = new HashTable(a.length * 2);
		LinkedStack  comp = new LinkedStack();
		
		for (double value : b) {
			setB.add(value, value);
		}

		for (double value : a) {
			if (!setB.contains(value, value)) {
				comp.push(value);
			}
		}

		double[] result = comp.popAll();
		   
		return result;
	}

	
	 
	 /**
	  * Calculates the factorial of a given integer.
	  * @param n the integer whose factorial to calculate
	  * @return the factorial of the input integer as a BigInteger
	  */
	 public BigInteger factorial(double n) {
		 BigInteger result = BigInteger.ONE;
		 if(n == 0 || n == 1) 
			 return result;
		 
		 for (int i = 2; i <= n; i++) {
			 result = result.multiply(BigInteger.valueOf(i));
		 }
		 return result;
	    }
	 
	 /**
	  * This method computes the combination of n choose k, where n and k are double values.
	  * The result is returned as a BigInteger to accommodate for large numbers that may result 
	  * from factorial calculations.
	  * 
	  * Applies  formula: nCk = n!/k!(n - k)!
	  * 
	  * @complexity O(n)
	  * @memory O(n) the factorial method is recursive, so n stack frames will be added every recursive call
	  * 
	  * @param n the number of elements in the set
	  * @param k the number of elements to be chosen from the set
	  * @return BigInteger all the possible ways a subset of k size could occur
	  * @throws ArithmeticException if n or k is negative or if k is greater than n
	 */
	 public BigInteger combination(double n, double k) {
		 BigInteger numerator = factorial(n);
		 BigInteger denominator = factorial(k).multiply(factorial(n - k));
		 return numerator.divide(denominator);
	 }
	 
	 /**
	  * This method is almost identical in implementation to the combination method, but
	  * uses formula: nPk = n!/(n - k)!  since the order of the subset of elements matters here
	  * 
	  * @param n total number of options
	  * @param k the number of elements in subset
	  * @return BigIntegerthe maximum number of ways elements of the subset can be arranged by order
	  */
	 public BigInteger permutation(double n, double k) {
		 BigInteger numerator = factorial(n);
		 BigInteger denominator = factorial(n - k);
		 return numerator.divide(denominator);
	 }
		
	/**
	 * This method first calculates q = 1 - p, which is the probability of failure for each trial. It 
	 * then calculates the probability of getting exactly k successes in n trials using the formula 
	 * P(k) = C(n, k) * p^k * q^(n-k), where C(n, k) is the number of combinations of k successes in 
	 * n trials.
	 * @param n total number of trials 
	 * @param k number of successful trials we are looking for
	 * @param p the probability of success for each independent trail
	 * @return the probability that k successes occur in n trials
	 */
	 public double binomialPmf(double p, double n, double k) {
		 BigDecimal q = BigDecimal.valueOf(1 - p);
		 BigInteger numerator = combination(n, k);
		 BigDecimal probability = BigDecimal.valueOf(p).pow((int) k).multiply(q.pow((int) (n - k)));
		 BigDecimal result = new BigDecimal(numerator).multiply(probability, MathContext.DECIMAL128);
		 return result.doubleValue();
	 }
	 /*
	  * Method overload since k can be an int
	  */
	 public double binomialPmf(double p, double n, int k) {
		 BigDecimal q = BigDecimal.valueOf(1 - p);
		 BigInteger numerator = combination(n, k);
		 BigDecimal probability = BigDecimal.valueOf(p).pow((int) k).multiply(q.pow((int) (n - k)));
		 BigDecimal result = new BigDecimal(numerator).multiply(probability, MathContext.DECIMAL128);
		 return result.doubleValue();
	 }
		
	 /**
	  * This method works by adding up the probabilities of getting k, k + 1, k + 2, ..., n 
	  * successes, which is the same as the probability of getting at least k successes. We 
	  * don't need to calculate the probability of getting at most k successes, because we 
	  * can use the complement rule: the probability of getting at least k successes is 1 
	  * minus the probability of getting fewer than k successes.
	  * 
	  * @param n the number of independent trials
	  * @param k the minimum number of successes to be observed
	  * @param p the probability of success in each trial
	  * @return double the probability of observing k or more successes in n trials
	  * @throws IllegalArgumentException if n or k is negative, or if p is outside the range [0, 1]
	  */
	 public double binomialDistributionAtLeast(double p, double n, double k) {
		 double q = 1.0 - p;
		 double probability = 0.0;
		 for (int i = 0; i < k; i++) {
			 probability += binomialPmf(n, i, p);
		 }
		 probability = 1.0 - probability;
		 return probability;
	 }
	 /**
	  * The binomialDistributionAtMost method calculates the probability of observing k or 
	  * fewer successes in n independent trials, each with a probability of success p, using 
	  * the binomial distribution.
	  * 
	  * @param n the number of independent trials
	  * @param k the maximum number of successes to be observed
	  * @param p the probability of success in each trial
	  * @return double the probability of observing k or fewer successes in n trials
	  * @throws IllegalArgumentException if n or k is negative, or if p is outside the range [0, 1]
	  */
	 public double binomialDistributionAtMost(double p, double n, double k) {
		 double probability = 0.0;
		 for (int i = 0; i <= k; i++) {
			 probability += binomialPmf(n, i, p);
		 }
		 return probability;
	 }
	 
	 /**
	  * Calculates the probability of A given B, using the formula P(A|B) = P(A ∩ B) / P(B).
	  * @param probAintersectB The probability of A intersection B.
	  * @param probB The probability of B.
	  * @return The probability of A given B.
	  */
	 public double probAgivenB(double probAintersectB, double probB) {
		 double ret = probAintersectB / probB;
		 return ret;
	 }
	 
	 /**
	  * This method calculates the conditional probability of event A given event B, using Bayes' theorem.
	  * @param probAintersectB the probability of the intersection of events A and B
	  * @param probA the prior probability of event A
	  * @param probB the prior probability of event B
	  * @return double the conditional probability of event A given event B
	  * @throws IllegalArgumentException if any of the input probabilities is outside the range [0, 1]
	  * @throws ArithmeticException if probB is equal to zero
	 */
	 public double bayesTheorem(double probA, double probB) {
	 double BgivenA = probAgivenB(probA * probB, probA);
	 double probability = (BgivenA * probA) / probB;
	 return probability;
	 }
	 
	 /**
	  * This method calculates the variance of a binomial distribution given the number of trials and the probability of success.
	  * @param n the number of trials
	  * @param p the probability of success
	  * @return double the variance of the binomial distribution
	  * @throws IllegalArgumentException if n is not a positive integer or if p is outside the range [0, 1]
	 */
	 public double binomialStdDev(double p, double n) {
		 return Math.sqrt(binomialVariance(n, p));
	 }
	 public double binomialMean(double p, double n) {
		 return n * p ;
	 }
	 public double binomialVariance(double p, double n) {
		 return n * p * (1 - p);
	 }
	 
		
	 /**
	  * This method calculates the expected value of a geometric distribution given the probability of success.
	  * @param p the probability of success
	  * @return double the expected value of the geometric distribution
	  * @throws IllegalArgumentException if p is outside the range (0, 1]
	 */
	 public double geometricExpectedValue(double p) {
		 return 1 / p;
	 }
	 /**
	  * This method calculates the probability of success on the kth trial of a geometric distribution given the probability of success.
	  * @param p the probability of success
	  * @param k the kth trial
	  * @return double the probability of success on the kth trial
	  * @throws IllegalArgumentException if p is outside the range (0, 1] or if k is not a positive integer
	 */
	 public double geometricPmf(double p, double k) {
		 return Math.pow(1 - p, k - 1) * p;
	 }
	 public double beforeTrialN(double p, double n) {
		 return Math.pow(1 - p, n);
	 }
	 public double geometricVariance(double p) {
		 return (1 - p) / p;
	 }
	 public double geometricStdDev(double p) {
		 return Math.sqrt(geometricVariance(p));
	 }

	
	 
	 
	 /*
	  * MATH METHODS FOR STATS LIBRARY
	  * 
	  * 
	  */
	 public double absoluteV(double value) {
		 return value < 0 ? -value : value;
	 }
	 public int absoluteV(int value) {
		 return value < 0 ? -value : value;
	 }
	 public double exponent(double base, int exponent) {
		 double result = 1;
		 for (int i = 0; i < exponent; i++) {
			 result *= base;
		 }
		 return result;
	 }
		
		
	 public double squareRoot(double num, double precision) {
		 double approx = (num + 1) / 2;
		 double difference = Math.abs(approx * approx - num);
		 if (difference <= precision) {
			 return approx;
		 }
		 return squareRoot((approx + num / approx) / 2, precision);
	 }
	 /**
	  * Sums all the elements of an array of doubles.
	  * 
	  * Time complexity: O(n)
	  * Memory Required O(1)
	  * 
	  * @param arr the input array of doubles
	  * @return the sum of all the elements in the array
	  */
	 public double sum(double[] arr) {
		 double sum = 0;
		 for (double element : arr) {
			 sum += element;
		 }
		 return sum;
	 }
	
	 /**
	  * Sorts an array of doubles using the Timsort algorithm.
	  * This method divides the input array into runs of minimum size MIN_RUN,
	  * and sorts each run using an insertion sort algorithm. The method then
	  * repeatedly merges adjacent runs until the entire array is sorted.
	  * 
	  * @param arr the input array of doubles to be sorted
	  * @throws NullPointerException if the input array is null
	  * @complexity O(n log n) in the worst case, where n is the length of the input array
	  * @memory O(n), where n is the length of the input array
	  */
	 
	 public void timSort(double[] arr) {
		 int MIN_RUN = 32;
		 int n = arr.length;
		 for (int i = 0; i < n; i += MIN_RUN) {
			 insertionSort(arr, i, Math.min((i + 31), (n - 1)));
		 }
		 for (int size = MIN_RUN; size < n; size = 2 * size) {
			 for (int left = 0; left < n; left += 2 * size) {
				 int mid = left + size - 1;
				 int right = Math.min((left + 2 * size - 1), (n - 1));
				 merge(arr, left, mid, right);
			 }
		 }
	 }
	 /**
	  * Sorts a portion of an array of doubles using an insertion sort algorithm.
	  * This method sorts the elements between the indices left and right, inclusive,
	  * using an insertion sort algorithm that sorts the elements in ascending order.
	  * 
	  * @param arr the input array of doubles to be sorted
	  * @param left the starting index of the portion of the array to be sorted
	  * @param right the ending index of the portion of the array to be sorted
	  */
	 private void insertionSort(double[] arr, int left, int right) {
		 for (int i = left + 1; i <= right; i++) {
			 double temp = arr[i];
			 int j = i - 1;
			 while (j >= left && arr[j] > temp) {
				 arr[j + 1] = arr[j];
				 j--;
			 }
			 arr[j + 1] = temp;
		 }
	 }
	 
	 /**
	  * Merges two adjacent runs of an array of doubles.
	  * This method merges the elements between the indices left and mid, and
	  * the elements between the indices mid+1 and right, into a single sorted run
	  * in the array.
	  * 
	  * @param arr the input array of doubles to be sorted
	  * @param left the starting index of the first run to be merged
	  * @param mid the ending index of the first run to be merged
	  * @param right the ending index of the second run to be merged
	  */
	 private void merge(double[] arr, int left, int mid, int right) {
		 int len1 = mid - left + 1, len2 = right - mid;
		 double[] leftArr = new double[len1];
		 double[] rightArr = new double[len2];
		 for (int x = 0; x < len1; x++) {
			 leftArr[x] = arr[left + x];
		 }
		 for (int x = 0; x < len2; x++) {
			 rightArr[x] = arr[mid + 1 + x];
		 }
		 int i = 0;
		 int j = 0;
		 int k = left;
		 while (i < len1 && j < len2) {
			 if (leftArr[i] <= rightArr[j]) {
				 arr[k] = leftArr[i];
				 i++;
			 } else {
				 arr[k] = rightArr[j];
				 j++;
			 }
			 k++;
		 }
		 while (i < len1) {
			 arr[k] = leftArr[i];
			 k++;
			 i++;
		 }
		 while (j < len2) {
			 arr[k] = rightArr[j];
			 k++;
			 j++;
		 }
	 }

	 public void validateArray(double[] arr) {
		 if (arr == null || arr.length == 0) {
			 throw new NullPointerException("Array is null");
		 }
	}
	 
	public void validCombo(int n, int r) {
		if (n < 0 || r < 0) {
			throw new IllegalArgumentException("Both n and r must be non-negative");
		}
		if (r > n) {
			throw new IllegalArgumentException("r must be less than or equal to n");
		}
	}
	
	public void print(double[] array) {
		
		 int rowCount = 0;
		 int printCount = 0;
			 
		 while(printCount < array.length) {
			 if(rowCount < 15) {
				 System.out.print(array[printCount] + ", ");
				 rowCount += 1;
				 printCount  += 1;
			 }
			 if(rowCount >= 15) {
				 System.out.println(array[printCount] + ", ");
				 rowCount = 0;
				 printCount  += 1;
			 }
		 }
	}


		
}
