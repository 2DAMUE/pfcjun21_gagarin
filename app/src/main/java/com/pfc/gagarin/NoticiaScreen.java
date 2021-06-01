package com.pfc.gagarin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.pfc.gagarin.adapter.AdaptadorMensajes;
import com.pfc.gagarin.entidad.Mensaje;
import com.pfc.gagarin.persistencia.AccesoFirebase;
import com.pfc.gagarin.ws_body_noticias.HiloPeticionBodyNoticias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eightbitlab.com.blurview.BlurView;

public class NoticiaScreen extends AppCompatActivity implements HiloPeticionBodyNoticias.InterfazBodyNoticias,AccesoFirebase.InterfazFirebase{
    private ViewGroup root_story;
    private BlurView blur_scroll_story;
    private BlurView blur_blu_story;
    private ImageView iv_arrow_back,iv_comment_user;
    private TextView tv_title_story;
    private ImageView iv_image_story;
    private TextView tv_body_story;
    private ProgressBar pg_progress_story;
    private TextView tv_source_story_link;
    private EditText et_comment_story;
    private RecyclerView rv_mensajes;

    private FirebaseAuth firebaseAuth;

    private List<Mensaje> lista_mensajes;
    private AdaptadorMensajes adaptadorMensajes;
    private String username;
    private int contador=1;
    private boolean is_liked=false;
    private boolean is_disliked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root_story= findViewById(R.id.root_story);
        blur_scroll_story = findViewById(R.id.blur_scroll_story);
        blur_blu_story = findViewById(R.id.blur_blu_story);
        iv_arrow_back = findViewById(R.id.iv_arrow_back);
        tv_title_story = findViewById(R.id.tv_title_story);
        tv_body_story = findViewById(R.id.tv_body_story);
        tv_source_story_link = findViewById(R.id.tv_source_story_link);
        iv_image_story = findViewById(R.id.iv_image_story);
        iv_comment_user = findViewById(R.id.iv_comment_user);
        pg_progress_story = findViewById(R.id.pg_progress_story);
        et_comment_story = findViewById(R.id.et_comment_story);
        rv_mensajes = findViewById(R.id.rv_comments);

        firebaseAuth=FirebaseAuth.getInstance();
        onStart();


