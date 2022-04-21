/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDBContext;
import dal.ProductDBContext;
import dal.regionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
public class ShopController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
        

        //view cart
        //cookies cart----------------------------------------------------
        // Lay cookies
        Cookie arr[] = request.getCookies();
        ArrayList<Product> cproducts = new ArrayList<>();
        // lay product theo id co the lap lai
        ProductDBContext pdb = new ProductDBContext();
        for (Cookie o : arr) {
            if (o.getName().equals("pid")) {
                String txt[] = o.getValue().split(",");
                for (String s : txt) {
                    cproducts.add(pdb.getProductByPid(s));
                }
            }
        }
        //xoa product chung va lay count
        for (int i = 0; i < cproducts.size(); i++) {
            int ccount = 1;
            int unit = cproducts.get(i).getUnitprice();
            cproducts.get(i).setTotal(ccount*unit);
            cproducts.get(i).setCount(ccount);
            for (int j = i+1; j < cproducts.size(); j++) {
                if(cproducts.get(i).getPid()== cproducts.get(j).getPid()){
                    ccount++;
                    cproducts.remove(j);
                    j--;
                    cproducts.get(i).setCount(ccount);
                    unit = cproducts.get(i).getUnitprice();
                    cproducts.get(i).setTotal(ccount*unit);
                }
            }
        }
        int total = 0;
        for (Product o : cproducts) {
            total = total + o.getCount()* o.getUnitprice();
        }
        //muc dich cuoi cung: lay ra product tu session
        request.setAttribute("cproducts", cproducts);
        request.setAttribute("total", total);
        //sort product by price
        ArrayList<Product> sortPriceproducts = pdb.SortProductByPrice(); 
        request.setAttribute("sortPriceproducts", sortPriceproducts);
        //sort product by id
        ArrayList<Product> sortIDproducts = pdb.SortLastestProduct(); 
        request.setAttribute("sortIDproducts", sortIDproducts);
        
        
        
        //--------------------------------------------------------------------------------
        request.getRequestDispatcher("Shop.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
