package com.pfc.gagarin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.pfc.gagarin.adapter.AdaptadorRecyclerLanzamientos;
import com.pfc.gagarin.ws_body_noticias.HiloPeticionBodyNoticias;

import eightbitlab.com.blurview.BlurView;

public class NoticiaScreen extends AppCompatActivity implements HiloPeticionBodyNoticias.InterfazBodyNoticias {
    private ViewGroup root_story;
    private BlurView blur_scroll_story;
    private BlurView blur_blu_story;
    private ImageView iv_arrow_back;
    private TextView tv_title_story;
    private ImageView iv_image_story;
    private TextView tv_body_story;
    private ProgressBar pg_progress_story;
    private TextView tv_source_story_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root_story= findViewById(R.id.root_story);
        blur_scroll_story = findViewById(R.id.blur_scroll_story);
        blur_blu_story = findViewById(R.id.blur_blu_story);
        iv_arrow_back = findViewById(R.id.iv_arrow_back);
        tv_title_story = findViewById(R.id.tv_title_story);
        tv_body_story = findViewById(R.id.tv_body_story);
        tv_source_story_link = findViewById(R.id.tv_source_story_link);
        iv_image_story = findViewById(R.id.iv_image_story);
        pg_progress_story = findViewById(R.id.pg_progress_story);

        tv_title_story.setText(getIntent().getStringExtra("Home"));
        //Set image story
        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("Image"))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            iv_image_story.setBackground(resource);
                        }
                    }
                });
        //Get url story
        String url_story = getIntent().getStringExtra("URL");
        tv_source_story_link.setText(url_story);
        // Hilo que llama al webscrapping
        pg_progress_story.setVisibility(View.VISIBLE);
        HiloPeticionBodyNoticias r = new HiloPeticionBodyNoticias(NoticiaScreen.this,url_story);
        Thread t1 = new Thread(r);
        t1.start();

        blurBackground();

        iv_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticiaScreen.this,HomeScreen.class);
                startActivity(intent);
            }
        });

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


    @Override
    public void devolverBodyNoticias(String cuerpoNoticia) {
        //Set Body Story
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_body_story.setText(cuerpoNoticia);
                pg_progress_story.setVisibility(View.GONE);
            }
        });
    }
}