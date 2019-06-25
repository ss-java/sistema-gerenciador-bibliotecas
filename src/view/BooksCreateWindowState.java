package view;

import javax.swing.JFrame;

public class BooksCreateWindowState implements WindowState{

	@Override
	public void onSave(JFrame stateOwner) {
		BooksWindow booksWindow = (BooksWindow) stateOwner;
		booksWindow.createBook();
	}
}
