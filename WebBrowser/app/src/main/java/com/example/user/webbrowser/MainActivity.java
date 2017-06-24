package com.example.user.webbrowser;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        db= SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);
        db.execSQL("Create table if not exists Music(Music varchar,Dir varchar);");
        db.execSQL("Create table if not exists Pictures(Music varchar,Dir varchar);");
        db.execSQL("Create table if not exists Videos(Music varchar,Dir varchar);");
        db.execSQL("Create table if not exists Others(Music varchar,Dir varchar);");
        db.execSQL("Create table if not exists Favourite(Favourite varchar);");
        db.execSQL("Create table if not exists History(History varchar);");
        db.execSQL("Create table if not exists Password(Password varchar);");


        CountDownTimer ct=new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Cursor cr = db.rawQuery("Select * from Password",null);
                if(cr.getCount()==0)
                {
                    final Dialog d = new Dialog(MainActivity.this);
                    d.setTitle("App Lock");
                    d.setCancelable(false);
                    d.setContentView(R.layout.activity_app_lock2);

                    final EditText e1 = (EditText)d.findViewById(R.id.editText3);
                    final EditText e2 = (EditText)d.findViewById(R.id.editText4);

                    Button b29 = (Button)d.findViewById(R.id.button29);

                    b29.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(e1.getText().toString().length()!=0&&e2.getText().toString().length()!=0)
                            {
                                if(e1.getText().toString().equals(e2.getText().toString()))
                                {
                                    db.execSQL("Insert into Password values('"+e1.getText().toString()+"');");
                                    d.dismiss();
                                    showdialoglogin();
                                }

                                else
                                {
                                    Toast.makeText(MainActivity.this, "Confirmation Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            else
                            {
                                Toast.makeText(MainActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    d.show();
                }

                else
                {
                    showdialoglogin();

                }



            }
        };
        ct.start();
    }

    private void showdialoglogin() {
        final Dialog d = new Dialog(MainActivity.this);
        d.setTitle("App Lock");
        d.setCancelable(false);
        d.setContentView(R.layout.activity_app_lock);

        final EditText e = (EditText)d.findViewById(R.id.editText2);
        Button b28 = (Button)d.findViewById(R.id.button28);
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr = db.rawQuery("Select * from Password",null);
                cr.moveToFirst();
                if(e.getText().toString().equals(cr.getString(0)))
                {
                    d.dismiss();
                    Intent i=new Intent(MainActivity.this,Home.class);
                    startActivity(i);
                }

                else
                {
                    Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
        d.show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
