/*
Date: 10/13/19
Name: Rushikesh Kulkarni, Vivek Panchal
Course: Database Connectivity & Access
PE 05
*/

import java.sql.*;
import java.util.*;
public class MySQLDatabase {
   //Connection attributes 
    private String driver = "com.mysql.jdbc.Driver"; 
    private String url = "jdbc:mysql://localhost:3306/travel?useSSL=false";
    private String urlw = "jdbc:mysql://localhost:3306/wrongtravel?useSSL=false";
    private String username = "root";
    private String password = "student";
    private Connection conn= null; //connection attribute
    
   // Connection to MySQL Database 
    public boolean connect() throws DLException {
      boolean conntx = false;
      try{
          Class.forName(driver);
          this.conn = DriverManager.getConnection(url,username,password);
          if (conn!=null){
            conntx=true;
            }       
        }// end try
      catch(ClassNotFoundException cnfe ){
        System.out.println("Cannot find or load driver" );
        ArrayList list = new ArrayList<String>();
        list.add("Error finding driver" + driver);
        throw new DLException(cnfe, list);
      }
      catch(SQLException sqle ){
         System.out.println("Could not connect to DB");
         ArrayList list = new ArrayList<String>();
         list.add("Error connecting to MySQLdatabase" + "\n" + "User : " + username + "\n" + "url: " + url);
         throw new DLException(sqle, list);
      }
      catch(Exception e){
           throw new DLException(e);
      } 
     return conntx;   
    }// end connect method
    
   // Fail Connection Method
    public boolean failconnect() throws DLException {
      boolean conntx = false;
      try{
          Class.forName(driver);
          this.conn = DriverManager.getConnection(urlw,username,password);
          if (conn!=null){
            conntx=true;
            }       
        }// end try
      catch(ClassNotFoundException cnfe ){
        System.out.println("Cannot find or load driver");
        ArrayList list = new ArrayList<String>();
        list.add("Error finding driver" + driver );
        throw new DLException(cnfe, list);
      }
      catch(SQLException sqle ){
         System.out.println("Could not connect to db");
         ArrayList list = new ArrayList<String>();
         list.add("Error connecting to MySQLdatabase" + "\n" + "User : " + username + "\n" + "url: " + urlw);
         throw new DLException(sqle, list);
      }
      catch(Exception e){
           throw new DLException(e);
      } 
     return conntx;   
    }// end connect method
       
   // Close connection to Database
   public boolean close() throws DLException{
      try{
         conn.close();
        System.out.println("Closing MySQL Database");
      }//end try
      catch(SQLException sqle ){
         System.out.println("Could not close MySQL Database");
         ArrayList list = new ArrayList<String>();
         list.add("Error disconnecting from MySQLdatabase" +"\n"+ "User : " + username  +"\n"+ "Driver : " + driver);
         throw new DLException(sqle, list);
      }
      catch(Exception e){
         throw new DLException(e);  
      }
      return true;
   }
   
   // getData using Query & Number of Fields and converts the resultset in 2D Array (PE03)
    public ArrayList<ArrayList<String>> getData(String SQLquery, int numFields) throws DLException{
    ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
    try{
    Statement stmnt = conn.createStatement();
    ResultSet rs = stmnt.executeQuery(SQLquery);
    while(rs.next()){
                ArrayList<String> row = new ArrayList<String>();
                for(int i= 1; i<=numFields;i++){
                row.add(rs.getString(i));
              }
               result.add(row);
          }//close while
      }//end try
      catch(SQLException sqle){
         System.out.println("SQL exception in getData");
         ArrayList listt = new ArrayList<String>();
         listt.add("Error in getData" +"\n"+ "Error in query : " + SQLquery);
         throw new DLException(sqle, listt);
      }
      catch(Exception e){
         System.out.println("getData error" + e.getMessage());
         throw new DLException(e);
      }
      return result;
   }//close getData
   
   // getData using Query & boolean flag and converts the resultset in 2D Array and also Adds MetaData (PE04)   
   public ArrayList<ArrayList<String>> getData(String SQLquery, boolean zenda)throws DLException{
    ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
    try{
    Statement stmnt = conn.createStatement();
    ResultSet rs = stmnt.executeQuery(SQLquery);
    ResultSetMetaData rsmd = rs.getMetaData();
    int numCols = rsmd.getColumnCount();
    ArrayList<String> colname = new ArrayList<String>();
    ArrayList<String> colwidth = new ArrayList<String>();
      for(int i = 1; i<= numCols;i++ ){
            colname.add(rsmd.getColumnName(i));
            colwidth.add(String.valueOf(rsmd.getColumnDisplaySize(i)));
      }
      if(zenda == true){
      result.add(colname);
      result.add(colwidth);}
      while(rs.next()){
                ArrayList<String> row = new ArrayList<String>();
                for(int i= 1; i<=numCols;i++){
                row.add(rs.getString(i));
              }
               result.add(row);
          }//close while
      }//end try
      catch(SQLException sqle){
         ArrayList list2 = new ArrayList<String>();
         list2.add("Error in getData" +"\n"+ "Error in query : " + SQLquery);
         throw new DLException(sqle, list2);    
      }
      catch(Exception e){
         throw new DLException(e);
      }
      return result;
   }// close getData
   
   /*
        setData accepts an SQL string and returns a Boolean
        setData performs the query that was passed
        If the query runs successfully, setData  returns the number of records
        affected, otherwise any errors should return -1
   */  
    public int SetData(String SQLquery)throws DLException{
      int count = -1;
         try{
               Statement stmnt = conn.createStatement();
               count = stmnt.executeUpdate(SQLquery);
            }//end try
      catch(SQLException sqle){
         ArrayList eList = new ArrayList<String>();
         eList.add("Execution failure");
         throw new DLException(sqle, eList);
      }
      catch(Exception e){
         System.out.println("getData error" + e.getMessage());
         throw new DLException(e);
      }
      return count;
   }//close setData
}//close class