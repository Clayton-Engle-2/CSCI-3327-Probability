package main.data.structures.arrays;

public class DynamicArray {
	 private double[] data;
	    private int size;
	    private static final int DEFAULT_CAPACITY = 10;

	    public DynamicArray() {
	        data = new double[DEFAULT_CAPACITY];
	    }

	    public DynamicArray(int initialCapacity) {
	        data = new double[initialCapacity];
	    }

	    public void add(double element) {
	        ensureCapacity();
	        data[size++] = element;
	    }

	    public double get(int index) {
	        if (index >= size) {
	            throw new IndexOutOfBoundsException();
	        }
	        return data[index];
	    }

	    public void set(int index, double element) {
	        if (index >= size) {
	            throw new IndexOutOfBoundsException();
	        }
	        data[index] = element;
	    }

	    public int size() {
	        return size;
	    }

	    private void ensureCapacity() {
	        if (size == data.length) {
	            double[] newData = new double[size * 2 + 1];
	            System.arraycopy(data, 0, newData, 0, size);
	            data = newData;
	        }
	    }
	    public int binarySearch(double target) {
		    int low = 0;
		    int high = data.length - 1;
		    while (low <= high) {
		        int mid = (low + high) / 2;
		        if (data[mid] == target) {
		            return mid;
		        } else if (data[mid] < target) {
		            low = mid + 1;
		        } else {
		            high = mid - 1;
		        }
		    }
		    return -1; // target not found
		}
	}



