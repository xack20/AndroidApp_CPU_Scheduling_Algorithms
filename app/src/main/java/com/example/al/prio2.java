package com.example.al;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.Character.toUpperCase;
import static java.lang.Math.log10;

public class prio2 extends AppCompatActivity {
    ArrayList<proccess> List;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        TextView out_list = findViewById(R.id.list);

        List = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList("list");
        assert List != null;


        int sz = List.size();
        int n = sz;

        Collections.sort(List, new COMP());

        HashMap<String, Pair<Double, Double>> MP = new HashMap<>();
        for (proccess P : List) {
            Pair<Double, Double> val = new Pair<>(P.at, P.bt);
            MP.put(P.name, val);
        }

        ArrayList<String> pro_list = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();


        times.add(0.0);

        double ct = 0.0;
        //int f = 1;

        while (n != 0) {
            int ind = 0;
            int f = 0;
            double val = 1 << 29, tm = 0.0;
            for (int i = 0; i < sz; i++) {
                if (List.get(i).at <= ct && List.get(i).opt < val && List.get(i).bt > 0.0) {
                    val = List.get(i).opt;
                    ind = i;
                    f = 1;
                } else if (List.get(i).at > ct) {
                    tm = List.get(i).at;
                    break;
                }
            }

            if (f == 0) {
                ct = tm;
                pro_list.add("Idle");
                times.add(ct);
            } else {
                ct += 1;
                List.get(ind).bt -= 1.0;
                pro_list.add(List.get(ind).name);
                times.add(ct);
                if (List.get(ind).bt == 0) n--;
            }
        }

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

        StringBuilder ans = new StringBuilder();


        ans.append("\n\n*****************     Gantt Chart    *****************\n\n");

        for (int i = 0; i < pro_list.size(); i++) ans.append("---------");
        ans.append("\n");

        for (String s : pro_list) {
            if (toUpperCase(s.charAt(0)) == 'p') ans.append("|  ").append(s).append("  ");
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
        ans.append("\nAverage WT -> ").append((w_t / WT.size())).append("\n");

        double t_t=0.0;
        ans.append("\n\n****************   Turnaround times  ****************\n\n");
        for (Pair<String, Double> stringDoublePair : TT) {
            ans.append(stringDoublePair.first).append(" ->  ").append(stringDoublePair.second).append('\n');
            t_t+=stringDoublePair.second;
        }
        ans.append("\nAverage TT -> ").append( (t_t / TT.size())).append("\n");

        out_list.setText(ans);
    }
    private static class COMP implements Comparator<proccess> {
        @Override
        public int compare(proccess o1, proccess o2) {
            if(o1.at == o2.at)return (int) (o1.opt- o2.opt);
            else return (int) (o1.at- o2.at);
        }
    }
}
