package birthday;

import statlib.StatLibrary;

public class BirthdayParadox {
	
	private StatLibrary stat;
	
	public BirthdayParadox(int people, int numOfSim) {
		stat = new StatLibrary();
		beginSimulation(people, numOfSim);
	}
	
	public void beginSimulation(int people, int numOfSim) {
		long s = System.nanoTime();
		conclusion(runSimulation(people, numOfSim));
		long e = System.nanoTime();
		//29696100 
		System.out.println("Time " + (e-s));
	}
	
	public double[] setUpSimulation(int people) {
		double[] room = new double[people];
		for(int i = 0; i < people; i++) {
			Person temp = new Person();
			room[i] = temp.getBirthday();
		}
		return room;
	}
	
	public boolean sameBirthday(double[] array) {
		boolean ret = true;
		array = stat.mergeSort(array);
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] == array[i + 1]) {
				return ret;
			}
		}
		ret = false;
		return ret;
	}
	
	public double runSimulation(int people, int numOfSim) {
		double result = 0;
		for(int i = 0; i < numOfSim; i++) {
			boolean match = sameBirthday(setUpSimulation(people));
			
			if(match == true) {
				result += 1;
			}
		}
		return result;
	}
	
	public void conclusion(double result) {
		System.out.println(result);
	}
    
}
