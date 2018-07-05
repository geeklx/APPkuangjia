package com.haier.cellarette.baselibrary.recycleviewgallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class RecycleViewGalleryActivity extends AppCompatActivity {

    private LinearLayout ll1;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewgallery);
        ll1 = findViewById(R.id.root);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start("hs.act.phone.gallerybigcards");
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start("hs.act.phone.weathersmallcards");
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start("hs.act.phone.verticalscroll");
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("https://github.com/yarolegovich/DiscreteScrollView");
                open(url);
            }
        });
    }

    private void start(String act) {
//        Intent intent = new Intent(this, token);
        Intent intent = new Intent(act);
        startActivity(intent);
    }

    private void open(Uri url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(url);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Snackbar.make(ll1,
                    "No app found to open the url",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}
