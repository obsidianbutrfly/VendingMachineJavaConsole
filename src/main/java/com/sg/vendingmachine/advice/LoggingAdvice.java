/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineInsufficentFundsException;
import com.sg.vendingmachine.service.VendingMachineNoInventoryException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author ashleybesser
 */
public class LoggingAdvice {
    //write entry into audit log

    VendingMachineAuditDao auditDao;

    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    /*
    we want to create an audit entry
    we want it tp write out the exception that is throw from insufficent funds 
    and no inventory
     */
    //join point carries the information from where it's inserted
    public void createAuditEntry(JoinPoint jp, Exception reason) {
        Object[] args = jp.getArgs(); //getting an array of objects
        String auditEntry = jp.getSignature().getName() + ": " + reason;//the format that it's writing in, gets the name of the method that has issue, and it's parameters
        for (Object currentArg : args) { //cycling through the objects in the array
            auditEntry += " " + currentArg;
        }
        try { //if there is a problem writing to the audit file
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
