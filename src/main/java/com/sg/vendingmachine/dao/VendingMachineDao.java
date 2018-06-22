/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import java.util.List;
import com.sg.vendingmachine.dto.Snacks;
import java.math.BigDecimal;

/**
 *
 * @author ashleybesser
 */
public interface VendingMachineDao {


    /**
     * Needed for service layer Getting a list of all items in inventory file
     *
     * @return
     * @throws com.sg.vendingmachine.dao.VendingMachinePersistenceException
     */
    List<Snacks> getAllSnacks() //This is for the manager menu
            throws VendingMachinePersistenceException;

    /**
     * Returns the Snacks object associated with the given title. Returns null if
     * no such snack exists
     *
     * @param snackName of the snack to retrieve
     * @return the Snacks object associated with the given snackName, null if no such
     * snack exists
     * @throws com.sg.vendingmachine.dao.VendingMachinePersistenceException
     */
    
    Snacks getSnack(String snackName)
            throws VendingMachinePersistenceException;


    void soldItem(String snackName) throws VendingMachinePersistenceException;

}
