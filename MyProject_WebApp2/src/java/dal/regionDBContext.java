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
import model.region;

/**
 *
 * @author PCDELL
 */
public class regionDBContext extends DBContext {
    public ArrayList<region> getRegions() {
        ArrayList<region> regions = new ArrayList<>();
        try {
            String sql = "SELECT TOP (1000) [rid]\n" +
                        "      ,[name]\n" +
                        "  FROM [Region]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                region s = new region();
                s.setRid(rs.getInt("rid"));
                s.setName(rs.getString("name"));
                regions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(regionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regions;
    }
    public region getRegionById(int rid) {
        region r = new region();
        try {
            String sql = "SELECT [rid]\n" +
                        "      ,[name]\n" +
                        "  FROM [Region] where rid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rid);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                r.setRid(rs.getInt("rid"));
                r.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(regionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
