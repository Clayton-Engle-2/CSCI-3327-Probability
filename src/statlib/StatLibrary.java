package statlib;

import java.util.Arrays;

public class StatLibrary {
	
	public double mean(double[] data) {
		double sum = 0.0;
		for (double a : data)
			sum += a;
		return sum / data.length;
	}

	public double median(double[] data) {
		Arrays.sort(data);
		if (data.length % 2 == 0)
			return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
		else
			return data[data.length / 2];
	}

	public double mode(double[] data) {
	    Arrays.sort(data);
	    double mode = 0;
	    int maxCount = 0;
	    int currentCount = 1;

	    for (int i = 1; i < data.length; i++) {
	        if (data[i] == data[i - 1]) {
	            currentCount++;
	        } else {
	            if (currentCount > maxCount) {
	                mode = data[i - 1];
	                maxCount = currentCount;
	            }
	            currentCount = 1;
	        }
	    }

	    if (currentCount > maxCount) {
	        mode = data[data.length - 1];
	    }

	    return mode;
	}

     
	public double variance(double[] data) {
		double mean = mean(data);
		double temp = 0;
		for(double a :data)
			temp += (a-mean)*(a-mean);
		return temp/data.length;
	}

	public double standardDeviation(double[] data) {
		return Math.sqrt(variance(data));
	}
     
	
	/**
	 * This method takes two arrays and returns 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public double[] intersection(double[] arr1, double[] arr2) {
	    Arrays.sort(arr1);
	    Arrays.sort(arr2);

	    int i = 0;
	    int j = 0;
	    int index = 0;
	    double[] result = new double[Math.min(arr1.length, arr2.length)];

	    while (i < arr1.length && j < arr2.length) {
	        if (arr1[i] == arr2[j]) {
	            if (index == 0 || result[index - 1] != arr1[i]) {
	                result[index++] = arr1[i];
	            }
	            i++;
	            j++;
	        } else if (arr1[i] < arr2[j]) {
	            i++;
	        } else {
	            j++;
	        }
	    }

	    return Arrays.copyOf(result, index);
	}

	 


	public double[] union(double[] arr1, double[] arr2) {
	    double[] result = new double[arr1.length + arr2.length];
	    int index = 0;

	    for (int i = 0, j = 0; i < arr1.length && j < arr2.length;) {
	        if (arr1[i] < arr2[j]) {
	            result[index++] = arr1[i++];
	        } else if (arr2[j] < arr1[i]) {
	            result[index++] = arr2[j++];
	        } else {
	            result[index++] = arr1[i++];
	            j++;
	        }
	    }

	    while (index < arr1.length + arr2.length) {
	        if (arr1.length > arr2.length) {
	            result[index++] = arr1[arr1.length - (arr1.length + arr2.length - index)];
	        } else {
	            result[index++] = arr2[arr2.length - (arr1.length + arr2.length - index)];
	        }
	    }

	    return Arrays.copyOf(result, index);
	}
	 
	 public static double[] complement(double[] arr1, double[] arr2) {
		    double[] result = new double[arr1.length];
		    int index = 0;
		    Arrays.sort(arr2);

		    for (int i = 0; i < arr1.length; i++) {
		        if (Arrays.binarySearch(arr2, arr1[i]) < 0) {
		            result[index++] = arr1[i];
		        }
		    }

		    return Arrays.copyOf(result, index);
		}
	 /**
	  The multinomialCoefficient method takes two arguments: n and an array of k values, 
	  where n is the total number of objects and k is the number of objects in each category. 
	  The method first calculates the factorial of n and the product of the factorials of 
	  the k values, and then returns the ratio of these values as the result.
	**/
	 
	 public static long multinomialCoefficient(int n, int[] k) {
		    long numerator = factorial(n);
		    long denominator = 1;
		    for (int i = 0; i < k.length; i++) {
		        denominator *= factorial(k[i]);
		    }
		    return numerator / denominator;
		}

		private static long factorial(int n) {
		    long result = 1;
		    for (int i = 2; i <= n; i++) {
		        result *= i;
		    }
		    return result;
		}


     
	 public void insertionSort(double[] arr) {
		 for (int i = 1; i < arr.length; i++) {
			 double key = arr[i];
			 int j = i - 1;
			 while (j >= 0 && arr[j] > key) {
				 arr[j + 1] = arr[j];
				 j--;
			 }
			 arr[j + 1] = key;
		 }
	 }
       	
	 public double[] mergeSort(double[] arr) {
		 if (arr.length < 2) {
			 return arr;
		 }
		 int mid = arr.length / 2;
		 double[] left = Arrays.copyOfRange(arr, 0, mid);
		 double[] right = Arrays.copyOfRange(arr, mid, arr.length);
		 return merge(mergeSort(left), mergeSort(right));
	 }
    	
    	


	 private double[] merge(double[] left, double[] right) {
		 double[] result = new double[left.length + right.length];
		 int i = 0, j = 0, k = 0;
		 while (i < left.length && j < right.length) {
			 if (left[i] <= right[j]) {
				 result[k++] = left[i++];
			 } else {
				 result[k++] = right[j++];
			 }
		 }
		 while (i < left.length) {
			 result[k++] = left[i++];
		 }
		 while (j < right.length) {
			 result[k++] = right[j++];
		 }
		 return result;
	 }
    	
    	
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



}
