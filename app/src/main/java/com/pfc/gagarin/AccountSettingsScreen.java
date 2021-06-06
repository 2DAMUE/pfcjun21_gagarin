package com.pfc.gagarin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.pfc.gagarin.entidad.Usuario;
import com.pfc.gagarin.persistencia.AccesoFirebase;

public class AccountSettingsScreen extends Activity {
    private ImageView iv_back2;
    private TextView tv_save;
    private FirebaseAuth firebaseAuth;
    private TextInputEditText username_txti,password_txti;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        firebaseAuth=FirebaseAuth.getInstance();

        iv_back2 = findViewById(R.id.iv_back2);
        tv_save = findViewById(R.id.tv_save);
        username_txti = findViewById(R.id.username_txti);
        password_txti = findViewById(R.id.password_txti);

        iv_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsScreen.this,SettingsScreen.class);
                startActivity(intent);
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username_txti.getText().toString().trim().isEmpty() && password_txti.getText().toString().trim().isEmpty()){
                   showToast("Fields cannot be empty");
                }else{
                    if(!username_txti.getText().toString().trim().isEmpty()){
                        Usuario user = new Usuario();
                        user.setEmail(firebaseAuth.getCurrentUser().getEmail());
                        user.setUsername(username_txti.getEditableText().toString());
                        AccesoFirebase.altaUsuario(user);
                        showToast("Username Changed");
                        Intent intent = new Intent(AccountSettingsScreen.this,SettingsScreen.class);
                        startActivity(intent);
                    }else{
                        FirebaseAuth.getInstance().sendPasswordResetEmail(firebaseAuth.getCurrentUser().getEmail())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            showToast("Mail to reset password has been sent");
                                            Intent intent = new Intent(AccountSettingsScreen.this,SettingsScreen.class);
                                            startActivity(intent);
                                        }
                                    }
                                });
                    }
                }


            }
        });

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
