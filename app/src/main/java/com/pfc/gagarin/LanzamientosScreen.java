package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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