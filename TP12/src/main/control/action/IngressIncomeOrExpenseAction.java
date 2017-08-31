package main.control.action;

import java.awt.event.ActionEvent;

import main.GUI.expenseSelector.ExpenseCreation;
import main.control.ExpenseManager;

public class IngressIncomeOrExpenseAction extends ExpenseManagerAction{

	public IngressIncomeOrExpenseAction(ExpenseManager expenseManager) {
		super(expenseManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		new ExpenseCreation(expenseManager);
		
	}

}
