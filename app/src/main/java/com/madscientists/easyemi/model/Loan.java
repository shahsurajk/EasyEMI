package com.madscientists.easyemi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by madscientist on 21/9/17.
 */

public class Loan implements Parcelable {

    public static final Parcelable.Creator<Loan> CREATOR = new Parcelable.Creator<Loan>() {
        @Override
        public Loan createFromParcel(Parcel source) {
            return new Loan(source);
        }

        @Override
        public Loan[] newArray(int size) {
            return new Loan[size];
        }
    };
    private long amount;
    private double rate;
    private int term;
    private Date openDate;
    private Date repaymentDate;

    public Loan() {
    }

    public Loan(long amount, double rate, int term, Date openDate, Date repaymentDate) {
        this.amount = amount;
        this.rate = rate;
        this.term = term;
        this.openDate = openDate;
        this.repaymentDate = repaymentDate;
    }

    protected Loan(Parcel in) {
        this.amount = in.readLong();
        this.rate = in.readDouble();
        this.term = in.readInt();
        long tmpOpenDate = in.readLong();
        this.openDate = tmpOpenDate == -1 ? null : new Date(tmpOpenDate);
        long tmpRepaymentDate = in.readLong();
        this.repaymentDate = tmpRepaymentDate == -1 ? null : new Date(tmpRepaymentDate);
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.amount);
        dest.writeDouble(this.rate);
        dest.writeInt(this.term);
        dest.writeLong(this.openDate != null ? this.openDate.getTime() : -1);
        dest.writeLong(this.repaymentDate != null ? this.repaymentDate.getTime() : -1);
    }
}
