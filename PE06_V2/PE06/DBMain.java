/*
Date: 10/31/19
Name: Vivek Panchal
Course: Database Connectivity & Access
PE 07
*/
public class DBMain {
    public static void main(String[] args) throws DLException{
  
  //Test Cases  
   Equipment e1 = new Equipment(568);
   e1.fetch();
   e1.swap(894);
   e1.fetch();
   Equipment e2 = new Equipment(894);
   e2.fetch();
   
   // e1.setEquipmentCapacity(550);
//    e1.put();
//    e1.fetch(); 
  
//Test cases for post,insert and delete  
    // Equipment e2 = new Equipment(143, "Vivekk", "testing1", 2500);
//     e2.post();
//     e2.fetch();
//     e2.delete();
//     e2.fetch();
//        

  
     }//end main
}//end DBMain