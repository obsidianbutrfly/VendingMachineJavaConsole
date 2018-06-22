/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controlller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ashleybesser
 */
public class App {

    public static void main(String[] args) {

//        UserIO myIo = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIo);
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
//        VendingMachineController controller
//                = new VendingMachineController(myService, myView);
//        controller.runProgram();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.runProgram();    
        
    }

}
