package com.pfc.gagarin;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;

import androidx.loader.app.LoaderManager;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderAWD;
import org.rajawali3d.loader.LoaderGCode;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.LoaderSTL;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.loader.fbx.LoaderFBX;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
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
        LoaderOBJ objParser = null;
        switch (nombreRover){
            case "curiosity":
                objParser = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.cusiosity_obj);
                try {
                    objParser.parse();
                    rover = objParser.getParsedObject();
                    getCurrentScene().addChild(rover);
                    getCurrentCamera().setZ(3.2f);
                    ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.rajawali_surface_rover));
                    arcball.setTarget(rover); //your 3D Object
                    arcball.setPosition(-3,3,4); //optional
                    getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);
                } catch (ParsingException e) {
                    e.printStackTrace();
                }
                break;

            case "perseverance":
                objParser = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.perseverance_obj);
                try {
                    objParser.parse();
                    rover = objParser.getParsedObject();
                    getCurrentScene().addChild(rover);
                    getCurrentCamera().setZ(3.2f);
                    ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.rajawali_surface_rover));
                    arcball.setTarget(rover); //your 3D Object
                    arcball.setPosition(-3,3,4); //optional
                    getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);
                } catch (ParsingException e) {
                    e.printStackTrace();
                }
                break;

            case "opportunity": case "spirit":
                objParser = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.opportunity_spirit_obj);
                try {
                    objParser.parse();
                    rover = objParser.getParsedObject();
                    getCurrentScene().addChild(rover);
                    getCurrentCamera().setZ(3.2f);
                    ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.rajawali_surface_rover));
                    arcball.setTarget(rover); //your 3D Object
                    arcball.setPosition(-1,2,2.5); //optional
                    getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);
                } catch (ParsingException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        super.onRender(elapsedTime, deltaTime);
        rover.rotate(Vector3.Axis.Y, 1.0);
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
