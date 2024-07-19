package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.Pedido;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPPIPedido {
    @GET("pedido")
    public abstract Call<List<Pedido>> listProduct();
    @POST("pedido/agregar")
    public abstract Call<Pedido> add(@Body Pedido obj);
    @PUT("pedido/modificar")
    public abstract Call<Pedido> put(@Body Pedido obj);
    @DELETE("pedido/eliminar/{id}")
    public abstract Call<Pedido> delete(@Path("id") int id);
    @POST("pedido/verificar")
    //public  abstract   Call<Void> verificacion(@Body Usuario obj);
    public abstract Call<Void> verificacion(@Body Pedido obj);

}