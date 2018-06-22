/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Snacks;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ashleybesser
 */
public interface VendingMachineServiceLayer {
    
   
    public List<Snacks> getAllSnacks() throws VendingMachinePersistenceException;
    
    public boolean validSelection(String selection) throws VendingMachinePersistenceException, VendingMachineNoInventoryException ;
    
    public BigDecimal sellItem(String selection, BigDecimal userMoney) throws VendingMachinePersistenceException, VendingMachineInsufficentFundsException ;
    
}
