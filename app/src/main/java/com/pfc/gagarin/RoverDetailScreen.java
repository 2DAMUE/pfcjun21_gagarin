package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.os.Bundle;

import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

public class RoverDetailScreen extends AppCompatActivity {
    private SimpleRendererRover renderer;
    private SurfaceView surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover_detail_screen);

        surface = findViewById(R.id.rajawali_surface_rover);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        renderer = new SimpleRendererRover(this,"oppo");
        surface.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surface.setSurfaceRenderer(renderer);

        /**
         * // TODO: 27/05/2021 Hacer cargar los diferentes modelos e informacion dependiendo del rover elegido 
         */
    }
}