/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Snacks;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineView {

    Scanner inputReader = new Scanner(System.in);

    VendingMachineDao dao = new VendingMachineDaoFileImpl();
    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayWelcomeMessage() {
        io.print(" -*-*-*- Welcome to the Besser Snack Machine -*-*-*-");
    }

    public String getUserMoney() {
        io.print(" ");
        String userMoney = io.readString("How much money would you like to insert in the machine?");
        return userMoney;
    }

    public void displayInventory(List<Snacks> inventory) {
        io.print("Snack,Price,Quanity in Stock");
        for (Snacks inv : inventory) { // going to get the items in the file
            io.print(inv.getSnackName() //name of snack [0]
                    + ": $ " + inv.getSnackCost().setScale(2, RoundingMode.HALF_UP) //price set for item , scale of 2 for 2 decimals and rounds for an even amount
                    + " QTY: " + inv.getQuanitySnacks()); // getting the last field quanity of snacks
        }
    }

    public String getSelection() {
        return io.readString("What would you like to purchase?");
    }

    public BigDecimal dispenseItem(String selection, BigDecimal getChange) {
        io.print("Getting Item...");
        io.print(selection);
        io.print("Thank you for purchase. Come back soon!");
        return getChange;
    }

    public void displayVendingMachineSnackList(List<Snacks> snackList) { //4 .listing current dvds in library
        for (Snacks currentSnack : snackList) {
            io.print(currentSnack.getSnackName() + ": "
                    + currentSnack.getSnackCost() + " "
                    + currentSnack.getQuanitySnacks());
        }
        io.readString("Please hit enter to continue.");
    }

    public void getCoins(int numOfCoins, String coins) {
        io.print(coins + " : " + numOfCoins);
        
    }
    
    public void displayGetChangeBanner () {
        io.print(" === Here is your change ==== ");
    }
    public void displayExitBanner() {
        io.print("Goodbye!!! Come back soon!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Choices!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== Error ===");
        io.print(errorMsg);
    }

    public void displayInsufficentFundsBanner() {
        io.print("Sorry, you did not submit enough money to purchase that item. Please submit more funds to get snacks.");
    }

    public void displayZeroInventoryBanner() {
        io.print("Sorry, we are out of stock on that item. Please make another choice.");
    }

    public void displayVendingSuccessBanner() {
        io.print("Here is your snack!");
    }

    public String addMore() {
        return io.readString("Do you want to add more money? Y or N.");
    }

    public void displayMoney(BigDecimal userMoney) {
        io.print(userMoney.toString());
    }
    
    public boolean getAnotherItem(){
        String choice = io.readString("Do you want to purchase another item? Y or N.");
        if(choice.equalsIgnoreCase("y")){
            return true;
        } else {
            return false;
        }
    }

}
