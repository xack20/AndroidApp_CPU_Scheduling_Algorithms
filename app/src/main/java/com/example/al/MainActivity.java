package com.example.al;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button FCFS = findViewById(R.id.FCFS);
        Button sjf = findViewById(R.id.sjf);
        Button sjf2 = findViewById(R.id.sjf2);
        Button rr = findViewById(R.id.rr);
        Button prio = findViewById(R.id.prio);
        Button prio2 = findViewById(R.id.prio2);

        Handler handler = new Handler();

        FCFS.setOnClickListener(handler);
        sjf.setOnClickListener(handler);
        sjf2.setOnClickListener(handler);
        rr.setOnClickListener(handler);
        prio.setOnClickListener(handler);
        prio2.setOnClickListener(handler);
    }

    class Handler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.FCFS)
            {
                openFCFS();
            }
            else if(v.getId()==R.id.sjf)
            {
                opensjf();
            }
            else if(v.getId()==R.id.sjf2)
            {
                opensjf2();
            }
            else if(v.getId()==R.id.rr)
            {
                openrr();
            }
            else if(v.getId()==R.id.prio)
            {
                openprio();
            }
            else if(v.getId()==R.id.prio2)
            {
                openprio2();
            }
        }
    }


    public void openFCFS(){
        Intent intent = new Intent(this,fcfs_act.class);
        startActivity(intent);
    }
    public void opensjf(){
        Intent intent = new Intent(this,sjf_act.class);
        startActivity(intent);
    }
    public void opensjf2(){
        Intent intent = new Intent(this,sjf2_act.class);
        startActivity(intent);
    }
    public void openrr(){
        Intent intent = new Intent(this,rr.class);
        startActivity(intent);
    }
    public void openprio(){
        Intent intent = new Intent(this,prio.class);
        startActivity(intent);
    }
    public void openprio2(){
        Intent intent = new Intent(this,prio2.class);
        startActivity(intent);
    }

}
