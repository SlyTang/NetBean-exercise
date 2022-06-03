/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.db.EquipmentDB;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JKc
 */
@WebServlet(name = "ListEquipment", urlPatterns = {"/ListEquipment"})
public class ListEquipment extends HttpServlet {
    EquipmentDB db;
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new EquipmentDB(dbUrl, dbUser, dbPassword);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String type = request.getParameter("value");
        
        out.print("<table style='border:1px solid'>");
        out.print("<tr><td>Name</td><td>States</td><td>Detail</td><td>Rant day limit</td></tr>");
        ArrayList<EquipmentBean> equipmentList = db.listEquipment(type);
        EquipmentBean currentEquipment;
        for(int count = 0 ; count < equipmentList.size();count++){
            currentEquipment = equipmentList.get(count);
            String id = currentEquipment.getId();
            String name = currentEquipment.getName();
            String detail = currentEquipment.getDetail();
            try{
                if(detail.equals("")){
                    detail = "No detail in equipment";
                }
            }catch(NullPointerException ex){
                 detail = "No detail in equipment";
            }
            
            
            String states = currentEquipment.getStates();
            int rantDateLimit = currentEquipment.getRantDateLimit();
            out.print("<tr>");
            out.print("<td ><form action='EditEquipmentServlet' method='POST' style='margin:0 auto;' >"
                    + "<input type='submit' value='"+name+"'>"
                    + "<input type='hidden' name='equipmentID' value='"+id+"'></form></td>");
            out.print("<td><span style='");
            
            if(states.equals("A")){
                out.print("background-color: green;");
            }else{
                out.print("background-color: red;");
            }
            out.print("border-radius: 50%;" +
"                display: inline-block;height: 25px;" +
"                width: 25px;></span>");
            out.print("'</td>");
            out.print("<td>"+detail+"</td>");
            out.print("<td>"+rantDateLimit+"</td>");
            out.print("</tr>");
        }   
        out.print("</table>"); 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
