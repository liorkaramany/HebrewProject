package com.example.user.hebrewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Difficulity extends AppCompatActivity {

    int setMenu, topic, dif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulity);

        Intent gt = getIntent();
        setMenu = gt.getIntExtra("setMenu", 1);
        topic = gt.getIntExtra("topic", 1);
    }

    public void basic(View view) {
        dif=1;
        toTest();
    }

    public void advanced(View view) {
        dif=2;
        toTest();
    }

    public void challenging(View view) {
        dif=3;
        toTest();
    }

    public void toTest() {
        Intent t=null;
        switch (topic){
            case 1: t=new Intent(this, Test1.class); break;
            case 2: t=new Intent(this, Test2.class); break;
            case 3: t=new Intent(this, Test3.class); break;
            default: switch ((int)Math.random()*3) {case 0:  t=new Intent(this, Test1.class); break;
                                                    case 1:  t=new Intent(this, Test1.class); break;
                                                    case 2:  t=new Intent(this, Test1.class); break;}
        }
        t.putExtra("dif", dif);
        t.putExtra("topic", topic);
        t.putExtra("subject", setMenu);
        startActivity(t);
    }

    public void back(View view) {
        finish();
    }
}
