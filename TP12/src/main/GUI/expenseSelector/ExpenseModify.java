package main.GUI.expenseSelector;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.*;


import main.control.ExpenseManager;
import main.model.AbstractExpense;
import main.model.Expense;
import main.model.Friend;
import main.model.Income;

public class ExpenseModify extends JFrame{
	private boolean b;
	
	private JPanel expensePanel,incomePanel,radioButtonPanel,buttonsPanel,friendsPanel, commonPanel;
	
	private JList<Friend> friends,friendsCopy;
	private DefaultListModel<Friend> friendsModel,friendCopyModel;
	private JScrollPane friendScrollPane,friendCopyScrollPane;	

    
	private ExpenseManager expenseManager;
	
	private JTextField amountField,placeField;
	
	private JTextArea splitAmountArea, TextoExigido;
	
	private JCheckBox tieneCuotasCheckBox;
	
	private JFormattedTextField numberFieldCuotas;
	
	private JRadioButton expenseRadio, IncomeRadio;
	private ButtonGroup expenseOrIncome;
	
	private JComboBox<String> categorias, subCategoria, diaInicio,diaFin , mesInicio,mesFin , anioInicio,anioFin;
	
	private SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/YYYY");
	
	private JButton save,edit,copy,delete,cancel;

	private AbstractExpense e;

	
	public ExpenseModify(ExpenseManager expenseManager) {
		// TODO Auto-generated constructor stub
		super("crear o modificar");
		b = true;
		this.expenseManager = expenseManager;
		this.setLayout(new BorderLayout());
		this.add(buildFriendsPanel(), BorderLayout.EAST);
		this.add(buildIncomePanel(), BorderLayout.WEST);
		this.add(buildExpensePanel(), BorderLayout.WEST);
		this.add(buildCommonPanel(),BorderLayout.CENTER);
		this.add(buildButtonsPanel(), BorderLayout.SOUTH);
		this.add(buildRadioButtonPanel(), BorderLayout.NORTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(300, 300));
		edit.setVisible(false);
		this.setVisible(true);	
	}
	
	
	private JPanel buildCommonPanel() {
		// TODO Auto-generated method stub
		commonPanel = new JPanel();


		JLabel amountLabel = new JLabel("Total");
		amountField= new JTextField();
		amountField.setMinimumSize(new Dimension(50, 80));
		
		commonPanel.add(amountLabel);
		commonPanel.add(amountField);
		
		JLabel placeLabel = new JLabel("Lugar");
		placeField = new JTextField();
		placeField.setMinimumSize(new Dimension(80,50));
		
		commonPanel.add(placeLabel);
		commonPanel.add(placeField);
		
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
				commonPanel.repaint();
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
		commonPanel.repaint();
		
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

		commonPanel.add(mesInicio);commonPanel.add(diaInicio);commonPanel.add(anioInicio);	


		
		return commonPanel;
	}
	private JPanel buildFriendsPanel() {
		// TODO Auto-generated method stub
		friendsPanel = new JPanel();
		friendsPanel.setLayout(new BorderLayout());
		
		friendsModel = new DefaultListModel<Friend>();
		for (Friend f : expenseManager.getOwnwerController().getOwner().searchAllFriend("")) {
			friendsModel.addElement(f);
		}
	
		friends = new JList<Friend>(friendsModel);
		friends.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		friendScrollPane = new JScrollPane(friends);
		
		friendCopyModel = new DefaultListModel<Friend>();
		friendsCopy = new JList<Friend>(friendCopyModel);
		friendsCopy.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		friendCopyScrollPane = new JScrollPane(friendsCopy);

		
		copy = new JButton("Copiar");
		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (Friend f : friends.getSelectedValuesList()) {
					friendCopyModel.addElement(f);
				}
				
				friendsCopy.repaint();
			}
		});
		delete = new JButton("Eliminar");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!friendsCopy.isSelectionEmpty()) {
					for (int i : friendsCopy.getSelectedIndices()) {
					friendCopyModel.remove(i);
					}
					friendsCopy.repaint();
				}	
			}
		});
		
		friendsPanel.add(friendScrollPane, BorderLayout.EAST);
		friendsPanel.add(friendCopyScrollPane, BorderLayout.WEST);
		friendsPanel.add(copy, BorderLayout.NORTH);
		friendsPanel.add(delete, BorderLayout.SOUTH);
		
		return friendsPanel;
	}
	private JPanel buildButtonsPanel() {
		// TODO Auto-generated method stub
		buttonsPanel = new JPanel(new BorderLayout());
		
		save = new JButton("Guardar");
		JFrame jFrame = this;
		save.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				if(b){
					if (expenseRadio.isSelected()) {						
						String place = placeField.getText();
						ArrayList <Friend> fr = new ArrayList<Friend>();

						if (friendsCopy.isSelectionEmpty()) {
							 List<Friend> f = new ArrayList<Friend>();
							 for (int i = 0; i < friendsCopy.getModel().getSize(); i++) {
								f.add(friendsCopy.getModel().getElementAt(i));
							}
							fr = new ArrayList<Friend>(f);
						}

						 boolean tieneCuotas = tieneCuotasCheckBox.isSelected();
						 int cuotas = Integer.parseInt(numberFieldCuotas.getText());  
						 double totalValue = Double.parseDouble(amountField.getText()); 
						double splitValut = totalValue/(fr.size()+1);
						Calendar dateOfExpense = new GregorianCalendar(Integer.parseInt((String) anioInicio.getSelectedItem()) , Integer.parseInt((String) mesInicio.getSelectedItem())-1, Integer.parseInt((String) diaInicio.getSelectedItem()));
						
						expenseManager.getOwnwerController().getOwner().addExpense(place, totalValue, dateOfExpense, fr, splitValut, tieneCuotas, cuotas);
						jFrame.dispose();

					}else{
						int repetition = 0;
						int []  repetitionsDays = {0};
						String place = placeField.getText();
						 double totalValue = Double.parseDouble(amountField.getText()); 
							Calendar dateOfExpense = new GregorianCalendar(Integer.parseInt((String) anioInicio.getSelectedItem()) , Integer.parseInt((String) mesInicio.getSelectedItem())-1, Integer.parseInt((String) diaInicio.getSelectedItem()));
						expenseManager.getOwnwerController().getOwner().addIncome(repetition, repetitionsDays, place, totalValue, dateOfExpense);
						jFrame.dispose();

					}
				}else{
					if (e instanceof Expense) {
						if (expenseRadio.isSelected()) {
							 e.setPlace(placeField.getText());
							 int f2 =1;
							 if (friendsCopy.isSelectionEmpty()) {
								 List<Friend> f = new ArrayList<Friend>();
								 for (int i = 0; i < friendsCopy.getModel().getSize(); i++) {
									f.add(friendsCopy.getModel().getElementAt(i));
								}
									((Expense) e).setFriendsInTheExpense(new ArrayList<Friend>(f));
									f2 = f.size();
							}
							 ((Expense) e).setTieneCuotas(tieneCuotasCheckBox.isSelected()); 
							 ((Expense) e).setCuotas(Integer.parseInt(numberFieldCuotas.getText()));  
							 e.setTotalValue(Double.parseDouble(amountField.getText())); 
							((Expense) e).setSplitValue(Double.parseDouble(amountField.getText())/(f2));							
							e.setDateOfExpense(new GregorianCalendar(Integer.parseInt((String) anioInicio.getSelectedItem()) , Integer.parseInt((String) mesInicio.getSelectedItem())-1, Integer.parseInt((String) diaInicio.getSelectedItem())));
							jFrame.dispose();

						}else{
							JOptionPane.showInternalMessageDialog(jFrame, "No puede cambiar un gasto a un ingreso todavia", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						if (!expenseRadio.isSelected()) {
							 e.setPlace(placeField.getText()); 
							 e.setTotalValue(Double.parseDouble(amountField.getText())); 
							e.setDateOfExpense(new GregorianCalendar(Integer.parseInt((String) anioInicio.getSelectedItem()) , Integer.parseInt((String) mesInicio.getSelectedItem())-1, Integer.parseInt((String) diaInicio.getSelectedItem())));
						jFrame.dispose();
						}else{
							JOptionPane.showInternalMessageDialog(jFrame, "No puede cambiar un ingreso a un gasto todavia", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}	
				}
			}
		});

		edit = new JButton("Modificar");
		edit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				tryEnableOrDisable(true);
				edit.setEnabled(false);
				repaint();
			}
		});
		
		cancel = new JButton("Cancelar");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		buttonsPanel.add(save, BorderLayout.EAST);
		buttonsPanel.add(edit, BorderLayout.CENTER);
		buttonsPanel.add(cancel, BorderLayout.WEST);

		return buttonsPanel;
	}
	private JPanel buildExpensePanel() {
		// TODO Auto-generated method stub
		expensePanel = new JPanel();
		friendsPanel.setVisible(true);

		NumberFormat n = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat d = (DecimalFormat) n;
		numberFieldCuotas = new JFormattedTextField(d);
		numberFieldCuotas.setColumns(5);
		numberFieldCuotas.setText(""+1);
		expensePanel.add(numberFieldCuotas);
//		categorias.setEditable(b);
//		subCategoria.setEditable(b);
		
		tieneCuotasCheckBox = new JCheckBox("Cuotas");
		expensePanel.add(tieneCuotasCheckBox);
		
		this.add(expensePanel, BorderLayout.CENTER);
		return expensePanel;
	}
	private JPanel buildIncomePanel() {
		// TODO Auto-generated method stub
		incomePanel = new JPanel(new GridLayout());
		friendsPanel.setVisible(false);
		
		
		this.add(incomePanel, BorderLayout.CENTER);
		return incomePanel;
	}
	private JPanel buildRadioButtonPanel() {
		// TODO Auto-generated method stub
		radioButtonPanel = new JPanel(new BorderLayout());
		IncomeRadio = new JRadioButton("income");
		expenseRadio = new JRadioButton("expense");
		expenseRadio.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (expenseRadio.isSelected()) {
					expensePanel.setVisible(true);
					incomePanel.setVisible(false);
					friendsPanel.setVisible(true);
					repaint();
				}
			}
		});
		IncomeRadio.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!expenseRadio.isSelected()) {
					incomePanel.setVisible(true);
					expensePanel.setVisible(false);
					friendsPanel.setVisible(false);

					repaint();
				}
			}
		});
		TextoExigido = new JTextArea("El objetivo de la inferencia estadistica casi siempre es sacar \n algun tipo de conclusion sobre uno o mas parametros \n (caracteristicas de la poblacion). Para hacer eso un investigador tie- ne que obtener datos muestrales \n de cada una de las poblaciones estudiadas. Las conclusio- nes pueden entonces basarse \n en los valores calculados de varias cantidades muestrales.");
		TextoExigido.setEditable(false);
		expenseOrIncome = new ButtonGroup();
		expenseOrIncome.add(IncomeRadio);
		expenseOrIncome.add(expenseRadio);
		radioButtonPanel.add(IncomeRadio,BorderLayout.EAST);
		radioButtonPanel.add(expenseRadio,BorderLayout.CENTER);
		expenseRadio.setSelected(true);		
		radioButtonPanel.add(TextoExigido,BorderLayout.WEST);
		return radioButtonPanel;
	}
	public ExpenseModify(ExpenseManager expenseManager, Expense e) {
		// TODO Auto-generated constructor stub
		this(expenseManager);
		this.e = e; 
		b = false;
		
		incomePanel.setVisible(false);
		
		amountField.setText(""+e.getTotalValue());
		placeField.setText(e.getPlace());
		diaInicio.setSelectedItem(e.getDateOfExpense().get(Calendar.DATE));
		mesInicio.setSelectedItem(e.getDateOfExpense().get(Calendar.MONTH + 1));
		anioInicio.setSelectedItem(e.getDateOfExpense().get(Calendar.YEAR));
		numberFieldCuotas.setValue(e.getCuotas());
		for (Friend friend : e.getFriendsInTheExpense()) {
			friendCopyModel.addElement(friend);
		}
		friendsCopy.setModel(friendCopyModel);
		friendsCopy.repaint();
//		categorias.setEditable(b);
//		subCategoria.setEditable(b);
		
		tryEnableOrDisable(false);
	}
	public ExpenseModify(ExpenseManager expenseManager,Income i) {
		// TODO Auto-generated constructor stub
		this(expenseManager);
		this.e = i; 
		b = false;
		
		expensePanel.setVisible(false);
		friendsPanel.setVisible(false);
		amountField.setText(""+e.getTotalValue());
		placeField.setText(e.getPlace());
		diaInicio.setSelectedItem(e.getDateOfExpense().get(Calendar.DATE));
		mesInicio.setSelectedItem(e.getDateOfExpense().get(Calendar.MONTH + 1));
		anioInicio.setSelectedItem(e.getDateOfExpense().get(Calendar.YEAR));
		
//		tryEnableOrDisable();	
		
//		categorias.setEditable(b);
//		subCategoria.setEditable(b);
		
		IncomeRadio.setSelected(true);

	}

	private void tryEnableOrDisable(boolean e) {
		// TODO Auto-generated method stub
		edit.setVisible(!e);
		incomePanel.setVisible(e);
		amountField.setEditable(e);
		placeField.setEditable(e);
		diaInicio.setEditable(e);
		mesInicio.setEditable(e);
		anioInicio.setEditable(e);
		numberFieldCuotas.setEditable(e);
//		categorias.setEditable(b);
//		subCategoria.setEditable(b);

	}

}
