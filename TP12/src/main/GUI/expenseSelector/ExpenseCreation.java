package main.GUI.expenseSelector;



import javax.swing.*;

import main.control.ExpenseManager;
import main.model.Friend;

public class ExpenseCreation extends JFrame{
	
	private JList<Friend> friends;
	private DefaultListModel<Friend> friendsModel;
    private ExpenseManager expenseManager;
	private JTextField amountField,placeField;
	private JRadioButton expenseOrIncome;
	private JComboBox<String> categorias;
	
    private JPanel panel;
    
    public ExpenseCreation(ExpenseManager expenseManager) {
		// TODO Auto-generated constructor stub
    	this.expenseManager =expenseManager;
	}

    
    


}
