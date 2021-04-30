package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import eightbitlab.com.blurview.BlurView;


public class LoginScreen extends AppCompatActivity {
    private ViewGroup root;
    private BlurView card;
    private BlurView view_title;
    private TextView tv_show;
    private TextInputEditText et_pass;
    private boolean condicion_toggle = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root= findViewById(R.id.root);
        card = findViewById(R.id.blur_card);
        view_title= findViewById(R.id.blur_tv);
        et_pass = findViewById(R.id.ed_pass);
        tv_show = findViewById(R.id.Show);

        //Toggle Password Show/Hide
        tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(condicion_toggle==false){
                    et_pass.setTransformationMethod(new SingleLineTransformationMethod().getInstance());
                    tv_show.setText("Hide");
                    condicion_toggle=true;
                }else{
                    tv_show.setText("Show");
                    et_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    condicion_toggle=false;
                }
            }
        });

        //Desenfocado imagen

        final float radius = 3f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();

        card.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
        view_title.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);

    }
}