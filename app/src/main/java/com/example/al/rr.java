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

public class rr extends AppCompatActivity {
    ArrayList<proccess> list = new ArrayList<>();
    EditText rr_pid,rr_at,rr_bt,rr_tq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rr);

        Button rr_calculate = findViewById(R.id.rr_calculate);
        Button rr_insert = findViewById(R.id.rr_insert);

        rr_pid = findViewById(R.id.p_id_rr);
        rr_at = findViewById(R.id.rr_at);
        rr_bt = findViewById(R.id.rr_bt);
        rr_tq = findViewById(R.id.rr_tq);

        Handler handler = new Handler();

        rr_calculate.setOnClickListener(handler);
        rr_insert.setOnClickListener(handler);

    }

    class Handler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.rr_calculate) {
                openrr_out();
            }
            else if(v.getId()==R.id.rr_insert){

                proccess p = new proccess(String.valueOf(rr_pid.getText()),Double.parseDouble(String.valueOf(rr_at.getText())),Double.parseDouble(String.valueOf(rr_bt.getText())),Double.parseDouble(String.valueOf(rr_tq.getText())));
                //System.out.println(p.name+" "+p.at+" "+p.bt+" "+p.pv);
                list.add(p);
                Toast.makeText(rr.this,"Inserted" , Toast.LENGTH_SHORT).show();

                rr_pid.onEditorAction(EditorInfo.IME_ACTION_DONE);
                rr_at.onEditorAction(EditorInfo.IME_ACTION_DONE);
                rr_bt.onEditorAction(EditorInfo.IME_ACTION_DONE);
                rr_tq.onEditorAction(EditorInfo.IME_ACTION_DONE);


                rr_pid.setText("");
                rr_at.setText("");
                rr_bt.setText("");

            }
        }
    }
    public void openrr_out() {
        Intent intent = new Intent(this, rr_out.class);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}
