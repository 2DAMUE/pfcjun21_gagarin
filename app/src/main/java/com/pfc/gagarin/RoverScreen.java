package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;

import eightbitlab.com.blurview.BlurView;

public class RoverScreen extends AppCompatActivity {
    private ViewGroup root;
    private BlurView card, card_curiosity, card_opportunity, card_perseverance, card_spirit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover_screen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root= findViewById(R.id.root);
        card = findViewById(R.id.blur_card);
        card_curiosity = findViewById(R.id.blur_card_curiosity);
        card_opportunity = findViewById(R.id.blur_card);
        card_perseverance = findViewById(R.id.blur_card);
        card_spirit = findViewById(R.id.blur_card);


        //Para hacer Borrroso
        final float radius = 3f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();

        card.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
        card_curiosity.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);
        card_opportunity.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);
        card_perseverance.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);
        card_spirit.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);


    }
}