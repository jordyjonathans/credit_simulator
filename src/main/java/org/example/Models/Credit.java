package org.example.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Credit {
    private String vehicleType,vehicleYear,vehicleCondition;
    private int loan,tenor,dp;
    private float baseInterest,interest1,interest2;

    public Credit() {
        setInterest1(0.1F);
        setInterest2(0.5F);
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public int getLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        this.loan = loan;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public float getBaseInterest() {
        return baseInterest;
    }

    public void setBaseInterest(float baseInterest) {
        this.baseInterest = baseInterest;
    }

    public float getInterest1() {
        return interest1;
    }

    public void setInterest1(float interest1) {
        this.interest1 = interest1;
    }

    public float getInterest2() {
        return interest2;
    }

    public void setInterest2(float interest2) {
        this.interest2 = interest2;
    }


    public boolean validateVehicleCondition(String condition){
        boolean result = !condition.isEmpty() &&
                condition.matches("[a-zA-Z]+")&&
                (condition.equalsIgnoreCase("baru") ||
                condition.equalsIgnoreCase("bekas")) ;
        return result;
    }
    public boolean validateVehicleYear(String year){
        boolean result = !year.isEmpty() && year.matches("\\d+") && year.length() == 4;
        return result;
    }
    public boolean validateLoan(int loan){
        boolean result = loan > 0 && loan <= 1000000000;
        return result;
    }
    public boolean validateTenor(int tenor){
        boolean result = tenor > 0 && tenor <= 6;
        return result;
    }
    public boolean validateDp(float dp){
        float pctgDp = 0;
        if(vehicleCondition.equalsIgnoreCase("bekas")) pctgDp = 35;
        else pctgDp = 25;

        float minDp = pctgDp/100 * loan;
        if(dp >= minDp) return true;
        return false;
    }

}
