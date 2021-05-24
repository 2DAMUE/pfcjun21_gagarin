package com.pfc.gagarin.ws_lanzamientos;

import android.util.Log;

import com.pfc.gagarin.HomeScreen;
import com.pfc.gagarin.entidad.Lanzamiento;
import com.pfc.gagarin.entidad.Noticia;

import java.util.ArrayList;
import java.util.List;

public class HiloPeticionLanzamientos implements Runnable {

    private HomeScreen context;

    public HiloPeticionLanzamientos(HomeScreen context) {
        this.context = context;
    }


    @Override
    public void run() {

       PeticionLanzamientos.conectar();
        List<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>();

       for (int i = 0; i < 6; i++){

           String nombreCohete = PeticionLanzamientos.pedirNombreCohete(i);
           String foto = PeticionLanzamientos.pedirFoto(i);
           String fecha = PeticionLanzamientos.pedirFecha(i);
           String lugar = PeticionLanzamientos.pedirLugar(i);
           String link = PeticionLanzamientos.pedirLink(i);

           Log.d("oppo", "run: " + nombreCohete + " " + foto + " " + fecha + " " + lugar);

           Lanzamiento n = new Lanzamiento(nombreCohete,fecha,foto,lugar);
           n.setLink(link);
           lanzamientos.add(n);

       }
        context.devolverLanzamientos(lanzamientos);
    }
    public interface InterfazLanzamientos{
        public void devolverLanzamientos(List<Lanzamiento> listaLanzamientos);
    }
}
