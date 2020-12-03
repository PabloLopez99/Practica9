/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica9.model;

import javax.swing.JOptionPane;

/**
 *
 * @author pabloantoniolopezmartin
 */
public class Handler {
    private String userName;
    private String password;
    {
        userName="DIU-aed56-noi";
        password="mysql-connector-java";
    }
    public static Conn checkCredentials(String username, char[] pass) {
       try{
           Conn conn = new Conn("mozart.dis.ulpgc.es","DIU_BD",username,String.valueOf(pass));
           return conn;
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Credenciales incorrectos \n"+e.getLocalizedMessage().substring(0,e.getLocalizedMessage().indexOf("'", 30)+1 ));
           return null;
       }
    }  
}
