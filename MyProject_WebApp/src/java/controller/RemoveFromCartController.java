/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PCDELL
 */
public class RemoveFromCartController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // xoa phan tu khoi cookies
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("pid");
        Cookie arr[] = request.getCookies();
        String txt = "";// luu cac doi tuong can xoa
        for (Cookie o : arr) {
            if (o.getName().equals("pid")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);// xoa cookie
                response.addCookie(o);
            }
        }
        //vi cac file show doc cookies id nen tao file output moi de doc Cookies
        
        String ids[] = txt.split(",");//tao mang kieu chuoi gom co id
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (!ids[i].equals(id)) {//neu id khac thi them
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "," + ids[i];
                }
            }
        }
        if (!txtOutPut.isEmpty()) {
            Cookie c = new Cookie("pid", txtOutPut);
            c.setMaxAge(60 * 60);// set tuoi cho cookies
            response.addCookie(c);
        }
        response.sendRedirect("viewcart");
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
