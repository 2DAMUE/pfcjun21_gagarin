package com.pfc.gagarin;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationsScreen extends Activity {
    private Switch switch_lauch;
    private Switch switch_news;
    private ImageView iv_back3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);
        iv_back3 = findViewById(R.id.iv_back3);
        switch_lauch = findViewById(R.id.switch1);
        switch_news = findViewById(R.id.switch2);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("1234","1234", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationChannel channel2 = new NotificationChannel("12345","12345", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager2 = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel2);

        }

        switch_lauch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationsScreen.this,"1234");
                    builder.setContentTitle("Gagarin");
                    builder.setContentText("You've turned on launch notifications");
                    builder.setSmallIcon(R.drawable.marsicon3);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotificationsScreen.this);
                    managerCompat.notify(1,builder.build());
                }
            }
        });
        switch_news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationsScreen.this,"12345");
                builder.setContentTitle("Gagarin");
                builder.setContentText("You've turned on news notifications");
                builder.setSmallIcon(R.drawable.marsicon3);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotificationsScreen.this);
                managerCompat.notify(1,builder.build());
            }
        });

        iv_back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationsScreen.this,SettingsScreen.class);
                startActivity(intent);
            }
        });
    }
}
