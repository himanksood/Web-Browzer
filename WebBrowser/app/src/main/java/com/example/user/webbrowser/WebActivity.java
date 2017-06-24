package com.example.user.webbrowser;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e;
    Button b;
    WebView wv;
    SQLiteDatabase db;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add To Favourite");
        menu.add("Favourite");
        menu.add("History");
        menu.add("Downloads");
        menu.add("Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }

        if(item.getTitle()=="Add To Favourite")
        {
            db.execSQL("Insert into Favourite values('"+wv.getUrl()+"');");

        }
        if (item.getTitle()=="Favourite")
        {
            Intent i=new Intent(WebActivity.this,Favourite.class);
            startActivity(i);
        }
        if(item.getTitle()=="History")
        {
            Intent i=new Intent(WebActivity.this,History.class);
            startActivity(i);
        }
        if (item.getTitle()=="Downloads")
        {
            Intent i=new Intent(WebActivity.this,Downloads.class);
            startActivity(i);
        }
        if (item.getTitle()=="Exit")
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(WebActivity.this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        db=SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Browser.sqlite", null);
        //backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        e=(EditText)findViewById(R.id.editText);
        b=(Button)findViewById(R.id.button10);
        wv=(WebView)findViewById(R.id.webView);

                wv.getSettings().setJavaScriptEnabled(true);
        final DownloadManager manager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        final File dDir=new File(Environment.getExternalStorageDirectory(), "Online Browser Downloads");
        if(!dDir.exists())
        {
            dDir.mkdir();
        }

        wv.setWebViewClient(new WebViewClient()

        {
            @Override
            public void onPageFinished(WebView view, final String url) {
                if (url.endsWith(".mp3")||url.endsWith(".mp4")||url.endsWith(".jpg")||url.endsWith(".pdf")||url.endsWith(".png")||url.endsWith(".jpeg")||url.endsWith(".3gp")||url.endsWith(".ppt")||url.endsWith(".doc")||url.endsWith(".rar")||url.endsWith(".zip"))
                {

                    final Uri source = Uri.parse(url);
                    AlertDialog.Builder ab = new AlertDialog.Builder(WebActivity.this);
                    ab.setCancelable(false);
                    ab.setMessage("Start Downloading "+source.getLastPathSegment());
                    ab.setTitle("Download");
                    ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            DownloadManager.Request request = new DownloadManager.Request(source);
                            File dFile = new File(dDir, source.getLastPathSegment());
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                            request.setDestinationUri(Uri.fromFile(dFile));
                            manager.enqueue(request);

                            if(url.endsWith(".mp3"))
                            {
                                db.execSQL("INSERT INTO Music VALUES('" + source.getLastPathSegment() + "','" + dDir.getAbsolutePath() + "');");

                            }

                            if(url.endsWith(".mp4")||url.endsWith(".3gp"))
                            {
                                db.execSQL("INSERT INTO Videos VALUES('" + source.getLastPathSegment() + "','" + dDir.getAbsolutePath() + "');");

                            }

                            if(url.endsWith(".jpg")||url.endsWith(".jpeg")||url.endsWith(".png"))
                            {
                                db.execSQL("INSERT INTO Pictures VALUES('" + source.getLastPathSegment() + "','" + dDir.getAbsolutePath() + "');");

                            }

                            if(url.endsWith(".pdf")||url.endsWith(".ppt")||url.endsWith(".zip")||url.endsWith(".rar")||url.endsWith(".doc"))
                            {
                                db.execSQL("INSERT INTO Others VALUES('" + source.getLastPathSegment() + "','" + dDir.getAbsolutePath() + "');");

                            }



                        }

                    });

                    ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.cancel();
                        }
                    });

                    ab.create().show();
                }


                super.onPageFinished(view, url);
            }
        });
        wv.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                db.execSQL("Insert into History values('"+view.getUrl()+"');");
                getSupportActionBar().setTitle(title);

                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

        String s=getIntent().getStringExtra("link");
        wv.loadUrl(s);
         b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        wv.loadUrl("https:/www.google.com/search?q="+e.getText().toString());

    }

    @Override
    public void onBackPressed() {
        if(wv.canGoBack())
        {
            wv.goBack();
        }
        else super.onBackPressed();

    }
}

