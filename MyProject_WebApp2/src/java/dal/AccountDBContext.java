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
import model.Account;
import model.Feature;

/**
 *
 * @author PCDELL
 */
public class AccountDBContext extends DBContext{
    public Account getAccount(String username, String password)
    {
         try {
            String sql = "select * from Account c left join GroupAccount ga\n" +
                            "on c.username = ga.username\n" +
                            "left join [Group] gr\n" +
                            "on ga.gid = gr.gid\n" +
                            "left join [GroupFeature] gf\n" +
                            "on gf.gid = gr.gid\n" +
                            "left join [Feature] f\n" +
                            "on f.fid = gf.fid\n" +
                            "where c.username = ? and c.password = ? \n" +
                            "order by c.username asc, gr.gid asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Account account = null;
            while(rs.next())
            {
                if(account == null){
                account = new Account();
                account.setUsername(username);
                account.setPassword(password);
                }
                int fid = rs.getInt("fid");
                if(fid != 0){
                    Feature f = new Feature();
                    f.setId(fid);
                    f.setUrl(rs.getString("url"));
                    account.getFeatures().add(f);
                }
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
