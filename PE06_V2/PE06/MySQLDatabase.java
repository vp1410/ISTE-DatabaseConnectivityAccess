/*
Date: 10/31/19
Name: Vivek Panchal
Course: Database Connectivity & Access
PE 07
*/

import java.sql.*;
import java.util.*;
public class MySQLDatabase {
   //Connection attributes 
    private String driver = "com.mysql.jdbc.Driver"; 
    private String url = "jdbc:mysql://localhost:3306/travel?useSSL=false";
    private String username = "root";
    private String password = "vivekpanchal";
    private Connection conn; //connection attribute
    
   // Connection to MySQL Database 
    public boolean connect(){
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
        new DLException(cnfe, list);
      }
      catch(SQLException sqle ){
         System.out.println("Could not connect to DB");
         ArrayList list = new ArrayList<String>();
         list.add("Error connecting to MySQLdatabase" + "\n" + "User : " + username + "\n" + "url: " + url);
          new DLException(sqle, list);
      }
      catch(Exception e){
           new DLException(e);
      } 
     return conntx;   
    }// end connect method
    
   // Fail Connection Method
   //  public boolean failconnect()  {
//       boolean conntx = false;
//       try{
//           Class.forName(driver);
//           this.conn = DriverManager.getConnection(urlw,username,password);
//           if (conn!=null){
//             conntx=true;
//             }       
//         }// end try
//       catch(ClassNotFoundException cnfe ){
//         System.out.println("Cannot find or load driver");
//         ArrayList list = new ArrayList<String>();
//         list.add("Error finding driver" + driver );
//         new DLException(cnfe, list);
//       }
//       catch(SQLException sqle ){
//          System.out.println("Could not connect to db");
//          ArrayList list = new ArrayList<String>();
//          list.add("Error connecting to MySQLdatabase" + "\n" + "User : " + username + "\n" + "url: " + urlw);
//           new DLException(sqle, list);
//       }
//       catch(Exception e){
//           new DLException(e);
//       } 
//      return conntx;   
//     }// end connect method
       
   // Close connection to Database
   public boolean close() {
      try{
         conn.close();
        //System.out.println("Closing MySQL Database");
      }//end try
      catch(SQLException sqle ){
         System.out.println("Could not close MySQL Database");
         ArrayList list = new ArrayList<String>();
         list.add("Error disconnecting from MySQLdatabase" +"\n"+ "User : " + username  +"\n"+ "Driver : " + driver);
         new DLException(sqle, list);
      }
      catch(Exception e){
          new DLException(e);  
      }
      return true;
   }
   
   // getData using Query & Number of Fields and converts the resultset in 2D Array (PE03)
    public ArrayList<ArrayList<String>> getData(String SQLquery, int numFields) {
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
        new DLException(sqle, listt);
      }
      catch(Exception e){
         System.out.println("getData error" + e.getMessage());
         new DLException(e);
      }
      return result;
   }//close getData
   
   // getData using Query & boolean flag and converts the resultset in 2D Array and also Adds MetaData (PE04)   
   public ArrayList<ArrayList<String>> getData(String SQLquery, boolean zenda){
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
          new DLException(sqle, list2);    
      }
      catch(Exception e){
         new DLException(e);
      }
      return result;
   }// close getData
   
   /*
        setData accepts an SQL string and returns a Boolean
        setData performs the query that was passed
        If the query runs successfully, setData  returns the number of records
        affected, otherwise any errors should return -1
   */  
    public int SetData(String SQLquery){
      int count = -1;
         try{
               Statement stmnt = conn.createStatement();
               count = stmnt.executeUpdate(SQLquery);
            }//end try
      catch(SQLException sqle){
         ArrayList eList = new ArrayList<String>();
         eList.add("Execution failure");
          new DLException(sqle, eList);
      }
      catch(Exception e){
         System.out.println("getData error" + e.getMessage());
          new DLException(e);
      }
      return count;
   }//close setData
   
 
