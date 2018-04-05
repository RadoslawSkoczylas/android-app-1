package com.example.radek.lab2;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private  TextView name;
    private TextView surname;
    private TextView marksNumber;
    private EditText editName;
    private EditText editSurname;
    private EditText editMarks;
    private boolean correctName;
    private boolean correctSurname;
    private boolean correctMarksNumber;
    private Button marksButton;
    private String victory;
    private String defeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView)findViewById(R.id.name);
        surname = (TextView)findViewById(R.id.surname);
        marksNumber = (TextView)findViewById(R.id.marksNumber);

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editSurname = (EditText)findViewById(R.id.editSurname);
        final EditText editMarks = (EditText)findViewById(R.id.editMarks);
        final Button marksButton = (Button)findViewById(R.id.marksButton);
        marksButton.setVisibility(View.INVISIBLE);
        marksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(marksButton.getText().toString().equals("Oceny")){
                    Intent intent = new Intent(MainActivity.this, MarksActivity.class);
                    intent.putExtra("test",Integer.parseInt(editMarks.getText().toString()));
//                startActivity(intent);
                    startActivityForResult(intent,1);
                } else if(marksButton.getText().toString().equals(victory)){
                    Toast.makeText(getApplicationContext(),"Gratulacje! Otrzymujesz zaliczenie!",Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Wysyłam podanie o zaliczenie warunkowe",Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               showButton();
            }
        });
        editName.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {

                        if(editName.getText().toString().equals("") && !hasFocus){
                            Toast.makeText(getApplicationContext(),
                            "Pole \"imie\" nie może być puste!",
                            Toast.LENGTH_SHORT).show();
                        }
                        else if(!isCorrectName() && !hasFocus){
                            Toast.makeText(getApplicationContext(),"Wprowadź poprawne imię!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        editSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showButton();
            }
        });
        editSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(editSurname.getText().toString().equals("") && !hasFocus){
                    Toast.makeText(getApplicationContext(), "Pole \"nazwisko\" nie może być puste!",
                    Toast.LENGTH_SHORT).show();
                }
                else if(!isCorrectSurname() && !hasFocus){
                    Toast.makeText(getApplicationContext(),"Wprowadź poprawne nazwisko!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        editMarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showButton();
            }
        });
        editMarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(editMarks.getText().toString().equals("") && !hasFocus){
                    Toast.makeText(getApplicationContext(), "Pole \"liczba ocen\" nie może być puste!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!isCorrectMarksNumber() && !hasFocus){
                    Toast.makeText(getApplicationContext(),"Wprowadź poprawną liczbę ocen!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("name", name.getText().toString());
        outState.putString("surname", surname.getText().toString());
        outState.putString("marksNumber", marksNumber.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        name.setText(savedInstanceState.getString("name"));
        surname.setText(savedInstanceState.getString("surname"));
        marksNumber.setText(savedInstanceState.getString("marksNumber"));
    }


    private boolean isCorrectName(){
        editName = (EditText)findViewById(R.id.editName);
        if (!((editName.getText().toString().matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+"))) || editName.getText().toString().equals("")) {
            return false;
        } else return true;
    }

    private boolean isCorrectSurname(){
        editSurname = (EditText)findViewById(R.id.editSurname);
        if (!(editSurname.getText().toString().matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+"))|| editSurname.getText().toString().equals("")){
            return false;
        } else return true;
    }

    private boolean isCorrectMarksNumber(){
        editMarks = (EditText)findViewById(R.id.editMarks);
        if(!(editMarks.getText().toString().matches("[0-9]+")) || editMarks.getText().toString().equals("") ){
            return false;
        }
        else if(editMarks.getText().toString().matches("\\d{1,2}")){
            int number = Integer.parseInt(editMarks.getText().toString());
            if (number < 5 || number > 15) {
                return false;
            } else return true;

        }
        return true;
    }
    private void showButton(){
        marksButton = (Button)findViewById(R.id.marksButton);
        if(isCorrectName()  && isCorrectSurname()  && isCorrectMarksNumber() ){
            marksButton.setVisibility(View.VISIBLE);
        }
        else marksButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        TextView result = (TextView)findViewById(R.id.result);
        String average = data.getStringExtra("average");
        defeat = "Tym razem mi nie poszło";
        victory = "Super :)";
        result.setText("Twoja średnia to: "+data.getStringExtra("average"));

        if(Double.parseDouble(average) < 3.0){
            marksButton.setText(defeat);
        } else {
            marksButton.setText(victory);
        }
    }
}
