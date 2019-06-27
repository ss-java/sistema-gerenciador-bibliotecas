package view;

import javax.swing.JFrame;

import controller.CustomerController;

public class CustomersEditWindowState implements WindowState {	
	public void onSave(JFrame stateOwner) {
		System.out.println("EditState");
		CustomersWindow customer = (CustomersWindow) stateOwner;
		customer.editCustomer();
	}
}