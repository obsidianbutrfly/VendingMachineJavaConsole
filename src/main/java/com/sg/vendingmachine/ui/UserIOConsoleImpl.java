/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author ashleybesser
 */
public class UserIOConsoleImpl implements UserIO {
    
        Scanner inputReader = new Scanner(System.in);
        
        

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double numD = inputReader.nextDouble();
        inputReader.nextLine();
        return numD;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double minMaxD;
        do {
            System.out.println(prompt);
            minMaxD = inputReader.nextDouble();
            inputReader.nextLine();

        } while (minMaxD < min || minMaxD > max);

        return minMaxD;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float numF = inputReader.nextFloat();
        inputReader.nextLine();
        return numF;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float minMaxF;
        do {
            System.out.println(prompt);
            minMaxF = inputReader.nextFloat();
            inputReader.nextLine();
        } while (minMaxF < min || minMaxF > max);
        return minMaxF;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int numInt = inputReader.nextInt();
        inputReader.nextLine();
        return numInt;  
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int intMinMax;
        do {
            System.out.println(prompt);
            intMinMax = inputReader.nextInt();
            inputReader.nextLine();
        } while (intMinMax < min || intMinMax > max);
        
        
        return intMinMax;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long wordLong = inputReader.nextLong();
        return wordLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long minMaxLong;
        do {
            System.out.println(prompt);
            minMaxLong = inputReader.nextLong();
        } while (minMaxLong < min || minMaxLong > max);
        return minMaxLong;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String giveString = inputReader.nextLine();
        return giveString;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal bigD;
        System.out.println(prompt);
        bigD = inputReader.nextBigDecimal();
        return bigD;
    }
    

    
}
