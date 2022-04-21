/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.auth.BaseRequiredAuthController;
import dal.ProductDBContext;
import dal.SubCategoryDBContext;
import dal.regionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;
import model.ProductRegion;
import model.SubCategory;
import model.region;

/**
 *
 * @author PCDELL
 */
public class UpdateController extends BaseRequiredAuthController {



    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDBContext db = new ProductDBContext();
        Product p = db.getproductById(id);
        
        SubCategoryDBContext sdb = new SubCategoryDBContext();
        ArrayList<SubCategory> subs = sdb.getAllSubCategory();
        
        regionDBContext context = new regionDBContext();
        ArrayList<region> regions = context.getRegions();
        
        request.setAttribute("product", p);
        request.setAttribute("regions", regions);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("Update.jsp").forward(request, response);
        
    }


    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Product s = new Product();
        s.setPid(Integer.parseInt(request.getParameter("pid")));
        s.setPname(request.getParameter("Productname"));
        s.setUnitprice(Integer.parseInt(request.getParameter("Unitprice")));
        SubCategory sub = new SubCategory();
        sub.setScid(Integer.parseInt(request.getParameter("subid")));
        s.setSub(sub);
        s.setImg(request.getParameter("Image"));
        
        
        String[] rids = request.getParameterValues("regionid");
        if(rids!=null)
        {
            for (String rid : rids) {//duyet chuoi cac region
                ProductRegion pr = new ProductRegion();
                pr.setD(s); // gan product vao trung gian
                region c = new region();
                c.setRid(Integer.parseInt(rid));//thong tin region
                pr.setR(c);//gan cert vao trung gian
                s.getProductRegion().add(pr);//gan lai trung gian vao product
            }
        }
        
        ProductDBContext db = new ProductDBContext();
        db.update(s);
        response.getWriter().println("Update thanh cong");
        
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
