package com.pfc.gagarin.comunicacionNasa;

import android.content.Context;

import com.pfc.gagarin.RoverScreen;
import com.pfc.gagarin.entidad.Photos;
import com.pfc.gagarin.entidad.Rover;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedirJson {

    public static void pedirRtf(String nombreRover, RoverScreen c) {
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/" + nombreRover + "/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfazDatos service = retrofit.create(InterfazDatos.class);service.recibirDatos();
        Call<Photos> llamada = service.recibirDatos();
        llamada.enqueue(new Callback<Photos>() {

            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {

                response.body();
                //List<Rover> datos = d.getDatosRover();
                //c.mostrarFotos(datos);

            }
            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                // TODO Auto-generated method stub

            }
        });
    }
    public static void hacerLlamada(Call<Photos> llamada, RoverScreen c){

    }
    public interface recogerFotosRover{
        public void mostrarFotos(List<Rover> r);

    }
}
