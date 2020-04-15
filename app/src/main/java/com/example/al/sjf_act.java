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

public class sjf_act extends AppCompatActivity {
    ArrayList<proccess> list = new ArrayList<>();
    EditText sjf_pid,sjf_at,sjf_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjf_act);

        Button sjf_calculate = findViewById(R.id.sjf_calculate);
        Button sjf_insert = findViewById(R.id.sjf_insert);

        sjf_pid = findViewById(R.id.p_id_sjf);
        sjf_at = findViewById(R.id.sjf_at);
        sjf_bt = findViewById(R.id.sjf_bt);

        Handler handler = new Handler();

        sjf_calculate.setOnClickListener(handler);
        sjf_insert.setOnClickListener(handler);
    }

    class Handler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.sjf_calculate) {
                openfcfs_out();
            }
            else if(v.getId()==R.id.sjf_insert){

                proccess p = new proccess(String.valueOf(sjf_pid.getText()),Double.parseDouble(String.valueOf(sjf_at.getText())),Double.parseDouble(String.valueOf(sjf_bt.getText())));
                //System.out.println(p.name+" "+p.at+" "+p.bt+" "+p.pv);
                list.add(p);
                Toast.makeText(sjf_act.this,"Inserted" , Toast.LENGTH_SHORT).show();

                sjf_pid.onEditorAction(EditorInfo.IME_ACTION_DONE);
                sjf_at.onEditorAction(EditorInfo.IME_ACTION_DONE);
                sjf_bt.onEditorAction(EditorInfo.IME_ACTION_DONE);

                sjf_pid.setText("");
                sjf_at.setText("");
                sjf_bt.setText("");

            }
        }
    }
    public void openfcfs_out() {
        Intent intent = new Intent(this, sjf_out.class);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}
