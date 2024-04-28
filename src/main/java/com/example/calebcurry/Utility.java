package com.example.calebcurry;

import java.util.ArrayList;

public class Utility {
    public static int get_live_process_with_minimum_value(ArrayList<Process> waiting, int minValue, int minID, String attribute){


        for (int i = 0; i < waiting.size(); i++) {

            if (waiting.get(i).burstTime > 0 && waiting.get(i).getAttribute(attribute) < minValue) {
                minValue = waiting.get(i).getAttribute(attribute);
                minID = i;
            }
        }


        return minID;
    }

    public static int greater_than_maximum_value(ArrayList<Process> q,String attribute){
        int x=0;


        for(int i=0;i<q.size();i++){
            x+=q.get(i).getAttribute(attribute);
        }


        return x+1;
    }

    public static void receiveArrivingProcesses(ArrayList<Process> w, ArrayList<Process> q, int time){

        for(int i=0;i<q.size();i++){
            if(time==q.get(i).arrivalTime){
                w.add(q.get(i));
            }
        }

    }

    public static void add_waiting_time(ArrayList<Process> p,String id,int time){
        for(int i=0;i<p.size();i++){
            if(p.get(i).burstTime!=0 && !p.get(i).id.equals(id) && p.get(i).arrivalTime<=time){
                p.get(i).waitingTime++;
            }

        }
    }

    public static int countTerminatedProcesses(ArrayList<Process> w,int count){

        for (int i = 0; i < w.size(); i++) {
            if (w.get(i).burstTime == 0) {
                count++;
            }
        }
        return count;
    }
    public static String execute_process(ArrayList<Process> p,ArrayList<Process> q,int index,int time,String sequence){
        System.out.println(p.get(index).id);
        Utility.add_waiting_time(q,p.get(index).id,time);
        p.get(index).burstTime--;
        return sequence+p.get(index).id+"_";
    }

    public static int get_live_process_with_minimum_value_hybrid(ArrayList<Process> p,ArrayList<Process> tempArray,int minValue,String attribute,int timequantum){

        Process temp=new Process();
        temp.id="";
        int index=-1;

        if(attribute.equals("rr")){
            for(int j=0;j<tempArray.size();j++) {
                if (tempArray.get(j).elapsed < timequantum && tempArray.get(j).burstTime > 0 && minValue > tempArray.get(j).arrivalTime) {
                    temp = tempArray.get(j);

                    minValue=temp.arrivalTime;
                    index = j;
                }
            }

        }
        else {
            for(int j=0;j<tempArray.size();j++) {
                if (tempArray.get(j).burstTime > 0 && minValue > tempArray.get(j).getAttribute(attribute)) {
                    temp = tempArray.get(j);
                    minValue=temp.getAttribute(attribute);
                    index = j;
                }
            }
        }


        for(int i=0;i<p.size();i++){
            if(p.get(i).id.equals(temp.id)){
                index=i;
            }
        }
        if(attribute.equals("rr") && index!=-1){
            p.get(index).elapsed++;
        }
        if(index==-1){
            for (int i=0; i < p.size(); i++) {
                p.get(i).elapsed=0;
            }
        }




        return index;
    }

    public  static boolean checkForTies(ArrayList<Process> p,ArrayList<Process> tempArray,int index,String attribute){

        boolean active=false;
        tempArray.add(p.get(index));
        for (int i = 0; i < p.size(); i++) {
            if (index != i && p.get(i).getAttribute(attribute) == p.get(index).getAttribute(attribute) && p.get(i).burstTime > 0) {

                active=true;
                tempArray.add(p.get(i));


            }
        }
        return active;
    }


}
