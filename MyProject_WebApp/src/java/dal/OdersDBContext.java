package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetails;
import model.Orders;

public class OdersDBContext extends DBContext {
    public void insert(Orders o) {
        try {
            connection.setAutoCommit(false);
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
            stm_order.setString(3, o.getNote());
            stm_order.executeUpdate();
            
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
            Logger.getLogger(OdersDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OdersDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OdersDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
