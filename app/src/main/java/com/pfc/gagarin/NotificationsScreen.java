package com.pfc.gagarin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class NotificationsScreen extends Activity {
    private ImageView iv_back3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);
        iv_back3 = findViewById(R.id.iv_back3);

        iv_back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationsScreen.this,SettingsScreen.class);
                startActivity(intent);
            }
        });
    }
}
