/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineServiceLayerTest {

//    private VendingMachineDao dao = new VendingMachineDaoFileImpl();
// 
//
//    private VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
    
    
    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test of getAllSnacks method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testValidSelection() throws Exception {
        // Write a function for good data
        //write a function for not having enough inventory
        String item1 = "Snickers";
        String item2 = "Brownie";

        //cheetos does actually exist so this should come back as true
        assertTrue(service.validSelection(item1));
        //Brownies does not exist in inventory so this should fail
        assertFalse(service.validSelection(item2));

        String item3 = "Twizzlers";
        try {
            assertFalse(service.validSelection(item3));
            fail("Expected VendingMachineNoInventoryException");
        } catch (VendingMachineNoInventoryException e) {

        }
        return;
    }

    /**
     * Test of sellItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSellItem() throws Exception {

        //write function for succesfully selling the item
        //write function for not having enough money
        String item1 = "Cheetos";
        BigDecimal money = new BigDecimal("1.00");
        BigDecimal money2 = new BigDecimal("3.00");

        //Cheetos cost 2.50 so I need to put in an amount large enough to "buy it"
        try {
            service.sellItem(item1, money2);
            service.sellItem(item1, money);
            fail("VendingMachineInsufficentFundsException");
        } catch (VendingMachineInsufficentFundsException e) {

        }
        return;
    }
}
