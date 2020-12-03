/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica9.model;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.*;

/**
 *
 * @author pabloantoniolopezmartin
 */
public class Conn {
    private HashMap<String,List<String>> bdd;
    private String pass;
    public Conn(String server,String bd, String user, String pass ) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection( "jdbc:mysql://"+server+"/"+bd+"?useSSL=true", user,
        pass);
        bdd = new HashMap<>();
        DatabaseMetaData md = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = md.getTables(null, null, "%", types);
        this.pass=pass;
        
        while (rs.next()) {
            String nombreTabla = rs.getString("TABLE_NAME");
            ResultSet rs2 = md.getColumns(null, null, nombreTabla, null);
            List<String> aux= new LinkedList<>();
            while (rs2.next()) {
            aux.add(rs.getString("TABLE_NAME") +"."+rs2.getString("COLUMN_NAME"));
            }
            bdd.put(rs.getString("TABLE_NAME"), aux);
            
            
        } 
        con.close();
    }
    public Map<String,List<String>> getResponse(){
        return bdd;
    }
    public List<String> getTables(){
        List<String> aux = new LinkedList<>();
       
        
        for (Map.Entry<String,List<String>> entry : bdd.entrySet()){
           aux.add(entry.getKey());
        }  
  
        return aux;
    } 
    public List<String> getRows(String Table){
       return bdd.get(Table);
    }

/*
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        
        Conn conn= new Conn("mozart.dis.ulpgc.es","DIU_BD","estudiante-DIU","DIU-aed56-noi");
        for (int i = 0; i < conn.getResponse().size(); i++) {
            System.out.println(i);
            
        }
    }
*/
    public String getUsername() {
        return pass;
    }
}
