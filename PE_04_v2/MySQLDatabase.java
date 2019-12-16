/**
 * Name: Vivek Panchal
 * Course: 722 - Database connectivity and access
 * PE: #4 - Metadata
 * IDE: jGRASP
 * Date: 10/03/2019
 */

import java.sql.*;
import java.util.ArrayList;

public class MySQLDatabase {

    //Connection attributes
    private String userid;
    private String pw;
    private String dbname;
    private String driver;
    private Connection connection;

    //Constructor
    public MySQLDatabase(){
        this.setdbname("jdbc:mysql://localhost/travel?useSSL=false");
        this.setuserid("root");
        this.setpw("student");
        this.setDriver("com.mysql.jdbc.Driver");
     }//end constructor

    //Connect method
     public boolean connect(){
        boolean connectn = false;
        try{
            Class.forName(driver);
            this.connection = DriverManager.getConnection(dbname,userid,pw);//using getconnection() method to establish connection with database credentials.
            if(connection!= null){
                connectn = true;

            }
        }
        catch(ClassNotFoundException ce){
            System.out.println(ce.getMessage());//since Class.forName() method is used, if requested class is not found it will throw an error.
        }
        catch(SQLException se){
            System.out.println(se.getMessage());// It will give JDBC driver's error message
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return connectn;
    }//end connect

    /*Close Method - It will check if driver is connected, if driver is connected then closeSuccess will return true else, as the driver is not
      connected, then closeStatus will return false since no connection was established
    */
    public boolean close(){
        boolean closeStatus = false;
        try{
            if(connection!=null) {
                connection.close();
                closeStatus = true;
            }
            else
                closeStatus = false;
        }
        catch(SQLException se){
            System.out.println("Cannot close database");
        }
        return closeStatus;
    }//end close

    // Mutator for driver
    public void setDriver(String driver) {

        this.driver = driver;
    }

    public void setdbname(String db) {

        this.dbname = db;
    }
    public void setuserid(String name) {

        this.userid = name;
    }
    public void setpw(String password) {

        this.pw = password;
    }
    /*
        getData accepts SQL String, performs query that was passed in & converts resultset in 2D array
     */
    public ArrayList<ArrayList<String>> getData(String q, int n) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(q);
            while(rs.next()) {//get next row
                ArrayList<String> row = new ArrayList<String>();
                for(int i = 1; i <= n; i++) { // each column
                    row.add(rs.getString(i));
                }
                result.add(row);
            }
        }
        catch (SQLException se) { }
        catch (Exception e) { }
        return result;

    }//end getData
    
 /*
  getData() accepts an SQL String and returns 2d Arraylist but here it also accepts a boolean value indicating
  column name and width. If the boolean value is true the first row will be field names and second row will be field widths.
*/   
    
        public ArrayList<ArrayList<String>> getData(String q, boolean flag) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(q);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();//Returns the number of columns in this ResultSet object.
            ArrayList<String> meta = new ArrayList<String>();
            ArrayList<String> width = new ArrayList<String>();
            for(int i= 1;i<=numCols;i++) 
               {
                  meta.add(rsmd.getColumnName(i));//Get the designated column's name.
                  width.add(String.valueOf(rsmd.getColumnDisplaySize(i)));//Indicates the designated column's normal maximum width in characters.
               }
            if(flag == true){
              result.add(meta);
              result.add(width);
            }
            
            while(rs.next()) {//get next row
                ArrayList<String> row = new ArrayList<String>();
                for(int i = 1; i<=numCols; i++) { // each column
                    row.add(rs.getString(i));
                }
                result.add(row);
            }
     }
        catch (SQLException se) { }
        catch (Exception e) { }
        return result;

    }//end getData
    
    
    
      /*
        setData accepts an SQL string and returns a Boolean
        setData performs the query that was passed
        If the query runs successfully, setData  returns the number of records
        affected, otherwise any errors should return -1
     */

    public int setData(String query) {

        int setDataCount = -1;

        try {
            Statement statement = connection.createStatement();//slide
            setDataCount = statement.executeUpdate(query);
        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return setDataCount;
    }
}// end MySqlDatabase

