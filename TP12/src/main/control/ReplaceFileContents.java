package main.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReplaceFileContents {
	
	   public void replace(String id, String string, String source) {
	      String oldFileName = source;
	      String tmpFileName = "tmp_try.txt";

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	         br = new BufferedReader(new FileReader(oldFileName));
	         bw = new BufferedWriter(new FileWriter(tmpFileName));
	         String line;
	         while ((line = br.readLine()) != null) {
	            if (line.contains(id))
	               line = string;
	            bw.write(line+"\n");
	         }
	      } catch (Exception e) {
	         return;
	      } finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException e) {
	            //
	         }
	         try {
	            if(bw != null)
	               bw.close();
	         } catch (IOException e) {
	            //
	         }
	      }
	      File oldFile = new File(oldFileName);
	      oldFile.delete();

	      File newFile = new File(tmpFileName);
	      newFile.renameTo(oldFile);

	   }
	}
