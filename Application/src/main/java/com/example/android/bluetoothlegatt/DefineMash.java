package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.ArrayList;
import java.util.List;

public class DefineMash extends Activity {

    Button addStage;
    Button submitMash;
    TableLayout table;
    Integer id_num;
    ArrayList<String> temps = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    EditText temp_txt;
    EditText time_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definemash);

        addStage = (Button)findViewById(R.id.button_addStage);
        submitMash = (Button)findViewById(R.id.button_submitMash);
        table = (TableLayout)findViewById(R.id.table);
        id_num = 2;

        addStage.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View tr = inflater.inflate(R.layout.activity_definemash_row, null, false);
                temp_txt = (EditText)tr.findViewById(R.id.temp_text1);
                time_txt = (EditText)tr.findViewById(R.id.time_text1);
                temp_txt.setTag("temp_text" + id_num);
                time_txt.setTag("time_text" + id_num);
                table.addView(tr);
                id_num++;
            }
        });

        submitMash.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                for(int i = 1, j = table.getChildCount(); i < j; i++) {
                    TableLayout layout = (TableLayout) findViewById(R.id.table);
                    View child = layout.getChildAt(i);
                    TableRow t = (TableRow) child;
                    EditText firstTextView = (EditText) t.getChildAt(0);
                    EditText secondTextView = (EditText) t.getChildAt(1);
                    String firstText = firstTextView.getText().toString();
                    String secondText = secondTextView.getText().toString();
                    temps.add(firstText);
                    times.add(secondText);

                    Intent intent = new Intent(v.getContext(), DeviceScanActivity.class);
                    intent.putExtra("temps_list", temps);
                    intent.putExtra("times_list", times);
                    startActivity(intent);
                }
            }
        });
    }
}
