package main.GUI.javaFileChooser.extencionFilters;


import javax.swing.filechooser.FileFilter;
import java.io.File;


public class TXTExtensionFilter extends FileFilter {


    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Extensions.getExtension(f);
        if (extension != null) {
            if (extension.equals(Extensions.TXT)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "*.txt(txt Files)";
    }
}