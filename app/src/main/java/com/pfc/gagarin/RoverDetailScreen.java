package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.pfc.gagarin.adapter.AdaptadorRecyclerFotosRoverApi;
import com.pfc.gagarin.adapter.AdaptadorRecyclerLanzamientos;
import com.pfc.gagarin.comunicacionNasa.PedirJson;
import com.pfc.gagarin.entidad.Photo;

import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.view.SurfaceView;

import java.util.List;

import eightbitlab.com.blurview.BlurView;

public class RoverDetailScreen extends AppCompatActivity implements HiloCargaModelo.InterfazCargaModelo, PedirJson.recogerFotosRover {
    private ViewGroup root;
    private SurfaceView surface;
    private BlurView card;
    private ProgressBar progressBar;
    private TextView title,body;

    private RecyclerView recyclerView_fotosAPI;
    private RecyclerView.LayoutManager gestor;
    private AdaptadorRecyclerFotosRoverApi adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover_detail_screen);
        root= findViewById(R.id.root);
        card = findViewById(R.id.blur_card);
        progressBar = findViewById(R.id.progressBar);
        title = findViewById(R.id.TV_lugarAterrizajeRover);
        body = findViewById(R.id.TV_infoRover);
        recyclerView_fotosAPI = findViewById(R.id.RC_FotosRover);

        //Recoge el nombre del Rover que ha seleccionado
        Intent intent = getIntent();
        String nombreRover = intent.getStringExtra("nombre_rover");

        //Se prepara para llamar a la clase que carga el objeto 3D
        surface = findViewById(R.id.rajawali_surface_rover);
        HiloCargaModelo h1 = new HiloCargaModelo(this, surface, nombreRover);
        Thread t1 = new Thread(h1);
        t1.start();

        //Establece los textos correspondientes a cada rover
        switch (nombreRover){
            case "curiosity":
                title.setText(R.string.curiosity_title);
                body.setText(R.string.curiosity_body);
                //Pide fotos sacadas por el rover seleccionado al API de la NASA
                PedirJson.pedirRtf(nombreRover,this);
                break;

            case "perseverance":
                title.setText(R.string.perseverance_title);
                body.setText(R.string.perseverance_body);
                break;

            case "opportunity":
                title.setText(R.string.opportunity_title);
                body.setText(R.string.opportunity_body);
                //Pide fotos sacadas por el rover seleccionado al API de la NASA
                PedirJson.pedirRtf(nombreRover,this);
                break;

            case "spirit":
                title.setText(R.string.spirit_title);
                body.setText(R.string.spirit_body);
                //Pide fotos sacadas por el rover seleccionado al API de la NASA
                PedirJson.pedirRtf(nombreRover,this);
                break;
        }

        //Para hacer Borrroso
        final float radius = 3f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();
        card.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }

    @Override
    public void cargarModelo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                surface.setVisibility(View.VISIBLE);

            }
        });
    }
    @Override
    public void mostrarFotos(List<Photo> r) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView_fotosAPI = findViewById(R.id.RC_FotosRover);
                adapt = new AdaptadorRecyclerFotosRoverApi(r, RoverDetailScreen.this);
                gestor = new LinearLayoutManager(RoverDetailScreen.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView_fotosAPI.setAdapter(adapt);
                recyclerView_fotosAPI.setLayoutManager(gestor);
            }
        });
    }
}