package control;


import main.data.types.DisplayData;
import main.data.types.DisplaySolution;
import view.MainMenuGUI;

public class Loader {
	
	
	public void load() {
		Pipe<DisplayData> viewToControl = new Pipe<DisplayData>();
		Pipe<DisplaySolution> controlToView = new Pipe<DisplaySolution>();
		Pipe<Integer> shutdown = new Pipe<Integer>();
		ProgramControl control = new ProgramControl(viewToControl, controlToView, shutdown);
		MainMenuGUI view = new MainMenuGUI(controlToView, viewToControl, shutdown);
		
		view.start();
		control.run();
		
		
		
		
			
		
		
	}

}
