package com.example.user.webbrowser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class Downloads extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lv1,lv2,lv3,lv4;
    ArrayAdapter<String> ar;
    TabHost th;
    TabHost.TabSpec ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv1=(ListView)findViewById(R.id.lv1);
        lv2=(ListView)findViewById(R.id.lv2);
        lv3=(ListView)findViewById(R.id.lv3);
        lv4=(ListView)findViewById(R.id.lv4);

        List ls1 = new ArrayList();

        db= SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);
        Cursor cr1=db.rawQuery("Select * from Music",null);
        while(cr1.moveToNext())
        {
            ls1.add(cr1.getString(0));
        }
        ar=new ArrayAdapter(Downloads.this,android.R.layout.simple_list_item_1,ls1);
        lv1.setAdapter(ar);

        List ls2 = new ArrayList();

        db= SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);
        Cursor cr2=db.rawQuery("Select * from Videos",null);
        while(cr2.moveToNext())
        {
            ls2.add(cr2.getString(0));
        }
        ar=new ArrayAdapter(Downloads.this,android.R.layout.simple_list_item_1,ls2);
        lv2.setAdapter(ar);

        List ls3 = new ArrayList();

        db= SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);
        Cursor cr3=db.rawQuery("Select * from Pictures",null);
        while(cr3.moveToNext())
        {
            ls3.add(cr3.getString(0));
        }
        ar=new ArrayAdapter(Downloads.this,android.R.layout.simple_list_item_1,ls3);
        lv3.setAdapter(ar);

        List ls4 = new ArrayList();

        db= SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);
        Cursor cr4=db.rawQuery("Select * from Others",null);
        while(cr4.moveToNext())
        {
            ls4.add(cr4.getString(0));
        }
        ar=new ArrayAdapter(Downloads.this,android.R.layout.simple_list_item_1,ls4);
        lv4.setAdapter(ar);

        th=(TabHost)findViewById(R.id.tabHost2);
        th.setup();

        ts=th.newTabSpec("Tag1");
        ts.setContent(R.id.linearLayout4);
        ts.setIndicator("Music");
        th.addTab(ts);

        ts=th.newTabSpec("Tag2");
        ts.setContent(R.id.linearLayout5);
        ts.setIndicator("Videos");
        th.addTab(ts);

        ts=th.newTabSpec("Tag3");
        ts.setContent(R.id.linearLayout6);
        ts.setIndicator("Pictures");
        th.addTab(ts);

        ts=th.newTabSpec("Tag4");
        ts.setContent(R.id.linearLayout7);
        ts.setIndicator("others");
        th.addTab(ts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Delete All");
        menu.add("Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        if (item.getTitle()=="Exit")
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(Downloads.this);
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
        if (item.getTitle()=="Delete All")
        {

        }
        if (item.getItemId()==android.R.id.home);
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
