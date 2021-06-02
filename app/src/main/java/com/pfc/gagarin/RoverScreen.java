package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.pfc.gagarin.comunicacionNasa.PedirJson;
import com.pfc.gagarin.entidad.Photos;
import com.pfc.gagarin.entidad.Rover;

import java.util.List;

import eightbitlab.com.blurview.BlurView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoverScreen extends AppCompatActivity {
    private ViewGroup root;
    private BlurView card, card_curiosity_blur, card_opportunity_blur, card_perseverance_blur, card_spirit_blur;
    private CardView card_spirit, card_perseverance, card_opportunity, card_curiosity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover_screen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root= findViewById(R.id.root);
        card = findViewById(R.id.blur_card);
        card_curiosity_blur = findViewById(R.id.blur_card_curiosity);
        card_opportunity_blur = findViewById(R.id.blur_card_opportunity);
        card_perseverance_blur = findViewById(R.id.blur_card_perseverance);
        card_spirit_blur = findViewById(R.id.blur_card_spirit);

        //API KEY FOTOS ROVERS vYqFoAd5xt4NAjJ0dznwAcynLDMba3AE4U7nD4kB
        card_spirit = findViewById(R.id.CV_curiosity);
        card_perseverance = findViewById(R.id.CV_curiosity);
        card_opportunity = findViewById(R.id.CV_curiosity);
        card_curiosity = findViewById(R.id.CV_curiosity) ;

        card_spirit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Photos> llamada=  PedirJson.pedirRtf("spirit");
                llamada.enqueue(new Callback<Photos>() {

                    @Override
                    public void onResponse(Call<Photos> call, Response<Photos> response) {
                        Photos d = response.body();
                        List<Rover> datos = d.getDatosRover();



                    }
                    @Override
                    public void onFailure(Call<Photos> call, Throwable t) {
                        // TODO Auto-generated method stub

                    }
                });
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