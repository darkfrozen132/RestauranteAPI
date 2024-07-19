package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.Plato;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPPIPlato {
    @GET("plato")
    public abstract Call<List<Plato>> listProduct();
    @POST("plato/agregar")
    public abstract Call<Plato> add(@Body Plato obj);
    @PUT("plato/modificar")
    public abstract Call<Plato> put(@Body Plato obj);
    @DELETE("plato/eliminar/{id}")
    public abstract Call<Plato> delete(@Path("id") int id);
    @POST("plato/verificar")
    //public  abstract   Call<Void> verificacion(@Body Usuario obj);
    public abstract Call<Void> verificacion(@Body Plato obj);

}