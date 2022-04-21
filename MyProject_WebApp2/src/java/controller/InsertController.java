
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
import model.Product;
import model.ProductRegion;
import model.SubCategory;
import model.region;

/**
 *
 * @author PCDELL
 */
public class InsertController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SubCategoryDBContext db = new SubCategoryDBContext();
        ArrayList<SubCategory> subs = db.getAllSubCategory();
        request.setAttribute("subs", subs);
        
        regionDBContext context = new regionDBContext();
        ArrayList<region> regions = context.getRegions();
        
        request.setAttribute("regions", regions);
        request.getRequestDispatcher("Insert.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Product s = new Product();
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
        db.insert(s);
        response.getWriter().println("Insert thanh cong");
    }

}
