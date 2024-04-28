package com.example.calebcurry;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityProcesses extends AppCompatActivity implements Serializable {



    RecyclerView recyclerProcesses;
    ArrayList<Process> processList = new ArrayList<>();
    //String algorithm="";
    String sequence;

    private List<String> xValues= Arrays.asList("Maths","Science","English","it");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricketers);

//        recyclerProcesses = findViewById(R.id.recycler_cricketers);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        recyclerCricketers.setLayoutManager(layoutManager);

        processList = (ArrayList<Process>) getIntent().getExtras().getSerializable("list");
        String algorithm= getIntent().getStringExtra("algorithm") ;



        switch (algorithm){
            case "FCFS":
                sequence=FCFS.run(processList);
                break;
            default:
                sequence=FCFS.run(processList);
                break;

        }
        Log.d("sequence",sequence);
        TextView display=findViewById(R.id.average_waiting_time);
        display.setText(sequence);

        String[] seq=sequence.split("_");
        int temp=0;
        ArrayList<String> labelsTemp=new ArrayList<String>();
        labelsTemp.add(seq[0]);
        ArrayList<Float> floatTemp=new ArrayList<Float>();
        floatTemp.add(1f);
        for(int i=1;i< seq.length;i++){
            temp=floatTemp.size()-1;
            if(seq[i-1].equals(seq[i])){
                floatTemp.set(temp,floatTemp.get(temp)+1f);
            }
            else{
                labelsTemp.add(seq[i]);
                floatTemp.add(1f);
            }
        }
        String[] labels=labelsTemp.toArray(new String[0]);
        float[] floats=new float[floatTemp.size()];
        for(int i=0;i<floatTemp.size();i++){
            floats[i]=floatTemp.get(i).floatValue();
            Log.d("che",labels[i]);
        }


//
//        recyclerCricketers.setAdapter(new CricketerAdapter(cricketersList));


        BarChart barChart=findViewById(R.id.chart);
        barChart.getAxisRight().setDrawLabels(true);

        ArrayList<BarEntry> entries=new ArrayList<>();




//        entries.add(new BarEntry(0,new float[]{50,80,90}));




      // entries.add(new BarEntry(0,new float[]{4f,9f,6f,7f,1f,5f,3f,4f}));
        entries.add(new BarEntry(0,floats));




//        entries.add(new BarEntry(2,65f));
//        entries.add(new BarEntry(3,45f));


//        YAxis yAxis=barChart.getAxisLeft();
//        yAxis.setAxisMaximum(0f);
//        yAxis.setAxisMaximum(0f);
//        yAxis.setAxisLineWidth(2f);
//        yAxis.setAxisLineColor(Color.BLACK);
//        yAxis.setLabelCount(10);







        BarDataSet dataSet=new BarDataSet(entries,"Subject");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        dataSet.setStackLabels(new String[]{"oajnd","ajsn","asnd","oajnd","ajsn","asnd","oajnd","ajsn"});
        dataSet.setStackLabels(labels);

        BarData barData=new BarData(dataSet);



        barChart.setData(barData);
        barChart.setFitBars(true);







//        barChart.getDescription().setEnabled(false);
//        barChart.invalidate();
//
//        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
//        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        barChart.getXAxis().setGranularity(1f);
//        barChart.getXAxis().setGranularityEnabled(true);



    }



}
