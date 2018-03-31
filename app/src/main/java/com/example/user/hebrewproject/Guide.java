package com.example.user.hebrewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Guide extends AppCompatActivity {

    int subject;
    int topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        Intent gt=getIntent();
        subject=gt.getIntExtra("subject", 1);
        topic=gt.getIntExtra("topic", 1);
    }

    public void next(View view) {
        Intent t=null;
        switch (subject)
        {
            case 1: t=new Intent(this, Test1.class); break;
            case 2: t=new Intent(this, Test2.class); break;
            case 3: t=new Intent(this, Test3.class); break;
            default: switch ((int)(Math.random()*3))
            {
                case 0: t=new Intent(this, Test1.class); break;
                case 1: t=new Intent(this, Test2.class); break;
                case 2: t=new Intent(this, Test3.class); break;
            }break;
        }
        t.putExtra("subject", subject);
        t.putExtra("topic", topic);
        startActivity(t);
    }
}
