/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.UserBean;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "HandleUser", urlPatterns = {"/handleUser"})
public class HandleUser extends HttpServlet {

     private UserDB db;
    
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        
        db = new UserDB(dbUrl, dbUser, dbPassword);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String role =request.getParameter("role");
        if ("list".equalsIgnoreCase(action)) {
            if(role.equals("stu")){ 
                ArrayList<UserBean> user = db.queryUser();
                request.setAttribute("user", user);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListStudent.jsp");
                rd.forward(request, response);
            }else if(role.equals("tec")){
                ArrayList<UserBean> user = db.queryTec();
                request.setAttribute("user", user);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListTec.jsp");
                rd.forward(request, response);
            }else{
                ArrayList<UserBean> user = db.querySen();
                request.setAttribute("user", user);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListSen.jsp");
                rd.forward(request, response);
            }
            
        } else if ("delete".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            if (id != null) {
                db.delRecord(id);
                response.sendRedirect("ListUser.jsp");
            }
        } else if ("getEditUser".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            if (id != null) {
                UserBean user = db.queryUserByID(id);
                request.setAttribute("u", user);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/editUser.jsp");
                rd.forward(request, response);
            }
        } else if ("search".equalsIgnoreCase(action)) {
            String username = request.getParameter("username");
            if (username != null) {
                ArrayList<UserBean> user = db.queryUserByName(username);
                request.setAttribute("user", user);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listUser.jsp");
                rd.forward(request, response);
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
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
