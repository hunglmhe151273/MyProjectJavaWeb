
package controller;

import controller.auth.BaseRequiredAuthController;
import dal.CustomerDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Customer;
import model.Product;

/**
 *
 * @author PCDELL
 */
public class OrderDetailController extends BaseRequiredAuthController {// lam du lieu truyen len checkout

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Account account = (Account)request.getSession().getAttribute("account");
        CustomerDBContext Cdb = new CustomerDBContext();
        ArrayList<Customer> customers = Cdb.getCustomersByAcc(account);
        request.setAttribute("customers", customers);
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
        
        request.setAttribute("products", products);
        request.setAttribute("total", total);
        
        
        request.getRequestDispatcher("Checkout.jsp").forward(request, response);
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
