/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Snacks;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    
        Snacks onlySnack;
    List<Snacks> snackList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        onlySnack = new Snacks("Snickers", new BigDecimal ("1.50"));
        onlySnack.setQuanitySnacks(1);
        snackList.add(onlySnack);
    }

    @Override
    public List<Snacks> getAllSnacks() throws VendingMachinePersistenceException {
        return snackList;
    }

    @Override
    public Snacks getSnack(String snackName) throws VendingMachinePersistenceException {
                if (snackName.equals(onlySnack.getSnackName())) {
            return onlySnack;
        } else {
            return null;
        }
    }

    @Override
    public void soldItem(String snackName) throws VendingMachinePersistenceException {
        snackList.get(0).itemsSold();
    }
    
}
