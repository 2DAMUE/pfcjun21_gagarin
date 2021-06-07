package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;

import eightbitlab.com.blurview.BlurView;

public class RoverScreen extends AppCompatActivity {
    private ViewGroup root;
    private BlurView card, card_curiosity_blur, card_opportunity_blur, card_perseverance_blur, card_spirit_blur;
    private CardView card_spirit, card_perseverance, card_opportunity, card_curiosity;
    private BlurView card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover_screen);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //API KEY FOTOS ROVERS vYqFoAd5xt4NAjJ0dznwAcynLDMba3AE4U7nD4kB
        //Asignacion de componentes
        root= findViewById(R.id.root);
        card = findViewById(R.id.blur_card);
        card_curiosity_blur = findViewById(R.id.blur_card_curiosity);
        card_opportunity_blur = findViewById(R.id.blur_card_opportunity);
        card_perseverance_blur = findViewById(R.id.blur_card_perseverance);
        card_spirit_blur = findViewById(R.id.blur_card_spirit);
        card_spirit = findViewById(R.id.CV_spirit);
        card2 = findViewById(R.id.blur_card2);
        card_perseverance = findViewById(R.id.CV_perseverance);
        card_opportunity = findViewById(R.id.CV_opportunity);
        card_curiosity = findViewById(R.id.CV_curiosity) ;
        //Menu slide
        findViewById(R.id.IMG_sliderMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(findViewById(R.id.menu_view_rovers), "translationX", 0, 890);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
               // card2.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.iv_x_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(findViewById(R.id.menu_view_rovers), "translationX", 890, 0);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                //card2.setVisibility(View.INVISIBLE);
            }
        });

        //on clicks del menu
        findViewById(R.id.tv_home_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoverScreen.this,HomeScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_launches_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoverScreen.this, LanzamientosScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_mars_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoverScreen.this, MapaMarte.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_satellites_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoverScreen.this, SatelitesScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_settings_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoverScreen.this, SettingsScreen.class);
                startActivity(intent);
            }
        });

        //Cambian de Activity y les pasan nombre de Rover
        card_spirit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PedirJson.pedirRtf("spirit",RoverScreen.this);
                Intent intent = new Intent(RoverScreen.this, RoverDetailScreen.class);
                intent.putExtra("nombre_rover", "spirit");
                startActivity(intent);
            }
        });
        card_opportunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PedirJson.pedirRtf("spirit",RoverScreen.this);
                Intent intent = new Intent(RoverScreen.this, RoverDetailScreen.class);
                intent.putExtra("nombre_rover", "spirit");
                startActivity(intent);
            }
        });
        card_curiosity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PedirJson.pedirRtf("spirit",RoverScreen.this);
                Intent intent = new Intent(RoverScreen.this, RoverDetailScreen.class);
                intent.putExtra("nombre_rover", "curiosity");
                startActivity(intent);
            }
        });

        card_perseverance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PedirJson.pedirRtf("spirit",RoverScreen.this);
                Intent intent = new Intent(RoverScreen.this, RoverDetailScreen.class);
                intent.putExtra("nombre_rover", "perseverance");
                startActivity(intent);
            }
        });
        //Para hacer Borrroso
        final float radius = 3f;
        final Drawable windowBackground = getWindow().getDecorView().getBackground();

        card.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
        card_curiosity_blur.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);
        card_opportunity_blur.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);
        card_perseverance_blur.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);
        card_spirit_blur.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(false);
    }
}