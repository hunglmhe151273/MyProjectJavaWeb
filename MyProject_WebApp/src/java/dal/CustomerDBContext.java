/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Feature;
import model.OrderDetails;
import model.Orders;

/**
 *
 * @author PCDELL
 */
public class CustomerDBContext extends DBContext{
    public ArrayList<Customer> getCustomersByAcc(Account acc)
    {
        ArrayList<Customer> customers = new ArrayList<>();
         try {
            String sql = "SELECT [ID]\n" +
                            "      ,[CustomerName]\n" +
                            "      ,[Address]\n" +
                            "      ,[Country]\n" +
                            "      ,[City]\n" +
                            "      ,[State]\n" +
                            "      ,[PostalCode]\n" +
                            "      ,[PhoneNumber]\n" +
                            "      ,[createdby]\n" +
                            "  FROM [Customer] c inner join Account a on c.createdby = a.username\n" +
                            "  where a.username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, acc.getUsername());
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Customer c = new Customer();
                c.setCusId(rs.getInt("ID"));
                c.setRecipientName(rs.getString("CustomerName"));
                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
     public Customer getCustomerById(int id)
    {
        Customer c = new Customer();
         try {
            String sql = "SELECT [ID]\n" +
                        "      ,[CustomerName]\n" +
                        "      ,[Address]\n" +
                        "      ,[Country]\n" +
                        "      ,[City]\n" +
                        "      ,[State]\n" +
                        "      ,[PostalCode]\n" +
                        "      ,[PhoneNumber]\n" +
                        "      ,[createdby]\n" +
                        "  FROM [Customer]\n" +
                        "  where Id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                c.setCusId(rs.getInt("ID"));
                c.setRecipientName(rs.getString("CustomerName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
}
