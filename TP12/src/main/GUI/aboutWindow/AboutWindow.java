package main.GUI.aboutWindow;


import javax.swing.*;

import main.control.ExpenseManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AboutWindow {
    JFrame mainFrame;
    JPanel buttonPanel, choosePanel;
    JButton  closeButton;
    JTextArea name,version,year, autores;
    ExpenseManager expenseManager;


    public AboutWindow(ExpenseManager umlGraph) {
        this.expenseManager = umlGraph;

        buildFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void buildFrame() {
        mainFrame = new JFrame("Acerca de");

        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(buildButtonsPanel(), BorderLayout.SOUTH);
        mainFrame.add(buildComboBoxPanel(), BorderLayout.CENTER);
        mainFrame.setPreferredSize(new Dimension(500,200));

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    private JPanel buildButtonsPanel() {
        buttonPanel = new JPanel();

        closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                closeFrame();
            }
        });
        buttonPanel.add(closeButton);

        return buttonPanel;
    }

    private void closeFrame() {
        mainFrame.setVisible(false);
        mainFrame.dispose();
    }


    private JPanel buildComboBoxPanel() {
        choosePanel = new JPanel();

        name = new JTextArea("Nombre: \t ExpenseWithFriends");
        version = new JTextArea("Version: \t 1.0");
        year = new JTextArea("year: \t 2017");
        autores = new JTextArea("Ornella Belloni & Juan Maria Marsicovetere");
        name.setEditable(false);
        version.setEditable(false);
        year.setEditable(false);
        autores.setEditable(false);
        

//        classSelect.setPreferredSize(new Dimension(150,20));

        choosePanel.add(name);
        choosePanel.add(version);
        choosePanel.add(year);
        choosePanel.add(autores);



        return choosePanel;
    }



}
