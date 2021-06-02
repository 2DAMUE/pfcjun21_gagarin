package com.pfc.gagarin.ws_lanzamientos;

import android.util.Log;

import com.pfc.gagarin.HomeScreen;
import com.pfc.gagarin.LanzamientosScreen;
import com.pfc.gagarin.entidad.Lanzamiento;

import java.util.ArrayList;
import java.util.List;

public class HiloPeticionLanzamientos implements Runnable {

    private HomeScreen context;
    private LanzamientosScreen context2;
    private int contador;


    public HiloPeticionLanzamientos(HomeScreen context) {
        this.context = context;
        this.context2 = null;
        this.contador = 6;
    }
    public HiloPeticionLanzamientos(LanzamientosScreen context2) {
        this.context2 = context2;
        this.context = null;
        this.contador = 25;
    }

    @Override
    public void run() {

       PeticionLanzamientos.conectar();
       List<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>();


       for (int i = 0; i < contador; i++){

           String model = PeticionLanzamientos.pedirCoheteModelo(i);
           String foto = PeticionLanzamientos.pedirFoto(i);
           String fecha = PeticionLanzamientos.pedirFecha(i);
           String lugar = PeticionLanzamientos.pedirLugar(i);
           String link = PeticionLanzamientos.pedirLink(i);
           String nombreCohete = PeticionLanzamientos.pedirNombreCohete(i);

           Log.d("oppo", "run: " + nombreCohete + " " + foto + " " + fecha + " " + lugar);

           Lanzamiento n = new Lanzamiento(nombreCohete,fecha,foto,lugar);
           n.setLink(link);
           n.setRocketModel(model);
           lanzamientos.add(n);

       }
       if (context == null){
           context2.devolverLanzamientos(lanzamientos);
       }else{
           context.devolverLanzamientos(lanzamientos);
       }

    }
    public interface InterfazLanzamientos{
        public void devolverLanzamientos(List<Lanzamiento> listaLanzamientos);
    }
}
