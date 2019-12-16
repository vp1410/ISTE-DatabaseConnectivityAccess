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
//     public void fetch(MySQLDatabase mysqldb) {
// 
//         int eId = this.getEquipmentId();
//         String query = "Select EquipId, EquipmentName, EquipmentDescription,EquipmentCapacity FROM Equipment WHERE EquipId = " + eId + ";";
//         ArrayList<ArrayList<String>> result = mysqldb.getData(query, 4);
//         if (result.isEmpty()) {
//             this.status = false;
//         } else {
//             //storing values
//             ArrayList<String> row = result.get(0);
//             this.setEquipmentId(Integer.parseInt(row.get(0)));
//             this.setEquipmentName(row.get(1));
//             this.setEquipmentDesc(row.get(2));
//             this.setEquipmentCapacity(Integer.parseInt(row.get(3)));
//             this.status = true;
//         }
// 
//     }

  /*
   Making a call to getData() to retrieve the equipment attributes.
   */    
  
 public void fetch(){
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect();   
        int eId = this.getEquipmentId();
        String query = "Select EquipId, EquipmentName, EquipmentDescription,EquipmentCapacity FROM Equipment WHERE EquipId = " + eId + ";";
        
        ArrayList<ArrayList<String>> result = mysqldb.getData(query, true);
        conn = mysqldb.close(); 
        if(result.isEmpty()){
            this.status = false;
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

                }
                System.out.println();
                dp += "\n";
            }

        }
    }

    /*
        post will insert the object's attribute values into the database as a new record
     */

    public void post() {
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect();
         int equipmentId = this.getEquipmentId();
        String q = "Insert into Equipment Values(";
        q += String.valueOf(this.getEquipmentId()) + "," +"\"" + this.getEquipmentName() + "\"" + ",";
        q += "\"" + this.getEquipmentDesc() + "\"" + "," + String.valueOf(this.getEquipmentCapacity()) + ");";
        int result = mysqldb.setData(q);
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
        String q1 = "Select EquipId, EquipmentName, EquipmentDescription, EquipmentCapacity FROM Equipment WHERE EquipId = " + eId + ";";
        ArrayList<ArrayList<String>> result1 = mysqldb.getData(q1, 4);
        if (result1.isEmpty()) {
            this.status = false;
        } else {
            ArrayList<String> row = result1.get(0);
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
        q2 += "Set EquipmentName = " + "\"" + this.getEquipmentName() + "\"" + ", EquipmentDescription = " + "\"" + this.getEquipmentDesc() + "\"";
        q2 += ", EquipmentCapacity = " + this.getEquipmentCapacity() + " Where EquipId = " + this.getEquipmentId() + ";";
        int result2 = mysqldb.setData(q2);
        conn = mysqldb.close();
        System.out.println("Number of rows affected = " + result2);
    }

    /*
        delete will remove from the database any data corresponding to the object's equipmentId
     */
    public void delete() {
        MySQLDatabase mysqldb = new MySQLDatabase();
        boolean conn = mysqldb.connect(); 
        String q3 = "Delete from Equipment Where  EquipId = " + this.getEquipmentId() + ";";
        int result = mysqldb.setData(q3);
        conn = mysqldb.close();
        System.out.println("Rows deleted = " + result);
    }

    public String toString() {

        String result = "EquipId: " + this.getEquipmentId() + "Equipname: " + this.getEquipmentName() + "Equipdesc: " + this.getEquipmentDesc() + "\n";
        result += "Equipcapacity" + this.getEquipmentCapacity();
        //System.out.println("Status: "+ status);
        if (this.status == false) {
            result = "--No Records found for given ID--";
        }

        return result;
    }
    
    
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

}//end Equipment



