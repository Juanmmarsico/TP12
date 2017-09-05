package main.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import main.control.action.SearchAction;


public class Expense extends AbstractExpense {
	private ArrayList<Friend> friendsInTheExpense = new ArrayList<Friend>();
	private double splitValue;
	private boolean tieneCuotas;
	private int cuotas = 1;

	public Expense() {
		// TODO Auto-generated constructor stub
	}
	
	public Expense(String place, double totalValue, Calendar dateOfExpense, double splitValue) {
		super(place, totalValue, dateOfExpense);
		this.splitValue = splitValue;
	}
	
	public Expense(String place, double totalValue, Calendar dateOfExpense, ArrayList<Friend> friendsInTheExpense,
			double splitValue) {
		super(place, totalValue, dateOfExpense);
		this.friendsInTheExpense = friendsInTheExpense;
		this.splitValue = splitValue;
	}
	
	public Expense(String place, double totalValue, Calendar dateOfExpense, ArrayList<Friend> friendsInTheExpense,
			double splitValue, boolean tieneCuotas, int cuotas) {
		super(place, totalValue, dateOfExpense);
		this.friendsInTheExpense = friendsInTheExpense;
		this.splitValue = splitValue;
		this.tieneCuotas = tieneCuotas;
		this.cuotas = cuotas;
	}

	public ArrayList<Friend> getFriendsInTheExpense() {
		return friendsInTheExpense;
	}

	public double getSplitValue() {
		return splitValue;
	}

	public boolean isTieneCuotas() {
		return tieneCuotas;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setFriendsInTheExpense(ArrayList<Friend> friendsInTheExpense) {
		this.friendsInTheExpense = friendsInTheExpense;
	}

	public void setSplitValue(double splitValue) {
		this.splitValue = splitValue;
	}

	public void setTieneCuotas(boolean tieneCuotas) {
		this.tieneCuotas = tieneCuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public void addFriend(Friend friend){
		friendsInTheExpense.add(friend);
	}
	
	@Override
	public double availableAfterTransaction(double disponible) {
		// TODO Auto-generated method stub
		return disponible - totalValue;
	}

	public boolean Search(String query){
		for (String string : categoria) {
			if (string.contains(query)) {
				return true;
			}
		}
		for (String string : subcategoria) {
			if (string.contains(query)) {
				return true;
			}
		}
		for(Friend friends: friendsInTheExpense){
			if (friends.search(query)) {
				return true;
			}
		}
		String cuotaStr = "" + cuotas;
		if (cuotaStr.contains(query)) {
			return true;
		}
		String totalValue = "" + getTotalValue();
		if (totalValue.contains(query)) {
			return true;
		}
		if (place.contains(query)) {
			return true;
		}	
		return false;
	}
	
	public String writeDocument() {
		SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");
		return friendsInTheExpense.toString() + ";" + splitValue + ";" + tieneCuotas + ";" + cuotas + ";" + place + ";"
				+ totalValue + ";" + sDF.format(dateOfExpense.getTime()) + "\n";
	}
	@Override
	public String toString() {
		SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");
		return "" + " "+cuotas +" "+ place
				+" "+ totalValue +" "+ sDF.format(dateOfExpense.getTime()) + "\n";
	}

	public boolean Search(String lugar, String precio) {
		// TODO Auto-generated method stub
		boolean l = place.contains(lugar);
		boolean p = false;
			String totalValue = "" + getTotalValue();
			if (totalValue.contains(precio)) {
				p= true;
			}
		
			if (lugar.equals("") && !precio.equals("")) {
				l=p;
			}
			if (!lugar.equals("") && precio.equals("")) {
				p=l;
			}
		
		
		return l || p;
	}
	
}
