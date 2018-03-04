package com.example.user.hebrewproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class Test1 extends AppCompatActivity {

    float points;
    int round;
    int dif, topic, subject;
    ImageView img;
    EditText root;
    Spinner build, body, time, gizra;
    ArrayAdapter<CharSequence> abuild;
    ArrayAdapter<CharSequence> abody;
    ArrayAdapter<CharSequence> atime;
    ArrayAdapter<CharSequence> agizra;

    Random rnd=new Random();

    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        img = (ImageView) findViewById(R.id.img);
        build = (Spinner) findViewById(R.id.build);
        body = (Spinner) findViewById(R.id.body);
        time = (Spinner) findViewById(R.id.time);
        gizra = (Spinner) findViewById(R.id.gizra);
        root=(EditText) findViewById(R.id.root);

        Intent gt=getIntent();
        dif=gt.getIntExtra("dif", 1);
        topic=gt.getIntExtra("topic", 1);
        subject=gt.getIntExtra("subject", 1);

        points=gt.getFloatExtra("points", 0);
        round=gt.getIntExtra("round", 1);


        abuild = ArrayAdapter.createFromResource(this, R.array.build, android.R.layout.simple_spinner_item);
        abody = ArrayAdapter.createFromResource(this, R.array.body, android.R.layout.simple_spinner_item);
        atime = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
        agizra = ArrayAdapter.createFromResource(this, R.array.gizra, android.R.layout.simple_spinner_item);

        abuild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        abody.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        atime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agizra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        build.setAdapter(abuild);
        body.setAdapter(abody);
        time.setAdapter(atime);
        gizra.setAdapter(agizra);

        int n1=1, n2=1;

        if (subject==1)
        {
            switch (topic)
            {
                case 1: n1=1; n2=20; break;
                case 2: n1=21; n2=40; break;
                case 3: n1=41; n2=60; break;
            }
        }

        n=(rnd.nextInt(n2-n1+1)+1);

        //Generates a random picture and displays it.
        Resources res = getResources();
        String mDrawableName = "i"+n;
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        img.setImageResource(resID);
    }

    public void next(View view) {
        Resources res = getResources();
        int resID = res.getIdentifier("v"+n, "array", getPackageName());
        String[] verb = res.getStringArray(resID);

        if (root.getText().toString().equals(""))
            Toast.makeText(this, "לא הכנסת שורש", Toast.LENGTH_SHORT).show();
        else
        {
            //Check the answers.
            if (verb[0].equals(root.getText().toString()))
                points+=0.2;
            if (verb[1].equals(build.getSelectedItem()))
                points+=0.2;
            if (verb[2].equals(body.getSelectedItem()))
                points+=0.2;
            if (verb[3].equals(time.getSelectedItem()))
                points+=0.2;
            if (verb[4].equals(gizra.getSelectedItem()))
                points+=0.2;

            Intent t=null;
            if (round==10)
                t=new Intent(this, Results.class);
            else if (topic>=4)
                switch (rnd.nextInt(3)) {
                    case 0: t=new Intent(this, Test1.class);
                        break;
                    case 1: t=new Intent(this, Test2.class);
                        break;
                    case 2: t=new Intent(this, Test3.class);
                        break;
                }
            else
                t=new Intent(this, Test1.class);

            round++;

            t.putExtra("points", points);
            t.putExtra("round", round);
            t.putExtra("dif", dif);
            t.putExtra("topic", topic);
            t.putExtra("subject", subject);

            startActivity(t);
        }
    }
}
