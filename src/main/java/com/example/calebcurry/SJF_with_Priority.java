package com.example.calebcurry;

import java.util.ArrayList;

public class SJF_with_Priority {
    public static String SJFthenPriority(ArrayList<Process> q){
        int count = 0;
        int time = 0;
        String sequence="";
        int minID=-1;

        int y=Utility.greater_than_maximum_value(q,"burstTime");
        int x=Utility.greater_than_maximum_value(q,"priority");

        boolean priorityActive=false;
        ArrayList<Process> priorityArray = new ArrayList<Process>();

        ArrayList<Process> waiting = new ArrayList<Process>();

        while(count!= q.size()){

            count = 0;

            Utility.receiveArrivingProcesses(waiting,q,time);

            if(!waiting.isEmpty()) {
                minID = Utility.get_live_process_with_minimum_value(waiting, y, 0, "burstTime");

                priorityArray.clear();
                priorityActive=Utility.checkForTies(waiting,priorityArray,minID,"burstTime");


                if (priorityActive) {
                    minID= Utility.get_live_process_with_minimum_value_hybrid(waiting,priorityArray,x,"priority",3);


                }

                System.out.println(waiting.get(minID).id);
                waiting.get(minID).burstTime--;
                sequence=sequence+waiting.get(count).id+"_";
            }

            time++;
            count=Utility.countTerminatedProcesses(waiting,count);




        }
        return  sequence;
    }

    public static String PrioritythenSJF(ArrayList<Process> q){
        int count = 0;
        int time = 0;
        String sequence="";

        int minID;
        int y=Utility.greater_than_maximum_value(q,"burstTime");
        int x=Utility.greater_than_maximum_value(q,"priority");
        boolean SJFactive=false;

        ArrayList<Process> waiting =new ArrayList<Process>();
        ArrayList<Process> sjfArray =new ArrayList<Process>();

        while(count!= q.size()){

            count = 0;

            Utility.receiveArrivingProcesses(waiting,q,time);

            if(!waiting.isEmpty()) {
                minID = Utility.get_live_process_with_minimum_value(waiting, x, 0, "priority");

                sjfArray.clear();
                SJFactive=Utility.checkForTies(waiting,sjfArray,minID,"priority");

                if(SJFactive){
                    minID=Utility.get_live_process_with_minimum_value_hybrid(waiting,sjfArray,y,"burstTime",3);
                }

                System.out.println(waiting.get(minID).id);
                waiting.get(minID).burstTime--;
                sequence=sequence+waiting.get(count).id+"_";
            }

            time++;
            count=Utility.countTerminatedProcesses(waiting,count);




        }
        return sequence;
    }
}
