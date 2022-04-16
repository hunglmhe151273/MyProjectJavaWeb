/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Customer {
    private int CusId;
    private String RecipientName;
    private String City;
    private String Address;
    private String PhoneNumber;
    private ArrayList<Orders> Orders = new ArrayList<>();
    private Account createdby;

    public Account getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Account createdby) {
        this.createdby = createdby;
    }
    
    public Customer(int CusId, String RecipientName, String City, String Address, String PhoneNumber, Account createdby) {
        this.CusId = CusId;
        this.RecipientName = RecipientName;
        this.City = City;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.createdby = createdby;
    }
    
    
    
    public int getCusId() {
        return CusId;
    }

    public void setCusId(int CusId) {
        this.CusId = CusId;
    }

    
    
    public ArrayList<Orders> getOrders() {
        return Orders;
    }

    public void setOrders(ArrayList<Orders> Orders) {
        this.Orders = Orders;
    }
    public Customer() {
    }

    public Customer(String RecipientName, String City, String Address, String PhoneNumber) {
        this.RecipientName = RecipientName;
        this.City = City;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String RecipientName) {
        this.RecipientName = RecipientName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    
     
    
}
