package org.example.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class CreditServiceTest {
    CreditService creditService;
    @BeforeEach
    void setUp() {
        creditService = new CreditService();
    }
    @Test
    void get() {
        String result = creditService.get("http://www.mocky.io/v2/5d11a58d310000b23508cd62");
        assertNotEquals(result,"");

        result = creditService.get("httpasdf");
        assertEquals(result,"");

        result = creditService.get("http://www.google.com");
        assertNotEquals(result,"");

    }

}