/*
Date: 12/05/19
Name: Vivek Panchal
Course: Database Connectivity & Access
PE 09
*/
import java.util.*;
class VivekMain {
   
   public static void main(String[] args) {
   
      Dump dump = new Dump();
      ArrayList<String> tables = new ArrayList<String>();
      tables = dump.fetchTables();
      System.out.println("****List of tables to create dump:*****");
      for(String s: tables) {
      System.out.println(s);
      }
      System.out.println();
      
      // initialize scanner object
      Scanner input = new Scanner(System.in);
      System.out.print("Enter Table Name:");
      String tableNames = input.next();
      
      // close scanner object
      input.close();
      
      // create mysqldump for the said object
      dump.createDump(tableNames);
      
      // read the dump
      System.out.println("****Resultant Dump:***");
      dump.readFile();
      
   }

   
} 