package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;

import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

import eightbitlab.com.blurview.BlurView;

public class RoverDetailScreen extends AppCompatActivity {
    private ViewGroup root;
    private SimpleRendererRover renderer;
    private SurfaceView surface;
    private BlurView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover_detail_screen);

        root= findViewById(R.id.root);
        card = findViewById(R.id.blur_card);

        surface = findViewById(R.id.rajawali_surface_rover);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        surface.setTransparent(true);
        renderer = new SimpleRendererRover(this,"oppo");
        surface.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surface.setSurfaceRenderer(renderer);



        /**
         * // TODO: 27/05/2021 Hacer cargar los diferentes modelos e informacion dependiendo del rover elegido 
         */

        //Para hacer Borrroso
        final float radius = 3f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();

        card.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);



    }
}