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
public class SubCategory {
    private int scid;
    private String SubName;
    private ArrayList<Product> prod = new ArrayList<>();

    public ArrayList<Product> getProd() {
        return prod;
    }

    public void setProd(ArrayList<Product> prod) {
        this.prod = prod;
    }
    
    
    private Category cate;

    public SubCategory() {
    }

    public SubCategory(int scid, String SubName, Category cate) {
        this.scid = scid;
        this.SubName = SubName;
        this.cate = cate;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public String getSubName() {
        return SubName;
    }

    public void setSubName(String SubName) {
        this.SubName = SubName;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }
    
    
}
