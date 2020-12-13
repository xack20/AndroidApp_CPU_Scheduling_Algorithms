package com.example.al;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class input extends AppCompatActivity {
    ArrayList<proccess> list = new ArrayList<>();
    int id;
    EditText at,bt,opt,pid;

    HashMap<Integer,String> options = new HashMap<>();


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        at =findViewById(R.id.att);
        bt =findViewById(R.id.btt);
        opt=findViewById(R.id.opt);
        pid=findViewById(R.id.p_id);

        options.put(24,"First Come First Serve");
        options.put(252,"SJF Non-Preemptive");
        options.put(480,"SJF Preemptive");
        options.put(708,"Round Robin");
        options.put(936,"Priority Non-Preemptive");
        options.put(1164,"Priority Preemptive");



        id = getIntent().getIntExtra("options",0);
        ((TextView)(findViewById(R.id.algo_name))).setText(options.get(id));

        if(id == 24 || id == 252 || id == 480) {
            ((EditText)findViewById(R.id.opt)).setText("0");
            findViewById(R.id.opt).setVisibility(View.GONE);
        }
        else if(id == 708) {
            ((EditText) findViewById(R.id.opt)).setHint("Time Quantum");
            findViewById(R.id.opt).setVisibility(View.FOCUS_UP);
        }
        else {
            ((EditText)findViewById(R.id.opt)).setHint("Priority");
            findViewById(R.id.opt).setVisibility(View.FOCUS_UP);
        }

        Toast.makeText(input.this,String.valueOf(id) , Toast.LENGTH_SHORT).show();

    }

    public void insertion(View view) {
        try{
            proccess p = new proccess(String.valueOf(pid.getText()),Double.parseDouble(String.valueOf(at.getText())),Double.parseDouble(String.valueOf(bt.getText())),Double.parseDouble(String.valueOf(opt.getText())));
            list.add(p);
            Toast.makeText(input.this,"Inserted" , Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(input.this,"Fill all fields" , Toast.LENGTH_SHORT).show();
        }

        pid.setText("");
        at.setText("");
        bt.setText("");
        if(id==936 || id==1164)opt.setText("");
    }

    public void calc(View view) {
        if(list.size()>=1){
            Intent intent = new Intent();
            if(id == 24)intent= new Intent(this,fcfs.class);
            if(id == 252)intent= new Intent(this,sjf.class);
            if(id == 480)intent= new Intent(this,sjf2.class);
            if(id == 708)intent= new Intent(this,rr.class);
            if(id == 936)intent= new Intent(this,prio.class);
            if(id == 1164)intent= new Intent(this,prio2.class);
            intent.putParcelableArrayListExtra("list",list);
            startActivity(intent);
        }
        else Toast.makeText(input.this,"Insert Data First" , Toast.LENGTH_SHORT).show();
    }
}
