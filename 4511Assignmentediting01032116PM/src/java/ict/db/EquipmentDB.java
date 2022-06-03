/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.BorrowRequestBean;
import ict.bean.EquipmentBean;
import ict.bean.EquipmentTypeBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JKc
 */
public class EquipmentDB {
    private String dbUrl="";
    private String dbUsername = "";
    private String dbPassword = "";
    public EquipmentDB(String url,String username, String password){
        this.dbUrl = url;
        this.dbUsername = username;
        this.dbPassword = password;
    }
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DriverManager.getConnection(dbUrl,dbUsername, dbPassword);
    }
    public boolean addEquipment(String name, String type,String dateLimit, String detail){
        boolean sucess = false;
        Connection cnnct = null;
        Statement stmnt = null;
        
        addType(type);
        String sql = "INSERT INTO `equipment` (`name`, `type`) "+
                              "VALUES ('"+name+"','"+type+"');";
        if(!dateLimit.equals("")&&!detail.equals("")){
            sql = "INSERT INTO `equipment` (`name`, `type`,`detail`,`rantDateLimit`) "+
                              "VALUES ('"+name+"','"+type+"','"+detail+"',"+dateLimit+");";
        }else if(!dateLimit.equals("")){
            sql = "INSERT INTO `equipment` (`name`, `type`,`rantDateLimit`) "+
                              "VALUES ('"+name+"','"+type+"','"+dateLimit+"');";
        }else if(!detail.equals("")){
            sql = "INSERT INTO `equipment` ( `name`, `type`, `detail`) VALUES ( "+
                              "'"+name+"','"+type+"','"+detail+"');";
        }
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute(sql);
            sucess = true;
            
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return sucess;
    }
    public ArrayList<EquipmentTypeBean> getType(){
        ArrayList<EquipmentTypeBean> typeList = new ArrayList();
        Connection cnnct = null;
        Statement stmnt = null;
        String sql = "SELECT * FROM `equipmentType`";
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            ResultSet rs = stmnt.executeQuery(sql);
            
            while(rs.next()){
                if(!rs.getBoolean("deleted")){
                    EquipmentTypeBean bean = new EquipmentTypeBean(rs.getString("id"),rs.getString("name"));
                    typeList.add(bean);
                }
            }
        }catch(SQLException ex){ex.printStackTrace();}
        catch(IOException ex){ex.printStackTrace();}
        return typeList;
    }
    
    public String addType(String type){
        
        Connection cnnct = null;
        Statement stmnt = null;
      
        if(!isSameType(type)){
            String sql = "INSERT INTO `equipmenttype` (`id`,`name`, `photoPath`) "+
                              "VALUES (NULL,'"+type+"',NULL)";
            try{
                cnnct = getConnection();
                stmnt = cnnct.createStatement();
                boolean sucess = stmnt.execute(sql);
                return ("Added new type "+type);
                
            
            }catch(SQLException ex){
                System.out.println("SQL connection error");
            }catch(IOException ex){
                System.out.println("Input error");
            }
        }else {
            return ("Same type in equipment! ");
        }
        return null;
    }
    
    
    //check is same type in type
    public boolean isSameType(String typeName){
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs= null;
        String sql = "SELECT * FROM `equipmenttype` WHERE name ='"+typeName+"';";
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            
            if(rs.next()){
                return true;
            }
        }catch(SQLException ex){ex.printStackTrace();}
        catch(IOException ex){ex.printStackTrace();}
        return false;
    }
    public ArrayList<EquipmentBean> listEquipment(String type){
        ArrayList<EquipmentBean> equipmentList = new ArrayList();
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs= null;
        String sql = "SELECT * FROM `equipment` WHERE type = '"+type+"';";
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            
            while(rs.next()){
                if(!rs.getString("deleted").equals("1")){
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String states = rs.getString("states");
                    String details = rs.getString("detail");
                    int rantDateLimit = rs.getInt("rantDateLimit");
                    boolean deleted = rs.getBoolean("deleted");
                    
                    EquipmentBean equipment = new EquipmentBean(id,name,type,states,details,rantDateLimit,deleted);
                    equipmentList.add(equipment);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return equipmentList;
    }
    public EquipmentBean getEquipment(String id){
        String sql = "SELECT * FROM equipment WHERE id = '"+id+"'";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs= null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            if(rs.next()){
                String name = rs.getString("name");
                String type = rs.getString("type");
                String states = rs.getString("states");
                String detail = rs.getString("detail");
                int rantDateLimit = rs.getInt("rantDateLimit");
                boolean isDeleted = rs.getBoolean("deleted");
                return new EquipmentBean(id,name,type,states,detail,rantDateLimit,isDeleted);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public ArrayList<EquipmentBean> getEquipment(){
        String sql = "SELECT * FROM `equipment`;";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs= null;
        ArrayList<EquipmentBean> equipmentsList = new ArrayList();
        
        try{
            
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                
                if(!rs.getBoolean("deleted")){
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String states = rs.getString("states");
                    String detail = rs.getString("detail");
                    int rantDateLimit = rs.getInt("rantDateLimit");
                    boolean isDeleted = rs.getBoolean("deleted");
                    equipmentsList.add(new EquipmentBean(id,name,type,states,detail,rantDateLimit,isDeleted)); 
                }
            
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return equipmentsList;
    }
    
    public boolean updateOrDeleteEquipment(String sql){
        boolean isFalse = true;
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            
            isFalse = stmnt.execute(sql);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isFalse;
    }
    public String getTypeName(String typeNo){
        String typeName = "";
        String sql = "SELECT * FROM `equipmenttype` WHERE id = '"+typeNo+"'";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            if(rs.next()){
                typeName = rs.getString("name");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        return typeName;
    }
    public String addNewRequest(String userID, String from, String rentDateLimit){
        Connection cnnct = null;
        Statement stmnt = null;
        
        String sql = "INSERT INTO `borrowrequest` (`userID`, `startDate`,`dateLimit`)"+
                "VALUES ('"+userID+"','"+from+"','"+rentDateLimit+"')";    // Insert a row
  
        String id = "";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmnt.getGeneratedKeys(); 
            if(rs.next()){
                java.math.BigDecimal serColVar = rs.getBigDecimal(1);
                id = serColVar.toString();
            }
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return id;
    }
    public boolean addEquipmentRentedReord(String borrowRequestID,String equipmentID){
        Connection cnnct = null;
        Statement stmnt = null;
        boolean isError = true;
        String sql = "INSERT INTO `equipmentrentingdetail` (`borrowRequestID`, `equipmentID`)"+
                "VALUES ('"+borrowRequestID+"','"+equipmentID+"')";    // Insert a row
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            int col = stmnt.executeUpdate(sql);
            if(col > 0 ){
                isError = false;
            }
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return isError;
    }
    
    public String listPersonalBorrowingRecord(String userID){
        String output = "";
        String sql = "SELECT * FROM `borrowrequest` WHERE userID = '"+userID+"'";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs = null;
        output += "<table><tr><td>ID</td><td>Start Date</td><td>Date limit</td><td>Status</td></tr>\n";

        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                String id = rs.getString("id");
                String startDate = rs.getString("startDate");
                String dateLimit = rs.getString("dateLimit");
                String states = rs.getString("result");
                output +="<tr><td><input type='button' value='"+id+"' id='watchDetailBtn'></td><td>"+startDate+"</td><td>"+dateLimit+"</td><td>";
                try{
                    if(states.equals("A"))
                        output += "Request accepted";
                    else if(states.equals("D"))
                        output += "Request rejected";
                    else if(states.equals("F"))
                        output += "Request finished";
                }catch(NullPointerException ex){
                    output += "Not processed request";
                }
                
                output += "</td></tr>\n";
            }
            output += "</table>";
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return output;
    }
    public String getRequestDetail(String requestID){
        String output = "";
        String sql = "SELECT * FROM `equipmentrentingdetail` WHERE borrowRequestID = '"+requestID+"'";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs = null;
        output += "<table><tr><td>ID</td><td>Name</td><td>Detail</td></tr>\n";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                
                String id = rs.getString("equipmentID");
                EquipmentBean currentEquipment = getEquipment(id);
                String name = currentEquipment.getName();
                String detail = currentEquipment.getDetail();
                output += "<tr><td>"+id+"</td><td>"+name+"</td><td>"+detail+"</td></tr>";
            }
            
            output += "</table>";
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return output;
    }
    public String getRequestNotProcessed(){
        String output = "";
        String sql = "SELECT * FROM `borrowrequest` WHERE result IS NULL";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs = null;
        output += "<table><tr><td>user ID</td><td>Start date</td><td>Date limit</td><td>Accept/Reject</td></tr>\n";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                
                String id = rs.getString("id");
                String user = rs.getString("userID");
                String startDate = rs.getString("startDate");
                String dateLimit = rs.getString("dateLimit");
                output += "<tr><td>"+user+"</td><td>"+startDate+"</td><td>"+dateLimit+"</td>"
                        + "<td><form action='AcceptRequest' method='POST'>"
                        + "<input type='hidden' name='requestID' value='"+id+"'>"
                        + "<input type='submit' value='accept'></form>"
                        + "<form action='RejectRequest' method='POST'>"
                        + "<input type='hidden' name='requestID' value='"+id+"'>"
                        + "<input type='submit' value='reject'>"
                        + "</form></td></tr>";
                
            }
            
            output += "</table>";
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return output;
    }
    public boolean setAcceptedRequest(String requestID){
        return setRequest(requestID,"A");
    }
    public boolean setRequest(String requestID,String states){
        Connection cnnct = null;
        Statement stmnt = null;
        String sql = "UPDATE `borrowrequest` SET result = '"+states+"' WHERE id = '"+requestID+"'";
        boolean error = true;
        System.out.print(requestID);
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            int col = stmnt.executeUpdate(sql);
            if (col > 1){
                System.out.print("Error : other column is effected");
            }else if(col == 0 ){
                System.out.print("Error : no column is effected");
            }else{
                error = false;
            }
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return error;
    }
    public boolean setRejectedRequest(String requestID) {
        return setRequest(requestID,"D");
    }
    
    public String setLendedOrReturned(String type,String borrowRequestID,String equipmentID){
        String output = "";
        Connection cnnct = null;
        Statement stmnt = null;
        String sql = "";
        if(type.equals("lend")){
            sql = "UPDATE equipmentrentingdetail SET states = 'L'"
                + " WHERE borrowRequestID ="+borrowRequestID+" AND equipmentID = "+equipmentID ;
        }else if(type.equals("return")){
            sql = "UPDATE equipmentrentingdetail SET states = 'R'"
                + " WHERE borrowRequestID ="+borrowRequestID+" AND equipmentID = "+equipmentID ;
        }
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            int col = stmnt.executeUpdate(sql);
            output = "single";
            if(type.equals("return")){
                ResultSet rs;
                sql = "SELECT * FROM equipmentrentingdetail WHERE borrowRequestID = "+borrowRequestID;
                rs = stmnt.executeQuery(sql);
                boolean allEquipmentsHasReturned = true;
                while(rs.next()){
                    String states = rs.getString("states");
                    if(!states.equals("R")){
                        allEquipmentsHasReturned = false;
                    }
                }
                
                if(allEquipmentsHasReturned){
                    sql = "UPDATE borrowrequest SET result = 'D' "
                        + "WHERE id = "+borrowRequestID;
                    stmnt.executeUpdate(sql);
                    output = "muti";
                }
            }
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return output;
    }
    public String getLateRequest(String userID){
        String output = "";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs;
        String sql = "SELECT * FROM `borrowrequest` WHERE `userID` = '"+userID+"'";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                
                String dateString = rs.getString("startDate");
                String limitString = rs.getString("dateLimit");
                String result = rs.getString("result");
                int limitNum = Integer.parseInt(limitString);
                String endDate = getEndDate(dateString,limitNum);
                try{
                    if(compareIsOverdue(endDate)&&result.equals("A")){
                        
                        output += "Late alert!! Please return the equipment\\n";
                        String id = rs.getString("id");
                        output += "Request ID : "+id+"\\n";
                        output += getLateRequestEquipments(id)+"\\n";
                        
                    }
                }catch(NullPointerException ex){}
                
            }
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return output;
    }
    public String getLateRequestEquipments(String id){
        String output = "";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs;   
        String sql = "SELECT * FROM `equipmentrentingdetail` WHERE `borrowRequestID` = '"+id+"'";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                String states = rs.getString("states");
                if(states != "R"){
                    String equipmentID = rs.getString("equipmentID");
                    String equipmentName = getEquipment(equipmentID).getName();
                    if(output.equals("")){
                        output += equipmentName;
                    }else{
                        output += ", "+equipmentName;
                    }
                    
                }
            }
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return output;
    }
    public String getLateRequestEquipmentsTable(String id){
        String output = "";
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs;   
        String sql = "SELECT * FROM `equipmentrentingdetail` WHERE `borrowRequestID` = '"+id+"'";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                String states = rs.getString("states");
                if(states != "R"){
                    String equipmentID = rs.getString("equipmentID");
                    String equipmentName = getEquipment(equipmentID).getName();
                    output += "<tr><td>"+equipmentID+"</td><td>"+equipmentName+"</td></tr>";
                    
                }
            }
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        return output;
    }
    public boolean compareIsOverdue(String date){
        boolean isLate = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Calendar nowDate = Calendar.getInstance();
        
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        isLate = c.before(nowDate);
        return isLate;
    }
    public String getEndDate(String date,int limit){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        c.add(Calendar.DATE, limit);  // number of days to add
        date = sdf.format(c.getTime());  // dt is now the new date
        return date;
    }
    public ArrayList<String> getLateBorrowRequest(){
        Connection cnnct = null;
        Statement stmnt = null;
        String sql = "SELECT * FROM `borrowrequest` WHERE result = 'A'";
        ResultSet rs ;
        ArrayList<String> borrowrequestID = new ArrayList();
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            while(rs.next()){
                String startDate = rs.getString("startDate");
                String limitString = rs.getString("dateLimit");
                int limitNum = Integer.parseInt(limitString);
                String endDate = getEndDate(startDate,limitNum);
                if(compareIsOverdue(endDate)){
                    String id = rs.getString("id");
                    borrowrequestID.add(id);
                }
            }
        }catch(SQLException ex){ex.printStackTrace();}
            catch(IOException ex){ex.printStackTrace();} 
        return borrowrequestID;
    }
    public BorrowRequestBean getBorrowRequestDetail(String id){
        Connection cnnct = null;
        Statement stmnt = null;
        String sql = "SELECT * FROM `borrowrequest` WHERE id = "+id;
        ResultSet rs ;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            rs = stmnt.executeQuery(sql);
            if(rs.next()){
                String startDate = rs.getString("startDate");
                String limitString = rs.getString("dateLimit");
                int limitNum = Integer.parseInt(limitString);
                String endDate = getEndDate(startDate,limitNum);
                String userID = rs.getString("userID");
                String result = rs.getString("result");
                BorrowRequestBean bean = new BorrowRequestBean(id,userID,startDate,limitString,result);
                bean.setEndDate(endDate);
                return bean;
            }
        }catch(SQLException ex){ex.printStackTrace();}
            catch(IOException ex){ex.printStackTrace();} 
        return null;
    }
    
    //ND
    public ArrayList<String> searchBookedDate(ArrayList<String> equipmentsId){
        ArrayList<String> limitedDate = new ArrayList();
        for(int count = 0;count<equipmentsId.size();count++){
            Connection cnnct = null;
            Statement stmnt = null;
            String sql = "SELECT borrowRequestID FROM `equipmentrentingdetail` WHERE `equipmentID` = '"+equipmentsId.get(count)+"'";
            
            try{
                cnnct = getConnection();
                stmnt = cnnct.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);
                
                while(rs.next()){
                    Statement stmnt2 = cnnct.createStatement();
                    ResultSet rs2;
                    String id = rs.getString("borrowRequestID");
                    String sqlSearchDate = "SELECT * FROM `borrowrequest` WHERE `id` = '"+id+"'";
                    rs2 = stmnt2.executeQuery(sqlSearchDate);
                    if(rs2.next()){
                        String date = rs2.getString("startDate");
                        String limit = rs2.getString("dateLimit");
                        int limitNum = Integer.parseInt(limit);
                        while(limitNum > 0){
                            
                            limitNum --;
                        }
                    }
                }
                
            }catch(SQLException ex){ex.printStackTrace();}
            catch(IOException ex){ex.printStackTrace();} 

        }
        return limitedDate;
    }
}
