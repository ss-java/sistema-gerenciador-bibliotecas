package view;

import controller.CustomerController;

public class CustomersCreateWindowState implements WindowState {	
	public void OnSave(String name) {
		System.out.println("CreateState");
		new CustomerController().createCustomer(name);
	}

}
