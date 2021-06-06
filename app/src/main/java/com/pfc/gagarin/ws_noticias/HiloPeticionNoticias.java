package com.pfc.gagarin.ws_noticias;

import android.util.Log;

import com.pfc.gagarin.HomeScreen;
import com.pfc.gagarin.entidad.Noticia;

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
           String link = PeticionNoticias.pedirLink(i);

           Noticia n = new Noticia();
           n.setTitular(titular);
           n.setImagen(foto);
           n.setFecha(fecha);
           n.setLink(link);

           Log.d("abobole", "run: " + link);

           noticias5.add(n);

       }
        context.devolverNoticias(noticias5);
    }
    public interface InterfazNoticias{
        public void devolverNoticias(List<Noticia> listaNoticias);
    }
}
