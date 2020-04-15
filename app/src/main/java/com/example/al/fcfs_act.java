package com.example.al;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class fcfs_act extends AppCompatActivity {
    private EditText fcfs_pid,fcfs_at,fcfs_bt;

    ArrayList<proccess>list = new ArrayList<>();
   // ActionBar ab;



    public fcfs_act() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcfs_act);

        //ab= getSupportActionBar();
      //  ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#bdc3c7")));

        Button fcfs_calculate = findViewById(R.id.fcfs_calculate);
        Button fcfs_insert = findViewById(R.id.fcfs_insert);
        fcfs_pid = findViewById(R.id.p_id_fcfs);
        fcfs_at = findViewById(R.id.fcfs_at);
        fcfs_bt = findViewById(R.id.fcfs_bt);

        Handler handler = new  Handler();

        fcfs_calculate.setOnClickListener(handler);
        fcfs_insert.setOnClickListener(handler);

    }
    class Handler implements View.OnClickListener {

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {



            if (v.getId()==R.id.fcfs_calculate) {
                openfcfs_out();
            }
            else if (v.getId()==R.id.fcfs_insert)
            {

                proccess p = new proccess(String.valueOf(fcfs_pid.getText()),Double.parseDouble(String.valueOf(fcfs_at.getText())),Double.parseDouble(String.valueOf(fcfs_bt.getText())));
                //System.out.println(p.name+" "+p.at+" "+p.bt+" "+p.pv);

                list.add(p);

                Toast.makeText(fcfs_act.this,"Inserted" , Toast.LENGTH_SHORT).show();

                fcfs_pid.onEditorAction(EditorInfo.IME_ACTION_DONE);
                fcfs_at.onEditorAction(EditorInfo.IME_ACTION_DONE);
                fcfs_bt.onEditorAction(EditorInfo.IME_ACTION_DONE);

                fcfs_pid.setText("");
                fcfs_at.setText("");
                fcfs_bt.setText("");
            }
        }
    }
    public void openfcfs_out() {
        Intent intent = new Intent(this, fcfs_out.class);
        intent.putExtra("list",list);
        startActivity(intent);
    }

}
