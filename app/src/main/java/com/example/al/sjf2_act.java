package com.example.al;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class sjf2_act extends AppCompatActivity {
    ArrayList<proccess> list = new ArrayList<>();
    EditText sjf2_pid,sjf2_at,sjf2_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjf2_act);

        Button sjf2_calculate = findViewById(R.id.sjf2_calculate);
        Button sjf2_insert = findViewById(R.id.sjf2_insert);

        sjf2_pid = findViewById(R.id.p_id_sjf2);
        sjf2_at = findViewById(R.id.sjf2_at);
        sjf2_bt = findViewById(R.id.sjf2_bt);

        Handler handler = new Handler();

        sjf2_calculate.setOnClickListener(handler);
        sjf2_insert.setOnClickListener(handler);
    }
    class Handler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.sjf2_calculate) {
                opensjf2_out();
            }
            else if(v.getId()==R.id.sjf2_insert){

                proccess p = new proccess(String.valueOf(sjf2_pid.getText()),Double.parseDouble(String.valueOf(sjf2_at.getText())),Double.parseDouble(String.valueOf(sjf2_bt.getText())));
                //System.out.println(p.name+" "+p.at+" "+p.bt+" "+p.pv);
                list.add(p);
                Toast.makeText(sjf2_act.this,"Inserted" , Toast.LENGTH_SHORT).show();

                sjf2_pid.onEditorAction(EditorInfo.IME_ACTION_DONE);
                sjf2_at.onEditorAction(EditorInfo.IME_ACTION_DONE);
                sjf2_bt.onEditorAction(EditorInfo.IME_ACTION_DONE);

                sjf2_pid.setText("");
                sjf2_at.setText("");
                sjf2_bt.setText("");

            }
        }
    }
    public void opensjf2_out() {
        Intent intent = new Intent(this, sjf2_out.class);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}
