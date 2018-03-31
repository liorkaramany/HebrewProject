package com.example.user.hebrewproject;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Test2 extends AppCompatActivity {

    EditText root;
    ImageView img;
    Spinner meanings;
    TextView answer, rans;
    ArrayAdapter<CharSequence> ameaning;
    RadioGroup rg;
    RadioButton[] options=new RadioButton[3];
    int subject, round;
    float points;
    int n;
    Button btn;
    LinearLayout bg;
    Random rnd=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        rg=(RadioGroup) findViewById(R.id.rg);
        options[0]=(RadioButton) findViewById(R.id.o1);
        options[1]=(RadioButton) findViewById(R.id.o2);
        options[2]=(RadioButton) findViewById(R.id.o3);
        img=(ImageView) findViewById(R.id.img);
        btn=(Button) findViewById(R.id.btn);
        answer=(TextView) findViewById(R.id.answer);
        rans=(TextView) findViewById(R.id.rans);
        root=(EditText) findViewById(R.id.root);
        bg=(LinearLayout) findViewById(R.id.bg);

        meanings=(Spinner) findViewById(R.id.meanings);
        ameaning=ArrayAdapter.createFromResource(this, R.array.meanings, android.R.layout.simple_spinner_item);
        ameaning.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meanings.setAdapter(ameaning);

        Intent gt=getIntent();
        subject=gt.getIntExtra("subject", 2);
        round=gt.getIntExtra("round", 1);
        points=gt.getFloatExtra("points", 0);

        int n1=1, n2=1;

        if (subject==5)
        {
            n1 = 110;
            n2 = 146;
            bg.setBackgroundResource(R.drawable.b3);
        }
        else
            n2=109;

        n=(rnd.nextInt(n2-n1+1)+n1);

        //Generates a random picture and displays it.
        Resources res = getResources();
        String mDrawableName = "n"+n;
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        img.setImageResource(resID);
    }

    public void next(View view) {
        int type=-1;
        int checked=rg.getCheckedRadioButtonId();
        if (checked==options[0].getId())
            type=1;
        else if (checked==options[1].getId())
            type=2;
        else if (checked==options[2].getId())
            type=3;

        if (!btn.getText().toString().equals("הבא"))
        {
            Resources res = getResources();
            int resID = res.getIdentifier("n" + n, "array", getPackageName());
            String[] noun = res.getStringArray(resID);

            if (type==-1)
                Toast.makeText(this, "לא סימנת את דרך התצורה", Toast.LENGTH_SHORT).show();
            else
            {
                //Check the answers.
                if (noun[0].equals(root.getText().toString()))
                    points += 1/3.0;
                if (noun[1].equals(""+type))
                    points += 1/3.0;
                if (noun[2].equals(meanings.getSelectedItem()))
                    points += 1/3.0;

                rans.setText(""+noun[0]);

                rg.clearCheck();
                options[Integer.parseInt(noun[1])-1].setChecked(true);

                answer.setText(""+noun[2]);
                
                btn.setText("הבא");
            }
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
                t = new Intent(this, Test2.class);

            round++;

            t.putExtra("points", points);
            t.putExtra("round", round);
            t.putExtra("subject", subject);

            startActivity(t);
        }
    }
}
