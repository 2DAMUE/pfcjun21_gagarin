package com.pfc.gagarin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    //Variables
    private ImageView logomars;
    private Animation marsAnimation;
    private Animation marsAnimation2;
    private TextView tv_splash_gagarin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Status Bar off
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);

        openApp(true);

        logomars = findViewById(R.id.homeis_logo);
        tv_splash_gagarin = findViewById(R.id.tv_splash_gagarin);
        marsAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        marsAnimation2 = AnimationUtils.loadAnimation(this, R.anim.tv_animation_splash);
        tv_splash_gagarin.startAnimation(marsAnimation2);
        logomars.startAnimation(marsAnimation);
    }

    private void openApp(boolean locationPermission) {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen
                        .this, LoginScreen.class);
                startActivity(intent);
            }
        }, 6000);
    }
}
