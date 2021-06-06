package com.pfc.gagarin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pfc.gagarin.LoginScreen;
import com.pfc.gagarin.NoticiaScreen;
import com.pfc.gagarin.R;
import com.pfc.gagarin.entidad.Mensaje;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdaptadorMensajes extends RecyclerView.Adapter<AdaptadorMensajes.MiContenedorDeVistas> implements View.OnClickListener {
    @NonNull
    private List<Mensaje> mensajes;
    private Context context;
    private NoticiaScreen noticiaScreen;
    private View.OnClickListener listener;

    public AdaptadorMensajes(@NonNull List<Mensaje> mensajes, NoticiaScreen noticiaScreen) {
        this.mensajes = mensajes;
        this.noticiaScreen = noticiaScreen;
    }

    @Override
    public MiContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_card_chat,parent,false);
        ImageView iv_comment=vista.findViewById(R.id.iv_comment);
        TextView tv_id_comment=vista.findViewById(R.id.tv_id_comment);
        TextView tv_body_comment=vista.findViewById(R.id.tv_body_comment);
        TextView tv_like_count = vista.findViewById(R.id.tv_like_count);
        TextView tv_dislike_count = vista.findViewById(R.id.tv_dislike_count);
        MiContenedorDeVistas contenedor = new MiContenedorDeVistas(vista);
        context = parent.getContext();
        vista.setOnClickListener(this);
        Log.d("Contenedor","Creando contenedor de vistas");
        return contenedor;
    }

    @Override
    public void onBindViewHolder(@NonNull MiContenedorDeVistas holder, int position) {
        Mensaje m=mensajes.get(position);
        holder.tv_like_count.setText(m.getLike()+"");
        holder.tv_dislike_count.setText(m.getDislike()+"");
        holder.tv_id_comment.setText(m.getUsername()+" · Now");
        holder.tv_body_comment.setText(m.getMessage());
        //Set photo
        if(!m.getPhoto().equals("por defecto")){
            Glide.with(context)
                    .load(m.getPhoto())
                    .circleCrop()
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                holder.iv_comment.setBackground(resource);
                            }
                        }
                    });
        }else{
            Glide.with(context)
                    .load(R.drawable.astronauta)
                    .circleCrop()
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                holder.iv_comment.setBackground(resource);
                            }
                        }
                    });
        }

        Timer timer = new Timer();
        TimerTask timerTask;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                noticiaScreen.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Long time =System.currentTimeMillis() - Long.valueOf(m.getTime());
                        Log.d("time",time.toString());
                        if (time > 0) {
                            int minutes = (int) ((time / (1000 * 60)) % 60);
                            int hours = (int) ((time / (1000 * 60 * 60)) % 24);
                            String texto = holder.tv_id_comment.getText().toString().split(" · ")[0];
                            if(minutes>=1 && minutes <= 60){
                                holder.tv_id_comment.setText(texto+" · "+ minutes +" minutes ago");
                            }if(hours>=1 && hours<=60){
                                holder.tv_id_comment.setText(texto+" · "+ hours +" hours ago");
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 60000);
        Log.d("Contenedor","vinculando contenedor de vistas");
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class MiContenedorDeVistas extends RecyclerView.ViewHolder{
        public TextView tv_id_comment,tv_body_comment,tv_like_count,tv_dislike_count;
        public ImageView iv_comment;

        public MiContenedorDeVistas(View vista) {
            super(vista);
            this.tv_dislike_count = vista.findViewById(R.id.tv_dislike_count);
            this.tv_like_count = vista.findViewById(R.id.tv_like_count);
            this.tv_id_comment = vista.findViewById(R.id.tv_id_comment);
            this.tv_body_comment = vista.findViewById(R.id.tv_body_comment);
            this.iv_comment = vista.findViewById(R.id.iv_comment);
        }

    }
}


