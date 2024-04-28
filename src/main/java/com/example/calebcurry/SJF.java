package com.example.calebcurry;

import java.util.ArrayList;

public class SJF{

    public static  String run(ArrayList<Process> q){
        int time=0;
        int count=0;
        String sequence="";
        int current=0;
        int minJob=0;
        int minID;
        int temp=0;

        int x=Utility.greater_than_maximum_value(q,"burstTime");
        ArrayList<Process> waiting=new ArrayList<Process>();
        while(count!=q.size()){

            Utility.receiveArrivingProcesses(waiting,q,time);

            if(!waiting.isEmpty()){
                if(waiting.get(current).burstTime==0){

                    current = Utility.get_live_process_with_minimum_value(waiting,x,0,"burstTime");

                }
                System.out.println(waiting.get(current).id);
                Utility.add_waiting_time(q,waiting.get(current).id,time);
                sequence=sequence+waiting.get(count).id+"_";
                waiting.get(current).burstTime--;
            }
            count=0;

            count=Utility.countTerminatedProcesses(waiting,count);
            time++;
        }
        return sequence;
    }

    public static String run_preemptive_new(ArrayList<Process> q) {
        int count = 0;
        int time = 0;
        int waitProcess = 0;
        String sequence="";
        int minID;
        int x=Utility.greater_than_maximum_value(q,"burstTime");
        ArrayList<Process> waiting = new ArrayList<Process>();


        while (count != q.size()) {

            count = 0;

            Utility.receiveArrivingProcesses(waiting,q,time);

            if(!waiting.isEmpty()) {
                minID = Utility.get_live_process_with_minimum_value(waiting,  x, 0, "burstTime");


                System.out.println(waiting.get(minID).id);
                Utility.add_waiting_time(q,waiting.get(minID).id,time);
                sequence=sequence+waiting.get(count).id+"_";
                waiting.get(minID).burstTime--;
            }

            time++;
            count=Utility.countTerminatedProcesses(waiting,count);


        }
        return sequence;
    }


}
