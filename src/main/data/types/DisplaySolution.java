package main.data.types;

public class DisplaySolution extends DisplayData{
	
	private String solution;
	private String opperation;
	
	public DisplaySolution(String solution, String opperation) {
		this.setSolution(solution);
		this.setOpperation(opperation);
	}

	public String getOpperation() {
		return opperation;
	}

	public void setOpperation(String opperation) {
		this.opperation = opperation;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

}
