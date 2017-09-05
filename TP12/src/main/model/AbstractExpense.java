package main.model;


import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public abstract class AbstractExpense {
	protected String place;
	protected static Set<String> categoria =new TreeSet<String>();
	protected static Set<String> subcategoria =new TreeSet<String>();
	private static boolean changeItsStatus = false;

	protected double totalValue;
	protected Calendar dateOfExpense;
	
	public AbstractExpense() {
		// TODO Auto-generated constructor stub
		changeItsStatus = true;

	}
	
	public AbstractExpense(String place, double totalValue, Calendar dateOfExpense) {
		this.place = place;
		this.totalValue = totalValue;
		this.dateOfExpense = dateOfExpense;
		changeItsStatus = true;

	}
	public static void resetChangeItsStatus() {
		changeItsStatus = false;
	}
	public static boolean isChangeItsStatus() {
		return changeItsStatus;
	}
	public String getPlace() {
		return place;
	}

	public static Set<String> getCategoria() {
		return categoria;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public Calendar getDateOfExpense() {
		return dateOfExpense;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public static void setCategoria(Set<String> categoria) {
		AbstractExpense.categoria = categoria;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public void setDateOfExpense(Calendar dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}

	public void agregarCategoria(String cate){
		categoria.add(cate.toUpperCase());
	}
	
	public boolean devolverCategoria(String cate){
		return categoria.remove(cate);
	}

	public String[] arrayListCategoriasAArray(){
		String [] devolver;
		if(categoria.size()>0){ devolver= (new String[categoria.size()]);
		}else{ String [] dev = {""};	return dev;}
	
		Comparator< String> comparator = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return o1.compareTo(o2);
		}
	};
		List<String> list = new ArrayList<String>(categoria);
		Collections.sort(list, comparator);
		devolver =  list.toArray(new String[list.size()]);
	
		return devolver;
}
	
	public abstract double availableAfterTransaction(double disponible);

}
