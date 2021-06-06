package com.pfc.gagarin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class AboutusScreen extends Activity {
    private ImageView iv_back5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        iv_back5 = findViewById(R.id.iv_back5);

        iv_back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutusScreen.this,SettingsScreen.class);
                startActivity(intent);
            }
        });


    }
}
