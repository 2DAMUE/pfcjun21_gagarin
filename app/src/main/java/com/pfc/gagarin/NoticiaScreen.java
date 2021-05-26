package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;

import eightbitlab.com.blurview.BlurView;

public class NoticiaScreen extends AppCompatActivity {
    private ViewGroup root_story;
    private BlurView blur_scroll_story;
    private BlurView blur_blu_story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root_story= findViewById(R.id.root_story);
        blur_scroll_story = findViewById(R.id.blur_scroll_story);
        blur_blu_story = findViewById(R.id.blur_blu_story);

        blurBackground();

    }

    //Desenfocado imagen

    private void blurBackground() {
        final float radius = 1f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();
        /*
        blur_scroll_story.setupWith(root_story)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
         */
        blur_blu_story.setupWith(root_story)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }


}