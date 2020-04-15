package com.example.al;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static java.lang.Character.toLowerCase;
import static java.lang.Math.log10;


public class fcfs_out extends AppCompatActivity {

    ArrayList<proccess> List;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcfs_out);
        TextView fcfs_list = findViewById(R.id.fcfs_list);

        List = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList("list");
        assert List != null;

        //List.sort(new arrival());

        Collections.sort(List,new arrival());

        //System.out.println(List.size());
        //for(int i = 0 ; i < List.size() ; i ++)
            //fcfs_list.setText(List.get(i).name+" "+List.get(i).at+" "+List.get(i).bt);


        int n = List.size();

        ArrayList<String> pro_list = new ArrayList<>(n*2 );

        double[] times = new double [n*2 + 1] ;

        double rt = 0;

        int ti=0;



        times[ti++] = rt;

        for(int i = 0 ; i < n ; i++)
        {
            if(List.get(i).at > rt)
            {

                pro_list.add("IDLE");

                rt = List.get(i).at; // 2

                times[ti++] = rt;
                pro_list.add(List.get(i).name);

                rt+= List.get(i).bt;
                times[ti++] = rt;

            }
            else
            {
                pro_list.add(List.get(i).name);
                rt+= List.get(i).bt;
                times[ti++] = rt;
            }
        }


        StringBuilder ans= new StringBuilder();

        ans.append("\n\n*****************     Gantt Chart    *****************\n\n");

        for(int i = 0 ; i < pro_list.size() ; i ++) ans.append("---------");
        ans.append("\n");

        for(int i = 0 ; i < pro_list.size() ; i ++){
            if(toLowerCase( pro_list.get(i).charAt(0) )=='p') ans.append("|  ").append(pro_list.get(i)).append("  ");
            else ans.append("| ").append(pro_list.get(i)).append(" ");
        }
        ans.append("|\n");

        for(int i = 0 ; i < pro_list.size() ; i ++) ans.append("---------");
        ans.append("\n");



        for(int i = 0 ; i < ti ; i ++){
            if((int)(log10(times[i])+1) == 1 || times[i]==0 ) ans.append((int) times[i]).append("      ");
            else ans.append((int) times[i]).append("     ");
        }
        ans.append("\n\n");

        ans.append("\n\n*****************     Waiting times    *****************\n\n");

        int wt = 0 ;

        int ind = 0;

        for(int i = 0 ; i < pro_list.size() ; i ++)
        {
            if(toLowerCase( pro_list.get(i).charAt(0) )=='p'){
                //printf("%s : %d\n", pro_list[i] ,(times[i]-name[ind++].arrival_time) );
                ans.append(pro_list.get(i)).append(" : ").append(times[i] - List.get(ind++).at).append('\n');
                wt+=(times[i]-List.get(ind-1).at);
            }
        }
        //printf("\n");
        ans.append('\n');


        //printf("Average Waiting time : %.2lf\n\n\n",( double(wt)/(double)n));
        ans.append("Average Waiting time : ").append( (double)(wt)/(double) (n) ).append("\n\n\n");








        //printf("\n\n*****************     Turnaround times    *****************\n\n");
        ans.append("\n\n****************   Turnaround times  ****************\n\n");

        int tt = 0 ;

        ind = 0;

        for(int i = 0 ; i < pro_list.size() ; i ++)
        {
            if(toLowerCase( pro_list.get(i).charAt(0) )=='p'){
                //printf("%s : %d\n", pro_list[i] ,(times[i+1]-name[ind++].arrival_time) );
                ans.append(pro_list.get(i)).append(" : ").append(times[i+1] - List.get(ind++).at).append('\n');
                tt+=(times[i+1]-List.get(ind-1).at);
            }
        }
        //printf("\n");
        ans.append('\n');


        //printf("Average TT : %.2lf\n",( (double)tt / (double) n ));
        ans.append("Average TT : ").append( (double)(tt)/(double) (n) ).append("\n\n\n");

        //printf("\n\n");//



        fcfs_list.setText(ans.toString());


    }


    private class arrival implements java.util.Comparator<proccess> {
        @Override
        public int compare(proccess a, proccess b) {
            return (int)(a.at - b.at);
        }
    }
}
