package main.GUI.tableModel;


import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.control.ExpenseManager;
import main.control.controllers.ExcepcionPropia;
import main.model.AbstractExpense;
import main.model.Expense;
import main.model.Income;

public class ExpenseOrIncomeTableModel extends AbstractTableModel{
	private  List<AbstractExpense> list;
	ExpenseManager expenseManager;
	String [] columnNames = {"Lugar","Total","Categorias","Dia","Amigos","Cuota","Vencimiento","Pagado","algo","no se"};
	
	public ExpenseOrIncomeTableModel(ExpenseManager expenseManager) {
		// TODO Auto-generated constructor stub
		
		this.expenseManager = expenseManager;
		list =expenseManager.getOwnwerController().searchAllExpense("");
	}
	public ExpenseOrIncomeTableModel(ExpenseManager expenseManager, List<AbstractExpense> list) {
		// TODO Auto-generated constructor stub
		
		this.expenseManager = expenseManager;
		this.list =list;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		try {
			return list.size();

		} catch (NullPointerException e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
  	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		  Object value = null;
          AbstractExpense expense = list.get(rowIndex);
          switch (columnIndex) {
              case 0:
                  value = expense.getPlace();
                  break;
              case 1:
                  value= expense.getTotalValue();
                  break;
              case 2: 
            	  value =  expense.getCategoria();
            	  break;
              case 3:
            	  value = sdf.format(expense.getDateOfExpense().getTime());
            	  break;
              case 4: 
            	  if (expense instanceof Expense) {
					value = ((Expense)expense).getFriendsInTheExpense();
				}
            	  break;
              case 5: 
            	  if (expense instanceof Expense) {
  					value = ((Expense)expense).getCuotas();
  				}
            	  break;
              case 6: 
            	  if (expense instanceof Expense) {
    					value = sdf.format(((Expense)expense).getDateOfExpense().getTime());
    				}
            	  break;
              case 7: 
            	  if (expense instanceof Expense) {
    					value = ((Expense)expense).getSplitValue();
    				}
            	  break;
              case 8: 
            	  if (expense instanceof Income) {
    				}
            	  break;
              case 9: 
            	  break;

          }
          return value;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	public void setColumnName(int i, String name) {
	    columnNames[i] = name;
	    fireTableStructureChanged();
	}
}
