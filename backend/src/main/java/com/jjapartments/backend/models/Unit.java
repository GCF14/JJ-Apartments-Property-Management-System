package com.jjapartments.backend.models;

public class Unit {

    private int id;
    private String unit_number;
    private boolean is_occupied;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitNumber() {
        return unit_number;
    }

    public void setUnitNumber(String unit_number) {
        this.unit_number = unit_number;
    }

    public boolean isOccupied() {
        return is_occupied;
    }

    public void setIsOccupied(boolean is_occupied) {
        this.is_occupied = is_occupied;
    }
}

