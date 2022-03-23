/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author PCDELL
 */
public class ProductRegion {
    private region r;
    private Product d;

    public ProductRegion() {
    }

    public ProductRegion(region r, Product d) {
        this.r = r;
        this.d = d;
    }

    public region getR() {
        return r;
    }

    public void setR(region r) {
        this.r = r;
    }

    public Product getD() {
        return d;
    }

    public void setD(Product d) {
        this.d = d;
    }
    
     
    
}
