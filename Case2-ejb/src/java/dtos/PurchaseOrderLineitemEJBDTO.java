/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Craig
 */
public class PurchaseOrderLineitemEJBDTO {
    private int qty;
    private String Productcode;
    private double Costprice;
    private String Productname;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getProductcode() {
        return Productcode;
    }

    public void setProductcode(String Productcode) {
        this.Productcode = Productcode;
    }

    public double getCostprice() {
        return Costprice;
    }

    public void setCostprice(double Costprice) {
        this.Costprice = Costprice;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String Productname) {
        this.Productname = Productname;
    }
    
}
