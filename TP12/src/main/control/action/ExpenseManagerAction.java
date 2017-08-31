package main.control.action;

import javax.swing.*;

import main.control.ExpenseManager;

public abstract class ExpenseManagerAction  extends AbstractAction {
	ExpenseManager expenseManager;	
	
	public ExpenseManagerAction(ExpenseManager expenseManager) {
		// TODO Auto-generated constructor stub
		this.expenseManager= expenseManager;
	}
	
public ExpenseManager getExpenseManager() {
	return expenseManager;
}

}
