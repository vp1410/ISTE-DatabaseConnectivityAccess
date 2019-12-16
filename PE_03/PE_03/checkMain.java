public class checkMain {
    public static void main(String[] args) {
        //Instantiating My SqlDatabase

        MySQLDatabase mysqldb = new MySQLDatabase();
        System.out.println("Mysql connection status? : "+mysqldb.connect());

        //fetch
        Equipment e1 = new Equipment(14);
        e1.fetch(mysqldb);
        System.out.println(e1);

        // post
        Equipment e2 = new Equipment(14, "Vivek", "testing", 250);
        e2.post(mysqldb);

        // put
        Equipment e3 = new Equipment(14);
        e3.setEquipmentCapacity(500);
        e3.put(mysqldb);
        e3.fetch(mysqldb);

       // delete
        Equipment e4 = new Equipment(14);
        e4.delete(mysqldb);
        e4.fetch(mysqldb);


        System.out.println("Mysql closing status? : "+mysqldb.close());
     }//end main
}//end checkMain

