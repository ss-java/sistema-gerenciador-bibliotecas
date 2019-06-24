package view;

import controller.CustomerController;

public class CustomersEditWindowState implements WindowState {	
	public void OnSave(String name) {
		System.out.println("EditState");
		new CustomerController().editCustomers(60, name);
	}
}