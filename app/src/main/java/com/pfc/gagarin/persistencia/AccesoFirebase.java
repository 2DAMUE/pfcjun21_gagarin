package com.pfc.gagarin.persistencia;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pfc.gagarin.NoticiaScreen;
import com.pfc.gagarin.RegisterScreen;
import com.pfc.gagarin.entidad.Mensaje;
import com.pfc.gagarin.entidad.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class AccesoFirebase {
    private static FirebaseDatabase bd;
    private static DatabaseReference ref;
    public static HashMap<String,String> usuariosBBDD= new HashMap<>();

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
    private static DatabaseReference conexionBBDD() {
        bd = FirebaseDatabase.getInstance();
        ref = bd.getReference("Usuarios");
        return ref;
    }
    public static void altaUsuario(Usuario usuario) {
        DatabaseReference ref = conexionBBDD();
        ref.child(usuario.getEmail().replace(".","")).setValue(usuario);
    }
    public static void devolverUsuarios(RegisterScreen llamante, NoticiaScreen llamante2) {
        DatabaseReference ref = conexionBBDD();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> datos = snapshot.getChildren();
                for (DataSnapshot d : datos) {
                    Usuario userBBDD = d.getValue(Usuario.class);
                    usuariosBBDD.put(userBBDD.getEmail(),userBBDD.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ERROR", error.getMessage());
            }
        });
        if(llamante!=null){
            llamante.devolverUsuarios(usuariosBBDD);
        }else if(llamante2!=null){
            llamante2.devolverUsuarios(usuariosBBDD);
        }

    }

    public static void grabarMensaje(Mensaje messageObj) {
        bd = FirebaseDatabase.getInstance();
        ref = bd.getReference("Chat").child(messageObj.getMessage_id());
        ref.push().setValue(messageObj);
    }


    public interface InterfazFirebase{
        public void devolverUsuarios(HashMap<String,String> usuariosBBDD);
    }
}