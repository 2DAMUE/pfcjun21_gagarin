package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen2 extends AppCompatActivity {
    private TextView tv_title_splash;
    private Animation tv_animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);
        getSupportActionBar().hide();

        //Status Bar off
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        openApp(true);
        tv_title_splash = findViewById(R.id.tv_splash_title);
        tv_animation = AnimationUtils.loadAnimation(this, R.anim.tv_animation_splash);
        tv_title_splash.startAnimation(tv_animation);

    }
    private void openApp(boolean locationPermission) {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen2
                        .this, LoginScreen.class);
                startActivity(intent);
            }
            }, 4000);
    }
}