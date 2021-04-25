package com.pfc.gagarin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.pfc.gagarin.traductor.Traductor;

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

           String fromLang = "en";
           String toLang = "es";
           String titularTraducido = "";

           if ( true){
               try {
                   titularTraducido =  Traductor.translate(fromLang, toLang, titular);
               } catch (Exception e) {
                   e.printStackTrace();
               }
               Log.d("AAAA", "run: " + titularTraducido + " " + foto + " " + fecha);
           }else{

           }

           Noticia n = new Noticia(titularTraducido,fecha,foto);
           noticias5.add(n);

       }
        context.devolverNoticias(noticias5);
    }
    public interface InterfazNoticias{
        public void devolverNoticias(List<Noticia> listaNoticias);
    }
}
