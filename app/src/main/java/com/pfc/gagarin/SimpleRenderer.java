package com.pfc.gagarin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.Renderer;

import javax.microedition.khronos.opengles.GL10;

public class SimpleRenderer extends Renderer {

    private Sphere mMarsSphere;
    private DirectionalLight mDirectionalLight;
    private Context context;

    public SimpleRenderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }
    @Override
    protected void initScene() {
        /*
        mDirectionalLight = new DirectionalLight(1f, .2f, -1.0f);
        mDirectionalLight.setColor(1.0f, 1.0f, 1.0f);
        mDirectionalLight.setPower(2);
        getCurrentScene().addLight(mDirectionalLight);*/

        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setColorInfluence(0);
        Texture earthTexture = new Texture("Marte", R.drawable.mars_map2);
        try{
            material.addTexture(earthTexture);

        } catch (ATexture.TextureException error){
            Log.d( ".initScene", error.toString());
        }

        mMarsSphere = new Sphere(1, 24, 24);
        mMarsSphere.setMaterial(material);
        getCurrentScene().addChild(mMarsSphere);
        getCurrentCamera().setZ(4.2f);

        ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.rajawali_surface));
        arcball.setTarget(mMarsSphere); //your 3D Object
        arcball.setPosition(0,0,5); //optional
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
    public void onTouchEvent(MotionEvent me) {

    }

}
