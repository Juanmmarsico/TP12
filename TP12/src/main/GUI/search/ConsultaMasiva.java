package main.GUI.search;

import main.GUI.tableModel.ExpenseOrIncomeTableModel;
import main.GUI.tableModel.FriendTableModel;
import main.control.ExpenseManager;
import main.control.controllers.ExcepcionPropia;
import main.model.AbstractExpense;
import main.model.Friend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaMasiva extends JFrame {
	
	private JTextField consulta;
	private JButton consultar;
	private ExpenseOrIncomeTableModel expenseOrIncomeTableModel;
	private FriendTableModel friendTableModel;
	private JTable friendTable, expenseTable;
	private JScrollBar friendScroll,expenseScroll;
	
	private ExpenseManager expenseManager;
	
	public ConsultaMasiva(ExpenseManager expenseManager) {
		// TODO Auto-generated constructor stub
		this.expenseManager = expenseManager;
		buildFrame();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void buildFrame() {
		// TODO Auto-generated method stub
		
		consulta = new JTextField();
		
		JPanel tablas = new JPanel(new GridLayout());
			
		expenseTable = new JTable(expenseOrIncomeTableModel);		
        expenseTable.setFillsViewportHeight(true);
        expenseTable.setShowHorizontalLines(true);
        expenseTable.setShowVerticalLines(false);

        JScrollPane scrollPaneExpense = new JScrollPane(expenseTable);
        
        friendTable = new JTable(friendTableModel);		
        friendTable.setFillsViewportHeight(true);
        friendTable.setShowHorizontalLines(true);
        friendTable.setShowVerticalLines(false);

        JScrollPane scrollPaneFriend = new JScrollPane(friendTable);

        consultar = new JButton("Consultar");
		consultar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					System.out.println(expenseManager.getOwnwerController().searchAllExpense(consulta.getText()));
					List<AbstractExpense> ex=expenseManager.getOwnwerController().searchAllExpense(consulta.getText());
					List<Friend> fr = expenseManager.getOwnwerController().searchAllFriend(consulta.getText());
					expenseOrIncomeTableModel = new ExpenseOrIncomeTableModel(expenseManager,ex);
					expenseTable.setModel(expenseOrIncomeTableModel);
					expenseTable.repaint();
					friendTableModel = new FriendTableModel(expenseManager,fr);
					friendTable.setModel(friendTableModel);
					friendTable.repaint();
					if (ex.isEmpty() && fr.isEmpty()) {
						throw new ExcepcionPropia(tablas);
					}
					
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block

				}catch (ExcepcionPropia e2) {
					// TODO: handle exception
					JOptionPane jPane = e2.sinResultado();
					jPane.setVisible(true);
					
					
				}
			}
		});
        
        tablas.add(scrollPaneExpense);
        tablas.add(scrollPaneFriend);

        

        
        this.add(consulta,BorderLayout.NORTH);
		this.add(consultar,BorderLayout.SOUTH);
        this.add(tablas,BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		
	}
	
}
