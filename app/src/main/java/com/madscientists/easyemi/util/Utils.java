package com.madscientists.easyemi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by madscientist on 21/9/17.
 */

public class Utils {

    public static double calculateEMIByPMT(long amount, double rate, double term) {
//        double v = (1+(rate/12));
//        double t = (-(term/12)*12);
//        double result=(amount*(rate/12))/(1-Math.pow(v,t));
//        return result;
        rate = rate / (12 * 100);
        return (amount * rate * Math.pow(1 + rate, term)) / (Math.pow(1 + rate, term) - 1);
    }

    public static double getDiscountFactor(double rate, int days) {
        return Math.pow((1 + rate), -(days / 360));
    }

    public static double getDiscountedCashFlow(double emi, double discountFactor) {
        return emi * discountFactor;
    }

    public static boolean validateEMI() {
        return false;
    }

    public static int getDaysDifference(Date startDate, Date endDate) {
        int diff = -1;
        if (endDate.after(startDate)) {
            diff = (int) ((endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000));
        }
        return diff;
    }

    public static Date addMonthsToDate(Date currentDate, int monthsToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, monthsToAdd);
        return calendar.getTime();
    }

    public static String getDateInString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return sdf.format(date);
    }
}
