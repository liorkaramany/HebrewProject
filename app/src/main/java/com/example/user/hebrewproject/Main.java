package com.example.user.hebrewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main extends AppCompatActivity {

    int setMenu;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message=(TextView) findViewById(R.id.message);

        boolean found=true;
        InputStream is=null;
        try {
            is=openFileInput("name.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            found=false;
        }
        if (!found)
        {
            Intent t=new Intent(this, Login.class);
            startActivity(t);
        }
        else
        {
            InputStreamReader tmp=new InputStreamReader(is);
            BufferedReader reader=new BufferedReader(tmp);
            String str="";
            StringBuffer buffer=new StringBuffer();

            try {
                while ((str=reader.readLine())!=null)
                    buffer.append(str+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            message.setText("שלום, "+buffer+"הגיע הזמן לתרגל ללשון!");
        }
    }

    public void nouns(View view) {
        setMenu = 2;
        Intent t=new Intent(this, Test2.class);
        t.putExtra("subject", 2);
        startActivity(t);
    }

    public void verbs(View view) {
        setMenu = 1;
        toMenu();
    }

    public void mixed(View view) {
        setMenu = 4;
        Intent t=null;
        switch ((int)Math.floor(Math.random()*3))
        {
            case 0: t=new Intent(this, Test1.class); break;
            case 1: t=new Intent(this, Test2.class); break;
            case 2: t=new Intent(this, Test3.class); break;
        }
        t.putExtra("subject", 4);
        startActivity(t);
    }

    public void number(View view) {
        setMenu = 3;
        Intent t=new Intent(this, Test3.class);
        t.putExtra("subject", 3);
        startActivity(t);
    }

    public void bagruts(View view) {
        setMenu = 5;
        toDifficulity();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        menu.add("שנה שם");
        menu.add("קרדיטים");

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        String st=item.getTitle().toString();
        Intent t;
        if (st.equals("קרדיטים"))
            t = new Intent(this, Credits.class);
        else
            t = new Intent(this, Login.class);
        startActivity(t);
        return true;
    }

    public void toMenu() {
        Intent t = new Intent(this, Menu.class);
        t.putExtra("setMenu", setMenu);
        startActivity(t);
    }
    public void toDifficulity() {
        Intent t = new Intent(this, Difficulity.class);
        t.putExtra("setMenu", setMenu);
        startActivity(t);
    }
}
