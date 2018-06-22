/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controlller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.service.VendingMachineInsufficentFundsException;
import com.sg.vendingmachine.service.VendingMachineNoInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineController {

    private VendingMachineServiceLayer service;
    private VendingMachineView view;
    private BigDecimal userMoney = new BigDecimal("0.00");

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void runProgram() {

        boolean keepGoing = true;
        try {
            welcomeMessage();

            while (keepGoing) {

                getMoney();

                displayMoney();

                displayInventory();

                String selection = getSelection();

                sellItem(selection);
                
                if(getAnotherItem()){
                    keepGoing = true;
                } else {
                    keepGoing = false;
                }

            }
        } catch (VendingMachinePersistenceException | VendingMachineInsufficentFundsException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
        dispenseChange(userMoney);
    }

    public void welcomeMessage() {
        view.displayWelcomeMessage();
    }

    public void displayInventory() throws VendingMachinePersistenceException {
        view.displayInventory(service.getAllSnacks());

    }

    public BigDecimal getMoney() throws VendingMachinePersistenceException {
        userMoney = userMoney.add(new BigDecimal(view.getUserMoney()));
        return userMoney;
    }

    public String getSelection() throws VendingMachinePersistenceException {
        //use the check from service to see if the item is there and available
        boolean validChoice = false;
        String selection = null;
        do {
            try {
                selection = view.getSelection();
                validChoice = service.validSelection(selection); //checking if valid chioce and if it's in stock
            } catch (VendingMachineNoInventoryException ex) {
                view.displayErrorMessage("Sorry: that item is out of stock. Make another choice.");
                validChoice = false;
            }
        } while (!validChoice);

        return selection;
    }

    public void sellItem(String selection) throws VendingMachinePersistenceException, VendingMachineInsufficentFundsException {
        /*
        item is valid, now we want to check to see if they have enough money to buy it
        if they do, give the item and change
        Need to get the cost (bigdecimal) of the item and subtract that from what the userhad on hand
        then use the change object to make change...
        dispense item
         */

        boolean enoughMoney = false;
        do {

            try {
                displayMoney();
                userMoney = userMoney.subtract(service.sellItem(selection, userMoney));
                view.dispenseItem(selection, userMoney);
                enoughMoney = true;

            } catch (VendingMachineInsufficentFundsException ex) {
                view.displayErrorMessage("Sorry, not enough money for this purchase.");

                String answer = view.addMore();
                if (answer.equalsIgnoreCase("Y")) {
                    getMoney();
                    displayMoney();
                    enoughMoney = false;
                } else {
                    enoughMoney = true;
                }
            }
        } while (!enoughMoney);

    }

    public void dispenseChange(BigDecimal userMoney) {
        view.displayGetChangeBanner();
        Change change = new Change(userMoney);
        view.getCoins(change.assignCoin(Change.CoinType.QUARTERS), "Quarters");
        view.getCoins(change.assignCoin(Change.CoinType.DIMES), "Dimes");
        view.getCoins(change.assignCoin(Change.CoinType.NICKELS), "Nickels");
        view.getCoins(change.assignCoin(Change.CoinType.PENNIES), "Pennies");

    }
    
    public boolean getAnotherItem() {
        //do they want to get anothe item.
      return view.getAnotherItem();
        
    }

    public void displayMoney() {
        view.displayMoney(userMoney);
    }
}
