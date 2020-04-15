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

public class prio2 extends AppCompatActivity {
    ArrayList<proccess> list = new ArrayList<>();
    EditText prio2_pid,prio2_at,prio2_bt,prio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prio2);

        Button prio2_calculate = findViewById(R.id.prio2_calculate);
        Button prio2_insert = findViewById(R.id.prio2_insert);

        prio2_pid = findViewById(R.id.p_id_prio2);
        prio2_at = findViewById(R.id.prio2_at);
        prio2_bt = findViewById(R.id.prio2_bt);
        prio2 = findViewById(R.id.prio2_num);

        Handler handler = new Handler();

        prio2_calculate.setOnClickListener(handler);
        prio2_insert.setOnClickListener(handler);
    }

    class Handler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.prio2_calculate) {
                openprio2_out();
            }
            else if(v.getId()==R.id.prio2_insert){

                proccess p = new proccess(String.valueOf(prio2_pid.getText()),Double.parseDouble(String.valueOf(prio2_at.getText())),Double.parseDouble(String.valueOf(prio2_bt.getText())),Integer.parseInt(String.valueOf(prio2.getText())));
                //System.out.println(p.name+" "+p.at+" "+p.bt+" "+p.pv);
                list.add(p);
                Toast.makeText(prio2.this,"Inserted" , Toast.LENGTH_SHORT).show();

                prio2_pid.onEditorAction(EditorInfo.IME_ACTION_DONE);
                prio2_at.onEditorAction(EditorInfo.IME_ACTION_DONE);
                prio2_bt.onEditorAction(EditorInfo.IME_ACTION_DONE);
                prio2.onEditorAction(EditorInfo.IME_ACTION_DONE);


                prio2_pid.setText("");
                prio2_at.setText("");
                prio2_bt.setText("");
                prio2.setText("");

            }
        }
    }
    public void openprio2_out() {
        Intent intent = new Intent(this, prio2_out.class);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}
