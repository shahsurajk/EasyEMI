package com.madscientists.easyemi.model;

import java.util.Calendar;

/**
 * Created by madscientist on 22/9/17.
 */

public class EMIData {

    private Calendar emiDate;
    private int daysCount;
    private double discountFactor;
    private double discountedCF;
    private double emi;

    public EMIData() {
    }

    @Override
    public String toString() {
        return "EMIData{" +
                "emiDate='" + emiDate + '\'' +
                ", daysCount=" + daysCount +
                ", discountFactor=" + discountFactor +
                ", discountedCF=" + discountedCF +
                ", emi=" + emi +
                '}';
    }

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

    public double getDiscountFactor() {
        return discountFactor;
    }

    public void setDiscountFactor(double discountFactor) {
        this.discountFactor = discountFactor;
    }

    public double getDiscountedCF() {
        return discountedCF;
    }

    public void setDiscountedCF(double discountedCF) {
        this.discountedCF = discountedCF;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public Calendar getEmiDate() {
        return emiDate;
    }

    public void setEmiDate(Calendar emiDate) {
        this.emiDate = emiDate;
    }
}
