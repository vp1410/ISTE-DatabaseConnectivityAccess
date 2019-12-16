/*
Date: 10/13/19
Name: Rushikesh Kulkarni, Vivek Panchal
Course: Database Connectivity & Access
PE 05
*/
import java.util.*;
public class Equipment{
      //attributes in Equipment
      private int EquipID;
      private String EquipmentName;
      private String EquipmentDescription;
      private int EquipmentCapacity;
      private boolean check;
      
      public Equipment() {} //default constructor
      
      public Equipment(int equipmentId){
            this.setEquipID(equipmentId);
      } //setting the equipmentid 
      
      public Equipment(int id, String eqname, String eqDesc, int eqCap){
         this.setEquipID(id);
         this.setEqName(eqname);
         this.setEqDesc(eqDesc);
         this.setEqCap(eqCap);
      }// all attributes
      
      //Mutators 
      public void setEquipID(int id){
         this.EquipID = id;
      }
      public void setEqName(String eqname){
         this.EquipmentName= eqname;
      }
      public void setEqDesc(String eqDesc){
         this.EquipmentDescription = eqDesc;
      }
      public void setEqCap(int eqCap){
         this.EquipmentCapacity = eqCap;
      }
      //Accessors
      public int getEquipID(){
         return this.EquipID;
      }
      public String getEqName(){
         return this.EquipmentName;
      }
      public String getEqDesc(){
         return this.EquipmentDescription;
      }
      public int getEqCap(){
         return this.EquipmentCapacity;
      }
      
  /*
   Making a call to getData() to retrieve the equipment attributes.
  */
   public void fetchGood()throws DLException{
     MySQLDatabase mysqldb = new MySQLDatabase();
     System.out.println("Connection to DB :" + mysqldb.connect());
     String query = "Select * FROM Equipment WHERE EquipId = 568 " + ";";
     ArrayList<ArrayList<String>> result = mysqldb.getData(query, true);
     if(result.isEmpty()){
            this.check = false;
      }
      else{
            String dp ="";
            for(int i=0;i< result.size();i++){
                for(int j=0;j< result.get(i).size();j++){
                    String answer = "";
                    answer = result.get(i).get(j);
                    dp += result.get(i).get(j);
                    answer = String.format("%25s",answer);
                    System.out.print(answer);
            }//end for inner
            System.out.println();
            dp += "\n";
          }//end for loop outer
        }// end else        
        System.out.println("DB CLose :" + mysqldb.close());
    }
    /*
      To show a bad fetch operation with a wrong attribute in WHERE clause
   */
     public void fetchBad()throws DLException{
         MySQLDatabase mysqldb = new MySQLDatabase();
         System.out.println("Connection to DB :" + mysqldb.connect());
         String query = "Select * FROM Equipment WHERE EqId = 568 " + ";";
         ArrayList<ArrayList<String>> result = mysqldb.getData(query, true);
        if(result.isEmpty()){
            this.check = false;
        }
        else{
            String dp ="";
            for(int i=0;i< result.size();i++){
                for(int j=0;j< result.get(i).size();j++){
                    String answer = "";
                    answer = result.get(i).get(j);
                    dp += result.get(i).get(j);
                    answer = String.format("%25s",answer);
                    System.out.print(answer);

                }// end for inner
                System.out.println();
                dp += "\n";
            }//end for outer
        }        
        System.out.println("DB CLose :" + mysqldb.close());
    }
    
    // Passing a wrong url for bad connection log file
     public void badConnect () throws DLException{
         MySQLDatabase mysqldb = new MySQLDatabase();
         System.out.println("Connection to DB :" + mysqldb.failconnect());
         String query = "Select * FROM Equipment WHERE EquId = 7624 " + ";";
         ArrayList<ArrayList<String>> result = mysqldb.getData(query,true);
         if(result.isEmpty()){
            this.check = false;
        }
        else{
            String dp ="";
            for(int i=0;i< result.size();i++){
                for(int j=0;j< result.get(i).size();j++){
                    String answer = "";
                    answer = result.get(i).get(j);
                    dp += result.get(i).get(j);
                    answer = String.format("%25s",answer);
                    System.out.print(answer);

                }// end for inner
                System.out.println();
                dp += "\n";
            }//end for outer
        }        
        System.out.println("DB CLose :" + mysqldb.close());
    }
}//end class