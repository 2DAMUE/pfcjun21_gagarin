package com.pfc.gagarin;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.pfc.gagarin.entidad.Usuario;
import com.pfc.gagarin.persistencia.AccesoFirebase;

import eightbitlab.com.blurview.BlurView;


public class RegisterScreen extends AppCompatActivity {
    private ViewGroup root;
    private BlurView register_card;
    private BlurView register_view_title;
    private TextView register_tv_show,register_tv_show2;
    private TextInputEditText et_pass_register,et_confirm_pass,et_email_register;
    private CheckBox checkBox;
    private Button btn_register;
    private FirebaseAuth firebaseAuth;
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
        et_email_register = findViewById(R.id.register_ed_email);
        et_pass_register = findViewById(R.id.register_ed_pass);
        et_confirm_pass = findViewById(R.id.register_ed_confirm_pass);
        register_tv_show = findViewById(R.id.register_Show);
        register_tv_show2 = findViewById(R.id.register_Show2);
        checkBox = findViewById(R.id.checkBox);
        btn_register = findViewById(R.id.BTN_register);

        firebaseAuth = FirebaseAuth.getInstance();

        //Toggle Password Show/Hide
        register_tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(condicion_toggle==false){
                    et_pass_register.setTransformationMethod(new SingleLineTransformationMethod().getInstance());
                    register_tv_show.setText("Hide");
                    condicion_toggle=true;
                }else{
                    register_tv_show.setText("Show");
                    et_pass_register.setTransformationMethod(PasswordTransformationMethod.getInstance());
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

        //Function Button Register
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email_register.getEditableText().toString();
                String pass = et_pass_register.getEditableText().toString();
                String confirm_pass = et_confirm_pass.getEditableText().toString();
                boolean validado = comprobarCampos(email,pass,confirm_pass);
                if(validado){
                    Usuario user = new Usuario(email,pass);
                    AccesoFirebase.registrarUsuario(firebaseAuth,user);
                    Intent intent = new Intent(RegisterScreen.this,LoginScreen.class);
                    startActivity(intent);
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
    private boolean comprobarCampos(String email, String pass, String confirm_pass) {
        if (email.isEmpty() && pass.isEmpty() && confirm_pass.isEmpty()) {
            showToast("Some of the fields are empty");
            return false;
        } else if (email.isEmpty()) {
            showToast("Email field cannot be empty");
            return false;
        } else if (pass.isEmpty()) {
            showToast("Password field cannot be empty");
            return false;
        } else if (confirm_pass.isEmpty()) {
            showToast("Confirm Password field cannot be empty");
            return false;
        }else if(!confirm_pass.equals(pass)){
            showToast("Passwords don't match");
            return false;
        }else {
            return true;
        }
    }
}