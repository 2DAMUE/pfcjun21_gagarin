package com.pfc.gagarin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
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
import com.pfc.gagarin.R;
import com.pfc.gagarin.entidad.Mensaje;

import java.util.List;

public class AdaptadorMensajes extends RecyclerView.Adapter<AdaptadorMensajes.MiContenedorDeVistas> {
    @NonNull
    private List<Mensaje> mensajes;
    private Context context;

    public AdaptadorMensajes(@NonNull List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public MiContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_card_chat,parent,false);
        ImageView iv_comment=vista.findViewById(R.id.iv_comment);
        TextView tv_id_comment=vista.findViewById(R.id.tv_id_comment);
        TextView tv_body_comment=vista.findViewById(R.id.tv_body_comment);
        MiContenedorDeVistas contenedor = new MiContenedorDeVistas(vista);
        context = parent.getContext();
        Log.d("Contenedor","Creando contenedor de vistas");
        return contenedor;
    }

    @Override
    public void onBindViewHolder(@NonNull MiContenedorDeVistas holder, int position) {
        Mensaje m=mensajes.get(position);
        holder.tv_id_comment.setText(m.getUsername());
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
        //---------------------
        Log.d("Contenedor","vinculando contenedor de vistas");
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public static class MiContenedorDeVistas extends RecyclerView.ViewHolder{
        public TextView tv_id_comment,tv_body_comment;
        public ImageView iv_comment;

        public MiContenedorDeVistas(View vista) {
            super(vista);
            this.tv_id_comment = vista.findViewById(R.id.tv_id_comment);
            this.tv_body_comment = vista.findViewById(R.id.tv_body_comment);
            this.iv_comment = vista.findViewById(R.id.iv_comment);
        }

    }
}

