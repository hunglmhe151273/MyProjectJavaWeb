/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.OrderDetails;
import model.Orders;

/**
 *
 * @author PCDELL
 */
public class CustomerDBContext extends DBContext{
    
    public void insert(Orders o) {
        try {
            connection.setAutoCommit(false);
            //insert customer
            String sql = "INSERT INTO [Customer]\n" +
                            "           ([CustomerName]\n" +
                            "           ,[Address]\n" +
                            "           ,[City]\n" +
                            "           ,[PhoneNumber])\n" +
                            "     VALUES\n" +
                            "           (?\n" +
                            "           ,?\n" +
                            "		,?\n" +
                            "           ,?\n" +
                            "           )";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, o.getCus().getRecipientName() );
            stm.setString(2, o.getCus().getAddress() );
            stm.setString(3, o.getCus().getCity());
            stm.setString(4, o.getCus().getPhoneNumber());
            stm.executeUpdate();
            //Tim customerId tu tang
            String sql_get_id = "SELECT @@IDENTITY as sid";
            PreparedStatement stm_get_id = connection.prepareStatement(sql_get_id);
            ResultSet rs = stm_get_id.executeQuery();
            if (rs.next()) {
                o.getCus().setCusId(rs.getInt("sid"));
            }
            
            //insert order
            String sql_order = "INSERT INTO [Orders]\n" +
                                "           ([ID]\n" +
                                "           ,[CustomerID]\n" +
                                "           ,[OrderNote])\n" +
                                "     VALUES\n" +
                                "           (?\n" +
                                "           ,?\n" +
                                "           ,?\n" +
                                "               )";
            PreparedStatement stm_order = connection.prepareStatement(sql_order);
            stm_order.setString(1, o.getId());
            stm_order.setInt(2, o.getCus().getCusId());
            stm.setString(3, o.getNote());
            stm.executeUpdate();
            
            //insert odetails

            for (OrderDetails od : o.getOrderDetails() ) {
                String sql_OrderDetails = "INSERT INTO [OrderDetails]\n" +
                                    "           ([OrderID]\n" +
                                    "           ,[ProductID]\n" +
                                    "           ,[Quantity])\n" +
                                    "     VALUES\n" +
                                    "           (?\n" +
                                    "           ,?\n" +
                                    "           ,?\n" +
                                    "		   )";
                PreparedStatement stm_OrderDetails = connection.prepareStatement(sql_OrderDetails);
                stm_OrderDetails.setString(1, od.getO().getId());
                stm_OrderDetails.setInt(2, od.getP().getPid());
                stm_OrderDetails.setInt(3, od.getQuantity());
                stm_OrderDetails.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
