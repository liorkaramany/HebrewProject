package com.example.user.hebrewproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Results extends AppCompatActivity {

    TextView grade, message;
    int dif, topic, subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        grade=(TextView) findViewById(R.id.grade);
        message=(TextView) findViewById(R.id.message);

        Intent gt=getIntent();
        int g=(int)(gt.getFloatExtra("points", 0)/(float)(gt.getIntExtra("round", 2)-1) *100);
        grade.setText(""+g);
        if (g<=25) {
            message.setText("יש לקבל עזרה");
            grade.setTextColor(Color.rgb(0, 0, 0));
        }
        else if (g<=50) {
            message.setText("יש להשתפר");
            grade.setTextColor(Color.rgb(200, 0, 0));
        }
        else if (g<=75) {
            message.setText("מספיק טוב");
            grade.setTextColor(Color.rgb(200, 100, 0));
        }
        else {
            message.setText("כל הכבוד");
            grade.setTextColor(Color.rgb(0, 200, 0));
        }
    }

    public void again(View view) {
        Intent gt=getIntent();
        dif=gt.getIntExtra("dif", 1);
        topic=gt.getIntExtra("topic", 1);
        subject=gt.getIntExtra("subject", 1);

        Random rnd=new Random();

        Intent t=null;
        if (topic>=4)
            switch (rnd.nextInt(3)) {
                case 0:
                    t = new Intent(this, Test1.class);
                    break;
                case 1:
                    t = new Intent(this, Test2.class);
                    break;
                case 2:
                    t = new Intent(this, Test3.class);
                    break;
            }
        else if (topic==1)
            t = new Intent(this, Test1.class);
        else if (topic==2)
            t = new Intent(this, Test2.class);
        else
            t = new Intent(this, Test3.class);

        t.putExtra("dif", dif);
        t.putExtra("topic", topic);
        t.putExtra("subject", subject);
        startActivity(t);
    }

    public void back(View view) {
        Intent t=new Intent(this, Main.class);
        startActivity(t);
    }
}
