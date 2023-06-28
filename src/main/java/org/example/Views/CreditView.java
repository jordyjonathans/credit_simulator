package org.example.Views;

import org.example.Controllers.CreditController;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CreditView {
    CreditController controller;
    Scanner myScanner;

    public CreditView() {
        myScanner  = new Scanner(System.in);
    }

    public void run(){
        showTitle();

        showInputVehicleType(myScanner);
        showInputVehicleCondition(myScanner);
        showInputVehicleYear(myScanner);
        showInputLoan(myScanner);
        showInputTenor(myScanner);
        showInputDp(myScanner);
        showCalculateInstallment();

        showEnding();
    }
    public void run(String vehicleCondition, String vehicleType, String tahunMobil, String jumlahDownPayment, String jumlahPinjaman, String tenorCicilan){
        showTitle();

        showLoadedVehicleType(vehicleType);
        showLoadedVehicleCondition(vehicleCondition);
        showLoadedVehicleYear(tahunMobil);
        showLoadedLoan(jumlahPinjaman);
        showLoadedTenor(tenorCicilan);
        showLoadedDp(jumlahDownPayment);
        showCalculateInstallment();

        showEnding();
    }
    public CreditController getController() {
        return controller;
    }

    public void setController(CreditController controller) {
        this.controller = controller;
    }
    public void showTitle(){
        System.out.println("Create console application to calculate monthly installments for vehicle loan");
    }
    public void showInputVehicleType(Scanner scanner){
        System.out.print("Input jenis kendaraan Mobil|Motor: ");
        String input = scanner.nextLine();

        if(!controller.saveVehicleType(input)) {
            System.out.println("[x] Alphabet, Mobil|Motor. ");
            showInputVehicleType(scanner);
        }
    }
    public void showInputVehicleCondition(Scanner scanner){
        System.out.print("Input Kendaraan Bekas|Baru: ");
        String input = scanner.nextLine();

        if(!controller.saveVehicleCondition(input)) {
            System.out.println("[x] Alphabet , Bekas|Baru. ");
            showInputVehicleCondition(scanner);
        }
    }
    public void showInputVehicleYear(Scanner scanner){
        System.out.print("Input Tahun Kendaraan: ");
        String input = scanner.nextLine();

        if(!controller.saveVehicleYear(input)) {
            System.out.println("[x] Numeric, 4digit. ");
            showInputVehicleYear(scanner);
        }
    }

    public void showInputLoan(Scanner scanner){
        System.out.print("Input Jumlah Pinjaman: ");
        String input = scanner.nextLine();

        if(!controller.saveLoan(input)) {
            System.out.println("[x] Numeric  <= 1 Miliyar. ");
            showInputLoan(scanner);
        }
    }
    public void showInputTenor(Scanner scanner){
        System.out.print("Input Tenor Pinjaman: ");
        String input = scanner.nextLine();

        if(!controller.saveTenor(input)) {
            System.out.println("[x] 1-6 tahun. ");
            showInputTenor(scanner);
        }
    }
    public void showInputDp(Scanner scanner){
        System.out.print("Input DP: ");
        String input = scanner.nextLine();

        if(!controller.saveDP(input)) {
            System.out.println("[x] DP BARU MIN. 35% , DP BEKAS MIN. 25%. ");
            showInputDp(scanner);
        }
    }
    public void showCalculateInstallment(){
        List<HashMap<String, String>> installment = controller.getInstallment();

        System.out.println("Output Jumlah Cicilan Perbulan");
        for(int i = 0 ; i < installment.size();i++){
            HashMap<String, String> map = installment.get(i);
            System.out.println("Tahun "+(i+1)+": Rp. "+map.get("payment")+"/bln, Suku Bunga : "+map.get("suku_bunga") + "%");
        }
    }
    public void showLoadedVehicleType(String data){
        System.out.println("Input jenis kendaraan Mobil|Motor: "+data);

        if(!controller.saveVehicleType(data)) {
            System.out.println("[x] Alphabet, Mobil|Motor. ");
            System.exit(0);
        }
    }
    public void showLoadedVehicleCondition(String data){
        System.out.println("Input Kendaraan Bekas|Baru: "+data);

        if(!controller.saveVehicleCondition(data)) {
            System.out.println("[x] Alphabet , Bekas|Baru. ");
            System.exit(0);
        }
    }
    public void showLoadedVehicleYear(String data){
        System.out.println("Input Tahun Kendaraan: "+data);

        if(!controller.saveVehicleYear(data)) {
            System.out.println("[x] Numeric, 4digit. ");
            System.exit(0);
        }
    }

    public void showLoadedLoan(String data){
        System.out.println("Input Jumlah Pinjaman: "+data);

        if(!controller.saveLoan(data)) {
            System.out.println("[x] Numeric  <= 1 Miliyar. ");
            System.exit(0);
        }
    }
    public void showLoadedTenor(String data){
        System.out.println("Input Tenor Pinjaman: "+data);

        if(!controller.saveTenor(data)) {
            System.out.println("[x] 1-6 tahun. ");
            System.exit(0);
        }
    }
    public void showLoadedDp(String data){
        System.out.println("Input DP: "+data);

        if(!controller.saveDP(data)) {
            System.out.println("[x] DP BARU MIN. 35% , DP BEKAS MIN. 25%. ");
            System.exit(0);
        }
    }
    public void showEnding(){
        System.out.print("Thank you for using this console application.");
    }
}
