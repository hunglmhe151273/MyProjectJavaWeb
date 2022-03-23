/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author PCDELL
 */
public class Category {
    private int cid;
    private String CategoryName;
    private ArrayList<SubCategory> subs = new ArrayList<>();

    public ArrayList<SubCategory> getSubs() {
        return subs;
    }

    public void setSubs(ArrayList<SubCategory> subs) {
        this.subs = subs;
    }
    
    
    public Category() {
    }

    public Category(int cid, String CategoryName) {
        this.cid = cid;
        this.CategoryName = CategoryName;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
    
    
}
