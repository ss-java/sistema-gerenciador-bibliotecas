package view;

public class WindowCustomersState {
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
	
	public void OnSave(String name) {
		this.currentState.OnSave(name);
	}

}
