package main.control.controllers;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.ComboBoxModel;

import main.GUI.mainWindow.firstTimeWindow.FirstTImeWindow;
import main.model.AbstractExpense;
import main.model.AppRequest;
import main.model.Expense;
import main.model.Friend;
import main.model.Income;
import main.model.Owner;

public class OwnwerController {
	private Owner owner;
	private FriendController fController;
	private ExpenseController eController;
	
	public OwnwerController() {
		// TODO Auto-generated constructor stub
		owner =  new Owner(0, "AD", "ASD", new AppRequest("algo", 456456456), 2);
		fController= new FriendController();
		fController.setOwnwerController(this);
		
		eController = new ExpenseController(this,fController);
		
//		wasRunBefore();
	}
	private void wasRunBefore() {
		// TODO Auto-generated method stub "./Files/owner.txt"
		String folder ="./Files/";
		File[] files= new File(folder).listFiles();
		if (files.length>1) {
			return;
		}
		int position = 0;
		for (int i = 0; i < files.length; i++) {
			if ((files[i].getName().equals("owner"+".txt"))) {
				position = i;
				leerOwnerTXT(files[i]);
				return;
			}
		}	
		FirstTImeWindow firstTImeWindow = new FirstTImeWindow(); 
		firstTImeWindow.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				createOwnerFile(firstTImeWindow.getName(), firstTImeWindow.getLastName(), firstTImeWindow.getMail(),firstTImeWindow.getId(), firstTImeWindow.getCelNumber(), firstTImeWindow.getDisponible(),firstTImeWindow.getFriendPath(),firstTImeWindow.getExpensePath());
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			leerOwnerTXT(files[position]);
			
	}
	
	
	public void leerOwnerTXT(File file){
		try {
			Scanner scanner = new Scanner(file);
			String [] strings = scanner.nextLine().split(";");
			
			long id= Long.parseLong(strings [0]);
			String name = strings [1];
			String lastName = strings [2];
			String mail= strings [3];
			long celNumber= Long.parseLong(strings [4]);
			double disponible= Double.parseDouble(strings [5]);
			double gastado= Double.parseDouble(strings [6]);
			String friendPath = scanner.nextLine();
			String expensePath = scanner.nextLine();
			
			owner = new Owner(id, name, lastName, new AppRequest(mail, celNumber),gastado, disponible);
			fController= new FriendController(friendPath,this);
			eController= new ExpenseController(expensePath, fController);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createOwnerFile(String name, String lastName,String mail, long id, long celNumber, double disponible,String friendPath,String expensePath){
		File file = new File("./Files/owner.txt");
		
		try {
			FileWriter f = new FileWriter(file);
			f.write( id 
					+ ";" + lastName  
					+ ";" + mail
					+ ";" + name
					+ ";" + celNumber
					+ ";" + disponible 
					+ ";" + 0 
					+ "\n"+ friendPath 
					+'\n'+ expensePath);
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Owner getOwner() {
	return owner;
}
	
	public List<Expense> ExpenseThisMonth(){
		List<Expense> devolver = new ArrayList<Expense>();
		ArrayList<AbstractExpense> expenses = new ArrayList<AbstractExpense>(owner.getExpense());
		for (AbstractExpense abstractExpense : expenses) {
			if(abstractExpense instanceof Expense){
				if (((Expense)abstractExpense).getDateOfExpense().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) && ((Expense)abstractExpense).getDateOfExpense().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
					devolver.add((Expense)abstractExpense);
				}
			}
		}
		return devolver;
	}
	public List<Income> IncomeThisMonth(){
		List<Income> devolver = new ArrayList<Income>();
		ArrayList<AbstractExpense> expenses = new ArrayList<AbstractExpense>(owner.getExpense());
		for (AbstractExpense abstractExpense : expenses) {
			if(abstractExpense instanceof Income){
				if (((Income)abstractExpense).getDateOfExpense().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) && ((Income)abstractExpense).getDateOfExpense().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
					devolver.add((Income)abstractExpense);
				}
			}
		}
		return   devolver;
	}
	
	public List<Friend> ownerFriends() {
		return owner.getFriends();
	}
	public void readFriends(File file) {
		// TODO Auto-generated method stub
		fController.readFriends(file);
	}
	
	public List<AbstractExpense> searchAllExpense(String query){
		
		return owner.searchAllExpense(query);
	}
	public List<Friend> searchAllFriend(String query){
		return owner.searchAllFriend(query);
		}
	public List<AbstractExpense> searchAllExpense(String text, String text2) {
		// TODO Auto-generated method stub
		return owner.searchAllExpense(text,text2);
	}
	public void writeFiles() {
		// TODO Auto-generated method stub
		fController.writeFriends();
		eController.writeExpense();
		
	}
	public List<String[]> leerCalendar() {
		// TODO Auto-generated method stub
		return eController.leerCalendar();
		
	}
	public double getDisponibleToDisplay() {
		// TODO Auto-generated method stub	
		return owner.getDisponible();
	}
	public double getGastadoToDisplay() {
		// TODO Auto-generated method stub
		return owner.getGastado();
	}
	public void readAll() {
		// TODO Auto-generated method stub
		fController.readFriends(new File("./Files/Friends.txt"));
		eController.readExpenses();
	}

}
