package org.example.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditTest {
    private Credit credit;

    @BeforeEach
    void setUp() {
        credit = new Credit() ;
    }

    @Test
    void getVehicleType() {
        credit.setVehicleType("mobil");
        assertEquals("mobil", credit.getVehicleType());
    }

    @Test
    void getVehicleYear() {
        credit.setVehicleYear("1994");
        assertEquals("1994", credit.getVehicleYear());
    }

    @Test
    void getVehicleCondition() {
        credit.setVehicleCondition("bekas");
        assertEquals("bekas", credit.getVehicleCondition());
    }

    @Test
    void getLoan() {
        credit.setLoan(1000000000);
        assertEquals(1000000000, credit.getLoan());
    }

    @Test
    void getTenor() {
        credit.setTenor(6);
        assertEquals(6, credit.getTenor());
    }

    @Test
    void getDp() {
        credit.setDp(25000000);
        assertEquals(25000000, credit.getDp());
    }

    @Test
    void getBaseInterest() {
        credit.setBaseInterest(8.1F);
        assertEquals(8.1F, credit.getBaseInterest());
    }

    @Test
    void getInterest1() {
        credit.setInterest1(8.1F);
        assertEquals(8.1F, credit.getInterest1());
    }

    @Test
    void getInterest2() {

        credit.setInterest2(8.1F);
        assertEquals(8.1F, credit.getInterest2());
    }

}