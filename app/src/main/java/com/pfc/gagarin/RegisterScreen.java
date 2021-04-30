package com.pfc.gagarin;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.google.android.material.textfield.TextInputEditText;

import eightbitlab.com.blurview.BlurView;


public class RegisterScreen extends AppCompatActivity {
    private ViewGroup root;
    private BlurView register_card;
    private BlurView register_view_title;
    private TextView register_tv_show,register_tv_show2;
    private TextInputEditText et_pass,et_confirm_pass;
    private boolean condicion_toggle = false;
    private boolean condicion_toggle2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root= findViewById(R.id.root2);
        register_card = findViewById(R.id.register_blur_card);
        register_view_title= findViewById(R.id.register_blur_tv);
        et_pass = findViewById(R.id.register_ed_pass);
        et_confirm_pass = findViewById(R.id.register_ed_confirm_pass);
        register_tv_show = findViewById(R.id.register_Show);
        register_tv_show2 = findViewById(R.id.register_Show2);

        //Toggle Password Show/Hide
        register_tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(condicion_toggle==false){
                    et_pass.setTransformationMethod(new SingleLineTransformationMethod().getInstance());
                    register_tv_show.setText("Hide");
                    condicion_toggle=true;
                }else{
                    register_tv_show.setText("Show");
                    et_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    condicion_toggle=false;
                }
            }
        });
        register_tv_show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(condicion_toggle2==false){
                    et_confirm_pass.setTransformationMethod(new SingleLineTransformationMethod().getInstance());
                    register_tv_show2.setText("Hide");
                    condicion_toggle2=true;
                }else{
                    register_tv_show2.setText("Show");
                    et_confirm_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    condicion_toggle2=false;
                }
            }
        });

        //Desenfocado imagen

        final float radius = 3f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();

        register_card.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
        register_view_title.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);

    }
}