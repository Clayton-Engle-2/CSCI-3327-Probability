package birthday;

import java.util.concurrent.ThreadLocalRandom;

public class Person {
	
	private double birthday;
	
	public Person() {
		birthday = ThreadLocalRandom.current().nextInt(1, 365 + 1);
	}
	
	public double getBirthday() {
		return birthday;
	}
}
