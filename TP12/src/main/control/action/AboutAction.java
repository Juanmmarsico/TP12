package main.control.action;

import java.awt.event.ActionEvent;

import main.GUI.aboutWindow.AboutWindow;
import main.control.ExpenseManager;

// hello
public class AboutAction extends ExpenseManagerAction {

	public AboutAction(ExpenseManager expenseManager) {
        super(expenseManager);
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AboutWindow(expenseManager);
	}

}
