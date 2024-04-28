package com.example.calebcurry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmitList;
    String algorithm;
    int processCount=0;

    List<String> algorithmList = new ArrayList<>();
    ArrayList<Process> processList = new ArrayList<Process>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitList = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSubmitList.setOnClickListener(this);


        algorithmList.add("Algorithm");
        algorithmList.add("FCFS");
        algorithmList.add("SJF");
        algorithmList.add("SJFPreemptive");
        algorithmList.add("Priority");
        algorithmList.add("PriorityPreemptive");
        algorithmList.add("RoundRobin");


        AppCompatSpinner spinnerTeam = (AppCompatSpinner)findViewById(R.id.spinner_team);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,algorithmList);
        spinnerTeam.setAdapter(arrayAdapter);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_add:

                addView();

                break;

            case R.id.button_submit_list:

                if(checkIfValidAndRead()){
                    processCount=0;
                    Intent intent = new Intent(MainActivity.this,ActivityProcesses.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",processList);
                    intent.putExtras(bundle);
                    intent.putExtra("algorithm",algorithm);
                    startActivity(intent);

                }

                break;

        }

    }

    private boolean checkIfValidAndRead() {
        processList.clear();
        boolean result = true;
        AppCompatSpinner spinnerTeam = (AppCompatSpinner)findViewById(R.id.spinner_team);
        for(int i=0;i<layoutList.getChildCount();i++){

            View processView = layoutList.getChildAt(i);

            TextView textViewId = (TextView) processView.findViewById(R.id.processID);
            EditText editTextArrivalTime = (EditText)processView.findViewById(R.id.arrivalTime);
            EditText editTextBurstTime = (EditText)processView.findViewById(R.id.burstTime);
            EditText editTextPriority = (EditText)processView.findViewById(R.id.priority);


            //AppCompatSpinner spinnerTeam = (AppCompatSpinner)cricketerView.findViewById(R.id.spinner_team);
      //      AppCompatSpinner spinnerTeam = (AppCompatSpinner)findViewById(R.id.spinner_team);

            Process formProcess = new Process();
            if(!textViewId.getText().toString().equals("")){
                Log.d("at",editTextArrivalTime.getText().toString());
                formProcess.setId(textViewId.getText().toString());
            }else {
                result = false;
                break;
            }

            if(!editTextArrivalTime.getText().toString().equals("")){
                Log.d("at",editTextArrivalTime.getText().toString());
                formProcess.setArrivalTime(Integer.parseInt(editTextArrivalTime.getText().toString()));
            }else {
                result = false;
                break;
            }
            if(!editTextBurstTime.getText().toString().equals("")){
                formProcess.setBurstTime(Integer.parseInt(editTextBurstTime.getText().toString()));
            }else {
                result = false;
                break;
            }
            if(!editTextPriority.getText().toString().equals("")){
                formProcess.setPriority(Integer.parseInt(editTextPriority.getText().toString()));
            }else {
                result = false;
                break;
            }



            processList.add(formProcess);

        }

        if(spinnerTeam.getSelectedItemPosition()!=0){
//            cricketer.setTeamName(algorithmList.get(spinnerTeam.getSelectedItemPosition()));
            algorithm=algorithmList.get(spinnerTeam.getSelectedItemPosition());
        }else {
            result = false;

        }

        if(processList.size()==0){
            result = false;
            Toast.makeText(this, "Add Processes First!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {

        final View cricketerView = getLayoutInflater().inflate(R.layout.row_add_cricketer,null,false);

       TextView textView = (TextView)cricketerView.findViewById(R.id.processID);
       processCount++;
       String processName="p"+Integer.toString(processCount);
       textView.setText(processName);
     //   AppCompatSpinner spinnerTeam = (AppCompatSpinner)cricketerView.findViewById(R.id.spinner_team);
        ImageView imageClose = (ImageView)cricketerView.findViewById(R.id.image_remove);

    //    ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,algorithmList);
    //    spinnerTeam.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
        });

        layoutList.addView(cricketerView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }

}