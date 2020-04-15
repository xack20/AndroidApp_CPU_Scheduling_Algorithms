package com.example.al;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;
import static java.lang.Math.log10;

public class rr_out extends AppCompatActivity {

    ArrayList<proccess> List;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rr_out);

        TextView out_list = findViewById(R.id.rr_list);

        List = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList("list");
        assert List != null;

        Collections.sort(List, new rrr());


        int sz = List.size();
        int n = sz;

        Collections.sort(List, new rrr());

        HashMap<String, Pair<Double, Double>> MP = new HashMap<>();
        for (proccess P : List) {
            Pair<Double, Double> val = new Pair<>(P.at, P.bt);
            MP.put(P.name, val);
        }

        ArrayList<String> pro_list = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();

        ArrayList<proccess> Q = new ArrayList<>();

        times.add(0.0);

        double ct = 0.0;
        int f = 1;


        while(sz != 0)
        {
            double tm = 0.0;

            for(int i = 0 ; i < n ; i ++)
            {
                if( ct >= List.get(i).at  && List.get(i).bt > 0.0 && !Q.contains(List.get(i)) ) Q.add(List.get(i));

                else if (ct < List.get(i).at){
                    tm = List.get(i).at;
                    break;
                }
            }

            if( ct > 0.0 &&  f==1  && Q.get(0).bt > 0.0 ) {
                Q.add(Q.get(0));
                Q.remove(0);
            }
            else if( ct > 0.0 && f==1 )Q.remove(0);

            if(Q.size()==0)
            {
                pro_list.add("Idle");
                times.add(tm);
                ct=tm;
                f=0;
            }
            else {

                pro_list.add(Q.get(0).name);

                ct += Math.min(Q.get(0).bt, Q.get(0).tq);
                times.add(ct);

                Q.get(0).bt-=Q.get(0).tq;

                if(Q.get(0).bt <= 0.0)sz--;
                f=1;

            }


        }

        StringBuilder ans = new StringBuilder();

        ArrayList<String> NA = new ArrayList<>();
        ArrayList<Double> TA = new ArrayList<>();

        TA.add(0.0);
        NA.add(pro_list.get(0));

        for (int i = 0; i < pro_list.size() - 1; i++) {
            if (!pro_list.get(i).equals(pro_list.get(i + 1))) {
                NA.add(pro_list.get(i + 1));
                TA.add(times.get(i + 1));
            }
        }
        TA.add(times.get(times.size() - 1));

        pro_list = NA;
        times = TA;




        ans.append("\n\n*****************     Gantt Chart    *****************\n\n");

        for (int i = 0; i < pro_list.size(); i++) ans.append("---------");
        ans.append("\n");

        for (String s : pro_list) {
            if (toUpperCase(s.charAt(0)) == 'P') ans.append("|  ").append(s).append("  ");
            else ans.append("| ").append(s).append(" ");
        }
        ans.append("|\n");

        for (int i = 0; i < pro_list.size(); i++) ans.append("---------");
        ans.append("\n");


        for (Double time : times) {
            if ((int) (log10(time.intValue() + 1)) == 1 || time == 0) ans.append(time.intValue()).append("      ");
            else ans.append(time.intValue()).append("     ");
        }
        ans.append("\n\n");





        HashMap<String, Boolean> mp = new HashMap<>();

        ArrayList<Pair<String, Double>> WT = new ArrayList<>(), TT = new ArrayList<>();

        for (int i = pro_list.size() - 1; i >= 0; i--) {
            if (!mp.containsKey(pro_list.get(i)) && !pro_list.get(i).equals("Idle")) {
                mp.put(pro_list.get(i), true);
                WT.add(new Pair<>(pro_list.get(i), times.get(i + 1) - (Objects.requireNonNull(MP.get(pro_list.get(i))).first + Objects.requireNonNull(MP.get(pro_list.get(i))).second)));
                TT.add(new Pair<>(pro_list.get(i), times.get(i + 1) - Objects.requireNonNull(MP.get(pro_list.get(i))).first));
            }
        }

        ans.append("\n\n*****************     Waiting times    *****************\n\n");
        double w_t = 0.0;
        for (Pair<String, Double> stringDoublePair : WT){
            ans.append(stringDoublePair.first).append(" ->  ").append(stringDoublePair.second).append('\n');
            w_t += stringDoublePair.second;
        }
        ans.append("\nAverage WT -> ").append( (w_t / WT.size())).append("\n");

        double t_t=0.0;
        ans.append("\n\n****************   Turnaround times  ****************\n\n");
        for (Pair<String, Double> stringDoublePair : TT) {
            ans.append(stringDoublePair.first).append(" ->  ").append(stringDoublePair.second).append('\n');
            t_t+=stringDoublePair.second;
        }
        ans.append("\nAverage TT -> ").append((t_t / TT.size())).append("\n");


        out_list.setText(ans);


    }


    private class rrr implements java.util.Comparator<proccess> {
        @Override
        public int compare(proccess a, proccess b) {
            return (int) (a.at - b.at);
        }
    }
}
