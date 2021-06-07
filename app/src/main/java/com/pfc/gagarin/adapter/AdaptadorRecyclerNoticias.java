package com.pfc.gagarin.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.pfc.gagarin.HomeScreen;
import com.pfc.gagarin.NoticiaScreen;
import com.pfc.gagarin.entidad.Noticia;
import com.pfc.gagarin.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

import eightbitlab.com.blurview.BlurView;

public class AdaptadorRecyclerNoticias extends RecyclerView.Adapter<AdaptadorRecyclerNoticias.ContenedorDeVistas> {
    private List<Noticia> lista_contactos;
    private Context context;
    private ViewGroup root_home;

    public AdaptadorRecyclerNoticias(List<Noticia> lista_contactos, HomeScreen homeScreen, ViewGroup root_home) {
        this.lista_contactos = lista_contactos;
        this.context = homeScreen;
        this.root_home = root_home;
    }

    @NonNull
    @Override
    public ContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_card_noticias, parent, false);

        TextView tv_titular = vista.findViewById(R.id.TV_nombrecohete);
        TextView tv_fecha = vista.findViewById(R.id.TV_fechaNoticia);
        ImageView img_pfp = vista.findViewById(R.id.IV_fotonoticia);
        CardView card = vista.findViewById(R.id.CV_Noticia);
        BlurView blur_card = vista.findViewById(R.id.blur_card);
        
        //Blur Background
        final float radius = 3f;
        final Drawable windowBackground = parent.getBackground();

        blur_card.setupWith(root_home)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(context))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);


        //El lisener no va aqui
        /*card.setOnClickListener(new View.OnClickListener() {
=======
/*
        card.setOnClickListener(new View.OnClickListener() {
>>>>>>> HomeScreen
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticiaScreen.class);
                intent.putExtra("Home",tv_titular.getText().toString());
                //send image
                context.startActivity(intent);
            }
        });*/




        ContenedorDeVistas contenedor = new ContenedorDeVistas(vista);
        Log.d("Contenedor","Creando contenedor de vistas");

        return contenedor;
    }

    @Override
    public void onBindViewHolder(@NonNull ContenedorDeVistas holder, int position) {
        Noticia c = lista_contactos.get(position);
        holder.tv_titular.setText(c.getTitular());
        holder.tv_fecha.setText(c.getFecha());

        Glide.with(context)
                .load(c.getImagen())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            holder.img_noticia.setBackground(resource);
                        }
                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticiaScreen.class);
                intent.putExtra("Home",holder.tv_titular.getText().toString());
                //send image
                intent.putExtra("Image",c.getImagen());
                intent.putExtra("Cuerpo",c.getCuerpo());
                intent.putExtra("URL",c.getLink());
                context.startActivity(intent);
            }
        });



        Log.d("Contenedor","Cvinculando la posicion" + position);

    }

    @Override
    public int getItemCount() {
        return lista_contactos.size();
    }

    public static class ContenedorDeVistas extends RecyclerView.ViewHolder {
        public TextView tv_titular,tv_fecha;
        public ImageView img_noticia;


        public ContenedorDeVistas(View vista) {
            super(vista);
            this.tv_titular = vista.findViewById(R.id.TV_nombrecohete);
            this.tv_fecha = vista.findViewById(R.id.TV_fechaNoticia);
            this.img_noticia = vista.findViewById(R.id.IV_fotonoticia);

        }
    }

}
