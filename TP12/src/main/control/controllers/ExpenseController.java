package main.control.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import main.model.AbstractExpense;
import main.model.Expense;
import main.model.Friend;
import main.model.Income;
import sun.net.www.content.text.plain;

public class ExpenseController {
	
	ArrayList<AbstractExpense> expenses;
	FriendController friendController;
	OwnwerController ownwerController;
	String expensePath = "./Files/Expenses.txt";
	String ingresPath="./Files/Ingress.txt";
	
	public ExpenseController(String expensePath,FriendController friendController) {
		// TODO Auto-generated constructor stub
		this.expensePath = expensePath;
		this.friendController = friendController;
	}

	public ExpenseController(OwnwerController ownwerController) {
		// TODO Auto-generated constructor stub
		this.ownwerController=ownwerController;
	}
	
	public ExpenseController(OwnwerController ownwerController2, FriendController fController) {
		// TODO Auto-generated constructor stub
	this.friendController = fController;
	this.ownwerController = ownwerController2;
	}

	public void writeExpense() {
		// TODO Auto-generated method stub
		try {
			File ingressFile =new File(ingresPath);

			FileWriter icomeWriter = new FileWriter(ingressFile);

			for(Income a:ownwerController.IncomeThisMonth()){
				if (!a.isChangeItsStatus()) {
					System.out.println("Salio sin guardar gasto o ingreso");
					return;
				}
				icomeWriter.write(a.writeDocument());
				}
//			ownwerController.IncomeThisMonth().get(ownwerController.IncomeThisMonth().size()-1).resetChangeItsStatus();
			Income.resetChangeItsStatus();
			icomeWriter.flush();

			
			File expenseFile =new File(expensePath);
			FileWriter expenseWriter = new FileWriter(expenseFile);
			for(Expense a:ownwerController.ExpenseThisMonth()){
				expenseWriter.write(a.writeDocument());
				}
			
			expenseWriter.flush();
				icomeWriter.close();
				expenseWriter.close();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String []> leerCalendar() {
		// TODO Auto-generated method stub
		File file = new File("./Files/Calendar.txt");
		List<String[]> anio = new ArrayList<String[]>();
		
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String string = (String) scanner.next();
				String m = string.substring(0,2);
				String d = string.substring(2,4);
				String y = string.substring(4);
				String [] cal = {m,d,y};
				anio.add(cal);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anio;
	}

	public void readExpenses() {
		// TODO Auto-generated method stub
		try {
			Scanner scannerE = new Scanner(new File(expensePath));
			while (scannerE.hasNextLine()){
				String [] strings = scannerE.nextLine().split(";");
				
					ArrayList<Friend> f = new ArrayList<Friend>();
					if (!strings[0].equals("[]")) {
						String aux = strings[0].substring(1, strings[0].length()-2);
						for (String friend : aux.split(",")) {
							f.add(friendController.getFriend(friend));
						}
					}
					String place =strings[4];
					double splitValue =Double.parseDouble(strings[1]);
					boolean tieneCuotas = Boolean.parseBoolean(strings[2]);
					int cuotas = Integer.parseInt(strings[3]);
					double totalValue =Double.parseDouble(strings[5]);
					
					Calendar dateOfExpense = new GregorianCalendar(Integer.parseInt(strings[6].substring(6)), Integer.parseInt(strings[6].substring(3, 5))-1, Integer.parseInt(strings[6].substring(0, 2)));
					addExpense(place, totalValue, dateOfExpense, f, splitValue, tieneCuotas, cuotas);
				}
			
			Scanner scannerI = new Scanner(new File(ingresPath));
			while (scannerI.hasNextLine()){
				String [] strings = scannerI.nextLine().split(";");
				
				int [] repetitionsDays = {0};
				int repetition = 0;
					
					String place =strings[2];
					double totalValue =Double.parseDouble(strings[3]);
					
					Calendar dateOfExpense = new GregorianCalendar(Integer.parseInt(strings[4].substring(6)), Integer.parseInt(strings[4].substring(3, 5))-1, Integer.parseInt(strings[4].substring(0, 2)));
					addIncome(repetition, repetitionsDays, place, totalValue, dateOfExpense);
				}
				
				scannerE.close();
				scannerI.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return;
	}
	
	private void addIncome(int repetitions,int[] repetitionsDays,String place, double totalValue, Calendar dateOfExpense) {
		// TODO Auto-generated method stub
		ownwerController.getOwner().addIncome(repetitions, repetitionsDays, place, totalValue, dateOfExpense);
		System.out.println(ownwerController.getOwner().getExpense());
	}

	private void addExpense(String place, double totalValue, Calendar dateOfExpense, ArrayList<Friend> friendsInTheExpense,
			double splitValue, boolean tieneCuotas, int cuotas){
		ownwerController.getOwner().addExpense(place, totalValue, dateOfExpense, friendsInTheExpense, splitValue, tieneCuotas, cuotas);
		System.out.println(ownwerController.getOwner().getExpense());

	}
}
