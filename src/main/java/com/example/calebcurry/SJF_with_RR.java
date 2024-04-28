package com.example.calebcurry;

import java.util.ArrayList;

public class SJF_with_RR {

    public static String SJFthenRR(ArrayList<Process> q, int time_quantum){
        int count = 0;
        int time = 0;
        String sequence="";
        int minID=-1;
        int y=Utility.greater_than_maximum_value(q,"burstTime");
        int x=Utility.greater_than_maximum_value(q,"arrivalTime");
        boolean rrActive=false;


        ArrayList<Process> waiting=new ArrayList<Process>();
        ArrayList<Process> rrArray=new ArrayList<Process>();
        while(count!=q.size()){
            count = 0;

            Utility.receiveArrivingProcesses(waiting,q,time);
            if(!waiting.isEmpty()) {

                if (!rrActive) {
                    minID = Utility.get_live_process_with_minimum_value(waiting, y, 0, "burstTime");

                    rrActive=Utility.checkForTies(waiting,rrArray,minID,"burstTime");
                }



                if (rrActive) {

                    minID = Utility.get_live_process_with_minimum_value_hybrid(waiting,rrArray,x,"rr", time_quantum);

                    if (minID == -1) {
                        rrActive=false;
                        rrArray.clear();
                    }

                }
                if (minID != -1) {
                    System.out.println( waiting.get(minID).id);
                    sequence=sequence+waiting.get(count).id+"_";
                    waiting.get(minID).burstTime--;
                }
            }
            count=Utility.countTerminatedProcesses(waiting,count);
            time++;
        }
        return sequence;
    }

}
