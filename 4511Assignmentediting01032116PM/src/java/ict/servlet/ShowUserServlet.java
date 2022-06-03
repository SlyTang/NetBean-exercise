/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.UserInfo;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ShowUserServlet", urlPatterns = {"/ShowUserServlet"})
public class ShowUserServlet extends HttpServlet {
    UserDB db = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new UserDB(dbUrl, dbUser, dbPassword);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          
            out.println("<h1>Servlet ShowUserServlet at " + request.getContextPath() + "</h1>");
            
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
        init();
        ArrayList<UserInfo> users = null;
        String type = "";
        String requiringUserType = request.getParameter("submit");
        if(requiringUserType.equals("stu")){
            users = db.getUser("stu");
            type = "Student";
        }else if(requiringUserType.equals("tec")){
            users = db.getUser("tec");
            type = "Technicon";
        }else if(requiringUserType.equals("sen")){
            users = db.getUser("sen");
            type = "Sen technicon";
        }
        
        try{
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<fieldset><legend>"+type+":</legend>");
            for(int count = 0; count < users.size() ; count ++){
                UserInfo currentUser = users.get(count);
                String userID = currentUser.getUserID();
                String username = currentUser.getUsername();
                out.println("<div name='userInfo'>");
                out.println("<form>");
                out.println("<input type='hidden' name='userID' value="+userID+">");
                out.println("<input type='submit' value='"+username+"'>");
                out.println("</form>");
                out.println("</div>");
            }
            out.println("</filedset>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Show user";
    }// </editor-fold>

}
