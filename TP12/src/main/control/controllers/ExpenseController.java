package main.control.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import main.model.AbstractExpense;
import main.model.Expense;
import main.model.Friend;
import main.model.Income;

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

	public void writeExpense() {
		// TODO Auto-generated method stub
		try {
			File ingressFile =new File(ingresPath);

			FileWriter icomeWriter = new FileWriter(ingressFile);

			for(Income a:ownwerController.IncomeThisMonth()){
				icomeWriter.write(a.writeDocument());
				}
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
	
}
