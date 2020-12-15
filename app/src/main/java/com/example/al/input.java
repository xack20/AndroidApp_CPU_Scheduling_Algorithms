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
    String id;
    EditText at,bt,opt,pid;

    HashMap<String,String> options = new HashMap<>();


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        at =findViewById(R.id.att);
        bt =findViewById(R.id.btt);
        opt=findViewById(R.id.opt);
        pid=findViewById(R.id.p_id);

        options.put("2131230722","First Come First Serve");
        options.put("2131230932","SJF Non-Preemptive");
        options.put("2131230933","SJF Preemptive");
        options.put("2131230908","Round Robin");
        options.put("2131230897","Priority Non-Preemptive");
        options.put("2131230898","Priority Preemptive");



        id = getIntent().getStringExtra("options");
        ((TextView)(findViewById(R.id.algo_name))).setText(options.get(id));

        if(id.equals("2131230722") || id.equals("2131230932") || id.equals("2131230933")) {
            ((EditText)findViewById(R.id.opt)).setText("0");
            findViewById(R.id.opt).setVisibility(View.GONE);
        }
        else if(id.equals("2131230908")) {
            ((EditText) findViewById(R.id.opt)).setHint("Time Quantum");
            findViewById(R.id.opt).setVisibility(View.FOCUS_UP);
        }
        else {
            ((EditText)findViewById(R.id.opt)).setHint("Priority");
            findViewById(R.id.opt).setVisibility(View.FOCUS_UP);
        }

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
        if(id.equals("2131230897") || id.equals("2131230898"))opt.setText("");
    }

    public void calc(View view) {
        if(list.size()>=1){
            Intent intent = new Intent();
            if(id.equals("2131230722"))intent= new Intent(this,fcfs.class);
            if(id.equals("2131230932"))intent= new Intent(this,sjf.class);
            if(id.equals("2131230933"))intent= new Intent(this,sjf2.class);
            if(id.equals("2131230908"))intent= new Intent(this,rr.class);
            if(id.equals("2131230897"))intent= new Intent(this,prio.class);
            if(id.equals("2131230898"))intent= new Intent(this,prio2.class);
            intent.putParcelableArrayListExtra("list",list);
            startActivity(intent);
        }
        else Toast.makeText(input.this,"Insert Data First" , Toast.LENGTH_SHORT).show();
    }
}
