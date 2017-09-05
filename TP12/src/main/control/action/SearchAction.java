package main.control.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import main.GUI.search.ConsultaMasiva;
import main.control.ExpenseManager;

public class SearchAction extends ExpenseManagerAction {

	public SearchAction(ExpenseManager expenseManager) {
		super(expenseManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String o = e.getActionCommand();
			if(o.equals("consultaMasiva")){
				System.out.println("vamos de a poco con masiva");
				new ConsultaMasiva(expenseManager);
				return;
			}else System.out.println("todo mal");
			
			if(o.equals("consulta")){
				System.out.println("vamos de a poco con actualizacion");
				expenseManager.getMainFrame().Consulta();
				return;
			}else System.out.println("todo mal");
		
		
		
		
		
		new ConsultaMasiva(expenseManager);
	}



}
