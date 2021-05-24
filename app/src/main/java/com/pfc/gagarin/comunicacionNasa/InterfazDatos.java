package com.pfc.gagarin.comunicacionNasa;

import com.pfc.gagarin.entidad.Photos;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfazDatos {
    @GET("photos?api_key=nuVK8EemK252ikOp8XcdPYvvzeibhQMndLBegwCC&sol=1000.json")
    Call<Photos> recibirDatos();

}
