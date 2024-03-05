package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.Personal;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPPIPersonal {
    @GET("personal")
    public abstract Call<List<Personal>> listProduct();
    @POST("personal/agregar")
    public abstract Call<Personal> add(@Body Personal obj);
    @PUT("personal/modificar")
    public abstract Call<Personal> put(@Body Personal obj);
    @DELETE("personal/eliminar/{id}")
    public abstract Call<Personal> delete(@Path("id") int id);
    @POST("personal/verificar")
    //public  abstract   Call<Void> verificacion(@Body Usuario obj);
    public abstract Call<Void> verificacion(@Body Personal obj);

}