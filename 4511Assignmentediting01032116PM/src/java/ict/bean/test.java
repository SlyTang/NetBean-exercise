/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import ict.db.EquipmentDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JKc
 */
public class test {

    public static void main(String[]args){
    String dbUrl = "jdbc:mysql://localhost:3306/4511_assignment";
    String dbUser = "root";
    String dbPassword = "";
    EquipmentDB db = new EquipmentDB(dbUrl, dbUser, dbPassword);
//    ArrayList<EquipmentTypeBean> bean= db.getType();
//    
//    ArrayList<EquipmentBean> bean = db.listEquipment("1");
//    for(int count = 0 ; count < bean.size() ; count++){
//        System.out.print(bean.get(count).getId());
//    }
//    String a = "";
//        try {
//            a = db.textGetDate();
//        } catch (SQLException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    System.out.print(a);
        System.out.print(db.getLateRequest("9"));
    }
}
