package main.GUI.javaFileChooser.extencionFilters;

import java.io.File;

public class Extensions {
    public final static String TXT = "TXT";


    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}