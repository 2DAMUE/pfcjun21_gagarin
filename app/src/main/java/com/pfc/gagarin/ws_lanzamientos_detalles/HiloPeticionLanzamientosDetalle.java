package com.pfc.gagarin.ws_lanzamientos_detalles;

import android.util.Log;

import com.pfc.gagarin.HomeScreen;
import com.pfc.gagarin.LanzamientosDetailScreen;
import com.pfc.gagarin.LanzamientosScreen;
import com.pfc.gagarin.entidad.Lanzamiento;
import com.pfc.gagarin.ws_lanzamientos.PeticionLanzamientos;

import java.util.ArrayList;
import java.util.List;

public class HiloPeticionLanzamientosDetalle implements Runnable {

    private LanzamientosDetailScreen context;
    private Lanzamiento d;

    public HiloPeticionLanzamientosDetalle(LanzamientosDetailScreen context,Lanzamiento d ) {
        this.context = context;
        this.d = d;
    }

    @Override
    public void run() {
       PeticionLanzamientosDetalle.conectar(d.getLink());

       Lanzamiento lanzamiento = new Lanzamiento();
       String info = PeticionLanzamientosDetalle.pedirInfo();
       String locate = PeticionLanzamientosDetalle.pedirLocation();
       /*String meses = PeticionLanzamientosDetalle.pedirMeses();
       String dias = PeticionLanzamientosDetalle.pedirDias();
       String horas = PeticionLanzamientosDetalle.pedirHoras();
       String minutos = PeticionLanzamientosDetalle.pedirMinutos();

       String countdown = meses + " " + dias + " " + horas + " "  + minutos;
       lanzamiento.setCountdown(countdown);*/
       lanzamiento.setInfor(info);
       lanzamiento.setLocalizacon(locate);
       context.devolverLanzamientos(lanzamiento);
    }
    public interface InterfazLanzamientos{
        public void devolverLanzamientos(Lanzamiento lanzamiento);
    }
}
