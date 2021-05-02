package com.pfc.gagarin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HiloPeticionNoticias implements Runnable {

    private HomeScreen context;

    public HiloPeticionNoticias(HomeScreen context) {
        this.context = context;
    }


    @Override
    public void run() {

       PeticionNoticias.conectar();
        List<Noticia> noticias5 = new ArrayList<Noticia>();

       for (int i = 0; i < 5; i++){

           String titular = PeticionNoticias.pedirTitular(i);
           String foto = PeticionNoticias.pedirFoto(i);
           String fecha = PeticionNoticias.pedirFecha(i);

           Noticia n = new Noticia(titular,fecha,foto);
           noticias5.add(n);

       }
        context.devolverNoticias(noticias5);
    }
    public interface InterfazNoticias{
        public void devolverNoticias(List<Noticia> listaNoticias);
    }
}
