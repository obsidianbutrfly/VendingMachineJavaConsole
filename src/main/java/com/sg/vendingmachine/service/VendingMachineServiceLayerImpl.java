/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Snacks;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao; //create, read, write, objects
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Snacks> getAllSnacks() throws VendingMachinePersistenceException {
        return dao.getAllSnacks();
    }

    @Override
    public boolean validSelection(String selection) throws VendingMachinePersistenceException, VendingMachineNoInventoryException {
        boolean validItem = false;
        List<Snacks> snacks = getAllSnacks();
        for (Snacks snack : snacks) {
            if (selection.equalsIgnoreCase(snack.getSnackName())) { //checking to see if what the user entered is a valid selection

                if (snack.getQuanitySnacks() <= 0) { // if the item is valid then check the inventory stock
                    validItem = false;
                    throw new VendingMachineNoInventoryException("Sorry, this item is out of stock.");
                } else {
                    validItem = true;
                }
            }
        }
        return validItem;
    }

    @Override
    public BigDecimal sellItem(String selection, BigDecimal userMoney) throws VendingMachinePersistenceException, VendingMachineInsufficentFundsException {
        //the item is valid, now we are checking to see if theu have enough money to buy the item
        //money comes from the main menu
        //compare it to the cost of the item
        //if the money they have is greater than the snackCost let them buy it
        //otherwise return insufficent funds error

        if (userMoney.compareTo(dao.getSnack(selection).getSnackCost()) >= 0) { // if the item is greater than the cost sell it, subtract 1
            dao.soldItem(selection);
            return dao.getSnack(selection).getSnackCost();

        } else {
            throw new VendingMachineInsufficentFundsException("Sorry, you don't have enough money to buy this item.");
        }
        //write to audit file?
        
    }
}
