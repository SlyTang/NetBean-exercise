/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.EquipmentDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JKc
 */
@WebServlet(name = "PrintEditedEquipmentResult", urlPatterns = {"/PrintEditedEquipmentResult"})
public class PrintEditedEquipmentResult extends HttpServlet {
    EquipmentDB db;
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new EquipmentDB(dbUrl, dbUser, dbPassword);
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String id = request.getParameter("equipmentID");
            String name = request.getParameter("newName");
            String type = request.getParameter("newType");
            String detail = request.getParameter("newDetail");
            String rantDateLimit = request.getParameter("newRantDateLimit");
            String states = request.getParameter("states");
            String movement = request.getParameter("movement");
            String sql = "";
           
            if(movement.equals("Delete")){
                sql = "UPDATE equipment SET `deleted` = '1' WHERE `equipment`.`id` = '"+id+"';";
            }else if(movement.equals("Edit")){
                sql = "UPDATE `equipment` SET `name`=\""+name+"\",`type`="+type+",`detail`=\""+detail+"\", `states`=\""+states+"\",`rantDateLimit`="+rantDateLimit+";";
            }
            
            init();
            boolean isFalse = db.updateOrDeleteEquipment(sql);
            if(movement.equals("Delete")){
                if(!isFalse){
                    out.print("Deleted equipment name: "+name+" id: "+id+"<br/>");
                    out.print("<a href='ListEquipmentType.jsp'>Jump to list page in 5 seconds</a>");
                }else {
                    out.print("Update error\n");
                    out.print("<a href='ListEquipmentType.jsp'>Jump to list page in 5 seconds</a>");
                    
                }
            }
            else if(movement.equals("Edit")){
                if(!isFalse){
                    out.print("Updated equipment "+name+"<br/>");
                    out.print("<a href='ListEquipmentType.jsp'>Jump to list page in 5 seconds</a>");
                }else {
                    out.print("Update error\n");
                    out.print("<a href='ListEquipmentType.jsp'>Jump to list page in 5 seconds</a>");
                }
            }
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
