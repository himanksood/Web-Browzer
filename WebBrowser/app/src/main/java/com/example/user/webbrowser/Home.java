package com.example.user.webbrowser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import java.sql.SQLIntegrityConstraintViolationException;


public class Home extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;

    TabHost th;
    TabHost.TabSpec ts;
    Button b,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,b27;
    ListView lv;
    String[] st={"App Lock","Favourite","History","Downloads","Exit"};
    ArrayAdapter<String> ar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //database
        db=SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Browser.sqlite",null);

        th=(TabHost)findViewById(R.id.tabHost);
        th.setup();
        ts=th.newTabSpec("Tag1");
        ts.setContent(R.id.linearLayout);
        ts.setIndicator("Social");
        th.addTab(ts);

        ts=th.newTabSpec("Tag2");
        ts.setContent(R.id.linearLayout2);
        ts.setIndicator("Music");
        th.addTab(ts);

        ts=th.newTabSpec("Tag3");
        ts.setContent(R.id.linearLayout3);
        ts.setIndicator("Shopping");
        th.addTab(ts);

        b=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button7);
        b8=(Button)findViewById(R.id.button8);
        b9=(Button)findViewById(R.id.button9);
        b10=(Button)findViewById(R.id.button10);
        b11=(Button)findViewById(R.id.button11);
        b12=(Button)findViewById(R.id.button12);
        b13=(Button)findViewById(R.id.button13);
        b14=(Button)findViewById(R.id.button14);
        b15=(Button)findViewById(R.id.button15);
        b16=(Button)findViewById(R.id.button16);
        b17=(Button)findViewById(R.id.button17);
        b18=(Button)findViewById(R.id.button18);
        b19=(Button)findViewById(R.id.button19);
        b20=(Button)findViewById(R.id.button20);
        b21=(Button)findViewById(R.id.button21);
        b22=(Button)findViewById(R.id.button22);
        b23=(Button)findViewById(R.id.button23);
        b24=(Button)findViewById(R.id.button24);
        b25=(Button)findViewById(R.id.button25);
        b26=(Button)findViewById(R.id.button26);
        b27=(Button)findViewById(R.id.button27);

        b.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);
        b17.setOnClickListener(this);
        b18.setOnClickListener(this);
        b19.setOnClickListener(this);
        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);
        b24.setOnClickListener(this);
        b25.setOnClickListener(this);
        b26.setOnClickListener(this);
        b27.setOnClickListener(this);

        lv=(ListView)findViewById(R.id.lv);
        ar=new ArrayAdapter<String>(Home.this,android.R.layout.simple_list_item_1,st);
        lv.setAdapter(ar);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==4)
                {
                    AlertDialog.Builder ad=new AlertDialog.Builder(Home.this);
                    ad.setIcon(R.drawable.exit);
                    ad.setTitle("Quit");
                    ad.setMessage("Do You Want To Exit");
                    ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                        }
                    });
                    ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    ad.create().show();
                }
                if (i==1)
                {
                    Intent j = new Intent(Home.this,Favourite.class);
                    startActivity(j);

                }
                if (i==2)
                {
                    Intent j=new Intent(Home.this,History.class);
                    startActivity(j);
                }
                if (i==3)
                {
                    Intent j=new Intent(Home.this,Downloads.class);
                    startActivity(j);
                }
                if (i==0)
                {
                    final Dialog d = new Dialog(Home.this);
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

                                final Dialog d = new Dialog(Home.this);

                                d.setTitle("Change Password");
                                d.setCancelable(false);
                                d.setContentView(R.layout.activity_app_lock2);

                                final EditText e1 = (EditText)d.findViewById(R.id.editText3);
                                final EditText e2 = (EditText)d.findViewById(R.id.editText4);

                                Button b29 = (Button)d.findViewById(R.id.button29);

                                b29.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (e1.getText().toString().length() != 0 && e2.getText().toString().length() != 0) {
                                            if (e1.getText().toString().equals(e2.getText().toString())) {
                                                db.execSQL("Delete from Password");
                                                db.execSQL("Insert into Password values('" + e1.getText().toString() + "');");
                                                d.dismiss();
                                            } else {
                                                Toast.makeText(Home.this, "Confirmation Error", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(Home.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                d.show();



                            }

                            else
                            {
                                Toast.makeText(Home.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                            }

                            d.dismiss();
                        }
                    });
                    d.show();
                }
            }
        });



    }

    @Override
    public void onClick(View view) {
        if (view==b)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","https://www.google.co.in/");
            startActivity(i);
        }
        if (view==b2)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","https://www.facebook.com/");
            startActivity(i);
        }
        if (view==b3)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","https://twitter.com/");
            startActivity(i);
        }
        if (view==b4)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","https://in.yahoo.com/");
            startActivity(i);
        }
        if (view==b5)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","https://mail.google.com/");
            startActivity(i);
        }
        if (view==b6)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link", "https://in.linkedin.com/");
            startActivity(i);
        }
        if (view==b7)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link", "https://www.instagram.com/");
            startActivity(i);
        }
        if (view==b8)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","https://www.pinterest.com/");
            startActivity(i);
        }
        if (view==b9)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.Youtube.com/");
            startActivity(i);
        }

        if (view==b10)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.saavn.com/");
            startActivity(i);
        }
        if (view==b11)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://gaana.com/");
            startActivity(i);
        }
        if (view==b12)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.indiamp3.com/");
            startActivity(i);
        }
        if (view==b13)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://mp3dhingana.com/");
            startActivity(i);
        }
        if (view==b14)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.raaga.com/");
            startActivity(i);
        }
        if (view==b15)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://djmazas.in/");
            startActivity(i);
        }
        if (view==b16)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.djpunjab.com/");
            startActivity(i);
        }
        if (view==b17)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://king-jatt.com/");
            startActivity(i);
        }
        if (view==b18)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://mp3skulls.info/free-mp3-download.html");
            startActivity(i);
        }
        if (view==b19)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.amazon.in/");
            startActivity(i);
        }
        if (view==b20)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.flipkart.com/");
            startActivity(i);
        }
        if (view==b21)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.snapdeal.com/");
            startActivity(i);
        }
        if (view==b22)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.myntra.com/");
            startActivity(i);
        }
        if (view==b23)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.jabong.com/");
            startActivity(i);
        }
        if (view==b24)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.homeshop18.com/");
            startActivity(i);
        }
        if (view==b25)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","https://www.localbanya.com/");
            startActivity(i);
        }
        if (view==b26)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.shopclues.com/");
            startActivity(i);
        }
        if (view==b27)
        {
            Intent i=new Intent(Home.this,WebActivity.class);
            i.putExtra("link","http://www.yepme.com/");
            startActivity(i);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle()=="Exit")
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(Home.this);
            ad.setIcon(R.drawable.exit);
            ad.setTitle("Quit");
            ad.setMessage("Do You Want To Exit");
            ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });
            ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            ad.create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}
