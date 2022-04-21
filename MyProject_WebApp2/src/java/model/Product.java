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
public class Product {
    private int pid;
    private String pname;
    private int unitprice;
    private String img;
    private SubCategory sub;
    private int count;
    private int total;
    
    private ArrayList<ProductRegion> ProductRegion = new ArrayList<>();

    
    public ArrayList<ProductRegion> getProductRegion() {
        return ProductRegion;
    }

    public void setProductRegion(ArrayList<ProductRegion> ProductRegion) {
        this.ProductRegion = ProductRegion;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product(int pid, String pname, int unitprice, String img, SubCategory sub) {
        this.pid = pid;
        this.pname = pname;
        this.unitprice = unitprice;
        this.img = img;
        this.sub = sub;
    }
    

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Product(int pid, String pname, int unitprice, SubCategory sub, String img) {
        this.pid = pid;
        this.pname = pname;
        this.unitprice = unitprice;
        this.sub = sub;
        this.img = img;
    }
    
    
    public Product() {
    }

    public Product(int pid, String pname, int unitprice, SubCategory sub) {
        this.pid = pid;
        this.pname = pname;
        this.unitprice = unitprice;
        this.sub = sub;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public SubCategory getSub() {
        return sub;
    }

    public void setSub(SubCategory sub) {
        this.sub = sub;
    }
    
}
