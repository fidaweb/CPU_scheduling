package com.example.calebcurry;

import java.io.Serializable;

public class Process implements Serializable {
    public String id;
    public int burstTime=0;
    public int arrivalTime;
    public int priority=0;
    public int waitingTime=0;
    public int firstStart=1;
    public int elapsed=0;
    public int flag=0;
    Process(){

    }
    Process(String id1, int burstTime1, int arrivalTime1, int priority1){
        this.id=id1;
        this.burstTime=burstTime1;
        this.arrivalTime=arrivalTime1;
        this.priority=priority1;
    }


    Process(String id1,int burstTime1,int arrivalTime1){
        id=id1;
        burstTime=burstTime1;
        arrivalTime=arrivalTime1;

    }

    public int getAttribute(String attribute) {
        switch (attribute) {
            case "burstTime":
                return burstTime;
            case "arrivalTime":
                return arrivalTime;
            case "priority":
                return priority;


        }
        return -1;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
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

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

}
