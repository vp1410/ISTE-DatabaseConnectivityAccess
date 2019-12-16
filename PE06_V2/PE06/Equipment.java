/*
Date: 10/31/19
Name: Vivek Panchal
Course: Database Connectivity & Access
PE 07
*/


import java.util.*;

public class Equipment {
    private int equipmentid;
    private String equipmentName;
    private String equipmentDesc;
    private int equipmentCapacity;
    private boolean status;

    public Equipment() {
    }// end default constructor

    public Equipment(int equipId) {
        this.setEquipmentId(equipId);

    }//end Equipment

    public Equipment(int id, String name, String desc, int capacity) {
        this.setEquipmentId(id);
        this.setEquipmentName(name);
        this.setEquipmentDesc(desc);
        this.setEquipmentCapacity(capacity);

    }

   /*
    fetch will use the object's equipmentId attribute and the Db class
    getData method to retrieve the database values for that particular equipmentId
    and update the object’s attributes
    */
     public void fetch() {
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect(); 
        int eId = this.getEquipmentId();
        String query = "Select EquipId, EquipmentName, EquipmentDescription,EquipmentCapacity FROM Equipment WHERE EquipId =?";
        ArrayList<String> q_new = new ArrayList<String>();
        q_new.add(String.valueOf(getEquipmentId()));
         ArrayList<ArrayList<String>> result = mysqldb.getData(query,q_new);
         conn = mysqldb.close(); 
         if (result.isEmpty()) {
             this.status = false;
         } 
         
         else {
             //storing values
             this.status = true;
             if(result.size() > 2)
                     {
             ArrayList<String> row = result.get(2);
             //System.out.print(row);
             this.setEquipmentId(Integer.parseInt(row.get(0)));
             this.setEquipmentName(row.get(1));
             this.setEquipmentDesc(row.get(2));
             this.setEquipmentCapacity(Integer.parseInt(row.get(3)));
                     }
             else{
               this.status = false;
               }
                System.out.println(toString());
             }
 
     }

  /*
   Making a call to getData() to retrieve the equipment attributes.
   */    
  
//  public void fetch(){
//         MySQLDatabase mysqldb = new MySQLDatabase();
//         boolean conn = mysqldb.connect();   
//         int eId = this.getEquipmentId();
//         String query = "Select EquipId, EquipmentName, EquipmentDescription,EquipmentCapacity FROM Equipment WHERE EquipId=?";
//        
//         ArrayList<ArrayList<String>> result = mysqldb.getData(query, true);
//         conn = mysqldb.close(); 
//         if(result.isEmpty()){
//             this.status = false;
//         }
//         else{
//             String dp ="";
// 
//             for(int i=0;i< result.size();i++){
//                 for(int j=0;j< result.get(i).size();j++){
//                     String answer = "";
//                     answer = result.get(i).get(j);
//                     dp += result.get(i).get(j);
//                     answer = String.format("%25s",answer);
//                     System.out.print(answer);
// 
//                 }
//                 System.out.println();
//                 dp += "\n";
//             }
// 
//         }
//     }

    /*
        post will insert the object's attribute values into the database as a new record
     */

    public void post() {
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect();
        int equipmentId = this.getEquipmentId();
        String q = "Insert into Equipment(EquipmentName"; 
               q += ", EquipmentDescription, EquipmentCapacity, EquipId)";
               q += " Values(?,?,?,?);";
          ArrayList<String> q_new_post = new ArrayList<String>();
         q_new_post.add(getEquipmentName());
         q_new_post.add(getEquipmentDesc());
         q_new_post.add(String.valueOf(getEquipmentCapacity()));
         q_new_post.add(String.valueOf(getEquipmentId()));
        int result = mysqldb.SetData(q,q_new_post);
        conn = mysqldb.close(); 
        System.out.println("Rows affected = " + result);
    }

    /*
        put will update the database values, for that object's equipmentId, using all the
        object's attribute values
     */

    public void put() {
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect(); 
        int eId = this.getEquipmentId();
        String q1 = "Select EquipId, EquipmentName, EquipmentDescription, EquipmentCapacity FROM Equipment WHERE EquipId =?;";
        ArrayList<String> q_new_put = new ArrayList<String>();
        q_new_put.add(String.valueOf(getEquipmentId()));
        ArrayList<ArrayList<String>> result1 = mysqldb.getData(q1, q_new_put);
        if (result1.isEmpty()) {
            this.status = false;
        } else {
            ArrayList<String> row = result1.get(2);
            String prevName = row.get(1);
            String prevDesc = row.get(2);
            int prevCapacity = Integer.parseInt(row.get(3));

            if (this.getEquipmentName() == null) {
                this.setEquipmentName(prevName);
            }
            if (this.getEquipmentDesc() == null) {
                this.setEquipmentDesc(prevDesc);
            }
            if (this.getEquipmentCapacity() == 0) {
                this.setEquipmentCapacity(prevCapacity);
            }
        }
        String q2 = "Update Equipment ";
         q2 += "Set EquipmentName=?";
         q2 += ", EquipmentDescription=?";
         q2 += ", EquipmentCapacity=?";
         q2 += " Where EquipId=?;";
         ArrayList<String> q = new ArrayList<String>();
         q.add(getEquipmentName());
         q.add(getEquipmentDesc());
         q.add(String.valueOf(getEquipmentCapacity()));
         q.add(String.valueOf(getEquipmentId()));
        int new_result = mysqldb.SetData(q2,q);
        conn = mysqldb.close();
        System.out.println("Number of rows affected = " + new_result);
    }

