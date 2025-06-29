package com.jjapartments.backend.models;

public class Payment {

    private int id;
    private int tenantId;
    private String reason;
    private String modeOfPayment;
    private float amount;
    private String dueDate;
    private String monthOfStart;
    private String monthOfEnd;
    private boolean isPaid;
    private String paidAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isIsPaid() {
        return this.isPaid;
    }

    public String getModeOfPayment() {
        return this.modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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

}
