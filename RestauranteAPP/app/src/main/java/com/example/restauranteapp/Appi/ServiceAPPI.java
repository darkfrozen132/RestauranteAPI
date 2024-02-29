package com.example.restauranteapp.Appi;
import com.example.restauranteapp.Models.Usuario;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface ServiceAPPI {
    @GET("usuario")
    public abstract Call<List<Usuario>> listProduct();
    @POST("usuario/agregar")
    public abstract Call<Usuario> add(@Body Usuario obj);
    @PUT("usuario/modificar")
    public abstract Call<Usuario> put(@Body Usuario obj);
    @DELETE("producto/eliminar/{id}")
    public abstract Call<Usuario> delete(@Path("id") int id);
    @GET("usuario/verficar")
  public  abstract   Call<Void> verificacion(@Body Usuario obj);
}