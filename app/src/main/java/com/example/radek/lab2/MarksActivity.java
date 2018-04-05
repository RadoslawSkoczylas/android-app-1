package com.example.radek.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity {

    private ArrayList<MarkModel> radioGroupList = new ArrayList<MarkModel>();
    private ListView listView;
    private ArrayAdapter<MarkModel> adapter;
    private int number;
    private double average;
    private RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        final Button b = (Button)findViewById(R.id.numberButton);
        Bundle bundle = getIntent().getExtras();
        number = bundle.getInt("test");

        for(int i = 0; i < number; i++){
            radioGroupList.add(new MarkModel("Ocena "+i));
        }
        adapter = new MarksListAdapter(this,radioGroupList);

        listView = (ListView)findViewById(R.id.marksList);
        listView.setAdapter(adapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double sum = 0;
                for (MarkModel markModel : radioGroupList){
                    sum+= markModel.getMark();
                }

                average = sum/radioGroupList.size();
             //   b.setText(Double.toString(average));
                Intent intent = new Intent();
                intent.putExtra("average",Double.toString(average));
                setResult(RESULT_OK,intent);
                finish();
            }

        });

    }



    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        for (int i=0; i<number; i++){
            outState.putInt("mark"+1,radioGroupList.get(i).getMark());
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        for(int i = 0; i<number; i++){
            radioGroupList.get(i).setMark(savedInstanceState.getInt("mark"+1));
            radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        }
    }

}
