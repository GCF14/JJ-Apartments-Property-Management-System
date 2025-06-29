package com.jjapartments.backend.models;

public class Utility {

    private int id;
    private String type;
    private float previousReading;
    private float currentReading;
    private float totalMeter;
    private float totalAmount;
    private String dueDate;
    private String monthOfStart;
    private String monthOfEnd;
    private boolean isPaid;
    private String paidAt;
    private int tenantId;
    private int rateId;
    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPreviousReading() {
        return this.previousReading;
    }

    public void setPreviousReading(float previousReading) {
        this.previousReading = previousReading;
    }

    public float getCurrentReading() {
        return this.currentReading;
    }

    public void setCurrentReading(float currentReading) {
        this.currentReading = currentReading;
    }

    public float getTotalMeter() {
        return this.totalMeter;
    }

    public void setTotalMeter(float totalMeter) {
        this.totalMeter = totalMeter;
    }

    public float getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getMonthOfStart() {
        return this.monthOfStart;
    }

    public void setMonthOfStart(String monthOfStart) {
        this.monthOfStart = monthOfStart;
    }

    public String getMonthOfEnd() {
        return this.monthOfEnd;
    }

    public void setMonthOfEnd(String monthOfEnd) {
        this.monthOfEnd = monthOfEnd;
    }

    public boolean getIsPaid() {
        return this.isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getPaidAt() {
        return this.paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public int getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getRateId() {
        return this.rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

}
