package com.example.user.hebrewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btn1, btn2, btn3;
    int setMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        Intent gt = getIntent();

        setMenu = gt.getIntExtra("setMenu", 1);

        if (setMenu == 1)
        {
            btn1.setText("שלמים, מרובעים, חפ\"נ, חפי\"ץ");
            btn2.setText("נל\"א, נל\"י/ה, נפ\"א");
            btn3.setText("נפי\"ו, נעי\"ו, גרוניות, ע\"ע");
        }
        else
        {
            btn1.setText("זיהוי דרכי תצורה");
            btn2.setText("זיהוי משקלים");
            btn3.setText("זיהוי משמעות של משקלים וצורנים");
        }
    }

    public void click1(View view) {
        toDifficulity(1);
    }

    public void click2(View view) {
        toDifficulity(2);
    }

    public void click3(View view) {
        toDifficulity(3);
    }

    public void toDifficulity(int topic)
    {
        Intent t = new Intent(this, Test1.class);
        t.putExtra("topic", topic);
        t.putExtra("setMenu", setMenu);
        startActivity(t);
    }

    public void back(View view) {
        finish();
    }
}
