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
import model.SubCategory;
import model.region;

/**
 *
 * @author PCDELL
 */
public class SubCategoryDBContext extends DBContext{
    public ArrayList<SubCategory> getSubCategoryByCid(int cid, int regionId) {
        ArrayList<SubCategory> subs = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n" +
                        "      ,[SubCategoryName]\n" +
                        "  FROM [SubCategory]\n" +
                        "  where [CategoryID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubCategory s = new SubCategory();
                s.setScid(rs.getInt("ID"));
                s.setSubName(rs.getString("SubCategoryName"));
                ProductDBContext db = new ProductDBContext();
                s.setProd(db.getproductBySubId(rs.getInt("ID"),regionId));
                subs.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }
    public ArrayList<SubCategory> getAllSubCategory() {
        ArrayList<SubCategory> subs = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n" +
                        "      ,[SubCategoryName]\n" +
                        "  FROM [SubCategory]\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubCategory s = new SubCategory();
                s.setScid(rs.getInt("ID"));
                s.setSubName(rs.getString("SubCategoryName"));
                subs.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }
    public SubCategory getSubById(int id) {
        SubCategory subs = new SubCategory();
        try {
            String sql = "SELECT [ID]\n" +
                        "      ,[SubCategoryName]\n" +
                        "  FROM [SubCategory]\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubCategory s = new SubCategory();
                s.setScid(rs.getInt("ID"));
                s.setSubName(rs.getString("SubCategoryName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }
}
