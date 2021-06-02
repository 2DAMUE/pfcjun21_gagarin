package com.pfc.gagarin.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.pfc.gagarin.LanzamientosDetailScreen;
import com.pfc.gagarin.LanzamientosScreen;
import com.pfc.gagarin.R;
import com.pfc.gagarin.RoverDetailScreen;
import com.pfc.gagarin.RoverScreen;
import com.pfc.gagarin.entidad.Lanzamiento;

import java.util.List;

public class AdaptadorRecyclerLanzamientos2 extends RecyclerView.Adapter<AdaptadorRecyclerLanzamientos2.ContenedorDeVistas> {
    private List<Lanzamiento> lista_contactos;
    private LanzamientosScreen context;


    public AdaptadorRecyclerLanzamientos2(List<Lanzamiento> lista_contactos, LanzamientosScreen c) {
        this.lista_contactos = lista_contactos;
        this.context = c;
    }

    @NonNull
    @Override
    public AdaptadorRecyclerLanzamientos2.ContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_card_lanzamientos_grande, parent, false);
        TextView tv_titular = vista.findViewById(R.id.TV_NombreCohete_2);
        TextView tv_fecha = vista.findViewById(R.id.TV_FechaPrevista);
        ImageView img_cohete = vista.findViewById(R.id.IV_fotoLanzamiento);
        CardView cv_cohete = vista.findViewById(R.id.CV_LanzamientosGrande);
        AdaptadorRecyclerLanzamientos2.ContenedorDeVistas contenedor = new AdaptadorRecyclerLanzamientos2.ContenedorDeVistas(vista);
        return contenedor;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerLanzamientos2.ContenedorDeVistas holder, int position) {
        Lanzamiento c = lista_contactos.get(position);
        holder.tv_nombreCohete.setText(c.getRocketModel());
        holder.tv_fecha.setText(c.getHora());
        holder.cv_cohete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LanzamientosDetailScreen.class);
                intent.putExtra("nombre_cohete", c.getRocket());
                intent.putExtra("modelo_cohete", c.getRocketModel());
                intent.putExtra("lugar_cohete", c.getLugar());
                intent.putExtra("imagen_cohete", c.getImagen());
                intent.putExtra("link_cohete",c.getLink());
                context.startActivity(intent);
            }
        });
        Glide.with(context)
                .load(c.getImagen())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            holder.img_cohete.setBackground(resource);
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
        public CardView cv_cohete;
        public TextView tv_nombreCohete, tv_fecha;
        public ImageView img_cohete;

        public ContenedorDeVistas(View vista) {
            super(vista);
            this.tv_nombreCohete = vista.findViewById(R.id.TV_NombreCohete_2);
            this.img_cohete = vista.findViewById(R.id.IV_fotoLanzamiento);
            this.cv_cohete = vista.findViewById(R.id.CV_LanzamientosGrande);
            this.tv_fecha = vista.findViewById(R.id.TV_FechaPrevista);
        }
    }
}
