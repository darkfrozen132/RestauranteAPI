package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.PedidoDetalle;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPPIPedidoDetalle {
    @GET("PedidoDetalle")
    public abstract Call<List<PedidoDetalle>> listProduct();
    @POST("PedidoDetalle/agregar")
    public abstract Call<PedidoDetalle> add(@Body PedidoDetalle obj);
    @PUT("PedidoDetalle/modificar")
    public abstract Call<PedidoDetalle> put(@Body PedidoDetalle obj);
    @DELETE("PedidoDetalle/eliminar/{id}")
    public abstract Call<PedidoDetalle> delete(@Path("id") int id);
    @POST("PedidoDetalle/verificar")
    //public  abstract   Call<Void> verificacion(@Body Usuario obj);
    public abstract Call<Void> verificacion(@Body PedidoDetalle obj);

}