/*
Date: 10/13/19
Name: Rushikesh Kulkarni, Vivek Panchal
Course: Database Connectivity & Access
PE 05
*/
public class DBMain {
    public static void main(String[] args) throws DLException{
    
    Equipment e1 = new Equipment();
    // Testing with a bad url. DLException  writes the log and report a message to the user
    e1.badConnect();
    
    /*query is passed to fetch() that adds a wrong field from equipment table. 
    An error is generated and log file is created using the same fetch(). 
    After logging, we removed the error.
    */
    
    e1.fetchBad();
    
    // For obtaining query results
    e1.fetchGood();
    
     }//end main
}//end DBMain