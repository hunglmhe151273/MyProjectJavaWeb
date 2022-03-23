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
import model.Category;
import model.Product;
import model.SubCategory;
import model.region;

/**
 *
 * @author PCDELL
 */
public class CategoryDBContext extends DBContext{
      public ArrayList<Category> getCategoryWithSubs(int regionId) {
        ArrayList<Category> Categories = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n" +
                        "      ,[CategoryName]\n" +
                        "  FROM [dbo].[Category]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                    Category cate = new Category();
                    cate.setCid(rs.getInt("ID"));
                    cate.setCategoryName(rs.getString("CategoryName"));
                    SubCategoryDBContext db = new SubCategoryDBContext();
                    ArrayList<SubCategory> subs = db.getSubCategoryByCid(rs.getInt("ID"),regionId);
                    cate.setSubs(subs);
                    Categories.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Categories;
    }
    
      
    
}
