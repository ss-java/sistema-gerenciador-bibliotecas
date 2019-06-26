package view;

import javax.swing.JFrame;

public class WindowCustomersState implements WindowState {
	private WindowState currentState;
	private static WindowCustomersState instance;
	
	public WindowCustomersState() {
		this.currentState = new CustomersCreateWindowState();
	}
	
	public static WindowCustomersState getInstance() {
		if(instance == null) {
			instance = new WindowCustomersState();
		}
		return instance;
	}
	
	public void setState(WindowState s) {
		this.currentState = s;
	}
	
	public void OnSave(JFrame stateOwner) {
		this.currentState.OnSave(stateOwner);
	}

}
