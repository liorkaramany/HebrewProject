package com.example.user.hebrewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Test2 extends AppCompatActivity {

    RadioGroup rg;
    RadioButton[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        rg=(RadioGroup) findViewById(R.id.rg);
        options[0]=(RadioButton) findViewById(R.id.o1);
        options[1]=(RadioButton) findViewById(R.id.o2);
        options[2]=(RadioButton) findViewById(R.id.o3);
    }

    public void next(View view) {
        Toast.makeText(this, ""+rg.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
    }
}
