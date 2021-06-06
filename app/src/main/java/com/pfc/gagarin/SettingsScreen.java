package com.pfc.gagarin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class SettingsScreen extends Activity {
    private ImageView iv_more_settings,iv_more_notifications,iv_aboutus,iv_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inicializarCampos();

        //Listeners

        iv_more_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsScreen.this,AccountSettingsScreen.class);
                startActivity(intent);
            }
        });
        iv_more_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsScreen.this,NotificationsScreen.class);
                startActivity(intent);
            }
        });
        iv_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsScreen.this,AboutusScreen.class);
                startActivity(intent);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsScreen.this,HomeScreen.class);
                startActivity(intent);
            }
        });
    }

    private void inicializarCampos() {
        iv_more_notifications = findViewById(R.id.iv_more_notifications);
        iv_more_settings = findViewById(R.id.iv_more_account_settings);
        iv_aboutus = findViewById(R.id.iv_more_about_us);
        iv_back = findViewById(R.id.iv_back);
    }
}
