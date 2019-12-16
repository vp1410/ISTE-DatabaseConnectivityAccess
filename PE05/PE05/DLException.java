/*
Date: 10/13/19
Name: Rushikesh Kulkarni, Vivek Panchal
Course: Database Connectivity & Access
PE 05
*/
import java.util.*;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class DLException extends Exception{

   public DLException(Exception e){
      writeLog("Opereation failed to complete");        
   }
   
   public DLException(Exception e, ArrayList<String> list){
   writeLog(list.get(0));
   }
   
   private void writeLog(String logmsg){
      String time = new SimpleDateFormat("yyyy, MM, dd H:H:mm:ss"+"\n").format(Calendar.getInstance().getTime());
      
      try{
            FileWriter stream = new FileWriter("Logfile_Vivek.txt",true);
            BufferedWriter bfw = new BufferedWriter(stream);
            
            bfw.write(time + logmsg);
            bfw.newLine();
            bfw.close();
         }
         catch(Exception e){
            System.out.println("Logging Error");      
      }  
   }
}//close class