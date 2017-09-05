package main.control.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.GUI.javaFileChooser.OpenFileChooser;
import main.GUI.javaFileChooser.extencionFilters.TXTExtensionFilter;
import main.control.ExpenseManager;

public class AddFriendAction extends ExpenseManagerAction{
	
	private final JFileChooser fileChooser;

	    private final static String NAME = "Select class to add";

	
	public AddFriendAction(ExpenseManager expenseManager) {
		super(expenseManager);
		// TODO Auto-generated constructor stub
        fileChooser = new OpenFileChooser(NAME, new TXTExtensionFilter(), JFileChooser.OPEN_DIALOG);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String string ="Seleccione el archivo con los amigos,\n PD la informacion puede llegar a duplicarse si el amigo ya existia previamente \n"
				+ "Dicho archivo tiene que estar dividido por \";\" \n Con el formato: Nombre;Apellido;0 \n"
				+ "el 0 se lo puede cambiar por el valor que le deba el individuo si es que le debe plata \n";
		
		if (JOptionPane.showConfirmDialog((Component) e.getSource(), string,"Agregar amigos", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
			 int returnVal = fileChooser.showOpenDialog(new JPanel());

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fileChooser.getSelectedFile();
		        	expenseManager.getOwnwerController().readFriends(file);		
		        	expenseManager.updateFriendPanelList();
		        	System.out.println(expenseManager.getOwnwerController().getOwner().getFriends());
		        } else {
		        	return;
		        }	
		}else{
			return;
		}			        
		
	}
	
}
