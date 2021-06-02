package com.pfc.gagarin;

import android.graphics.PixelFormat;

import com.pfc.gagarin.entidad.Lanzamiento;

import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

import java.util.List;

public class HiloCargaModelo implements Runnable {

    private RoverDetailScreen context;
    private SurfaceView surface;
    private String nombreRover;

    public HiloCargaModelo(RoverDetailScreen context, SurfaceView surface, String nombreRover) {
        this.context = context;
        this.surface = surface;
        this.nombreRover = nombreRover;
    }

    @Override
    public void run() {
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        surface.setTransparent(true);
        SimpleRendererRover renderer = new SimpleRendererRover(context, nombreRover);
        surface.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surface.setSurfaceRenderer(renderer);
        context.cargarModelo();

    }
    public interface InterfazCargaModelo{
        public void cargarModelo();
    }
}
