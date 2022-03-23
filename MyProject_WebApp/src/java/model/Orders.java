
package model;

import java.util.ArrayList;

public class Orders {
    private String id;
    private Customer cus;
    private String note;
    private ArrayList<OrderDetails> OrderDetails = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> OrderDetails) {
        this.OrderDetails = OrderDetails;
    }
    
    public Orders() {
    }

    public Orders(String id, Customer cus, String note) {
        this.id = id;
        this.cus = cus;
        this.note = note;
    }


    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }
public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
