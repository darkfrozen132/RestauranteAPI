package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.Mesas;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPPIMesas {
    @GET("mesas")
    public abstract Call<List<Mesas>> listProduct();
    @POST("mesas/agregar")
    public abstract Call<Mesas> add(@Body Mesas obj);
    @PUT("mesas/modificar")
    public abstract Call<Mesas> put(@Body Mesas obj);
    @DELETE("mesas/eliminar/{id}")
    public abstract Call<Mesas> delete(@Path("id") int id);
    @POST("mesas/verificar")
    //public  abstract   Call<Void> verificacion(@Body Usuario obj);
    public abstract Call<Void> verificacion(@Body Mesas obj);

}