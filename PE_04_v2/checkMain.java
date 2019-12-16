public class checkMain {
    public static void main(String[] args) {
        //Instantiating My SqlDatabase

        MySQLDatabase mysqldb = new MySQLDatabase();
        System.out.println("Mysql connection status? : "+mysqldb.connect());

        //Display Column name and Attributes
        Equipment e1 = new Equipment(568);
        e1.fetch(mysqldb);

        System.out.println("Mysql closing status? : "+mysqldb.close());
     
     }//end main
}//end checkMain

