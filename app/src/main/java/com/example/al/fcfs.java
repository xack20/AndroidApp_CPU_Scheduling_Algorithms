package com.example.al;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static java.lang.Character.toLowerCase;
import static java.lang.Math.log10;


public class fcfs extends AppCompatActivity {

    ArrayList< proccess > List;
    ArrayList< Integer > intList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        TextView fcfs_list = findViewById(R.id.list);

        List = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList("list");
        assert List != null;


        Collections.sort(List, new arrival() );
        //Collections.sort(intList);


        int n = List.size();

        ArrayList<String> pro_list = new ArrayList<>(n*2 );

        double[] times = new double [n*2 + 1] ;

        double rt = 0;

        int ti = 0;


        times[ti++] = rt;

        for(int i = 0 ; i < n ; i++)
        {
            if(List.get(i).at > rt)
            {

                pro_list.add("IDLE");

                rt = List.get(i).at; // 2

                times[ti++] = rt;

            }
            pro_list.add(List.get(i).name);
            rt+= List.get(i).bt;
            times[ti++] = rt;
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

                ans.append(pro_list.get(i)).append(" : ").append(times[i] - List.get(ind++).at).append('\n');
                wt+=(times[i]-List.get(ind-1).at);
            }
        }

        ans.append('\n');


        ans.append("Average Waiting time : ").append( (double)(wt)/(double) (n) ).append("\n\n\n");









        ans.append("\n\n****************   Turnaround times  ****************\n\n");

        int tt = 0 ;

        ind = 0;

        for(int i = 0 ; i < pro_list.size() ; i ++)
        {
            if(toLowerCase( pro_list.get(i).charAt(0) )=='p'){
                ans.append(pro_list.get(i)).append(" : ").append(times[i+1] - List.get(ind++).at).append('\n');
                tt+=(times[i+1]-List.get(ind-1).at);
            }
        }

        ans.append('\n');


        ans.append("Average TT : ").append( (double)(tt)/(double) (n) ).append("\n\n\n");



        fcfs_list.setText(ans.toString());


    }


    private static class arrival implements java.util.Comparator<proccess> {
        @Override
        public int compare(proccess a, proccess b) {
            return (int)(a.at - b.at);
        }
    }
}
