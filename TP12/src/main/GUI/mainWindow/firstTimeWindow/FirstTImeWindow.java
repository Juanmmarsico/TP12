package main.GUI.mainWindow.firstTimeWindow;


import javax.swing.JFrame;

public class FirstTImeWindow extends JFrame{
	final JFrame frame = new JFrame("Bienvenido Al Programa");
	
	private String name;
	private String lastName;
	private double disponible;
	private String mail;
	private long id;
	private long celNumber;
	private String friendPath;
	private String expensePath;
	
	public FirstTImeWindow() {
		// TODO Auto-generated constructor stub
	}                                  
	
    
	
	public double getDisponible(){
		return disponible;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getMail() {
		// TODO Auto-generated method stub
		return mail;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public long getCelNumber() {
		// TODO Auto-generated method stub
		return celNumber;
	}

	public String getFriendPath() {
		// TODO Auto-generated method stub
		return friendPath;
	}

	public String getExpensePath() {
		// TODO Auto-generated method stub
		return expensePath;
	}

	
}
