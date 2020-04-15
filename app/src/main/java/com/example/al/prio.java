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

public class prio extends AppCompatActivity {

    ArrayList<proccess> list = new ArrayList<>();

    EditText prio_pid,prio_at,prio_bt,prio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prio);
        Button prio_calculate = findViewById(R.id.prio_calculate);
        Button prio_insert = findViewById(R.id.prio_insert);

        prio_pid = findViewById(R.id.p_id_prio);
        prio_at = findViewById(R.id.prio_at);
        prio_bt = findViewById(R.id.prio_bt);
        prio = findViewById(R.id.prio_num);

        Handler handler = new Handler();

        prio_calculate.setOnClickListener(handler);
        prio_insert.setOnClickListener(handler);
    }

    class Handler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.prio_calculate) {
                openprio_out();
            }
            else {

                proccess p = new proccess(String.valueOf(prio_pid.getText()),Double.parseDouble(String.valueOf(prio_at.getText())),Double.parseDouble(String.valueOf(prio_bt.getText())),Integer.parseInt(String.valueOf(prio.getText())));
                //System.out.println(p.name+" "+p.at+" "+p.bt+" "+p.pv);
                list.add(p);
                Toast.makeText(prio.this,"Inserted" , Toast.LENGTH_SHORT).show();

                prio_pid.onEditorAction(EditorInfo.IME_ACTION_DONE);
                prio_at.onEditorAction(EditorInfo.IME_ACTION_DONE);
                prio_bt.onEditorAction(EditorInfo.IME_ACTION_DONE);
                prio.onEditorAction(EditorInfo.IME_ACTION_DONE);

                prio_pid.setText("");
                prio_at.setText("");
                prio_bt.setText("");
                prio.setText("");

            }
        }
    }
    public void openprio_out() {
        Intent intent = new Intent(this, prio_out.class);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}
