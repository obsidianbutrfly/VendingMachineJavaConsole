/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author ashleybesser
 */
public class Snacks {
    
    private String snackName;
    private BigDecimal snackCost;
    private int quanitySnacks;

    //I want the menu etc to use both the title and the cost amount

    public Snacks(String snackName, BigDecimal snackCost) {
        this.snackName = snackName;
        this.snackCost = snackCost;
    }

    
    public String getSnackName() {
        return snackName;
    }

    public BigDecimal getSnackCost() {
        return snackCost;
    }

    public int getQuanitySnacks() {
        return quanitySnacks;
    }

    public void setQuanitySnacks(int quanitySnacks) {
        this.quanitySnacks = quanitySnacks;
    }

//When an items sells it will take away 1 item from the set quanity I make
    public void itemsSold() {
        quanitySnacks--;
    }

    public boolean equals(Snacks obj) {
        if(this.snackName.equals(obj.getSnackName())
            && this.snackCost.equals(obj.getSnackCost())
                && this.quanitySnacks == obj.getQuanitySnacks()){
            return true;
        }
        return false;
    }
    
@Override
public String toString() {
    return "Snack: " + snackName + " |Snack Cost: " + snackCost + " |Qty: " + quanitySnacks;
}
    
    
}
