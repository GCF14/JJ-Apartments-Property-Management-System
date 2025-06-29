package com.jjapartments.backend.models;

public class MonthlyReport {
    
    private int id;
    private int year;
    private int month;
    private float totalEarnings;
    private float totalExpenses;
    private float netIncome;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public float getTotalEarnings() {
        return this.totalEarnings;
    }

    public void setTotalEarnings(float totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public float getTotalExpenses() {
        return this.totalExpenses;
    }

    public void setTotalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public float getNetIncome() {
        return this.netIncome;
    }

    public void setNetIncome(float netIncome) {
        this.netIncome = netIncome;
    }

}
