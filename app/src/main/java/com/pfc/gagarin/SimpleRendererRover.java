package com.pfc.gagarin;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;

import androidx.loader.app.LoaderManager;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.loader.LoaderAWD;
import org.rajawali3d.loader.LoaderGCode;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.LoaderSTL;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.loader.fbx.LoaderFBX;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.renderer.Renderer;

public class SimpleRendererRover extends Renderer {
    private Context context;
    private String nombreRover;
    private Object3D rover;

    public SimpleRendererRover(Context context, String nombreRover) {
        super(context);
        this.context = context;
        this.nombreRover = nombreRover;
        setFrameRate(60);
    }
    @Override
    protected void initScene() {
        Material material = new Material();
        LoaderOBJ objParser = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.perseverance_obj);

        try {
            objParser.parse();
            rover = objParser.getParsedObject();
            getCurrentScene().addChild(rover);



        } catch (ParsingException e) {
            e.printStackTrace();
        }
        getCurrentCamera().setZ(3.2f);

        ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.rajawali_surface_rover));
        arcball.setTarget(rover); //your 3D Object

        arcball.setPosition(2,1,2); //optional

        getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);

    }
    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        super.onRender(elapsedTime, deltaTime);

    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
