package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.pfc.gagarin.adapter.AdaptadorRecyclerLanzamientos;
import com.pfc.gagarin.adapter.AdaptadorRecyclerLanzamientos2;
import com.pfc.gagarin.entidad.Lanzamiento;
import com.pfc.gagarin.ws_lanzamientos.HiloPeticionLanzamientos;

import java.util.List;

public class LanzamientosScreen extends AppCompatActivity implements HiloPeticionLanzamientos.InterfazLanzamientos {

    private RecyclerView recicler_lanzamientos_central;
    private RecyclerView.LayoutManager gestor;
    private AdaptadorRecyclerLanzamientos2 adapt;
    private ProgressBar PB_lanz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzamientos_screen);
        PB_lanz = findViewById(R.id.PB_RC_Lanzamientos2);
        PB_lanz.setVisibility(View.VISIBLE);

        //Menu slide
        findViewById(R.id.IV_SlideMenuIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(findViewById(R.id.menu_view_lanz), "translationX", 0, 880);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        });
        findViewById(R.id.iv_x_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(findViewById(R.id.menu_view_lanz), "translationX", 880, 0);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        });

        //on clicks del menu
        findViewById(R.id.tv_home_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanzamientosScreen.this,HomeScreen.class);
                startActivity(intent);
            }
        });
        /*findViewById(R.id.tv_launches_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanzamientosScreen.this, LanzamientosScreen.class);
                startActivity(intent);
            }
        });*/
        findViewById(R.id.tv_mars_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanzamientosScreen.this, MapaMarte.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_satellites_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanzamientosScreen.this, SatelitesScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_settings_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanzamientosScreen.this, SettingsScreen.class);
                startActivity(intent);
            }
        });

        //Hilo que llama al webscrapping
        HiloPeticionLanzamientos a = new HiloPeticionLanzamientos(LanzamientosScreen.this);
        Thread t2 = new Thread(a);
        t2.start();

    }

    @Override
    public void devolverLanzamientos(List<Lanzamiento> listaLanzamientos) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recicler_lanzamientos_central = findViewById(R.id.RC_Lanzaminetos_central);
                gestor = new LinearLayoutManager(LanzamientosScreen.this, LinearLayoutManager.HORIZONTAL, false);
                adapt = new AdaptadorRecyclerLanzamientos2(listaLanzamientos, LanzamientosScreen.this);
                recicler_lanzamientos_central.setAdapter(adapt);
                recicler_lanzamientos_central.setLayoutManager(gestor);
                PB_lanz.setVisibility(View.INVISIBLE);
            }
        });
    }
}