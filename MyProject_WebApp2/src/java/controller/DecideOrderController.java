/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.xml.soap.Detail;
import model.Customer;
import model.OrderDetails;
import model.Orders;
import model.Product;

public class DecideOrderController extends BaseRequiredAuthController{
    int orderId;
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String RecipientName = request.getParameter("Recipient");
        String City = request.getParameter("City");
        String Address = request.getParameter("Address");
        String PhoneNumber = request.getParameter("PhoneNumber");
        String note = request.getParameter("note");
        
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
        //insert lai
        //insert vao customer-->
        Customer c = new Customer(RecipientName,City, Address, PhoneNumber);
        //INSERT VAO ORDER
        Orders o = new Orders();
        String demo = String.valueOf(orderId)+'a';
        orderId++;
        o.setId(demo); 
        o.setNote(note);
        o.setCus(c);
        //Insert vao orderdetail
        for (Product p : products) {
            OrderDetails odetails = new OrderDetails();
            odetails.setO(o);
            odetails.setP(p);
            o.getOrderDetails().add(odetails);
        }
        CustomerDBContext db = new CustomerDBContext();
        db.insert(o);
        
        for (Cookie od : arr) {
            if (od.getName().equals("pid")) {
                od.setMaxAge(0);
                response.addCookie(od);
            }
        }
        response.sendRedirect("home");
    }
@Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
