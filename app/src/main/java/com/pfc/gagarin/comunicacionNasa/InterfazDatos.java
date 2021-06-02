package com.pfc.gagarin.comunicacionNasa;

import com.pfc.gagarin.entidad.Photos;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfazDatos {
    @GET("api_key=vYqFoAd5xt4NAjJ0dznwAcynLDMba3AE4U7nD4kB&sol=1000")
    Call<Photos> recibirDatos();

}
