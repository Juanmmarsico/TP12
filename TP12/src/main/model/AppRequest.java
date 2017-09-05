package main.model;


public class AppRequest {
	private String mail;
	private long celNumber;
	
	public AppRequest() {
		// TODO Auto-generated constructor stub
	}

	public AppRequest(String mail, long celNumber) {
		this.mail = mail;
		this.celNumber = celNumber;
	}

	public String getMail() {
		return mail;
	}



	public long getCelNumber() {
		return celNumber;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	

	public void setCelNumber(long celNumber) {
		this.celNumber = celNumber;
	}

	@Override
	public String toString() {
		return mail + ";" + celNumber;
	}
	
	
}
