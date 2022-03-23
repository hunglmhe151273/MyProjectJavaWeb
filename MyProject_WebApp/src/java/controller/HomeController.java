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
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        region curregion = (region) session.getAttribute("curregion");
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
        
        //cookies cart----------------------------------------------------
        // Lay cookies
        Cookie arr[] = request.getCookies();
        ArrayList<Product> products = new ArrayList<>();
        // lay product theo id co the lap lai
        ProductDBContext pdb = new ProductDBContext();
        for (Cookie o : arr) {
            if (o.getName().equals("pid")) {
                String txt[] = o.getValue().split(",");
                for (String s : txt) {
                    products.add(pdb.getProductByPid(s));
                }
            }
        }
        //xoa product chung va lay count
        for (int i = 0; i < products.size(); i++) {
            int count = 1;
            int unit = products.get(i).getUnitprice();
            products.get(i).setTotal(count*unit);
            products.get(i).setCount(count);
            for (int j = i+1; j < products.size(); j++) {
                if(products.get(i).getPid()== products.get(j).getPid()){
                    count++;
                    products.remove(j);
                    j--;
                    products.get(i).setCount(count);
                    unit = products.get(i).getUnitprice();
                    products.get(i).setTotal(count*unit);
                }
            }
        }
        int total = 0;
        for (Product o : products) {
            total = total + o.getCount()* o.getUnitprice();
        }
        //muc dich cuoi cung: lay ra product tu session
        request.setAttribute("cproducts", products);
        request.setAttribute("total", total);
        //--------------------------------------------------------------------------------
        request.setAttribute("curregion", curregion);
        request.setAttribute("regions", regions);
        request.setAttribute("cates", cates);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
        }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
