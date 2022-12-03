package com.example.werg23u;

public class studentitem {
    private String roll;
    private String Name;
    private String status;

    public studentitem(String roll, String name) {
        this.roll = roll;
        this.Name = name;
        status= "";
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
