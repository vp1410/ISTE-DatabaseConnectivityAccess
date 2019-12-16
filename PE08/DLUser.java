/*
Date: 11/07/19
Name: Vivek Panchal
Course: Database Connectivity & Access
PE 08
*/

import java.sql.*;
import java.util.ArrayList;

public class DLUser{
   private String userName;
   private String password;
   private String name;
   private String access;  
   private boolean loginCheck;
   
   public DLUser(String userName, String password){
      this.userName = userName;
      this.password = password;
   }       

/* Method used to authenticate the user.*/

  public boolean login(){
      MySQLDatabase mysqldb = new MySQLDatabase();
      boolean success = false;
      //mySQL query
      String login_query = "Select name, access FROM users"
      + " WHERE username=? AND password=md5(?);";

      ArrayList<String> values = new ArrayList<String>();
      values.add(userName);
      values.add(password);

      try{
         
         mysqldb.connect();
         
         ArrayList<ArrayList<String>> result = mysqldb.getData(login_query, values);
         ArrayList<String> row = new ArrayList<String>();
         row = result.get(2);
         
         
         if(row.size()==2){
            success = true;
           setName(row.get(0));
           setAccess(row.get(1));
          
      
         }
  
          mysqldb.close();
      }
      catch(Exception ex) {
         new DLException(ex);
      }
      return success;
   }
   
   // Accessors
   public String getUserName() {
      return userName;
   }
   
   public String getPassword() {
      return password;
   }
   
   public String getName() {
      return name;
   }
   
   public String getAccess() {
      return access;
   }
   
   public boolean getLoginStatus() {
      return loginCheck;
   }   
   
   // Mutators
   public void setUserName(String username) {
      if (username != null) {
         userName = username;
      }
   }
   
   public void setPassword(String pw) {
      if (pw != null) {
         password = pw;
      }
   }
   
   public void setName(String n) {
      name = n;
   }
   
   public void setAccess(String acc) {
      access = acc;
   }
   
   public void setLoginStatus(boolean statusCheck) {
      loginCheck = statusCheck;
   }   
   
}