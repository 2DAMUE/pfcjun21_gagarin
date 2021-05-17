package com.pfc.gagarin.comunicacionNasa;

import com.pfc.gagarin.entidad.Photos;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedirJson {

    public static Call<Photos> pedirRtf(String nombreRover) {
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/" + nombreRover +"/photos?";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://datos.comunidad.madrid/").addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfazDatos service = retrofit.create(InterfazDatos.class);
        return service.recibirDatos();

    }
}
