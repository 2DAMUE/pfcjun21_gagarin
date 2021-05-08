package com.pfc.gagarin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.cameras.OrthographicCamera;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

public class MapaMarte extends AppCompatActivity {

    private SimpleRenderer renderer;
    SurfaceView surface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_marte);

        // Crea el modelo de Marte 3Dy genera la camara
        surface = findViewById(R.id.rajawali_surface);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        renderer = new SimpleRenderer(this);
        surface.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surface.setSurfaceRenderer(renderer);









    }
}