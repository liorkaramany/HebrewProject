package com.example.user.hebrewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Test2 extends AppCompatActivity {

    Spinner meanings;
    ArrayAdapter<CharSequence> ameaning;
    RadioGroup rg;
    RadioButton[] options=new RadioButton[3];
    int subject, round;
    float points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        rg=(RadioGroup) findViewById(R.id.rg);
        options[0]=(RadioButton) findViewById(R.id.o1);
        options[1]=(RadioButton) findViewById(R.id.o2);
        options[2]=(RadioButton) findViewById(R.id.o3);

        meanings=(Spinner) findViewById(R.id.meanings);
        ameaning=ArrayAdapter.createFromResource(this, R.array.meanings, android.R.layout.simple_spinner_item);
        ameaning.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meanings.setAdapter(ameaning);

        Intent gt=getIntent();
        subject=gt.getIntExtra("subject", 2);
        round=gt.getIntExtra("round", 0);
        points=gt.getFloatExtra("points", 0);
    }

    public void next(View view) {
        int type=1;
        int checked=rg.getCheckedRadioButtonId();
        if (checked==options[0].getId())
            type=1;
        else if (checked==options[1].getId())
            type=2;
        else if (checked==options[2].getId())
            type=3;
        else type=-1;
    }
}
