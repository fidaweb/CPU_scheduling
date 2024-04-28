package com.example.calebcurry;

import java.io.Serializable;

public class FormProcess implements Serializable {

    public String processID;
    public int arrivalTime;
    public int burstTime;
    public int priority;



    public FormProcess() {

    }

    public FormProcess(String processID, int arrivalTime,int burstTime,int priority) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime=burstTime;
        this.priority=priority;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
}
