package org.example.Controllers;

import org.example.Models.BikeCredit;
import org.example.Models.CarCredit;
import org.example.Models.Credit;
import org.example.Services.CreditService;
import org.example.Views.CreditView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditController {
    Credit credit ;
    CreditView view;

    private List<HashMap<String,String>> installment;
    public CreditController() {
        view = new CreditView();
        this.view.setController(this);
        installment = new ArrayList<HashMap<String,String>>();
        view.run();
    }
    public CreditController(String param) {
        view = new CreditView();
        this.view.setController(this);
        installment = new ArrayList<HashMap<String,String>>();
            try {
                if(param.contains(".txt")) {
                    runFromFile(param);
                }else if(param.contains("http")){
                    runFromUrl(param);

                }else view.run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
    public void runFromUrl(String s) throws IOException {
        CreditService creditService = CreditService.getInstance();
        String result = creditService.get(s);
        viewLoadedData(result);
    }
    public void runFromFile(String filename) throws IOException {
        String result = readFile(filename);
        viewLoadedData(result);

    }
    public void viewLoadedData(String result){
        JSONObject jsonObject = new JSONObject(result);
        JSONObject vehicleModel = jsonObject.getJSONObject("vehicleModel");

        String vehicleCondition = vehicleModel.getString("vehicleCondition");
        String vehicleType = vehicleModel.getString("vehicleType");
        String tahunMobil = vehicleModel.getString("tahunMobil");
        String jumlahDownPayment = vehicleModel.getString("jumlahDownPayment");
        String jumlahPinjaman = vehicleModel.getString("jumlahPinjaman");
        String tenorCicilan = vehicleModel.getString("tenorCicilan");

        view.run(vehicleCondition,vehicleType,tahunMobil,jumlahDownPayment,jumlahPinjaman,tenorCicilan);

    }
    public String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }
    public boolean saveVehicleType(String data){

        if(!data.isEmpty() && data.matches("[a-zA-Z]+")){
            data = data.toLowerCase();
            if(data.equalsIgnoreCase("mobil")){
                credit = new CarCredit();
            }else if(data.equalsIgnoreCase("motor")){
                credit = new BikeCredit();
            }

            if(credit != null){
                credit.setVehicleType(data);
                return true;
            }
        }
        return false;
    }
    public boolean saveVehicleCondition(String data){
        data = data.toLowerCase();
        if(credit.validateVehicleCondition(data)){
            credit.setVehicleCondition(data);
            return true;
        }
        return false;
    }
    public boolean saveVehicleYear(String data){
        if(credit.validateVehicleYear(data)){
            credit.setVehicleYear(data);
            return true;
        }
        return false;
    }
    public boolean saveLoan(String data){
        try{
            if(credit.validateLoan(Integer.parseInt(data))){
                credit.setLoan(Integer.parseInt(data));
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
    public boolean saveTenor(String data){
        try{
            if(credit.validateTenor(Integer.parseInt(data))){
                credit.setTenor(Integer.parseInt(data));
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
    public boolean saveDP(String data){
        try{
            if(credit.validateDp(Integer.parseInt(data))){
                credit.setDp(Integer.parseInt(data));
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }


    public List<HashMap<String,String>> getInstallment(){
        installment.clear();

        HashMap<String,String> hMap;
        float sisa = 0;
        float lastInterest = 0;

        for(int i = 0 ; i < credit.getTenor();i++){
            float loanSaja = 0 ;
            float payment = 0;
            float sukuBunga = 0;
            float yearlyPayment = 0;
            int monthlyPayment = 0;
            if(i == 0) {
                loanSaja = credit.getLoan()- credit.getDp(); //75
                sukuBunga = credit.getBaseInterest();
                lastInterest = sukuBunga;

            }else if(i == 1 || i % 2 != 0){
                loanSaja = sisa;
                sukuBunga = lastInterest + credit.getInterest1();
                lastInterest = sukuBunga;

            }else{
                loanSaja = sisa;
                sukuBunga = lastInterest + credit.getInterest2();
                lastInterest = sukuBunga;
            }
            payment = loanSaja + (loanSaja * sukuBunga / 100); //81 58 31
            yearlyPayment = payment/(credit.getTenor()-i); //27 29 31
            monthlyPayment = (int)yearlyPayment/12; //2.25 2,43 2.641

            sisa = payment - yearlyPayment; //54 29

            hMap = new HashMap<String,String>();

            hMap.put("payment",String.format("%,.2f", (double)monthlyPayment));
            hMap.put("suku_bunga",String.format("%.1f", (double)sukuBunga));

            installment.add(hMap);
        }
        return installment;
    }
}
