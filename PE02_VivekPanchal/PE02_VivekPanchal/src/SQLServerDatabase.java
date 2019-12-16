/**
 * Name: Vivek Panchal
 * Course: 722 - Database connectivity and access
 * Lab: #2
 * Date: 09/18/2019
 */
import java.sql.*;
public class SQLServerDatabase{

    //Connection attributes
    private String dbname;
    private String driver;
    private Connection connection;

    //Constructor
    public SQLServerDatabase() {
        this.dbname = "jdbc:sqlserver://theodore.ist.rit.edu;databaseName=Jobs;user=330User;password=330Password";
        this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    }//end constructor

    // Connect method
    public boolean connect (){
        boolean connectn = false;
        try{
            Class.forName(driver);
            this.connection = DriverManager.getConnection(dbname);//using getconnection() method to establish connection with database credentials.
            if(connection!= null){
                connectn = true;
                System.out.println("-------Connected to  MS SqlServer successfully!----");
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
    }

    /*Close Method - It will check if driver is connected, if driver is connected then closeSuccess will return true else, as the driver is not
      connected, then closeSuccess will return false since no connection was established
    */
    public boolean close(){
        boolean closeSuccess = false;
         try{
            if(connection!=null) {
                connection.close();
                closeSuccess = true;
            }
            else
            closeSuccess = false;
            }
        catch(SQLException se){
           System.out.println("Cannot close database");
            }
         return closeSuccess;
    }//end close

}//end SQLServerDatabase
