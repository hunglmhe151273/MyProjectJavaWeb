/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author PCDELL
 */
public class LoginController extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("pass");

        AccountDBContext db = new AccountDBContext();
        Account account = db.getAccount(username, password);
        if (account != null) {
            request.getSession().setAttribute("account", account);
            String remember = request.getParameter("remember");
            Cookie c_user = new Cookie("username", username);
            Cookie c_pass = new Cookie("password", password);
//            c_user.setMaxAge(3600/2);
//            c_pass.setMaxAge(3600/2);
//            response.addCookie(c_user);
//            response.addCookie(c_pass);
            
            if (remember != null) {
                c_user.setMaxAge(24 * 3600 * 7);
                c_pass.setMaxAge(24 * 3600 * 7);
                response.addCookie(c_user);
                response.addCookie(c_pass);
            }
            response.sendRedirect("home");
        } else {
            response.getWriter().println("login failed!");
        }

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