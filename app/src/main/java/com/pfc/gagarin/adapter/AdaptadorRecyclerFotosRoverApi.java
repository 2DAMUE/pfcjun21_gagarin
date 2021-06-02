package com.pfc.gagarin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import com.pfc.gagarin.HomeScreen;
import com.pfc.gagarin.R;
import com.pfc.gagarin.RoverDetailScreen;
import com.pfc.gagarin.entidad.Noticia;
import com.pfc.gagarin.entidad.Photo;

import java.util.List;

public class AdaptadorRecyclerFotosRoverApi extends RecyclerView.Adapter<AdaptadorRecyclerFotosRoverApi.ContenedorDeVistas> {
    private List<Photo> lista_contactos;
    private Context context;

    public AdaptadorRecyclerFotosRoverApi(List<Photo> lista_contactos, RoverDetailScreen Screen) {
        this.lista_contactos = lista_contactos;
        this.context = Screen;
    }

    @NonNull
    @Override
    public ContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_card_foto_rover, parent, false);
        ImageView img_pfp = vista.findViewById(R.id.IV_fotoroverAPI);
        /*
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticiaScreen.class);
                context.startActivity(intent);
            }
        });*/


        ContenedorDeVistas contenedor = new ContenedorDeVistas(vista);
        Log.d("Contenedor","Creando contenedor de vistas");

        return contenedor;
    }

    @Override
    public void onBindViewHolder(@NonNull ContenedorDeVistas holder, int position) {
        Photo c = lista_contactos.get(position);

        Glide.with(context)
                .load(c.getImgSrc())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            holder.img_API.setBackground(resource);
                        }
                    }
                });



        Log.d("Contenedor","Cvinculando la posicion" + position);

    }

    @Override
    public int getItemCount() {
        return lista_contactos.size();
    }

    public static class ContenedorDeVistas extends RecyclerView.ViewHolder {
        public ImageView img_API;

        public ContenedorDeVistas(View vista) {
            super(vista);
            this.img_API = vista.findViewById(R.id.IV_fotoroverAPI);

        }
    }

}
