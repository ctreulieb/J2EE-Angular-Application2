/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.ArrayList;

/**
 *
 * @author Craig
 */
public class PurchaseOrderEJBDTO {
    private double total;
    private ArrayList<PurchaseOrderLineitemEJBDTO> items;
    private int vendorno;
    private String date;
    private int ponumber;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<PurchaseOrderLineitemEJBDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseOrderLineitemEJBDTO> items) {
        this.items = items;
    }

    public int getVendorno() {
        return vendorno;
    }

    public void setVendorno(int vendorno) {
        this.vendorno = vendorno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPonumber() {
        return ponumber;
    }

    public void setPonumber(int ponumber) {
        this.ponumber = ponumber;
    }
}
