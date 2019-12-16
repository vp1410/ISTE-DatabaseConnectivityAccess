/*
Date: 11/07/19
Name: Vivek Panchal
Course: Database Connectivity & Access
PE 08
*/
class VivekMain {
   
   public static void main(String args[]) {
   
      DLUser u1 = new DLUser("vivek14", "vp123");
      System.out.println("Login Status:" + u1.login());
    
      BLUser generalUser = new BLUser("vivek14", "vp123");
         
         if(generalUser.login()){
         System.out.println("*****BEFORE SWAPPING*******");
         Equipment e1 = new Equipment(568);
         e1.fetch();
         System.out.println("*****AFTER SWAPPING*******");
         e1.swap(generalUser,894);
         e1.fetch();
         System.out.println("*****FETCHED RESULT FOR SWAPPED ID*******");
         Equipment e2 = new Equipment(894);
         e2.fetch();
      }
  }
   
} 