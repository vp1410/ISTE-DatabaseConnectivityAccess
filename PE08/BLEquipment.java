/*
save() method in the business layer that tests the user’s access. Only Editors and
Admins should be allowed to use the save() method.
*/

public class BLEquipment extends Equipment{

    public boolean save(BLUser obj){
      if(obj.getAccess().equals("Admin") || obj.getAccess().equals("Editor")){
          return true;
      }
      else{
         System.out.println("Unauthorized User");
         return false;
      }
   }
}