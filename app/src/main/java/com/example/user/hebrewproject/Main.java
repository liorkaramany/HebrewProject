package com.example.user.hebrewproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
        Intent t=new Intent(this, Guide.class);
        t.putExtra("subject", 2);
        startActivity(t);
    }

    public void verbs(View view) {
        setMenu = 1;
        toMenu();
    }

    public void mixed(View view) {
        setMenu = 4;
        Intent t=new Intent(this, Guide.class);
        t.putExtra("subject", 4);
        startActivity(t);
    }

    public void number(View view) {
        setMenu = 3;
        Intent t=new Intent(this, Guide.class);
        t.putExtra("subject", 3);
        startActivity(t);
    }

    public void bagruts(View view) {
        setMenu = 5;
        Intent t=new Intent(this, Guide.class);
        t.putExtra("subject", 5);
        startActivity(t);
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
        if (st.equals("קרדיטים")) {
            Intent t = new Intent(this, Credits.class);
            startActivity(t);
        }
        else {
            final Intent t = new Intent(this, Login.class);

            AlertDialog.Builder adb=new AlertDialog.Builder(this);
            adb.setTitle("אזהרה");
            adb.setMessage("האם את\\ה בטוח\\ה שברצונך לשנות את שמך?");
            adb.setPositiveButton("כן", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(t);
                }
            });
            adb.setNegativeButton("לא", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog ad=adb.create();
            ad.show();
        }
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
