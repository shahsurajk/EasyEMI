package com.madscientists.easyemi.model;

/**
 * Created by madscientist on 22/9/17.
 */

public class LoanSummary {
    private long totalAmountPaid;
    private float totalInterestPaid;
    private long loanAmount;
    private int term;


    public LoanSummary(long totalAmountPaid, float totalInterestPaid, long loanAmount, int term) {
        this.totalAmountPaid = totalAmountPaid;
        this.totalInterestPaid = totalInterestPaid;
        this.loanAmount = loanAmount;
        this.term = term;
    }

    public float getTotalInterestPaid() {
        return totalInterestPaid;
    }

    public void setTotalInterestPaid(int totalInterestPaid) {
        this.totalInterestPaid = totalInterestPaid;
    }

    public long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public long getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(long totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }
}
