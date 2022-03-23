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
public class OrderDetails {
    private int quantity;

    private Product p;
    private Orders o;

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public Orders getO() {
        return o;
    }

    public void setO(Orders o) {
        this.o = o;
    }

    
    
    public OrderDetails(int quantity, Product p, Orders o) {
        this.quantity = quantity;
        this.p = p;
        this.o = o;
    }

    
    public OrderDetails() {
    }

    public OrderDetails(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