//Prepare Method (PE06)

   /*
   Method named “prepare” that accepts an SQL string and a list of string
   values, prepares the statement, binds the values, and returns a prepared statement.
   */
  
   private PreparedStatement prepare(String query, ArrayList<String> values){
      PreparedStatement pStmnt = null;
      //String sql = sql;
      try{
         pStmnt = conn.prepareStatement(query);
       }
      catch(SQLException sqle){
         ArrayList<String> error = new ArrayList<String>();
         error.add("Error in lines");      
         new DLException(sqle,error);
      }
      return pStmnt;
  
   } 
   
   // ExecuteStmnt (PE06)
    
    /*
    Method named “executeStmt” that accepts a string and a list of string values. This method should call the “prepare” method, 
    then execute the SQL statement and return a single integer value indicating the row count.  
    */
      
      public int executeStmt(String query, ArrayList<String> values) {
      //String sql = query;
      int result = 0;
      try {
         ArrayList<String> err = new ArrayList<String>();
         err.add(""); 
         PreparedStatement pS = prepare(query, err);
         if(values.size()==1){
             pS.setInt(1, Integer.parseInt(values.get(0)));
         }
   else if(values.size()==4){
   
         pS.setString(1, values.get(0));
         pS.setString(2, values.get(1));
         pS.setInt(3, Integer.parseInt(values.get(2)));
         pS.setInt(4, Integer.parseInt(values.get(3)));
        
         }
         else if(values.size()==2){// (PE07)
            pS.setString(1, values.get(0));
            pS.setInt(2, Integer.parseInt(values.get(1)));
         }
         result = pS.executeUpdate();
         
      }
       catch(SQLException sqle){
         ArrayList<String> error = new ArrayList<String>();
         error.add("Error in lines");      
         new DLException(sqle,error);
      }
      catch (Exception e) {
         System.out.println(" Try again.");
         ArrayList<String> error = new ArrayList<String>();
         error.add("Some error");      
         new DLException(e, error);
      }
      //System.out.println(result);
      return result;
  } 
  
  //setData (PE06)
  
  /*
   Method named “setData”  that accepts an SQL string and a list of string values. 
   This returns an integer indicating how many rows were changed. 
   This setData method calls the executeStmt method.
  */
  
     public int SetData(String query, ArrayList<String> values){
      int count = -1;
         try{
              count = executeStmt(query,values);
            }//end try
      
      catch(Exception e){
         System.out.println(" Try again.");
         ArrayList<String> err = new ArrayList<String>();
         err.add("Some error");      
         new DLException(e, err);
      }
      return count;
   }//close setData
   
  //getData (PE06)
    /*
      Method named “getData”  that accepts an SQL string and a list of string values. 
      This method calls the “prepare” method, executes the statement, and returns a 2-d data structure. 
      If the query fails to run, return null.
    */
    public ArrayList<ArrayList<String>> getData(String query,ArrayList<String> values){
    ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
    try{
    ArrayList<String> str = new ArrayList<String>();
    str.add("");
    PreparedStatement prepareStmnt = prepare(query,str);
    for(int i=0; i < values.size(); i++) {
            prepareStmnt.setInt(i+1, Integer.parseInt(values.get(i)));
         }
    //Statement stmnt = conn.createStatement();
    ResultSet rs = prepareStmnt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int numCols = rsmd.getColumnCount();
    ArrayList<String> colname = new ArrayList<String>();
    ArrayList<String> colwidth = new ArrayList<String>();
      for(int i = 1; i<= numCols;i++ ){
            colname.add(rsmd.getColumnName(i));
            colwidth.add(String.valueOf(rsmd.getColumnDisplaySize(i)));
      }
     result.add(colname);
     result.add(colwidth);
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
         list2.add("Error in getData" +"\n"+ "Error in query : " + query);
          new DLException(sqle, list2);    
      }
      catch(Exception e){
         new DLException(e);
      }
      return result;
   }// close getData
   
 //Start Transaction PE07
   public void startTrans() {
      try{
         conn.setAutoCommit(false);//start Transaction
      }
      catch(Exception e){
          new DLException(e);
      }
   }
   
 //End Transaction PE07
  public void endTrans() {
      try{
         conn.commit();
         conn.setAutoCommit(true);
      }
      catch(Exception e){
         new DLException(e);
      }
   } 
   
 //Roll Back Transaction PE07
    public void rollbackTrans(){
      try{
         conn.rollback();
      }
      catch(Exception e){
          new DLException(e);
      }
   }   
  
}//close class