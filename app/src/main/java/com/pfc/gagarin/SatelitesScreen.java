package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class SatelitesScreen extends AppCompatActivity {

    private WebView wb;
    private  ImageView img_back;
    private  TextView prov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satelites_screen);
        wb = findViewById(R.id.WV_satélites);
        prov = findViewById(R.id.TV_InfoProvided);

        //web view satelites orbitando en la tierra
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
            }
        });
        wb.loadUrl("http://www.stuffin.space");
        prov.setText(R.string.H_satel);

        img_back = findViewById(R.id.IV_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}