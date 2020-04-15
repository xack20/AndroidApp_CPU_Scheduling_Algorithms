package com.example.al;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import static java.lang.Character.toLowerCase;
import static java.lang.Math.log10;

public class sjf_out extends AppCompatActivity {
    ArrayList<proccess> List;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjf_out);

        TextView out_list = findViewById(R.id.sjf_list);


        List = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList("list");
        assert List != null;


        int sz = List.size();
        Collections.sort(List,new COMP());

        ArrayList<String> pro_list = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();

        times.add(0.0);

        double ct = 0.0;

        int n = sz;
        while(n != 0)
        {
            int ind = 0;
            int f= 0;
            double val  = 1<<29 ,tm = 0.0;
            for(int i = 0 ; i < sz ; i ++ )
            {
                if(List.get(i).at <= ct && List.get(i).bt < val && List.get(i).bt >= 0.0 )
                {
                    val = List.get(i).bt;
                    ind = i;
                    f=1;
                }
                else if (List.get(i).at > ct) {
                    tm = List.get(i).at;
                    break;
                }
            }

            if(f==0)
            {
                ct=tm;
                pro_list.add("Idle");
                times.add(ct);
            }
            else{
                ct+=List.get(ind).bt;
                List.get(ind).bt = -1.0;
                pro_list.add(List.get(ind).name);
                times.add(ct);
                n--;
            }
        }

        StringBuilder ans = new StringBuilder();


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



        for(int i = 0 ; i < times.size() ; i ++){
            if((int)(log10( times.get(i).intValue() +1) ) == 1 || times.get(i) == 0 ) ans.append( times.get(i).intValue() ).append("      ");
            else ans.append( times.get(i).intValue() ).append("     ");
        }
        ans.append("\n\n");



        ans.append("\n\n*****************     Waiting times    *****************\n\n");

        int wt = 0 ;

        int ind = 0;

        for(int i = 0 ; i < pro_list.size() ; i ++)
        {
            if(toLowerCase( pro_list.get(i).charAt(0) )=='p'){
                //printf("%s : %d\n", pro_list[i] ,(times[i]-name[ind++].arrival_time) );
                ans.append(pro_list.get(i)).append(" : ").append( times.get(i) - List.get(ind++).at ).append('\n');
                wt+=(times.get(i)-List.get(ind-1).at);
            }
        }
        //printf("\n");
        ans.append('\n');


        //printf("Average Waiting time : %.2lf\n\n\n",( double(wt)/(double)n));
        ans.append("Average Waiting time : ").append( (double)(wt)/(double) (sz) ).append("\n\n\n");








        //printf("\n\n*****************     Turnaround times    *****************\n\n");
        ans.append("\n\n****************   Turnaround times  ****************\n\n");

        int tt = 0 ;

        ind = 0;

        for(int i = 0 ; i < pro_list.size() ; i ++)
        {
            if(toLowerCase( pro_list.get(i).charAt(0) )=='p'){
                //printf("%s : %d\n", pro_list[i] ,(times[i+1]-name[ind++].arrival_time) );
                ans.append(pro_list.get(i)).append(" : ").append(times.get(i+1) - List.get(ind++).at).append('\n');
                tt+=(times.get(i+1)-List.get(ind-1).at);
            }
        }
        //printf("\n");
        ans.append('\n');


        //printf("Average TT : %.2lf\n",( (double)tt / (double) n ));
        ans.append("Average TT : ").append( (double)(tt)/(double) (sz) ).append("\n\n\n");


        out_list.setText(ans);


    }
    private static class COMP implements Comparator<proccess> {
        @Override
        public int compare(proccess o1, proccess o2) {
            if (o1.at == o2.at)return (int)(o1.bt - o2.bt);
            else return (int) (o1.at- o2.at);
        }
    }

}
