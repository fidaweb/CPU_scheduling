package com.example.calebcurry;

import java.util.ArrayList;

public class Priority {

    public static String run(ArrayList<Process> q){
        int time=0;
        int count=0;
        int current=0;
        String sequence="";


        int x=Utility.greater_than_maximum_value(q,"priority");
        ArrayList<Process> waiting=new ArrayList<Process>();
        while(count!=q.size()){

            Utility.receiveArrivingProcesses(waiting,q,time);

            if(!waiting.isEmpty()){
                if(waiting.get(current).burstTime==0){

                    current=Utility.get_live_process_with_minimum_value(waiting,x,0,"priority");;

                }
                System.out.println(waiting.get(current).id);
                Utility.add_waiting_time(q,waiting.get(current).id,time);
                sequence=sequence+waiting.get(count).id+"_";
                waiting.get(current).burstTime--;}
            count=0;

            count=Utility.countTerminatedProcesses(waiting,count);
            time++;
        }
        return sequence;
    }

    public static String run_preemptive(ArrayList<Process> q) {
        int count = 0;
        int time = 0;
        int minID;
        int x=Utility.greater_than_maximum_value(q,"priority");
        ArrayList<Process> waiting = new ArrayList<Process>();
        String sequence="";


        while (count != q.size()) {
            count = 0;

            Utility.receiveArrivingProcesses(waiting,q,time);

            if(!waiting.isEmpty()) {
                minID = Utility.get_live_process_with_minimum_value(waiting, x, 0, "priority");


                System.out.println(waiting.get(minID).id);
                Utility.add_waiting_time(q,waiting.get(minID).id,time);
                waiting.get(minID).burstTime--;
                sequence=sequence+waiting.get(count).id+"_";
            }

            time++;
            count=Utility.countTerminatedProcesses(waiting,count);


        }
        return sequence;
    }

}
