/*
Date: 12/05/19
Name: Vivek Panchal,Rishi Wadekar
Course: Database Connectivity & Access
PE 09
*/
import java.util.*;
import java.io.*;

class Dump {
    
   private String fName;
   private ArrayList<String> colName;
   private ArrayList<String> colType;
   private ArrayList<String> is_nullable;
   private ArrayList<String> keys;
   private final String db = "travel";
   private boolean connStat;
   private String current_table;
   
   public Dump() {}

/**
     * Method:       fetchTables
     * Description:  this method is used to get the tables present in db.
     *               <BR>
     * @return       The returned value is an arraylist of table names.
 */  
   
   public ArrayList<String> fetchTables() {
      
      String tableName = "SELECT table_name ";
      tableName += "FROM information_schema.tables ";
      tableName += "WHERE table_schema=?;";
      
      ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
      ArrayList<String> tables = new ArrayList<String>();
      ArrayList<String> db_1 = new ArrayList<String>();
      db_1.add(db);
     // connect
      MySQLDatabase mysqld = new MySQLDatabase();
      connStat = mysqld.connect();
      // get the table names
    result = mysqld.getData(tableName, db_1);
      // close
      connStat = mysqld.close();
      // parse result
      for(int i = 2; i < result.size(); i++) {
         tables.add(result.get(i).get(0));
      }
      
      return tables;
   }  // end fetchTables
   
   /**
     * Method:       createDump
     * Description:  this method is used to get the required data.
     *               <BR>
     * @param        _name The query used to select the table.
     */   
   
   public void createDump(String name) {
      try {
         this.current_table = name;
         ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
         //ArrayList<String> information = new ArrayList<String>();
         String description = "SELECT COLUMN_NAME, COLUMN_KEY, IS_NULLABLE, COLUMN_TYPE ";
         description += "FROM information_schema.columns ";
         description += "WHERE table_name=? AND table_schema=?;";
         //ArrayList<String> db_1 = new ArrayList<String>();
         ArrayList<String> name1 = new ArrayList<String>();
        // db_1.add(name);
         name1.add(name);
         name1.add(db);
         //connect
         MySQLDatabase mysqld = new MySQLDatabase();
         connStat = mysqld.connect();
         
         //get the table contents
         result = mysqld.getData(description, name1);
         
         //close
         connStat = mysqld.close();
         writeToFile(result);
      }
      catch(Exception ex) {
         new DLException(ex);
      }
      
   }  // end createDump
   
   /**
     * Method:       writeToFile
     * Description:  this method is used to write the dump.
     *               <BR>
     * @param        _result The result from which the dump is written.
     */   
   public void writeToFile(ArrayList<ArrayList<String>> result) {
      
      colName = new ArrayList<String>();
      colType = new ArrayList<String>();
      is_nullable = new ArrayList<String>();
      keys = new ArrayList<String>();
      
      // populating string arrays
      try {
         for(int i = 2; i <result.size(); i++) {
            colName.add(result.get(i).get(0));
            keys.add(result.get(i).get(1));
            is_nullable.add(result.get(i).get(2));
            colType.add(result.get(i).get(3));
         }
      }
      catch(Exception ex) {
         new DLException(ex);
      }
      
      // writing to .sql file
      try {
         File sqlDump = new File(current_table + ".sql");
         
         // "true" will allow  us to append various dumps 
         // to same file
         FileWriter fw = new FileWriter(sqlDump, false);
         fw.write("CREATE TABLE " + current_table + " (");
         fw.write("\n");
         
         // interesting part
         for(int i = 0; i < colName.size(); i++) {
            
            // columnName
            fw.write("\t" + colName.get(i) + " ");
            
            // columnType
            fw.write(colType.get(i));
            
            // is it NOT NULL
            if(is_nullable.get(i).equals("NO")) {
               fw.write(" NOT NULL");
            }
            
            if(i != colName.size() - 1) {
               fw.write(",");
               fw.write("\n");
            }
         }
         
         // primary key
         if(keys.contains("PRI")) {
            ArrayList<String> primaryKeyCols = new ArrayList<String>();
            for(int i = 0; i < colName.size(); i++) {
               if(keys.get(i).equals("PRI")) {
                  primaryKeyCols.add(colName.get(i));
               }
            }
            fw.write(", \n\tPRIMARY KEY (");
            for(int i = 0; i < primaryKeyCols.size()-1; i++) {
               fw.write(primaryKeyCols.get(i) + ", ");
            }
            fw.write(primaryKeyCols.get(primaryKeyCols.size() - 1) + ")"); 
            fw.write("\n);\n");
         }
         else {
            fw.write("\n);\n");
         }
         
         // flush the filewriter buffer
         fw.flush();    
         // close the filewriter         
         fw.close();    
      }
      catch (IOException ioe) {
         new DLException(ioe);
      }
      catch (NullPointerException npe) {
         new DLException(npe);
      }
      
   }  // end writeToFile
   
   
   /**
     * Method:       readFile
     * Description:  this method is used to read the dump file.
     */
   public void readFile() {
   
      try {
         System.out.println("File name: " + current_table + ".sql");
         FileReader fr=new FileReader(current_table + ".sql");
         int i;    
         while((i=fr.read())!=-1) {    
            System.out.print((char)i);
         }    
         fr.close();
      }
      catch (IOException ioe) {
         new DLException(ioe);
      }
   
   }
   
}  // end class Dump