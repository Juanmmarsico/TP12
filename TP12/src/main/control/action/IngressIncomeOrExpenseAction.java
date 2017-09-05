package main.control.action;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.DefaultComboBoxModel;

import main.GUI.expenseSelector.ExpenseCreation;
import main.GUI.expenseSelector.ExpenseModify;
import main.GUI.mainWindow.Window.MainFrame;
import main.control.ExpenseManager;
import main.model.Friend;

public class IngressIncomeOrExpenseAction extends ExpenseManagerAction implements ActionListener,ItemListener{
	ExpenseCreation ex;
	MainFrame mf;
	

public void itemStateChanged(ItemEvent e) {
	  if (ex.getComboCat().getSelectedIndex()!=0){
		  if (e.getStateChange() ==  ItemEvent.SELECTED){
		     	ex.getLblCat().setText("Categoria: " + ex.getComboCat().getSelectedIndex());
		     	ex.getComboSubCat().setModel(new DefaultComboBoxModel(ex.getCategorias(ex.getComboCat().getSelectedItem().toString())));
		     		}
	  			}
	  if(e.getSource()==ex.getExpense()){
		  if(e.getStateChange()== ItemEvent.SELECTED){
			  ex.getLblExorinc().setText(ex.getExpense().getText());
			  ex.getPanel().setVisible(true);
		  }
		  else{
			  ex.getPanel().setVisible(false);
			  ex.getLblExorinc().setText(ex.getIncome().getText());
		  }
	  }
	  if(e.getSource()==ex.getCbCuotas()){
		  if(e.getStateChange() == ItemEvent.SELECTED){
		  ex.getLblCuotas().setText("Tiene Cuotas");
		  }
	  }
	  
			}
	
	public void setVista(ExpenseCreation ex){
		this.ex=ex;
	}

	public IngressIncomeOrExpenseAction(ExpenseManager expenseManager) {
		super(expenseManager);
		mf=expenseManager.getMainFrame();
		//mf=new MainFrame();
	}

	public void actionPerformed(ActionEvent e) {
		 new ExpenseModify(expenseManager);
		if(e.getSource()==mf.getAddExpense()){
			new ExpenseCreation(this);
		}else{
			for(Friend f: ex.getFriends().getSelectedValuesList()){
			ex.getModeloCopy().addElement(f);}
			}
		if(e.getSource()==ex.getAceptar()){
			if(ex.getExpense().isSelected()){
			expenseManager.getOwnwerController().getOwner().addExpense(ex.getPlaceField().getText(),Double.parseDouble(ex.getAmountField().getText()), ex.getComboSubCat().getSelectedItem(), ex.getFriendsCopy(), ";");
			}else{
			expenseManager.getOwnwerController().getOwner().addIncome(repetition, repetitionsDays, ex.getPlaceField().getText(), Double.parseDouble(ex.getAmountField().getText()), ex.getComboSubCat().getSelectedItem());
			}
		}else{
			if (e.getSource()==ex.getCancelar()){
				if(ex.getExpense().isSelected()){
				ex.getPlaceField().setText("");
				ex.getAmountField().setText("");
				ex.getComboCat().setSelectedIndex(0);
				}else{
				ex.getPlaceField().setText("");
				ex.getAmountField().setText(""); 
				ex.getComboCat().setSelectedIndex(0);
				ex.getFriendsCopy().removeAll();
				}
		}
		}
		}
	
	public void Guardar() throws IOException{
		FileWriter archivo=new FileWriter("Income.txt",true);
		PrintWriter archivoSalida= new PrintWriter(archivo);
		
	}
		
	}

