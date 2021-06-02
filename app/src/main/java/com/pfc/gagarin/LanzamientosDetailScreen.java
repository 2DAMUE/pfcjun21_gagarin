package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pfc.gagarin.adapter.AdaptadorRecyclerLanzamientos;
import com.pfc.gagarin.entidad.Lanzamiento;
import com.pfc.gagarin.ws_lanzamientos.HiloPeticionLanzamientos;
import com.pfc.gagarin.ws_lanzamientos_detalles.HiloPeticionLanzamientosDetalle;

import java.util.List;

import eightbitlab.com.blurview.BlurView;

public class LanzamientosDetailScreen extends AppCompatActivity implements OnMapReadyCallback, HiloPeticionLanzamientosDetalle.InterfazLanzamientos {

    private GoogleMap mMap;
    private ViewGroup root;
    private BlurView card;
    private TextView tv_nombreCohete, tv_nombreModeloCohete, tv_lugarCohete, tv_countdown, tv_body;
    private ImageView img_cohete, img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzamientos_detail_screen);
        root= findViewById(R.id.root);
        card = findViewById(R.id.blur_card);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Recoge datos del Intent
        Intent intent = getIntent();
        String nombreModeloCohete = intent.getStringExtra("modelo_cohete");
        String nombreCohete = intent.getStringExtra("nombre_cohete");
        String lugarCohete = intent.getStringExtra("lugar_cohete");
        String imagenCohete = intent.getStringExtra("imagen_cohete");
        String linkinfo = intent.getStringExtra("link_cohete");
        //Boton back
        img_back = findViewById(R.id.IV_back3);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Localiza componentes
        tv_nombreCohete  = findViewById(R.id.TV_NombreCohete_3);
        tv_nombreModeloCohete = findViewById(R.id.TV_ModeloCohete);
        tv_lugarCohete = findViewById(R.id.TV_LugarLanzamiento);
        tv_body = findViewById(R.id.TV_body);
        tv_countdown = findViewById(R.id.TV_cuenta_atras);

        //Settea Valores
        tv_nombreModeloCohete.setText(nombreModeloCohete);
        tv_nombreCohete.setText(nombreCohete);
        tv_lugarCohete.setText(lugarCohete);

        img_cohete = findViewById(R.id.IV_fotoLanzamiento_3);
        Glide.with(this)
                .load(imagenCohete)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            img_cohete.setBackground(resource);
                        }
                    }
                });
        //Hilo que llama al webscrapping
        Lanzamiento L = new Lanzamiento();
        L.setRocketModel(nombreModeloCohete);
        L.setRocket(nombreCohete);
        L.setLugar(lugarCohete);
        L.setImagen(imagenCohete);
        L.setLink(linkinfo);

        HiloPeticionLanzamientosDetalle a = new HiloPeticionLanzamientosDetalle(LanzamientosDetailScreen.this, L);
        Thread t2 = new Thread(a);
        t2.start();

        //Para hacer Borroso
        final float radius = 3f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();
        card.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0, 0);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void devolverLanzamientos(Lanzamiento lanzamiento) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_body.setText(lanzamiento.getInfor());
                //tv_countdown.setText(lanzamiento.getCountdown());
                String [] latlng = lanzamiento.getLocalizacon().split(",");
                LatLng loc = new LatLng(Double.valueOf(latlng[0]),Double.valueOf(latlng[1]));
                mMap.addMarker(new MarkerOptions().position(loc));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            }
        });

    }
}