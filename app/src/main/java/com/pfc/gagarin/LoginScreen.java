package com.pfc.gagarin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eightbitlab.com.blurview.BlurView;


public class LoginScreen extends AppCompatActivity {
    private ViewGroup root;
    private BlurView card;
    private BlurView view_title;
    private TextView tv_show;
    private TextInputEditText et_pass,et_email;
    private TextView tv_sign;
    private Button btn_login;
    private SpannableStringBuilder spanSB;
    private boolean condicion_toggle = false;

    private FirebaseAuth firebaseAuth;

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
        et_email= findViewById(R.id.ed_email);
        tv_show = findViewById(R.id.Show);
        tv_sign = findViewById(R.id.sign);
        btn_login = findViewById(R.id.BTN_login);

        firebaseAuth=FirebaseAuth.getInstance();
        //btn_login.setEnabled(false);

        //et_pass.addTextChangedListener(validarCampos);
        int errorColor =getResources().getColor(R.color.super_white);
        String errorString = "This field cannot be empty";  // Your custom error message.
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
        spanSB = new SpannableStringBuilder(errorString);
        spanSB.setSpan(foregroundColorSpan, 0, errorString.length(), 0);

        //Function button login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getEditableText().toString();
                String pass = et_pass.getEditableText().toString();
                boolean validado = comprobarCampos(email,pass);
                if(validado==true){
                    if(firebaseAuth.getCurrentUser() != null ){
                        firebaseAuth.signInWithEmailAndPassword(et_email.getEditableText().toString(),et_pass.getEditableText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }else{
                                                Toast.makeText(LoginScreen.this,"Por favor, verifica tu email",Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            //showToast("Usuario o contraseña incorrectos");
                                            Toast.makeText(LoginScreen.this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                                            Log.e("error",task.getException().getLocalizedMessage());
                                        }
                                    }
                                });

                    }else{
                        //showToast();
                    }
                }

            }
        });

        //Go to RegisterScreen

        tv_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });

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



    //Validar campos cada vez que se escribe en un input
    /*
    private TextWatcher validarCampos= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email = et_email.getEditableText().toString();
            String pass = et_pass.getEditableText().toString();
            boolean validado = comprobarCampos(email,pass);
            btn_login.setEnabled(validado);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
     */

    private boolean comprobarCampos(String email, String pass) {
        if(email.isEmpty() && pass.isEmpty()){

            showToast("Some of the fields are empty");
            return false;
        }
        else if (email.isEmpty()) {
            showToast("Email field cannot be empty");
            return false;
        } else if (pass.isEmpty()) {
            showToast("Password field cannot be empty");
            return false;
        } else {
            return true;
        }
    }
}