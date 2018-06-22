/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineDataValidationException extends Exception {

    public VendingMachineDataValidationException(String string) {
        super(string);
    }

    public VendingMachineDataValidationException(String string, Throwable cause) {
        super(string, cause);
    }

}
