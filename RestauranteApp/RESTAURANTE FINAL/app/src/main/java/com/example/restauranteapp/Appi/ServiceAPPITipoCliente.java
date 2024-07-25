package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.TipoCliente;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPPITipoCliente {
    @GET("TipoCliente")
    public abstract Call<List<TipoCliente>> listProduct();
    @POST("TipoCliente/agregar")
    public abstract Call<TipoCliente> add(@Body TipoCliente obj);
    @PUT("TipoCliente/modificar")
    public abstract Call<TipoCliente> put(@Body TipoCliente obj);
    @DELETE("TipoCliente/eliminar/{id}")
    public abstract Call<TipoCliente> delete(@Path("id") int id);
    @POST("TipoCliente/verificar")
    //public  abstract   Call<Void> verificacion(@Body Usuario obj);
    public abstract Call<Void> verificacion(@Body TipoCliente obj);

}