package main.GUI.expenseSelector;



import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import main.GUI.mainWindow.Window.MainFrame;
import main.control.ExpenseManager;
import main.control.action.IngressIncomeOrExpenseAction;
import main.model.Friend;

public class ExpenseCreation extends JFrame{
	
	
	private JList<Friend> friends;
	private JList<Friend> friendsCopy;
	private JScrollPane scrllFriends, scrllFriendsCopy;
	private DefaultListModel<Friend> friendsModel;
    private ExpenseManager expenseManager;
    private MainFrame mf;
    private JPanel panel;
	private JTextField amountField,placeField;
	private JRadioButton expense;
	private JRadioButton income;
	private ButtonGroup exorinc;
	private JLabel LblExorinc;
	private JTextField txt;
	private JCheckBox CbCuotas;
	private JLabel LblCuotas;
	private JButton aceptar;
	private JButton cancelar;
	private JComboBox comboCat;
	private JComboBox comboSubCat;
	private JComboBox<String>  diaInicio, mesInicio,mesFin , anioInicio,anioFin;
	private DefaultListModel modelo;
	private DefaultListModel modeloCopy;
	private JLabel lblCat=new JLabel("Categoria");
	private JButton btnCop=new JButton("Copiar>>");
    IngressIncomeOrExpenseAction iioea;
    