        lista_mensajes = new ArrayList<Mensaje>();
        RecyclerView.LayoutManager gestor = new LinearLayoutManager(NoticiaScreen.this);
        AdaptadorMensajes adaptador = new AdaptadorMensajes(lista_mensajes,NoticiaScreen.this);
        rv_mensajes.setLayoutManager(gestor);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_like = (TextView)view.findViewById(R.id.tv_like);
                TextView tv_dislike = (TextView)view.findViewById(R.id.tv_dislike);
                TextView tv_like_count =(TextView)view.findViewById(R.id.tv_like_count);
                TextView tv_dislike_count=(TextView)view.findViewById(R.id.tv_dislike_count);
                tv_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mensaje msj = lista_mensajes.get(rv_mensajes.getChildAdapterPosition(view));
                        if(!msj.getUsers_who_like_list().contains(username)){
                            tv_like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like2, 0, 0, 0);
                            tv_like_count.setText(Integer.parseInt(tv_like_count.getText().toString())+contador+"");
                            addLike(tv_like_count.getText().toString(),rv_mensajes.getChildAdapterPosition(view));
                            is_liked=true;
                            if(is_disliked){
                                tv_dislike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dislike, 0, 0, 0);
                                tv_dislike_count.setText(Integer.parseInt(tv_dislike_count.getText().toString())-1+"");
                                msj.getUsers_who_dislike_list().remove(username);
                                FirebaseFirestore.getInstance().collection(tv_title_story.getText().toString().substring(0,15).replace(" ","").replace("'","").replace("-","")).document(msj.getTime()).set(msj);
                                is_disliked=false;
                            }
                        }else{
                            tv_like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like, 0, 0, 0);
                            tv_like_count.setText(Integer.parseInt(tv_like_count.getText().toString())-1+"");
                            msj.getUsers_who_like_list().remove(username);
                            FirebaseFirestore.getInstance().collection(tv_title_story.getText().toString().substring(0,15).replace(" ","").replace("'","").replace("-","")).document(msj.getTime()).set(msj);
                            is_liked=false;
                        }
                    }
                });
                tv_dislike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mensaje msj = lista_mensajes.get(rv_mensajes.getChildAdapterPosition(view));
                        if(!msj.getUsers_who_dislike_list().contains(username)){
                            tv_dislike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dislike2, 0, 0, 0);
                            tv_dislike_count.setText(Integer.parseInt(tv_dislike_count.getText().toString())+contador+"");
                            addDislike(tv_dislike_count.getText().toString(),rv_mensajes.getChildAdapterPosition(view));
                            is_disliked=true;
                            if(is_liked){
                                tv_like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like, 0, 0, 0);
                                tv_like_count.setText(Integer.parseInt(tv_like_count.getText().toString())-1+"");
                                msj.getUsers_who_like_list().remove(username);
                                FirebaseFirestore.getInstance().collection(tv_title_story.getText().toString().substring(0,15).replace(" ","").replace("'","").replace("-","")).document(msj.getTime()).set(msj);
                                is_liked=false;
                            }
                        }else{
                            tv_dislike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dislike, 0, 0, 0);
                            tv_dislike_count.setText(Integer.parseInt(tv_dislike_count.getText().toString())-1+"");
                            msj.getUsers_who_dislike_list().remove(username);
                            FirebaseFirestore.getInstance().collection(tv_title_story.getText().toString().substring(0,15).replace(" ","").replace("'","").replace("-","")).document(msj.getTime()).set(msj);
                            is_disliked=false;
                        }
                    }
                });
            }
        });
        rv_mensajes.setAdapter(adaptador);

        FirebaseFirestore.getInstance().collection(getIntent().getStringExtra("Home").substring(0,15).replace(" ","").replace("'","").replace("-","")).orderBy("time", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange mDocumentChange : queryDocumentSnapshots.getDocumentChanges()){
                            if(mDocumentChange.getType() == DocumentChange.Type.ADDED){
                                lista_mensajes.add(mDocumentChange.getDocument().toObject(Mensaje.class));
                                adaptador.notifyDataSetChanged();
                                rv_mensajes.smoothScrollToPosition(lista_mensajes.size());
                            }
                        }
                    }
                });



        //get user photo
        obtenerFotoPerfil();

        tv_title_story.setText(getIntent().getStringExtra("Home"));
        //Set image story
        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("Image"))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            iv_image_story.setBackground(resource);
                        }
                    }
                });
        //Get url story
        String url_story = getIntent().getStringExtra("URL");
        tv_source_story_link.setText(url_story);
        // Hilo que llama al webscrapping
        pg_progress_story.setVisibility(View.VISIBLE);
        HiloPeticionBodyNoticias r = new HiloPeticionBodyNoticias(NoticiaScreen.this,url_story);
        Thread t1 = new Thread(r);
        t1.start();

        blurBackground();

        iv_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticiaScreen.this,HomeScreen.class);
                startActivity(intent);
            }
        });

        et_comment_story.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    addMessageToChat();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        AccesoFirebase.devolverUsuarios(null,NoticiaScreen.this,null);
    }

    private void addLike(String like, int childAdapterPosition) {
        Mensaje msj = lista_mensajes.get(childAdapterPosition);
        if(true){
            List lista=msj.getUsers_who_like_list();
            lista.add(username);
            msj.setUsers_who_like((ArrayList<String>) lista);
        }
        //msj.setUsers_who_like(userss);
        msj.setLike(Integer.parseInt(like));
        FirebaseFirestore.getInstance().collection(tv_title_story.getText().toString().substring(0,15).replace(" ","").replace("'","").replace("-","")).document(msj.getTime()).set(msj);
    }
    private void addDislike(String dislike, int childAdapterPosition) {
        Mensaje msj = lista_mensajes.get(childAdapterPosition);
        if(true){
            List lista=msj.getUsers_who_dislike_list();
            lista.add(username);
            msj.setUsers_who_dislike_list((ArrayList<String>) lista);
        }
        //msj.setUsers_who_like(userss);
        msj.setDislike(Integer.parseInt(dislike));
        FirebaseFirestore.getInstance().collection(tv_title_story.getText().toString().substring(0,15).replace(" ","").replace("'","").replace("-","")).document(msj.getTime()).set(msj);
    }

    private void addMessageToChat() {
        String message = et_comment_story.getEditableText().toString();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String time = String.valueOf(System.currentTimeMillis());
        String message_id = tv_title_story.getText().toString();
        if(!message.trim().isEmpty()){
            Mensaje messageObj = new Mensaje(message,username,time,message_id);
            if(user.getPhotoUrl()!=null){
                messageObj.setPhoto(user.getPhotoUrl().toString());
            }else{
                messageObj.setPhoto("por defecto");
            }

            FirebaseFirestore.getInstance().collection(message_id.substring(0,15).replace(" ","").replace("'","").replace("-","")).document(messageObj.getTime()).set(messageObj);
            et_comment_story.setText("");
        }
    }

    private void obtenerFotoPerfil() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user.getPhotoUrl() != null){
            Glide.with(getApplicationContext())
                    .load(user.getPhotoUrl())
                    .circleCrop()
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                iv_comment_user.setBackground(resource);
                            }
                        }
                    });
        }else{
            Glide.with(getApplicationContext())
                    .load(R.drawable.astronauta)
                    .circleCrop()
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                iv_comment_user.setBackground(resource);
                            }
                        }
                    });
        }


    }

    //Desenfocado imagen

    private void blurBackground() {
        final float radius = 1f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();
        /*
        blur_scroll_story.setupWith(root_story)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
         */
        blur_blu_story.setupWith(root_story)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }


    @Override
    public void devolverBodyNoticias(String cuerpoNoticia) {
        //Set Body Story
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_body_story.setText(cuerpoNoticia);
                pg_progress_story.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void devolverUsuarios(HashMap<String,String> usuariosBBDD) {
        String email_usuario=firebaseAuth.getCurrentUser().getEmail();
        Log.d("emailes",usuariosBBDD.toString());
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user.getDisplayName()==null){
            username = usuariosBBDD.get(email_usuario);
        }else{
            username = user.getDisplayName();
        }

    }
}