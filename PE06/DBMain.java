/*
Date: 10/27/19
Name: Vivek Panchal
Course: Database Connectivity & Access
PE 05
*/
public class DBMain {
    public static void main(String[] args) throws DLException{
    
   Equipment e1 = new Equipment(568);
   e1.fetch();
   e1.setEquipmentCapacity(550);
   e1.put();
   e1.fetch(); 
  
//Test cases for post,insert and delete  
    // Equipment e2 = new Equipment(143, "Vivekk", "testing1", 2500);
//     e2.post();
//     e2.fetch();
//     e2.delete();
//     e2.fetch();
//        

  
     }//end main
}//end DBMain