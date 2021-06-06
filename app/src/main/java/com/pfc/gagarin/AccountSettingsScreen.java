package com.pfc.gagarin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AccountSettingsScreen extends Activity {
    private ImageView iv_back2;
    private TextView tv_save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        iv_back2 = findViewById(R.id.iv_back2);
        tv_save = findViewById(R.id.tv_save);

        iv_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsScreen.this,SettingsScreen.class);
                startActivity(intent);
            }
        });

    }

}
