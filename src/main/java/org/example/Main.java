package org.example;

import org.example.Controllers.CreditController;

public class Main {
    public static void main(String[] args) {
        if(args.length > 0){
            new CreditController(args[0]);
        }else new CreditController();

    }
}