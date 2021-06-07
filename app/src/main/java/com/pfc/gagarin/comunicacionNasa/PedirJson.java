package com.pfc.gagarin.comunicacionNasa;

import android.util.Log;
import android.widget.Toast;

import com.pfc.gagarin.RoverDetailScreen;
import com.pfc.gagarin.entidad.Photo;
import com.pfc.gagarin.entidad.Root;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedirJson {

    public static void pedirRtf(String nombreRover, RoverDetailScreen c) {
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/" + nombreRover + "/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfazDatos service = retrofit.create(InterfazDatos.class);
        Call<Root> llamada = service.recibirDatos();
        llamada.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(response.isSuccessful()){
                    Root body = response.body();
                    List<Photo> listaFotos = body.getPhotos();
                    c.mostrarFotos(listaFotos);

                }else{
                    List<Photo> listaFotos = null;
                    c.mostrarFotos(listaFotos);


                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(c,t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    public static void hacerLlamada(Call<Photo> llamada, RoverDetailScreen c){

    }
    public interface recogerFotosRover{
        public void mostrarFotos(List<Photo> r);

    }
}
