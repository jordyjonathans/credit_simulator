package org.example.Controllers;

import org.example.Models.Credit;
import org.example.Views.CreditView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditControllerTest {
    CreditController controller;
    @BeforeEach
    void setUp() {
        controller = new CreditController("credit_simulator.txt");
    }

    @Test
    void readFile() {
        assertThrows(FileNotFoundException.class,()->{
            controller.readFile("coba");
        });
    }

    @Test
    void getInstallment() {
        controller.credit.setVehicleType("mobil");
        controller.credit.setVehicleCondition("baru");
        controller.credit.setVehicleYear("2019");
        controller.credit.setLoan(100000000);
        controller.credit.setDp(25000000);
        controller.credit.setTenor(5);
        List<HashMap<String,String>> installment = controller.getInstallment();
        assertEquals(installment.get(0).get("payment"),"1,350,000.00");
        assertEquals(installment.get(0).get("suku_bunga"),"8.0");
        assertEquals(installment.get(1).get("payment"),"1,459,350.00");
        assertEquals(installment.get(1).get("suku_bunga"),"8.1");
        assertEquals(installment.get(2).get("payment"),"1,584,854.00");
        assertEquals(installment.get(2).get("suku_bunga"),"8.6");
        assertEquals(installment.get(3).get("payment"),"1,722,736.00");
        assertEquals(installment.get(3).get("suku_bunga"),"8.7");
        assertEquals(installment.get(4).get("payment"),"1,881,228.00");
        assertEquals(installment.get(4).get("suku_bunga"),"9.2");


    }

}