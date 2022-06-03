/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.EquipmentDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JKc
 */
@WebServlet(name = "AddNewBorrowingRecord", urlPatterns = {"/AddNewBorrowingRecord"})
public class AddNewBorrowingRecord extends HttpServlet {
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
        String[] equipments = request.getParameterValues("equipmentsSelected");
        String rentDateLimit = request.getParameter("dateLimit");
        String from = request.getParameter("from");
        String userID = request.getParameter("userID");
        init();
        String requestID = db.addNewRequest(userID,from,rentDateLimit);
        String output = "";
        if(requestID != ""){
            for(int count = 0 ; count<equipments.length;count ++){
                boolean error = db.addEquipmentRentedReord(requestID, equipments[count]);
                if(error){
                    output = "Fall to add equipments to request";
                }
            }
            if(output == ""){
                output = "Sucess to add equipments borrowing request.\\n"
                        +"Please remember the id ("+requestID+") of the request "+
                         "Jump to personal borrowing records page.";
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title></title>\n" +
                        "        <script src=\"jquery/jquery-1.6.js\"></script>\n"+
                        "        <script>\n");
            //java script
            out.println("           $(document).ready(function(){ "
                    + "                 alert(\""+output+"\");"
                    + "                 window.location.href = 'ListPersonalBorrowingRecord.jsp';"
                    + "             });");
            out.println("        </script>\n");
            out.println("</head>\n" +
                        "<body>\n" +
                        "    \n");
            
            out.println(output);
            out.println("</body>\n" +
                        "</html>");
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
