/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import static com.sg.vendingmachine.dto.Change.CoinType.QUARTERS;
import java.lang.ProcessBuilder.Redirect.Type;
import java.math.BigDecimal;
import static javafx.scene.effect.BlendMode.MULTIPLY;
import static javafx.scene.input.KeyCode.DIVIDE;
import static javafx.scene.input.KeyCode.MINUS;

/**
 *
 * @author ashleybesser
 */
public class Change {
    

    private static int quarters;
    private static int dimes;
    private static int nickels;
    private static int pennies;

    //I'll take the users money and convert it to penny amounts
    //this should make turning pennies into the other change easier
    
    public Change(BigDecimal change){
        this.pennies = change.multiply(new BigDecimal(100)).intValueExact();
        //I mulitple by 100 since there are 100 pennies in a dollar, convert it to int to make it easier to work with
        
        calculateChange();
    }

    private static void calculateChange(){
        quarters = pennies / 25; // 25 pennies equals 1 quarters
        pennies = pennies % 25; // getting the remainder of the pennies
        
        dimes = pennies / 10; // 10 pennies for a dime
        pennies = pennies % 10; //getting the remainder of the pennies after it got the dimes
        
        nickels = pennies / 5; // 5 pennies for a nickel
        pennies = pennies % 5; // remainder of pennies after getting the nickles
    }
            
    //since I am not setting a specified amount ahead of time, only used getters
    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
    
    public enum CoinType {
        QUARTERS, DIMES, NICKELS, PENNIES
    }
   
    public int assignCoin (CoinType coin) {
       
        switch(coin) {
            case QUARTERS:
                return quarters;
            case DIMES:
                return dimes;
            case NICKELS:
                return nickels;
            case PENNIES:
                return pennies;
            default:
                throw new UnsupportedOperationException();
        }
    }
        
}
