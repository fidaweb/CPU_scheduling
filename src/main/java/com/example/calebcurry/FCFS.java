package com.example.calebcurry;

import android.util.Log;

import java.util.ArrayList;

public class FCFS {

    public static String run(ArrayList<Process> q) {
        int count = 0;
        int time = 0;
        String sequence="";

        int x=Utility.greater_than_maximum_value(q,"arrivalTime");

        ArrayList<Process> waiting=new ArrayList<Process>();
        while(count!=q.size()){

            Utility.receiveArrivingProcesses(waiting,q,time);

            if(!waiting.isEmpty()){
                if(waiting.get(count).burstTime==0){
                    count++;
                }
                waiting.get(count).burstTime--;
                Utility.add_waiting_time(q,waiting.get(count).id,time);
                sequence=sequence+waiting.get(count).id+"_";
                Log.d("fcfs",waiting.get(count).id);
                System.out.println(waiting.get(count).id);
            }
            count=0;
            count=Utility.countTerminatedProcesses(waiting,count);
            time++;
        }

        return sequence;
    }



}
