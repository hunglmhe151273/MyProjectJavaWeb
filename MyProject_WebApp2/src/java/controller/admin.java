/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.auth.BaseRequiredAuthController;
import dal.CategoryDBContext;
import dal.ProductDBContext;
import dal.regionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Product;
import model.region;

/**
 *
 * @author PCDELL
 */
public class admin extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //lay session cua region
        HttpSession session = request.getSession();
        region curregion = (region) session.getAttribute("curregion");
        //nhan vao cate and sub
//        ---------------------------------------------------------------
        if(curregion==null){
            response.sendRedirect("region");
        }else{
        int srid = curregion.getRid();
        // lay ra het region
        regionDBContext context = new regionDBContext();
        ArrayList<region> regions = context.getRegions();
        //cho rid vao trong session
        curregion = context.getRegionById(srid);
        session.setAttribute("curregion", curregion);
        CategoryDBContext catedb = new CategoryDBContext();
        ArrayList<Category> cates = catedb.getCategoryWithSubs(srid);
//        --------------Product+page-----------------------
        int cateId = 1;
        String raw_cateId = request.getParameter("cateId");
        if(raw_cateId == null || raw_cateId.length() == 0 ) cateId = 1;
        else cateId = Integer.parseInt(request.getParameter("cateId"));
        
        int subId = 1;
        String raw_subId = request.getParameter("subId") ;
        if(raw_subId == null || raw_subId.length() == 0 ) subId = 0;
        else subId = Integer.parseInt(request.getParameter("subId"));
        
        int pageindex = 1;
        String raw_pageindex = request.getParameter("page") ;
        if(raw_pageindex == null || raw_pageindex.length() == 0 ) pageindex = 1;
        else pageindex = Integer.parseInt(request.getParameter("page"));

        int regionId = curregion.getRid();
        int pagesize = 6;
        //viet ham lay ra so luong san pham
        ProductDBContext productDB= new ProductDBContext();
        int count = productDB.getRowCount(regionId, cateId, subId, pageindex, pagesize);
        int totalpage = (count % pagesize ==0)?count/pagesize: (count/pagesize) +1;
        //viet ham lay ra san pham
        ArrayList<Product> products = productDB.getproductByCateIdSubId(regionId, cateId, subId, pageindex, pagesize);
        request.setAttribute("products", products);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        
//        ----------------------------------------
        request.setAttribute("curregion", curregion);
        request.setAttribute("regions", regions);
        request.setAttribute("cates", cates);
        request.setAttribute("curcateid", cateId);
        request.setAttribute("cursubid", subId);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
