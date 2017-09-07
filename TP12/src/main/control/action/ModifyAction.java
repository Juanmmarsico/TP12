package main.control.action;

import java.awt.event.ActionEvent;

import main.GUI.expenseSelector.ExpenseCreation;
import main.GUI.expenseSelector.ExpenseModify;
import main.control.ExpenseManager;
import main.model.AbstractExpense;
import main.control.action.IngressIncomeOrExpenseAction;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JList;
import main.model.Expense;
import main.model.Friend;
import main.model.Income;

public class ModifyAction extends ExpenseManagerAction {
//IngressIncomeOrExpenseAction iioe=new IngressIncomeOrExpenseAction(expenseManager);
	public ModifyAction(ExpenseManager expenseManager) {
		super(expenseManager);
		// TODO Auto-generated constructor stub
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JButton) {
			if (!expenseManager.getMainFrame().seleccionado().isSelectionEmpty()) {
				if (expenseManager.getMainFrame().seleccionado().getSelectedValue() instanceof Expense) {
					new ExpenseModify(expenseManager, (Expense) expenseManager.getMainFrame().seleccionado().getSelectedValue());
					}else{
					new ExpenseModify(expenseManager,(Income)expenseManager.getMainFrame().seleccionado().getSelectedValue());		
					}
						}
					}
						
		//ExpenseCreation(iioe);

	}

}
