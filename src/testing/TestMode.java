package testing;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import structures.HashTable;

public class TestMode {
	
	public void runTest() {
		double[] array1 = new double[20000];
		
		Random rand = new Random();
        for (int i = 0; i < 20000; i++) {
            double randDouble = Math.round((rand.nextDouble() * 4999) + 1); 
            array1[i] = randDouble;
        }
        
        MethodTimer timer = new MethodTimer();
        timer.timeTest(array1);
	
	}
	public void timeTest(double[] array) {
		long method1Time = timeMethod1(array);
        long method2Time = timeMethod2(array);
        System.out.println("Method 1 took " + method1Time + " nanoseconds to run.");
        System.out.println("Method 2 took " + method2Time + " nanoseconds to run.");
	}
	
	public long timeMethod1(double[] array) {
        long startTime = System.nanoTime();
        double answer = findMode(array);
        long endTime = System.nanoTime();
        System.out.println("Method 1 result: " + answer);
        return (endTime - startTime);
    }
    
    public long timeMethod2(double[] array) {
        long startTime = System.nanoTime();
        double answer = mode(array);
        long endTime = System.nanoTime();
        System.out.println("Method 2 result: " + answer);
        return (endTime - startTime);
    }
	
	public double findMode(double[] array) {
        Map<Double, Integer> frequencyMap = new HashMap<>();

        for (double num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        double mode = array[0];
        int maxFrequency = 0;

        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mode = entry.getKey();
            }
        }

        return mode;
    }

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
	
}
