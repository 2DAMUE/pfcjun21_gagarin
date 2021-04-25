package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.pfc.gagarin.adapter.AdaptadorRecyclerNoticias;
import com.pfc.gagarin.traductor.Traductor;

import java.util.List;

public class HomeScreen extends AppCompatActivity implements HiloPeticionNoticias.InterfazNoticias {

    private TextView verMasNoticias;
    private TextView verMasLanzamientos;

    private CardView cardMarte;
    private CardView cardSatelites;

    private RecyclerView recyclerNoticias;
    private RecyclerView.LayoutManager gestor2;
    private AdaptadorRecyclerNoticias adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        verMasNoticias = findViewById(R.id.TV_VerMasNoticias);
        verMasLanzamientos = findViewById(R.id.TV_VerMasLanzamientos);

        cardMarte = findViewById(R.id.CV_Marte);
        cardSatelites = findViewById(R.id.CV_Satelites);



        HiloPeticionNoticias r = new HiloPeticionNoticias(HomeScreen.this);
        Thread t1 = new Thread(r);
        t1.start();




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

            }
        });





    }
}