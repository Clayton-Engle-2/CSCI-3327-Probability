package statlib;

import java.util.Arrays;

public class ToolBox {
	
	public static int binarySearch(double[] arr, double target) {
	    int low = 0;
	    int high = arr.length - 1;
	    while (low <= high) {
	        int mid = (low + high) / 2;
	        if (arr[mid] == target) {
	            return mid;
	        } else if (arr[mid] < target) {
	            low = mid + 1;
	        } else {
	            high = mid - 1;
	        }
	    }
	    return -1; // target not found
	}

	
	public static double exponent(double base, int exponent) {
		double result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
	
	public static long factorial(int n) {
	    long result = 1;
	    for (int i = 2; i <= n; i++) {
	        result *= i;
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
	
	public static void insertionSort(double[] arr) {
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
	
	 public static double[] mergeSort(double[] arr) {
		 if (arr.length < 2) {
			 return arr;
		 }
		 int mid = arr.length / 2;
		 double[] left = Arrays.copyOfRange(arr, 0, mid);
		 double[] right = Arrays.copyOfRange(arr, mid, arr.length);
		 return merge(mergeSort(left), mergeSort(right));
	 }
    	
	 private static double[] merge(double[] left, double[] right) {
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
    	
	
	public static double squareRoot(double num, double precision) {
	    double approx = (num + 1) / 2;
	    double difference = Math.abs(approx * approx - num);
	    if (difference <= precision) {
	        return approx;
	    }
	    return squareRoot((approx + num / approx) / 2, precision);
	}
	
	public static double sum(double[] arr) {
	    double sum = 0;
	    for (double element : arr) {
	        sum += element;
	    }
	    return sum;
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

	 public static void validateArray(double[] arr) {
		 if (arr == null || arr.length == 0) {
			 throw new NullPointerException("Array is null");
		 }
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