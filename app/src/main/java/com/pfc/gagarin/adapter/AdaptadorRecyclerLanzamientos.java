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
import com.pfc.gagarin.entidad.Lanzamiento;
import com.pfc.gagarin.R;

import java.util.List;

public class AdaptadorRecyclerLanzamientos extends RecyclerView.Adapter<AdaptadorRecyclerLanzamientos.ContenedorDeVistas> {
    private List<Lanzamiento> lista_contactos;
    private Context context;

    public AdaptadorRecyclerLanzamientos(List<Lanzamiento> lista_contactos, HomeScreen homeScreen) {
        this.lista_contactos = lista_contactos;
        this.context = homeScreen;
    }

    @NonNull
    @Override
    public ContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_card_lanzamientos, parent, false);

        TextView tv_titular = vista.findViewById(R.id.TV_nombrecohete);
        TextView tv_fecha = vista.findViewById(R.id.TV_fechacohete);
        TextView tv_lugar = vista.findViewById(R.id.TV_lugarcohete);
        ImageView img_cohete = vista.findViewById(R.id.IV_cohete);
        CardView cv_cohete = vista.findViewById(R.id.CV_Cohete);
        ContenedorDeVistas contenedor = new ContenedorDeVistas(vista);
        Log.d("Contenedor","Creando contenedor de vistas");
        return contenedor;
    }

    @Override
    public void onBindViewHolder(@NonNull ContenedorDeVistas holder, int position) {
        Lanzamiento c = lista_contactos.get(position);

        holder.tv_nombreCohete.setText(c.getRocket());
        holder.tv_fecha.setText(c.getHora());
        holder.tv_lugar.setText(c.getLugar());
        holder.cv_cohete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambio de ventana para ver mas informacion de dicho cohete
                //dicha informacion se saca de ese link
                //c.getLink();
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
        public TextView tv_nombreCohete,tv_fecha,tv_lugar;
        public ImageView img_cohete;


        public ContenedorDeVistas(View vista) {
            super(vista);
            this.tv_nombreCohete = vista.findViewById(R.id.TV_nombrecohete);
            this.tv_fecha = vista.findViewById(R.id.TV_fechacohete);
            this.tv_lugar = vista.findViewById(R.id.TV_lugarcohete);
            this.img_cohete = vista.findViewById(R.id.IV_cohete);
            this.cv_cohete = vista.findViewById(R.id.CV_Cohete);


        }
    }

}
