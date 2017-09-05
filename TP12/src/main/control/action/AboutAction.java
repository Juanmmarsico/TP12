package main.control.action;

import java.awt.event.ActionEvent;

import main.GUI.aboutWindow.AboutWindow;
import main.control.ExpenseManager;

public class AboutAction extends ExpenseManagerAction {

	public AboutAction(ExpenseManager expenseManager) {
        super(expenseManager);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AboutWindow(expenseManager);
	}

}
