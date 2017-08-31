package main.GUI.tableModel;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


import main.control.ExpenseManager;
import main.control.controllers.ExcepcionPropia;
import main.model.Friend;

public class FriendTableModel extends DefaultTableModel{
	
	private static final String[] coloumName = {"Nombre", "Apellido", "Adeuda", "pago"};
	private  List<Friend> list;
	ExpenseManager expenseManager;

	
	public FriendTableModel(ExpenseManager expenseManager) {
		// TODO Auto-generated constructor stub
	list = new ArrayList<Friend>();
	this.expenseManager = expenseManager;
	}
	public FriendTableModel(ExpenseManager expenseManager, List< Friend> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.expenseManager = expenseManager;
	}
	
	public void addAllFriends() {
		list.addAll(expenseManager.getOwnwerController().getOwner().getFriends());
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return coloumName.length;
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
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		Object value = null;
		Friend friend = list.get(arg0);
		switch (arg1) {
		case 0:
			value = friend.getName();
			break;

		case 1:
			value = friend.getLastName();
			break;
			
		case 2:
			value = friend.getAdeuda();
			break;
		case 3: 
			value = false;
			break;
		}
		
		return value;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		Class value = String.class;
		switch (columnIndex) {
		case 0:
			value = String.class;
			break;

		case 1:
			value = String.class;
			break;
			
		case 2:
			value = Double.class;
			break;
		case 3: 
			value = Boolean.class;
			break;
		}
		
		return value;
	}
	
	 @Override
	    public void setValueAt(Object aValue, int row, int column) {
	      if (aValue instanceof Boolean && column == 2) {
	        System.out.println(aValue);
	        Vector rowData = (Vector)getDataVector().get(row);
	        rowData.set(2, (boolean)aValue);
	        fireTableCellUpdated(row, column);
	      }
	    }
	 
	  @Override
	    public boolean isCellEditable(int row, int column) {
	      return column == 3;
	    }
	  
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return coloumName[column];
	}
	public void setColumnName(int i, String name) {
	    coloumName[i] = name;
	    fireTableStructureChanged();
	}
}
