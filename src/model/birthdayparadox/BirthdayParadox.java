package model.birthdayparadox;

import main.data.structures.HashTable;

public class BirthdayParadox {
	
	public BirthdayParadox() {
		
	}
	
	public double beginSimulation(int people, int numOfSim) {
		
		double ret = conclusion(runSimulation(people, numOfSim));
		//29696100 
		return ret;
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
		boolean ret = false;
		HashTable room = new HashTable(array.length * 2);
		double frequency = 0;
		for(int i = 0; i < array.length - 1; i++) {
			frequency = room.add(array[i], array[i]);
			if(Double.compare(frequency, 2.0) == 0)
				ret = true;
		}
		return ret;
	}
	
	public double runSimulation(int people, int numOfSim) {
		double result = 0;
		for(int i = 0; i < numOfSim; i++) {
			boolean match = sameBirthday(setUpSimulation(people));
			
			if(match == true) 
				result += 1;
			
		}
		return result;
	}
	
	public double conclusion(double result) {
		return result;
	}
    
}