    /*
        delete will remove from the database any data corresponding to the object's equipmentId
     */
    public void delete() {
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect(); 
        String q3 = "Delete from Equipment Where  EquipId =?;";
        ArrayList<String> q_new_del = new ArrayList<String>();
        q_new_del.add(String.valueOf(getEquipmentId()));
        int result = mysqldb.SetData(q3,q_new_del);
        conn = mysqldb.close();
        System.out.println("Rows deleted = " + result);
    }
    
 //Swap Method PE07
   public void swap(int swapVal){
     int result1 = 0;
     int result2 = 0;
      try{
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect(); 
         mysqldb.startTrans();
         //this.getEquipmentId();
         String nameQuery = "Select EquipmentName From Equipment"
         +" Where EquipId =?;";
        //Retrieve name of ID passed 
         ArrayList curntVal = new ArrayList<String>();
         curntVal.add(getEquipmentId() +"");
         ArrayList<ArrayList<String>> curRet = mysqldb.getData(nameQuery, curntVal);
         ArrayList<String> curntRes = curRet.get(2);
         String currentName = curntRes.get(0);
         
        //Retrieve name of SwapId passed
         ArrayList swapValue = new ArrayList<String>();
         swapValue.add(swapVal +"");
         ArrayList<ArrayList<String>> swapRet = mysqldb.getData(nameQuery, swapValue);
         ArrayList<String> swapRes = swapRet.get(2);
         String swappedName = swapRes.get(0);
         
         //Update
         String update_query = "Update Equipment Set EquipmentName = ?"
         + " Where EquipId = ?;";
         ArrayList swapName = new ArrayList<String>();
         swapName.add(swappedName);
         swapName.add(String.valueOf(getEquipmentId()));
         
         //Get Number of rows affected
         result1 = mysqldb.SetData(update_query, swapName);
         
         
         //Change the name of EquipId Passed
         ArrayList updatedName = new ArrayList<String>();
         updatedName.add(currentName);
         updatedName.add(String.valueOf(swapVal));
         //Get Number of rows affected
         result2 = mysqldb.SetData(update_query, updatedName);
          
        if(result1 + result2 ==2){
          try{
           mysqldb.endTrans();
            }
         catch(Exception e){
         new DLException(e);
             }
      }
        
       else{
         try{ 
             mysqldb.rollbackTrans();
             }
         catch(Exception e){
         new DLException(e);
                }
       }
        conn = mysqldb.close();
         }
       catch(Exception e){
         new DLException(e);
      }
         }   

   //  public String toString() {
// 
//         String result = "EquipId: " + this.getEquipmentId() + "Equipname: " + this.getEquipmentName() + "Equipdesc: " + this.getEquipmentDesc() + "\n";
//         result += "Equipcapacity" + this.getEquipmentCapacity();
//         //System.out.println("Status: "+ status);
//         if (this.status == false) {
//             result = "--No Records found for given ID--";
//         }
// 
//         return result;
//     }
    
    
//Mutators
    public void setEquipmentId(int id){
        this.equipmentid = id;
    }
    public void setEquipmentName(String name){
        this.equipmentName = name;
    }
    public void setEquipmentDesc(String desc){
        this.equipmentDesc = desc;
    }
    public void setEquipmentCapacity(int capacity){
        this.equipmentCapacity = capacity;
    }

    //Accessors
     public int getEquipmentId() {

        return this.equipmentid;
     }

    public String getEquipmentName() {

        return this.equipmentName;
    }

    public String getEquipmentDesc() {

        return this.equipmentDesc;
    }

    public int getEquipmentCapacity() {

        return this.equipmentCapacity;
    }
    
public String toString() {
   
      String output = String.format("%-21s: %s%n%-21s: %s%n%-21s: %s%n%-21s: %s",
                      "EquipID", getEquipmentId(), "EquipmentName", getEquipmentName(), 
                     "EquipmentDescription", getEquipmentDesc(), "EquipmentCapacity", 
                      getEquipmentCapacity());
      if(this.status == false) {
         output = "No Records found for given ID.";
      }
      return output;
   }

}//end Equipment



