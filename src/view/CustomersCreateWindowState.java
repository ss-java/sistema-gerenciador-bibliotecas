package view;

import javax.swing.JFrame;

import controller.CustomerController;

public class CustomersCreateWindowState implements WindowState {	
	public void OnSave(JFrame stateOwner) {
		System.out.println("CreateState");
		CustomersWindow customer = (CustomersWindow) stateOwner;
		customer.createCustomer();
	}
}
