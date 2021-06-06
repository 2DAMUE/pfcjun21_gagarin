package com.pfc.gagarin;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsScreen extends Activity {
    private ConstraintLayout settings_content;
    private ImageView iv_more_settings,iv_more_notifications,iv_aboutus,iv_back;
    private FirebaseAuth firebaseAuth;
    private TextView settings_logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        firebaseAuth=FirebaseAuth.getInstance();

        inicializarCampos();
        comprobarLogin();

        //Listeners
        settings_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseAuth.getCurrentUser().getDisplayName()!=null){
                    LoginScreen.mGoogleSignInClient.signOut();
                    cerrarSesion();
                }else if(LoginScreen.condicion_facebook){
                    com.facebook.login.LoginManager.getInstance().logOut();
                }else{
                    firebaseAuth.signOut();
                    cerrarSesion();
                }
            }
        });

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

    private void cerrarSesion() {
        showToast("You are logged out");
        Intent intent = new Intent(SettingsScreen.this,LoginScreen.class);
        startActivity(intent);
    }

    private void comprobarLogin() {
        //Si el usuario es normal, mostramos la opción de ajustes de cuenta.
        if(firebaseAuth.getCurrentUser().getDisplayName()!=null || LoginScreen.condicion_facebook==true){
            ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(settings_content, "translationY", 0, -190);
            objectAnimator.setDuration(0);
            objectAnimator.start();
        }
    }

    private void inicializarCampos() {
        settings_content = findViewById(R.id.settings_content);
        iv_more_notifications = findViewById(R.id.iv_more_notifications);
        iv_more_settings = findViewById(R.id.iv_more_account_settings);
        iv_aboutus = findViewById(R.id.iv_more_about_us);
        iv_back = findViewById(R.id.iv_back);
        settings_logout = findViewById(R.id.settings_logout);
    }
    private void showToast(String texto) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.toast_card));
        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText(texto);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }
}
