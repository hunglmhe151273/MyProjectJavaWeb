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
import model.Product;
import model.ProductRegion;
import model.SubCategory;
import model.region;

/**
 *
 * @author PCDELL
 */
public class ProductDBContext extends DBContext{
    
    public ArrayList<Product> SortLastestProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP(10)\n" +
                            "		[ID]\n" +
                            "      ,[ProductName]\n" +
                            "      ,[UnitPrice]\n" +
                            "      ,[SubCategoryID]\n" +
                            "      ,[img]\n" +
                            "  FROM [Product]\n" +
                            "  order by id desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product d = new Product();
                d.setPid(rs.getInt("ID"));
                d.setPname(rs.getString("ProductName"));
                d.setUnitprice(rs.getInt("UnitPrice"));
                d.setImg(rs.getString("img"));
                products.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public ArrayList<Product> SortProductByPrice() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP(10)\n" +
                            "		[ID]\n" +
                            "      ,[ProductName]\n" +
                            "      ,[UnitPrice]\n" +
                            "      ,[SubCategoryID]\n" +
                            "      ,[img]\n" +
                            "  FROM [Product]\n" +
                            "  order by UnitPrice asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product d = new Product();
                d.setPid(rs.getInt("ID"));
                d.setPname(rs.getString("ProductName"));
                d.setUnitprice(rs.getInt("UnitPrice"));
                d.setImg(rs.getString("img"));
                products.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public ArrayList<Product> getproductBySearch(String pname) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n" +
                            "      ,[ProductName]\n" +
                            "      ,[UnitPrice]\n" +
                            "      ,[SubCategoryID]\n" +
                            "      ,[img]\n" +
                            "  FROM [Product]\n" +
                            "  where ProductName like '%' + ? + '%'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pname);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product d = new Product();
                d.setPid(rs.getInt("ID"));
                d.setPname(rs.getString("ProductName"));
                d.setUnitprice(rs.getInt("UnitPrice"));
                d.setImg(rs.getString("img"));
                products.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    //---------------
    public ArrayList<Product> getproductBySubId(int SubId,int regionId) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP (4) [ID]\n" +
                            "      ,[ProductName]\n" +
                            "      ,[UnitPrice]\n" +
                            "      ,[SubCategoryID]\n" +
                            "      ,[img]\n" +
                            "	  , r.[rid]\n" +
                            "  FROM [Product] p inner join ProductRegion pr on p.id = pr.pid\n" +
                            "  inner join Region r on r.rid = pr.rid\n" +
                            "  where SubCategoryID = ?  and  r.rid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, SubId);
            stm.setInt(2, regionId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product d = new Product();
                d.setPid(rs.getInt("ID"));
                d.setPname(rs.getString("ProductName"));
                d.setUnitprice(rs.getInt("UnitPrice"));
                d.setImg(rs.getString("img"));
                products.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
//    ---------------------
    public ArrayList<Product> getproductByCateIdSubId(int regionId, int cateId, int subId, int pageindex, int pagesize) {
        ArrayList<Product> products = new ArrayList<>();
        
        if(subId ==0){
            try {
            String sql = "SELECT\n" +
                            "	[ID]\n" +
                            "      ,[ProductName]\n" +
                            "      ,[UnitPrice]\n" +
                            "      ,[SubCategoryID]\n" +
                            "      ,[img]\n" +
                            "	  from\n" +
                            "\n" +
                            "(SELECT ROW_NUMBER() OVER (ORDER BY p.id asc) as rownum,\n" +
                            "		p.[ID]\n" +
                            "      ,[ProductName]\n" +
                            "      ,[UnitPrice]\n" +
                            "      ,[SubCategoryID]\n" +
                            "      ,[img]\n" +
                            "  FROM [Product] p inner join SubCategory s\n" +
                            "  on p.SubCategoryID  = s.ID\n" +
                            "  inner join Category c on c.ID = s.CategoryID\n" +
                            "  where c.ID = ? and p.ID in\n" +
                            "  (SELECT p.[ID]\n" +
                            "  FROM [Product] p inner join ProductRegion pr on p.ID = pr.pid\n" +
                            "  inner join Region r on r.rid = pr.rid\n" +
                            "  where r.rid = ?)) t\n" +
                            "  where rownum >= (?-1)*? +1 AND rownum <= ?*? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateId);
            stm.setInt(2, regionId);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            stm.setInt(5, pageindex);
            stm.setInt(6, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product d = new Product();
                d.setPid(rs.getInt("ID"));
                d.setPname(rs.getString("ProductName"));
                d.setUnitprice(rs.getInt("UnitPrice"));
                d.setImg(rs.getString("img"));
                products.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
        }else{
            try {
                String sql = "SELECT\n" +
                                "	[ID]\n" +
                                "      ,[ProductName]\n" +
                                "      ,[UnitPrice]\n" +
                                "      ,[SubCategoryID]\n" +
                                "      ,[img]\n" +
                                "	  from\n" +
                                "\n" +
                                "(SELECT ROW_NUMBER() OVER (ORDER BY p.id asc) as rownum,\n" +
                                "		p.[ID]\n" +
                                "      ,[ProductName]\n" +
                                "      ,[UnitPrice]\n" +
                                "      ,[SubCategoryID]\n" +
                                "      ,[img]\n" +
                                "  FROM [Product] p inner join SubCategory s\n" +
                                "  on p.SubCategoryID  = s.ID\n" +
                                "  inner join Category c on c.ID = s.CategoryID\n" +
                                "  where s.ID = ? and p.ID in\n" +
                                "  (SELECT p.[ID]\n" +
                                "  FROM [Product] p inner join ProductRegion pr on p.ID = pr.pid\n" +
                                "  inner join Region r on r.rid = pr.rid\n" +
                                "  where r.rid = ?)) t\n" +
                                "  where rownum >= (?-1)*? +1 AND rownum <= ?*? ";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, subId);
                stm.setInt(2, regionId);
                stm.setInt(3, pageindex);
                stm.setInt(4, pagesize);
                stm.setInt(5, pageindex);
                stm.setInt(6, pagesize);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Product d = new Product();
                    d.setPid(rs.getInt("ID"));
                    d.setPname(rs.getString("ProductName"));
                    d.setUnitprice(rs.getInt("UnitPrice"));
                    d.setImg(rs.getString("img"));
                    products.add(d);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            return products;
        }
   
    }
    public int getRowCount(int regionId, int cateId, int subId, int pageindex, int pagesize) {
        ArrayList<Product> products = new ArrayList<>();
        int count = 0;
        if(subId ==0){
            try {
            String sql = "SELECT count(*) as count\n" +
                            "  FROM [Product] p inner join SubCategory s\n" +
                            "  on p.SubCategoryID  = s.ID\n" +
                            "  inner join Category c on c.ID = s.CategoryID\n" +
                            "  where c.ID = ? and p.ID in\n" +
                            "  (SELECT p.[ID]\n" +
                            "  FROM [Product] p inner join ProductRegion pr on p.ID = pr.pid\n" +
                            "  inner join Region r on r.rid = pr.rid\n" +
                            "  where r.rid = ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateId);
            stm.setInt(2, regionId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
        }else{
            try {
                String sql = "SELECT count(*) as count\n" +
                            "  FROM [Product] p inner join SubCategory s\n" +
                            "  on p.SubCategoryID  = s.ID\n" +
                            "  inner join Category c on c.ID = s.CategoryID\n" +
                            "  where s.ID = ? and p.ID in\n" +
                            "  (SELECT p.[ID]\n" +
                            "  FROM [Product] p inner join ProductRegion pr on p.ID = pr.pid\n" +
                            "  inner join Region r on r.rid = pr.rid\n" +
                            "  where r.rid = ?)";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, subId);
                stm.setInt(2, regionId);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                count = rs.getInt("count");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
        }
    }
    public Product getProductByPid(String pid) {
        Product d = new Product();
        int ipid = Integer.parseInt(pid);
        try {
            String sql = "SELECT [ID]\n" +
                        "      ,[ProductName]\n" +
                        "      ,[UnitPrice]\n" +
                        "      ,[SubCategoryID]\n" +
                        "      ,[img]\n" +
                        "  FROM [Product]\n" +
                        "  where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ipid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                d.setPid(rs.getInt("ID"));
                d.setPname(rs.getString("ProductName"));
                d.setUnitprice(rs.getInt("UnitPrice"));
                d.setImg(rs.getString("img"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
     public void delete(int id) {
        try {
            connection.setAutoCommit(false);
            //lay ra p
            ProductDBContext pdb = new ProductDBContext();
            Product p = pdb.getproductById(id);
            //get student pid
            for (ProductRegion pregion : p.getProductRegion()) {
                String sql_delete_pregion = "DELETE FROM [ProductRegion]\n" +
                                                "      WHERE pid = ?";
                PreparedStatement stm_delete_pregion = connection.prepareStatement(sql_delete_pregion);
                stm_delete_pregion.setInt(1, pregion.getD().getPid());
                stm_delete_pregion.executeUpdate();
            }
            String sql = "DELETE [Product] WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public Product getproductById(int id) {
        try {
            String sql = "SELECT TOP (1000) [ID]\n" +
                            "      ,[ProductName] as pname \n" +
                            "      ,[UnitPrice]\n" +
                            "      ,[SubCategoryID]\n" +
                            "      ,[img]\n" +
                            "	  ,r.[rid]\n" +
                            "	  ,r.[name] as rname \n" +
                            "  FROM [Product] p inner join [ProductRegion] pr on p.ID = pr.pid\n" +
                            " inner join [Region] r on pr.rid = r.rid\n" +
                            " where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Product d = null;
            while (rs.next()) {
                if (d == null) {
                    d = new Product();
                    d.setPid(rs.getInt("id"));
                    SubCategory sub = new SubCategory();
                    sub.setScid(rs.getInt("SubCategoryID"));
                    SubCategoryDBContext subdb = new SubCategoryDBContext();
                    sub.setSubName(subdb.getSubById(rs.getInt("SubCategoryID")).getSubName());
                    d.setSub(sub);
                    d.setPname(rs.getString("pname"));
                    d.setUnitprice(rs.getInt("UnitPrice"));
                    d.setImg(rs.getString("img"));

                }
                int rid = rs.getInt("rid");
                if (rid != 0) {
                    ProductRegion sc = new ProductRegion();
                    region r = new region();
                    r.setRid(rid);
                    r.setName(rs.getString("rname"));
                    sc.setR(r);
                    sc.setD(d);
                    d.getProductRegion().add(sc);
                }
            }
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public void insert(Product p) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Product]\n" +
                        "           ([ProductName]\n" +
                        "           ,[UnitPrice]\n" +
                        "           ,[SubCategoryID]\n" +
                        "           ,[img])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getPname());
            stm.setInt(2, p.getUnitprice());
            stm.setInt(3, p.getSub().getScid());
            stm.setString(4, p.getImg());
            stm.executeUpdate();

            //get student pid
            String sql_get_id = "SELECT @@IDENTITY as sid";
            PreparedStatement stm_get_id = connection.prepareStatement(sql_get_id);
            ResultSet rs = stm_get_id.executeQuery();
            if (rs.next()) {
                p.setPid(rs.getInt("sid"));
            }
            //insert ProductRegion

            for (ProductRegion pregion : p.getProductRegion()) {
                String sql_insert_pregion = "INSERT INTO [ProductRegion]\n" +
                                        "           ([pid]\n" +
                                        "           ,[rid])\n" +
                                        "     VALUES\n" +
                                        "           (?\n" +
                                        "           ,?)";
                
                PreparedStatement stm_insert_pregion = connection.prepareStatement(sql_insert_pregion);
                stm_insert_pregion.setInt(1, p.getPid());
                stm_insert_pregion.setInt(2, pregion.getR().getRid());
                stm_insert_pregion.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
     public void update(Product p) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Product]\n" +
                            "   SET [ProductName] = ?\n" +
                            "      ,[UnitPrice] = ?\n" +
                            "      ,[SubCategoryID] = ?\n" +
                            "      ,[img] = ?\n" +
                            " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getPname());
            stm.setInt(2, p.getUnitprice());
            stm.setInt(3, p.getSub().getScid());
            stm.setString(4, p.getImg());
            stm.setInt(5, p.getPid());
            stm.executeUpdate();
            // remove ProductRegion
            String sql_remove_certs = "DELETE FROM [ProductRegion]\n" +
                                        "      WHERE pid = ?";
            PreparedStatement stm_remove_certs = connection.prepareStatement(sql_remove_certs);
            stm_remove_certs.setInt(1, p.getPid());
            stm_remove_certs.executeUpdate();
            
            //add ProductRegion
            for (ProductRegion cert : p.getProductRegion()) {
                String sql_insert_cert = "INSERT INTO [ProductRegion]\n" +
                                            "           ([pid]\n" +
                                            "           ,[rid])\n" +
                                            "     VALUES\n" +
                                            "           (?\n" +
                                            "           ,?)";
                PreparedStatement stm_insert_pregion = connection.prepareStatement(sql_insert_cert);
                stm_insert_pregion.setInt(1, p.getPid());
                stm_insert_pregion.setInt(2, cert.getR().getRid());
                stm_insert_pregion.executeUpdate();
            }
            //commit transaction
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
