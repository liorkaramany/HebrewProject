package com.example.user.hebrewproject;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Test3 extends AppCompatActivity {

    TextView answer;
    EditText word;
    ImageView img;
    Button btn;

    float points;
    int round;
    int subject;
    int n;
    Random rnd=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        answer = (TextView) findViewById(R.id.answer);
        word = (EditText) findViewById(R.id.word);
        img = (ImageView) findViewById(R.id.img);
        btn = (Button) findViewById(R.id.btn);

        Intent gt = getIntent();
        subject = gt.getIntExtra("subject", 1);
        points = gt.getFloatExtra("points", 0);
        round = gt.getIntExtra("round", 1);

        int n1 = 1, n2 = 1;

        if (subject == 5)
        {
            n1=69;
            n2 = 121;
        }
        else
            n2=68;

        n=(rnd.nextInt(n2-n1+1)+n1);

        //Generates a random picture and displays it.
        Resources res = getResources();
        String mDrawableName = "m"+n;
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        img.setImageResource(resID);
    }

    public void next(View view) {
        if (word.getText().toString().equals(""))
            Toast.makeText(this, "לא הכנסת את השורש או את מילה היחס", Toast.LENGTH_SHORT).show();
        else
        {
            if (!btn.getText().toString().equals("הבא"))
            {
                Resources res = getResources();
                int resID = res.getIdentifier("m" + n, "string", getPackageName());
                String m = res.getString(resID);

                if (m.equals(word.getText().toString()))
                    points += 1;

                answer.setText(""+m);

                btn.setText("הבא");
            }
            else
            {
                Intent t = null;
                if (round == 20)
                    t = new Intent(this, Results.class);
                else if (subject >= 4)
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
                else
                    t = new Intent(this, Test3.class);

                round++;

                t.putExtra("points", points);
                t.putExtra("round", round);
                t.putExtra("subject", subject);

                startActivity(t);
            }
        }
    }
}
