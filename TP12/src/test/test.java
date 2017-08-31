	package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import main.GUI.mainWindow.Window.MainFrame;
import main.model.Friend;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("b");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("e");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("w");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("a");

		Set<String> set = new HashSet<String>(arrayList);
		System.out.println(set);
		
		
		
//			MainFrame mainFrame = new MainFrame();
//			
//			mainFrame.getExpenseManager().getOwnwerController().getOwner().addExpense("algo", 12, Calendar.getInstance(), 2);
//			mainFrame.getExpenseManager().getOwnwerController().getOwner().addExpense("sdasf", 12, Calendar.getInstance(), 2);
//			mainFrame.getExpenseManager().getOwnwerController().getOwner().addExpense("fasd", 12, Calendar.getInstance(), 2);
//			mainFrame.getExpenseManager().getOwnwerController().getOwner().addExpense("trte", 12, Calendar.getInstance(), 2);
//			mainFrame.getExpenseManager().getOwnwerController().getOwner().addExpense("ytry", 12, Calendar.getInstance(), 2);
//			
////			
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("carlos", "adsa");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("dfad", "ppo");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("gadfd", "netr");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("hrt", "jyt");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("ngdf", "verw");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("wtre", "rwe");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("er", "se");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("qe", "fd");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("tw", "re");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("w", "gfd");
////			mainFrame.getExpenseManager().getOwnwerController().getOwner().addFriend("ywre", "fd");
////			
//			ArrayList<Friend> f = new ArrayList<>(mainFrame.getExpenseManager().getOwnwerController().getOwner().searchAllFriend("a"));
//			
//			mainFrame.getExpenseManager().getOwnwerController().getOwner().addExpense("ytry", 12, Calendar.getInstance(), f,2);
//
//
//			mainFrame.repaint();
			
//		ArrayList<String> algo = new ArrayList<String>();
//		algo.add("algo");
//		algo.add("as");
//		algo.add("asd");
//		algo.add("as");
//		algo.add("as");
//		algo.add("algo");
//		algo.add("as");
//		algo.add("asd");
//		algo.add("as");
//		algo.add("as");
//		
//		LinkedList< String> linkedList = new LinkedList<>();
//		linkedList.addAll(algo);
//		
//		ArrayList<String> a = new ArrayList<>();
//		for (String string : linkedList) {
//			if (string.contains("s")) {
//				a.add(string);
//			}
//		}
//		
//			System.out.println(a);
			
	
		
		
//		Calendar calendar = new GregorianCalendar(1991, 11, 12);
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
//		System.out.println(sdf.format(calendar.getTime()));
		
//		JFrame jFrame = new JFrame("algo");
//		jFrame.setSize(300, 300);
//		jFrame.setLayout(new BorderLayout());
//		JPanel jPanelExt = new JPanel();
//		JPanel jPanelWest = new JPanel();
//		JPanel jPanelEast = new JPanel();
//		jPanelEast.add(new JTextArea("espero que este a la izquierda"));
//		jPanelWest.add(new JTextArea("espero que este a la derecha"));
//		
//		JPanel jPanel = new JPanel();
//		jPanel.add(new JTextArea("east"),BorderLayout.EAST);
//		jPanel.add(new JTextArea("west"),BorderLayout.WEST);
//		JPanel jPanel2 = new JPanel();
//		jPanel2.add(new JTextArea("north"),BorderLayout.NORTH);
//		jPanel2.add(new JTextArea("south"),BorderLayout.SOUTH);
//		jPanel2.setBackground(Color.BLUE);
//		jPanel.setBackground(Color.red);
//		jPanelExt.add(jPanel,BorderLayout.NORTH);
//		jPanelExt.add(jPanel2,BorderLayout.SOUTH);
//		
//		jFrame.add(jPanelExt, BorderLayout.CENTER);
//		jFrame.add(jPanelEast, BorderLayout.EAST);
//		jFrame.add(jPanelWest, BorderLayout.WEST);
//		jFrame.setVisible(true);
		
	}

}