    public ExpenseCreation(IngressIncomeOrExpenseAction iioea){
    	super("Ingresos y Expensas");
    	setLayout(new FlowLayout());
    	final String str[]={"",  "CAT1", "CAT2"};
    	final String str2[]={""};
    	setSize(8,8);
    	amountField=new JTextField("Precio");
    	amountField.setSize(2, 4);
    	placeField=new JTextField("Lugar");
    	placeField.setSize(2, 4);
    	panel=new JPanel();
    	aceptar=new JButton("Aceptar");
    	cancelar=new JButton("Cancelar");
    	aceptar.addActionListener(iioea);
    	cancelar.addActionListener(iioea);
    	CbCuotas=new JCheckBox("Cuotas");
    	CbCuotas.addItemListener(iioea);
    	LblCuotas= new JLabel("");
    	expense=new JRadioButton("Expense");
    	income=new JRadioButton("Income");
    	exorinc=new ButtonGroup();
    	LblExorinc=new JLabel("");
    	exorinc.add(income);
    	exorinc.add(expense);
    	expense.addItemListener(iioea);
    	income.addItemListener(iioea);
    	txt=new JTextField("Indique si es expensa o ingreso");
    	txt.setEditable(false);
    	comboCat = new JComboBox(str);
    	comboSubCat = new JComboBox(str2);
    	comboCat.addItemListener(iioea);
    	comboCat.setMaximumRowCount(5);
    	comboSubCat.setMaximumRowCount(5);
    	modelo=new DefaultListModel();
    	modeloCopy=new DefaultListModel();
    	//modelo.addElement(amigos);//add element agrega solo un elemento
    	//primero objeto, nombre variable,de dnde saca la info
    	mf=iioea.getExpenseManager().getMainFrame();
    	expenseManager=iioea.getExpenseManager();
    	expenseManager.getOwnwerController().getOwner().addFriend("carlos", "adsa");
    	expenseManager.getOwnwerController().getOwner().addFriend("dfad", "ppo");
		expenseManager.getOwnwerController().getOwner().addFriend("gadfd", "netr");
    	expenseManager.getOwnwerController().getOwner().addFriend("hrt", "jyt");
    	for(Friend f: expenseManager.getOwnwerController().getOwner().getFriends()){
    	modelo.addElement(f);}
    	friends= new JList<Friend>(modelo);
    	friendsCopy= new JList<Friend>(modeloCopy);
    	friends.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    	friendsCopy.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    	scrllFriends=new JScrollPane(friends);
    	scrllFriendsCopy=new JScrollPane(friendsCopy);
    	btnCop.addActionListener(iioea);
    	iioea.setVista(this);
    	panel.add(scrllFriends);
    	panel.add(scrllFriendsCopy);
    	add(comboCat);
    	add(comboSubCat);
    	add(lblCat);
    	panel.add(btnCop);
    	panel.add(txt);
    	add(expense);
    	add(income);
    	panel.add(LblExorinc);
    	panel.add(CbCuotas);
    	panel.add(LblCuotas);
    	add(amountField);
    	add(placeField);
    	add(aceptar);
    	add(cancelar);
    	add(panel);
    	
    	int count = 0;
		String [] diaArray = new String[expenseManager.getOwnwerController().leerCalendar().size()];
		ArrayList<String> meStrings = new ArrayList<String>();
		ArrayList<String> anioString = new ArrayList<String>();

		
		for (String[] d : expenseManager.getOwnwerController().leerCalendar()) {
			diaArray[count] = d[1];
			meStrings.add(d[0]);
			anioString.add(d[2]);
			count++;
		}
		
		Comparator<String> c= new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return Integer.parseInt(o1)- Integer.parseInt(o2);
			}
		};
		
		Collections.sort(anioString);
		Collections.sort(meStrings);
		Set<String> mesSet = new HashSet<>(meStrings);
		Set<String> anioSet = new HashSet<String>(anioString);
		
		String [] anio= anioSet.toArray(new String [anioSet.size()]);
		String [] mesArray= mesSet.toArray(new String [mesSet.size()]);
		count=0;
	
		DefaultComboBoxModel<String> cDay= new DefaultComboBoxModel<String>(diaArray);
		DefaultComboBoxModel<String> cMonth= new DefaultComboBoxModel<String>(mesArray);
	
		mesInicio = new JComboBox<>(cMonth);

		diaInicio = new JComboBox<String>();

		
		mesInicio.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				cDay.removeAllElements();
				int co= 0;
				for (String string : mesArray) {
					if (string.equals(mesInicio.getSelectedItem())) {
						int i = co;
						break;
					}
					co++;
				}
				String diasInt [] = new String [Integer.parseInt(diaArray[co])];
				for(int i = 0; i<diasInt.length;i++){
					cDay.addElement("" + (i +1));
				}
				
				diaInicio.setModel(cDay);
			}
		});
		DefaultComboBoxModel<String> cYear= new DefaultComboBoxModel<>(anio);

		anioInicio = new JComboBox<>(cYear);


		cDay.removeAllElements();
		int co= 0;
		for (String string : mesArray) {
			if (string.equals(mesInicio.getSelectedItem())) {
				int i = co;
				break;
			}
			co++;
		}
		String diasInt [] = new String [Integer.parseInt(diaArray[co])];
		for(int i = 0; i<diasInt.length;i++){
			cDay.addElement("" + (i +1));
		}
		
		diaInicio.setModel(cDay);
		
		 int thisYearInt = 0;
		 int thisMonthInt = 0;
		 int thisDayInt = 0;

		for (int i = 0; i < anioInicio.getItemCount(); i++) {
			if (anioInicio.getItemAt(i).equals(""+Calendar.getInstance().get(Calendar.YEAR))) {
				thisYearInt = i;
			}
		}
		for (int i = 0; i < mesInicio.getItemCount(); i++) {
			if (mesInicio.getItemAt(i).equals((new SimpleDateFormat("MM")).format((Calendar.getInstance().getTime())))) {
				thisMonthInt = i;
			}
		}	
		for (int i = 0; i < diaInicio.getItemCount(); i++) {
			if (diaInicio.getItemAt(i).equals(""+Calendar.getInstance().get(Calendar.DATE))) {
				thisDayInt = i;
			}
		}
		
		anioInicio.setSelectedIndex(thisYearInt);
		mesInicio.setSelectedIndex(thisMonthInt);
		diaInicio.setSelectedIndex(thisDayInt);

		panel.add(mesInicio);panel.add(diaInicio);panel.add(anioInicio);
    	
    	setVisible(true);
    }
    
    public JLabel getLblExorinc() {
		return LblExorinc;
	}

	public void setLblExorinc(JLabel lblExorinc) {
		LblExorinc = lblExorinc;
	}

	public JList<Friend> getFriendsCopy() {
		return friendsCopy;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void setFriendsCopy(JList<Friend> friendsCopy) {
		this.friendsCopy = friendsCopy;
	}

	public JScrollPane getScrllFriends() {
		return scrllFriends;
	}

	public void setScrllFriends(JScrollPane scrllFriends) {
		this.scrllFriends = scrllFriends;
	}

	public JScrollPane getScrllFriendsCopy() {
		return scrllFriendsCopy;
	}

	public void setScrllFriendsCopy(JScrollPane scrllFriendsCopy) {
		this.scrllFriendsCopy = scrllFriendsCopy;
	}

	public JButton getBtnCop() {
		return btnCop;
	}

	public void setBtnCop(JButton btnCop) {
		this.btnCop = btnCop;
	}

	public String[] getCategorias(String cat){
    	final String str2[]={"",  "SUBCAT1", "SUBCAT2"};
    	if(cat.equals("CAT1")){
    		str2[0]="SUBCAT1";
    		str2[1]="SUBCAT2";	
    	}else{
    		str2[0]="subcat1";
    		str2[1]="subcat2";
    	}
    	return str2;
    	}
    
	public JLabel getLblCat() {
		return lblCat;
	}

	public void setLblCat(JLabel lblCat) {
		this.lblCat = lblCat;
	}

	public JList<Friend> getFriends() {
		return friends;
	}

	public void setFriends(JList<Friend> friends) {
		this.friends = friends;
	}

	public DefaultListModel<Friend> getFriendsModel() {
		return friendsModel;
	}

	public void setFriendsModel(DefaultListModel<Friend> friendsModel) {
		this.friendsModel = friendsModel;
	}

	public ExpenseManager getExpenseManager() {
		return expenseManager;
	}

	public void setExpenseManager(ExpenseManager expenseManager) {
		this.expenseManager = expenseManager;
	}

	public JTextField getAmountField() {
		return amountField;
	}

	public void setAmountField(JTextField amountField) {
		this.amountField = amountField;
	}

	public JTextField getPlaceField() {
		return placeField;
	}

	public void setPlaceField(JTextField placeField) {
		this.placeField = placeField;
	}

	public MainFrame getMf() {
		return mf;
	}

	public void setMf(MainFrame mf) {
		this.mf = mf;
	}

	public JRadioButton getExpense() {
		return expense;
	}

	public void setExpense(JRadioButton expense) {
		this.expense = expense;
	}

	public JRadioButton getIncome() {
		return income;
	}

	public void setIncome(JRadioButton income) {
		this.income = income;
	}

	public ButtonGroup getExorinc() {
		return exorinc;
	}

	public void setExorinc(ButtonGroup exorinc) {
		this.exorinc = exorinc;
	}

	public JTextField getTxt() {
		return txt;
	}

	public void setTxt(JTextField txt) {
		this.txt = txt;
	}

	public JCheckBox getCbCuotas() {
		return CbCuotas;
	}

	public void setCbCuotas(JCheckBox cbCuotas) {
		CbCuotas = cbCuotas;
	}

	public JLabel getLblCuotas() {
		return LblCuotas;
	}

	public void setLblCuotas(JLabel lblCuotas) {
		LblCuotas = lblCuotas;
	}

	public JButton getAceptar() {
		return aceptar;
	}

	public void setAceptar(JButton aceptar) {
		this.aceptar = aceptar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}

	public JComboBox getComboCat() {
		return comboCat;
	}

	public void setComboCat(JComboBox comboCat) {
		this.comboCat = comboCat;
	}

	public JComboBox getComboSubCat() {
		return comboSubCat;
	}

	public void setComboSubCat(JComboBox comboSubCat) {
		this.comboSubCat = comboSubCat;
	}

	public IngressIncomeOrExpenseAction getIioea() {
		return iioea;
	}

	public void setIioea(IngressIncomeOrExpenseAction iioea) {
		this.iioea = iioea;
	}
	public DefaultListModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultListModel modelo) {
		this.modelo = modelo;
	}

	public DefaultListModel getModeloCopy() {
		return modeloCopy;
	}

	public void setModeloCopy(DefaultListModel modeloCopy) {
		this.modeloCopy = modeloCopy;
	}

    
    


}
