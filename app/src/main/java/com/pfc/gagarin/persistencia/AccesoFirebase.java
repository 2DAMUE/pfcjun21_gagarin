package com.pfc.gagarin.persistencia;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pfc.gagarin.entidad.Usuario;

public class AccesoFirebase {

    public static void registrarUsuario(FirebaseAuth firebaseAuth, Usuario user) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                        }else{
                            Log.d("error","error");
                        }
                    }
                });
    }
}
