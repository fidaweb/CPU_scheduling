package com.example.calebcurry;

import java.util.ArrayList;

public class RoundRobin {
    public static String run(ArrayList<Process> q, int timeQuantum){
        int time=0;
        int count=0;
        int current=0;
        int minArrival=0;
        int minID;
        String sequence="";
        int round=0;

        int x=Utility.greater_than_maximum_value(q,"arrivalTime");
        ArrayList<Process> waiting=new ArrayList<Process>();
        while(count!=q.size()){

            Utility.receiveArrivingProcesses(waiting,q,time);
            if(!waiting.isEmpty()) {

                if (waiting.get(current).burstTime == 0 || (waiting.get(current).firstStart - time) % timeQuantum == 0) {

                    minArrival = x + 1;
                    minID = -1;


                    // done = 0;

                    for (int i = 0; i < waiting.size(); i++) {

                        if (waiting.get(i).burstTime > 0 && minArrival > waiting.get(i).arrivalTime && waiting.get(i).flag == round) {
                            minID = i;
                            minArrival = waiting.get(i).arrivalTime;

                            // done = 1;

                        }
                    }
                    if (minID != -1) {

                        current = minID;
                        waiting.get(current).firstStart = time;
                        waiting.get(current).flag++;


                    }

                    else {
                        round++;
                    }

                }

                if (waiting.get(current).burstTime != 0) {
                    System.out.println(waiting.get(current).id);
                    Utility.add_waiting_time(q,waiting.get(current).id,time);
                    sequence=sequence+waiting.get(count).id+"_";
                    waiting.get(current).burstTime--;
                }
            }
            count=0;

            count=Utility.countTerminatedProcesses(waiting,count);
            time++;
        }
        return sequence;
    }

}
