package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pfc.gagarin.adapter.AdaptadorRecyclerLanzamientos;
import com.pfc.gagarin.adapter.AdaptadorRecyclerNoticias;
import com.pfc.gagarin.entidad.Lanzamiento;
import com.pfc.gagarin.entidad.Noticia;
import com.pfc.gagarin.ws_lanzamientos.HiloPeticionLanzamientos;
import com.pfc.gagarin.ws_noticias.HiloPeticionNoticias;

import java.util.List;

public class HomeScreen extends AppCompatActivity implements HiloPeticionNoticias.InterfazNoticias, HiloPeticionLanzamientos.InterfazLanzamientos {

    private TextView verMasNoticias;
    private TextView verMasLanzamientos;
    private TextView tv_settings_icon;
    private ImageView iv_menu_home;
    private ImageView iv_x_menu;

    private CardView cardMarte;
    private CardView cardSatelites;
    private CardView menu_view_home;

    private RecyclerView recyclerNoticias;
    private RecyclerView.LayoutManager gestor2;
    private AdaptadorRecyclerNoticias adapt;
    private ProgressBar PB_noticias;

    private RecyclerView recyclerLanzamientos;
    private RecyclerView.LayoutManager gestor3;
    private AdaptadorRecyclerLanzamientos adapt2;
    private ProgressBar PB_lanzamientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        verMasNoticias = findViewById(R.id.TV_VerMasNoticias);
        verMasLanzamientos = findViewById(R.id.TV_VerMasLanzamientos);
        PB_noticias = findViewById(R.id.progressBar_noticias);
        PB_lanzamientos = findViewById(R.id.progressBar_lanzamientos);

        cardMarte = findViewById(R.id.CV_Marte);
        cardSatelites = findViewById(R.id.CV_Satelites);
        iv_menu_home = findViewById(R.id.iv_menu_home);
        menu_view_home = findViewById(R.id.menu_view_home);
        iv_x_menu = findViewById(R.id.iv_x_menu);
        tv_settings_icon = findViewById(R.id.tv_settings_icon);


        iv_menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(menu_view_home, "translationX", 0, 880);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        });
        iv_x_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(menu_view_home, "translationX", 880, 0);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        });

        //on clicks del menu
        /*indViewById(R.id.tv_home_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,SettingsScreen.class);
                startActivity(intent);
            }
        });*/
        findViewById(R.id.tv_launches_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, LanzamientosScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_mars_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, MapaMarte.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_satellites_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, SatelitesScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_settings_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, SettingsScreen.class);
                startActivity(intent);
            }
        });


        // Hilo que llama al webscrapping
        HiloPeticionNoticias r = new HiloPeticionNoticias(HomeScreen.this);
        Thread t1 = new Thread(r);
        t1.start();

        HiloPeticionLanzamientos a = new HiloPeticionLanzamientos(HomeScreen.this);
        Thread t2 = new Thread(a);
        t2.start();


        PB_noticias.setVisibility(View.VISIBLE);
        PB_lanzamientos.setVisibility(View.VISIBLE);

        // Cambia al Activity de Marte
        findViewById(R.id.CV_Marte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, MapaMarte.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.CV_Satelites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, SatelitesScreen.class);
                startActivity(intent);
            }
        });
        //Cambia el activity de lanzazmientos
        verMasLanzamientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, LanzamientosScreen.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void devolverNoticias(List<Noticia> listaNoticias) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerNoticias = findViewById(R.id.RC_Noticias);
                gestor2 = new LinearLayoutManager(HomeScreen.this, LinearLayoutManager.HORIZONTAL, false);
                adapt = new AdaptadorRecyclerNoticias(listaNoticias, HomeScreen.this);
                recyclerNoticias.setAdapter(adapt);
                recyclerNoticias.setLayoutManager(gestor2);
                PB_noticias.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void devolverLanzamientos(List<Lanzamiento> listaLanzamientos) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerLanzamientos = findViewById(R.id.RC_Lanzamientos);
                gestor3 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                adapt2 = new AdaptadorRecyclerLanzamientos(listaLanzamientos, HomeScreen.this);
                recyclerLanzamientos.setAdapter(adapt2);
                recyclerLanzamientos.setLayoutManager(gestor3);
                PB_lanzamientos.setVisibility(View.INVISIBLE);
            }
        });
    }
}