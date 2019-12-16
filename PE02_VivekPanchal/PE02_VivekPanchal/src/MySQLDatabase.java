import java.sql.*;

public class MySQLDatabase {

    //Connection attributes
    private String userid;
    private String pw;
    private String dbname;
    private String driver;
    private Connection connection;

    //Constructor
    public MySQLDatabase(){
        this.dbname = "jdbc:mysql://simon.ist.rit.edu/networx_ser";
        this.userid = "330User";
        this.pw = "330Password";
        this.driver = "com.mysql.jdbc.Driver";
     }//end constructor

    //Connect method
     public boolean connect(){
        boolean connectn = false;
        try{
            Class.forName(driver);
            this.connection = DriverManager.getConnection(dbname,userid,pw);//using getconnection() method to establish connection with database credentials.
            if(connection!= null){
                connectn = true;
                System.out.println("-------Connected to MySqlDatabase successfully!----");
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
}// end MySqlDatabase

