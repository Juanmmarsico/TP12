package main.GUI.javaFileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;



public class OpenFileChooser extends JFileChooser {
    String name;

    public OpenFileChooser(String name, FileFilter fileFilter, int function) {
        super();
        this.setName(name);

        setFileSelectionMode(function);
        setFileFilter(fileFilter);
    }

}
