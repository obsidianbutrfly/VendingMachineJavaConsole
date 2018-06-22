/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Snacks;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Snacks> snacks = new HashMap<>();


    @Override
    public List<Snacks> getAllSnacks() throws VendingMachinePersistenceException {
        loadInventory();
        return new ArrayList<Snacks>(snacks.values());
    }

    @Override
    public void soldItem(String snackName) throws VendingMachinePersistenceException {
        //when an item sells update the inventory
        snacks.get(snackName).itemsSold();
        writeInventory();
    }

    @Override
    public Snacks getSnack(String snackName) throws VendingMachinePersistenceException {
        loadInventory();
        return snacks.get(snackName);
    }



    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner inputReader;

        try {
            // Create Scanner for reading the file
            inputReader = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        String[] currentTokens;
        // Go through INVENTORY_FILE line by line, decoding each line into a 
        // Snacks object.
        // Process while we have more lines in the file
        while (inputReader.hasNextLine()) {
            // get the next line in the file
            currentLine = inputReader.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Snacks object and put it into the map of snacks
            // NOTE: We are going to use the snackName
            // which is currentTokens[0] as the map key for our Snacks object.
            // We also have to pass the snacksName into the Snack constructor
            Snacks currentSnack = new Snacks(currentTokens[0],new BigDecimal(currentTokens[1]));
            //set quanity manually
            currentSnack.setQuanitySnacks(Integer.parseInt(currentTokens[2]));

            // Put currentSnack into the map using snackName and cost as the key
            snacks.put(currentSnack.getSnackName(), currentSnack);
        }
        // close inputReader
        inputReader.close();
    }

    /**
     * Writes all snacks in the inventory out to a INVENTORY_FILE. See
     * loadInventory for file format.
     *
     * @throws DvdLibraryPersistenceException if an error occurs writing to the
     * file
     */
    private void writeInventory() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save candy data.", e);
        }

        // Write out the Snack objects to the inventory file.
        // NOTE TO THE APPRENTICES: We could just grab the snack map,
        // get the Collection of Snacks and iterate over them but we've
        // already created a method that gets a List of Snacks so
        // we'll reuse it.
        List<Snacks> snackList = this.getAllSnacks();
        for (Snacks currentSnack : snackList) {
            // write the Snacks object to the file
            out.println(currentSnack.getSnackName() + DELIMITER
                    + currentSnack.getSnackCost() + DELIMITER
                    + currentSnack.getQuanitySnacks());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
