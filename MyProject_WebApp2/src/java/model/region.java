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
public class region {
        private int rid;
        private String name;
        private ArrayList<ProductRegion> ProductRegion = new ArrayList<>();

    public ArrayList<ProductRegion> getProductRegion() {
        return ProductRegion;
    }

    public void setProductRegion(ArrayList<ProductRegion> ProductRegion) {
        this.ProductRegion = ProductRegion;
    }
   
        public region() {
    }

    public region(int rid, String name) {
        this.rid = rid;
        this.name = name;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
         
}
