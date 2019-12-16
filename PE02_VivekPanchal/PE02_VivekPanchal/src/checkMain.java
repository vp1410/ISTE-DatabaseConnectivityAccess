public class checkMain {
    public static void main(String[] args) {

        //Instantiating MS SqlServer and reporting the status
        SQLServerDatabase sqldb = new  SQLServerDatabase();
        System.out.println("MS SqlServer connection status? : "+sqldb.connect());
        System.out.println("MS SqlServer closing status? : "+sqldb.close());

        //Instantiating My SqlDatabase and reporting the status
        MySQLDatabase mysqldb = new MySQLDatabase();
        System.out.println("Mysql connection status? : "+mysqldb.connect());
        System.out.println("Mysql closing status? : "+mysqldb.close());

     }//end main
}//end checkMain

