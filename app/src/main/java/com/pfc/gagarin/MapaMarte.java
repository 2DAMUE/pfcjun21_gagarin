package com.pfc.gagarin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.cameras.OrthographicCamera;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

public class MapaMarte extends AppCompatActivity {

    private SimpleRenderer renderer;
    private SurfaceView surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_marte);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.TV_VerRovers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapaMarte.this, RoverScreen.class);
                startActivity(intent);
            }
        });
        // Crea el modelo de Marte 3Dy genera la camara
        surface = findViewById(R.id.rajawali_surface);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        renderer = new SimpleRenderer(this);
        surface.setTransparent(true);
        surface.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surface.setSurfaceRenderer(renderer);

    }
}