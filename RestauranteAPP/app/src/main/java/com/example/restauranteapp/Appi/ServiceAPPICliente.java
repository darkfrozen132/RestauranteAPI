package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.Cliente;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPPICliente {
    @GET("cliente")
    public abstract Call<List<Cliente>> listProduct();
    @POST("cliente/agregar")
    public abstract Call<Cliente> add(@Body Cliente obj);
    @PUT("cliente/modificar")
    public abstract Call<Cliente> put(@Body Cliente obj);
    @DELETE("cliente/eliminar/{id}")
    public abstract Call<Cliente> delete(@Path("id") int id);
    @POST("cliente/verificar")
    public abstract Call<Void> verificacion(@Body Cliente obj);

}