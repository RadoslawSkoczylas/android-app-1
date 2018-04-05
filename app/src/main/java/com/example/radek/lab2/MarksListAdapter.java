package com.example.radek.lab2;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MarksListAdapter extends ArrayAdapter<MarkModel> {

    private List<MarkModel> marksList;
    private Activity context;
    private RadioButton radioButton;
    public MarksListAdapter(Activity context, List<MarkModel> marksList) {
        super(context,R.layout.marks_list_row, marksList);
        this.marksList = marksList;
        this.context = context;


    }


    @Override
    public View getView(final int rowNumber, View view , ViewGroup parent){

        View row = null;
        RadioGroup radioGroup = null;

        if(view == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            row = layoutInflater.inflate(R.layout.marks_list_row, null);
            radioGroup = (RadioGroup)row.findViewById(R.id.radioGroup);
            Button b = (Button)row.findViewById(R.id.numberButton);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    radioGroup.setTag(rowNumber);
                    int id = radioGroup.getCheckedRadioButtonId();
                    radioButton = radioGroup.findViewById(id);
                  //  Toast.makeText(getContext(),radioButton.getText(),Toast.LENGTH_SHORT).show();
                    marksList.get(rowNumber).setMark(Integer.parseInt(radioButton.getText().toString()));
                }
            });

        }else {
            row = view;
            radioGroup = (RadioGroup)row.findViewById(R.id.radioGroup);
            radioGroup.setTag(rowNumber);
        }
        TextView textView = (TextView)row.findViewById(R.id.mark);
        textView.setText(marksList.get(rowNumber).toString()+(rowNumber+1));



        return row;
    }


}

