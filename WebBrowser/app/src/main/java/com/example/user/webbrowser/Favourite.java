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

import java.util.ArrayList;
import java.util.List;

public class Favourite extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lv2;
    ArrayAdapter<String> ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv2=(ListView)findViewById(R.id.listView2);
        List ls = new ArrayList();
        db= SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);

        Cursor cr = db.rawQuery("Select * from Favourite",null);

        while(cr.moveToNext())
        {
            ls.add(cr.getString(0));
        }
        ar=new ArrayAdapter(Favourite.this,android.R.layout.simple_list_item_1,ls);
        lv2.setAdapter(ar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Delete All");
        menu.add("Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home);
        {
            onBackPressed();
        }

        if (item.getTitle()=="Delete All")
        {
            db.execSQL("Delete from Favourite");
            List ls = new ArrayList();
            db= SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);

            Cursor cr = db.rawQuery("Select * from Favourite",null);

            while(cr.moveToNext())
            {
                ls.add(cr.getString(0));
            }
            ar=new ArrayAdapter(Favourite.this,android.R.layout.simple_list_item_1,ls);
            lv2.setAdapter(ar);

        }
        if (item.getTitle()=="Exit")
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(Favourite.this);
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
