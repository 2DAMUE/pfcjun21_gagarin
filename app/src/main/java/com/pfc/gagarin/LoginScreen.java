package com.pfc.gagarin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

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
    private Button btn_google;
    private SpannableStringBuilder spanSB;
    private boolean condicion_toggle = false;

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 100;

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
        btn_google = findViewById(R.id.sign_google);


        createGoogleRequest();
        firebaseAuth=FirebaseAuth.getInstance();

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
                boolean validado = checkFields(email,pass);
                if(validado==true){
                    if(firebaseAuth.getCurrentUser() != null ){
                        firebaseAuth.signInWithEmailAndPassword(et_email.getEditableText().toString(),et_pass.getEditableText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                                Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
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
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
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

    private void createGoogleRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
                // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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

    private boolean checkFields(String email, String pass) {
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("tag", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("tag", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("tag", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String uid = user.getUid();
                            String email = user.getEmail();
                            Log.d("tag","email:"+email);
                            Log.d("tag","uid:"+uid);
                            if(task.getResult().getAdditionalUserInfo().isNewUser()){
                                showToast("Account Created...");
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("tag", "signInWithCredential:failure", task.getException());
                        }
                        loadMain();
                        finish();
                    }
                });
    }

    private void loadMain() {
        Log.e("he","se ha metido en el metodo");

            Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

    }
}