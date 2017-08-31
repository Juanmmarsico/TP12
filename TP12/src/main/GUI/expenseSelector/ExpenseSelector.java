package main.GUI.expenseSelector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.AclEntry.Builder;
import java.util.Calendar;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import main.control.ExpenseManager;
import main.control.controllers.ExcepcionPropia;
import main.model.AbstractExpense;
import main.model.Expense;
import main.model.Friend;
import main.model.Income;


public class ExpenseSelector extends JFrame{
	
	
    final static Dimension MIN_DIMENSION = new Dimension(300,100);
    final static Dimension LIST_DIMENSION = new Dimension(200,50);
    
	private JPanel  incomePanelList,  expensesListPanel, consultaPanel;
    private JList jListExpense,jlistIncome;
    private JScrollPane scrollPaneExpense,scrollPaneIncome;
    private DefaultListModel<Expense> expenseModel;
    private	DefaultListModel<Income> incomeModel;
    private ExpenseManager expenseManager;
    private JTextField lugar,precio;
    private JButton consultar;
    
    public ExpenseSelector() {
		// TODO Auto-generated constructor stub
    super("Gastos o Ingresos");
    }

	public ExpenseSelector(ExpenseManager expenseManager) {
		// TODO Auto-generated constructor stub
		this();
		this.setMinimumSize(MIN_DIMENSION);
		this.expenseManager = expenseManager;
		this.setTitle("ingresos y gastos");
		this.add(buildConsulta(), BorderLayout.NORTH);
    	this.add(buildIncomePanel(), BorderLayout.EAST);
    	this.add(buildExpensePanel(), BorderLayout.WEST);
    	
    	int [] algo= {1};
    	expenseManager.getOwnwerController().getOwner().addIncome(1, algo, "asd", 23, Calendar.getInstance());;
    	expenseManager.getOwnwerController().getOwner().addExpense("asd", 23, Calendar.getInstance(),2);
    	
    	updateExpenseOrIncomeList();
    	this.setVisible(true);
	}

	private JPanel buildConsulta() {
		// TODO Auto-generated method stub
		consultaPanel = new JPanel();
		JLabel lugarLable = new JLabel("Lugar");
		lugar = new JTextField();
		lugar.setPreferredSize(new Dimension(100, 100));
		lugar.add(lugarLable);
		consultaPanel.add(lugar);
		precio = new JTextField();
		precio.setPreferredSize(new Dimension(100, 100));
		JLabel precioLabel = new JLabel("Precio");
		precio.add(precioLabel);
		consultaPanel.add(precio);
		
		consultar = new JButton("Consultar");
		consultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				expenseModel = new DefaultListModel<Expense>();
				incomeModel = new DefaultListModel<Income>();
							
				try {
					List<AbstractExpense> ex = expenseManager.getOwnwerController().searchAllExpense(lugar.getText(),precio.getText());
					if (ex.isEmpty()) {
						throw new ExcepcionPropia(consultaPanel);
					}
					for (AbstractExpense abstractExpense : ex) {
						if (abstractExpense instanceof Expense) {
							expenseModel.addElement((Expense) abstractExpense);
						}
						if (abstractExpense instanceof Income) {
							incomeModel.addElement((Income) abstractExpense);
						}
					}
					jListExpense = new JList<>(expenseModel);
					jListExpense.repaint();
					
					jlistIncome = new JList<>(incomeModel);
					jlistIncome.repaint();
					
					System.out.println(	expenseManager.getOwnwerController().searchAllExpense(lugar.getText(),precio.getText()));
					
					
				} catch (ExcepcionPropia excepcionPropia) {
					// TODO: handle exception
					excepcionPropia.sinResultado();
				}
				
			}
		});
		
		consultaPanel.add(consultar);
		
		consultaPanel.setVisible(false);
		
		return consultaPanel;
	}

	private JPanel buildExpensePanel() {
		// TODO Auto-generated method stub
		expensesListPanel = new JPanel();
		JLabel expenseListLabel = new JLabel("Gastado");
		expensesListPanel.add(new JTextArea("gastos"));
		expensesListPanel.setBackground(Color.RED);
		
		expenseModel = new DefaultListModel<Expense>();
		jListExpense = new JList<Expense>();

		scrollPaneExpense = new JScrollPane(jListExpense);
		
		expensesListPanel.add(scrollPaneExpense);
      jListExpense.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				jlistIncome.clearSelection();
			}
		});
		return expensesListPanel;
	}

	private JPanel buildIncomePanel() {
		// TODO Auto-generated method stub
		incomePanelList = new JPanel();
		JLabel incomeListLabel = new JLabel("ingresos");
		incomePanelList.add(new JTextArea("ingreso"));
		incomePanelList.setBackground(Color.GREEN);
		
		
		incomeModel = new DefaultListModel<Income>();
		jlistIncome = new JList<Income>();
		
		scrollPaneIncome = new JScrollPane(jlistIncome);

		
		incomePanelList.add(scrollPaneIncome);
      jlistIncome.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				jListExpense.clearSelection();
			}
		});
      jlistIncome.setSize(100, 100);
      jlistIncome.setVisible(true);
		
		return incomePanelList;
	}

	  public void updateExpenseOrIncomeList() {
	    	if (expenseManager.getOwnwerController().getOwner().getExpense().size()>0) {
	    		incomeModel.removeAllElements();
	    		expenseModel.removeAllElements();
	    		incomeModel = new DefaultListModel<Income>();
	    		expenseModel = new DefaultListModel<Expense>();
	    		for (Expense f : expenseManager.getOwnwerController().ExpenseThisMonth()) {
			    	expenseModel.addElement(f);
				}
	    		for (Income f : expenseManager.getOwnwerController().IncomeThisMonth()) {
			    	incomeModel.addElement(f);
				}
			}
	        jListExpense.removeAll();
	        jListExpense.setModel(expenseModel);
	        jlistIncome.removeAll();
	        jlistIncome.setModel(incomeModel);
	        jListExpense.repaint();
	        jlistIncome.repaint();
	        }

	public void consulta() {
		// TODO Auto-generated method stub
		if (consultaPanel.isVisible()) {
			consultaPanel.setVisible(false);
			updateExpenseOrIncomeList();	
		}else{ consultaPanel.setVisible(true);
		}
	}

}
