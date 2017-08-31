package main.control.controllers;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.GUI.mainWindow.Window.MainFrame;

public class ExcepcionPropia extends Exception {
JPanel tablas;
	public ExcepcionPropia(JPanel tablas) {
		// TODO Auto-generated constructor stub
		this.tablas = tablas;
		

	}
	public JOptionPane sinResultado(){
		JOptionPane jOptionPane = new JOptionPane();
//		jOptionPane.createDialog(tablas, "no hay resultados en la busqueda");
//		jOptionPane.setMessage("no hay resultados en la busqueda");
//	    jOptionPane.setOptionType(JOptionPane.OK_OPTION);
//	    
	    JOptionPane.showMessageDialog(new JFrame(), "no hay resultados en la busqueda", "no hay resultados en la busqueda", JOptionPane.ERROR_MESSAGE);
	    return jOptionPane;
	}
}
