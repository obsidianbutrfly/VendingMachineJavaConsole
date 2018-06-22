/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Snacks;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineDaoTest {

    private VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllSnacks() throws Exception {
        assertEquals(4, dao.getAllSnacks().size());
    }

    /**
     * Test of getSnack method, of class VendingMachineDao.
     */
    @Test
    public void testGetSnack() throws Exception {
        Snacks snack1 = new Snacks("Snickers", new BigDecimal("1.50"));
        snack1.setQuanitySnacks(10);

        Snacks snack2 = dao.getSnack("Snickers");

        assertTrue(snack1.equals(snack2));
    }

    /**
     * Test of soldItem method, of class VendingMachineDao.
     */
    @Test
    public void testSoldItem() throws Exception {
        Snacks snack1 = new Snacks("Snickers", new BigDecimal("1.50"));
        snack1.setQuanitySnacks(10);

        snack1.itemsSold();

        assertEquals(9, snack1.getQuanitySnacks());
    }

}
