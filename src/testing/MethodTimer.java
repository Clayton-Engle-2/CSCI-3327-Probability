package testing;

import statlib.StatLibrary;

public class MethodTimer {
	
	public void timeTest(double[] array) {
		long method1Time = timeMethod1(array);
        long method2Time = timeMethod2(array);
        System.out.println("Method 1 took " + method1Time + " nanoseconds to run.");
        System.out.println("Method 2 took " + method2Time + " nanoseconds to run.");
	}
	
	public long timeMethod1(double[] array) {
		StatLibrary stat = new StatLibrary();
        long startTime = System.nanoTime();
        double answer = stat.findMode(array);
        long endTime = System.nanoTime();
        System.out.println("Method 1 result: " + answer);
        return (endTime - startTime);
    }
    
    public long timeMethod2(double[] array) {
    	StatLibrary stat = new StatLibrary();
        long startTime = System.nanoTime();
        double answer = stat.mode(array);
        long endTime = System.nanoTime();
        System.out.println("Method 2 result: " + answer);
        return (endTime - startTime);
    }

}
