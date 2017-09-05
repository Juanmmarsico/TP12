package main.model;

public class Friend {
	
	private String name;
	private String lastName;
	private AppRequest appRequest;
	private double adeuda;
	private static int counter = 0;
	private int id;
	private static boolean hasChange = false;

	
	public Friend(String mail, long celNumber, String name, String lastName, double adeuda) {
		this();
		this.appRequest = new AppRequest(mail, celNumber);
		this.name = name;
		this.lastName = lastName;
		this.adeuda = adeuda;
	}
	public Friend(String name, String lastName, double adeuda) {
		this();
		this.name = name;
		this.lastName = lastName;
		this.adeuda = adeuda;
	}
	public Friend() {
		// TODO Auto-generated constructor stub
		id=counter;
		counter ++;
		hasChange = true;
	}
	public AppRequest getAppRequest() {
		return appRequest;
	}
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setAppRequest(String mail, long celNumber) {
		this.appRequest = new AppRequest(mail, celNumber);
	}
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		Friend.counter = counter;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAdeuda(double adeuda) {
		this.adeuda = adeuda;
	}
	public boolean isAdeuda() {
		return adeuda>0;
	}
	public double getAdeuda() {
		return adeuda;
	}
	public static void resetHasChange() {
		hasChange = false;
	}
	public static boolean isHasChange() {
		return hasChange;
	}
	public int getId() {
		return id;
	}
	
	public String writeDocument() {
		try {
			return  counter + ";" 
					+name + ";" 
					+ lastName + ";"
					+  adeuda  + ";"
					+ appRequest.toString()
					 + "\n";
		} catch (NullPointerException e) {
			// TODO: handle exception
			return  id +";" +name + ";" + lastName+";"+ adeuda  + ";" + ";"+ ";"  + "\n";

		}
		
	}

	@Override
	public String toString() {
		try {
			return  counter + ";" 
					+name + ";" 
					+ lastName + ";"
					+  adeuda  + ";"
					+ appRequest.toString()
					 + "\n";
		} catch (NullPointerException e) {
			// TODO: handle exception
			return  name + " " + lastName +" " + adeuda ;

		}
		
	}
	public	boolean search(String query) {
		// TODO Auto-generated method stub
		if (name.contains(query)) {
			return true;
		}
		if (lastName.contains(query)) {
			return true;
		}
		if ((""+adeuda).contains(query)) {
			return true;
		}
		
		return false;
	}
	
	
	
}
