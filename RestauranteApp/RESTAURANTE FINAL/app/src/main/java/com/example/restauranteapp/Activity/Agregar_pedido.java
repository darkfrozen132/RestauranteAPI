package com.example.restauranteapp.Activity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restauranteapp.Appi.ServiceAPPIMesas;
import com.example.restauranteapp.Appi.ServiceAPPIPedido;
import com.example.restauranteapp.Appi.ServiceAPPIPlato;
import com.example.restauranteapp.Models.Mesas;
import com.example.restauranteapp.Models.Pedido;
import com.example.restauranteapp.Models.Plato;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Agregar_pedido extends AppCompatActivity {

    private TextView textView;
    private Spinner spiner_mesa;
    private ServiceAPPIPlato serviceAPI;

    private ServiceAPPIPedido serviceAPPIPedido;
    private Spinner spiner_tipo;
    private Button boton_atras;
    private TextView seleccionar_tipo;
    private TextView scrollView;

    private LinearLayout checkbox_container;

    private String tipo_plato;

    private Button aceptar;
    private Button limpiar;
    private ScrollView summary;

    private TextView mesa;

    private LinearLayout linealres;

    private Button  Btn_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.agregar_pedido);

        //edit_mesa = (EditText) findViewById(R.id.edit_mesa);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIPlato.class);
        boton_atras=(Button) findViewById(R.id.btn_atras);
        textView =(TextView) findViewById(R.id.textView_mesa);
        spiner_tipo=(Spinner) findViewById(R.id.spinner_tipo);
        seleccionar_tipo=(TextView) findViewById(R.id.textView_mesa);
        checkbox_container  =(LinearLayout) findViewById(R.id.checkboxContainer);
        summary =(ScrollView) findViewById(R.id.scrolresumen);
        mesa=(TextView) findViewById(R.id.mesa);
        linealres=(LinearLayout) findViewById(R.id.linearlayout_resumen);
        Btn_final = (Button)   findViewById(R.id.btn_enviar);

        int id_mesa = getIntent().getIntExtra("ID_MESA", 0);

        mesa.setText(String.valueOf(id_mesa));
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIPlato.class);
        serviceAPPIPedido = ConnectionREST.getConnection().create(ServiceAPPIPedido.class);
        get_mesas(id_mesa,textView);
        spinner_tipo();



        boton_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
            }
        });

        Btn_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();
                Pedido pObj = new Pedido(1,1,1,1,currentDate,currentTime,currentTime,200);
                  addPedido(pObj);
            }

        });



    }
    public  void spinner_mesas()
    {
        ServiceAPPIMesas serviceAPPIMesas =ConnectionREST.getConnection().create(ServiceAPPIMesas.class);
        Call<List<Mesas>> call =serviceAPPIMesas.listProduct();

        call.enqueue(new Callback<List<Mesas>>() {
            @Override
            public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
                if(response.isSuccessful())
                {
                    List<Mesas> respuesta = response.body();
                    List<String> idsMesas = new ArrayList<>();
                    idsMesas.add("Seleccionar");
                    for(Mesas x:respuesta)
                    {
                        idsMesas.add(String.valueOf(x.getIdMesa()));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Agregar_pedido.this, android.R.layout.simple_spinner_item, idsMesas);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spiner_mesa.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(null,"Error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Mesas>> call, Throwable t) {
                Toast.makeText(null,"Ocurrop un error", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void get_mesas(int id_mesa,TextView textView){
        ServiceAPPIMesas serviceAPPIMesas =ConnectionREST.getConnection().create(ServiceAPPIMesas.class);
        Call<List<Mesas>> call =serviceAPPIMesas.listProduct();

        call.enqueue(new Callback<List<Mesas>>() {
            @Override
            public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
                if(response.isSuccessful())
                {
                    List<Mesas> respuesta = response.body();
                    List<String> idsMesas = new ArrayList<>();
                    idsMesas.add("Seleccionar");
                    String asientos=" ";
                    String descripcion=" ";
                    int reservado=2;

                    for(Mesas x:respuesta)
                    {
                        if(x.getIdMesa()==id_mesa){
                             asientos=String.valueOf(x.getCantidadAsientos());
                             descripcion=String.valueOf(x.getDescripcion());
                             reservado=x.getReservado();
                        }
                    }
                    if(reservado==0) {
                        textView.setText("La mesa contiene:" + asientos + " y esta ubicada en " + descripcion);
                    }
                    else if (reservado==1){
                        mensaje("Esta mesa esta ocupada");
                        textView.setText(" ");
                    }



                }
                else
                {
                    Toast.makeText(null,"Error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Mesas>> call, Throwable t) {
                Toast.makeText(null,"Ocurrop un error", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void spinner_tipo(){

        ServiceAPPIPlato serviceAPPIPlatos =ConnectionREST.getConnection().create(ServiceAPPIPlato.class);

        Call<List<Plato>> call1 =serviceAPPIPlatos.listProduct();


        call1.enqueue(new Callback<List<Plato>>() {

            @Override
            public void onResponse(Call<List<Plato>> call1, Response<List<Plato>> response1) {

                if(response1.isSuccessful())
                {

                    List<Plato> respuesta = response1.body();
                    List<String> tipos = new ArrayList<>();
                    tipos.add("Seleccionar");

                    for(Plato x:respuesta)
                    {
                        String tipoPlato = x.getTipoPlato();
                        if(!tipos.contains(tipoPlato))
                            tipos.add(x.getTipoPlato());

                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Agregar_pedido.this, android.R.layout.simple_spinner_item, tipos);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spiner_tipo.setAdapter(adapter);
                    spiner_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // Obtener la opción seleccionada
                            tipo_plato  = parent.getItemAtPosition(position).toString();
                            crear_botones(tipo_plato);

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(Agregar_pedido.this,"Error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {

                Toast.makeText(Agregar_pedido.this,"Ocurrop un error", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void crear_botones(String tipo_plato) {
        ServiceAPPIPlato serviceAPPIPlatos = ConnectionREST.getConnection().create(ServiceAPPIPlato.class);

        Call<List<Plato>> call1 = serviceAPPIPlatos.listProduct();

        call1.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call1, Response<List<Plato>> response1) {
                if (response1.isSuccessful()) {
                    List<Plato> respuesta = response1.body();
                    checkbox_container.removeAllViews();

                    for (Plato x : respuesta) {
                        String tipoPlato = x.getTipoPlato();
                        if (tipo_plato != null && tipoPlato.equals(tipo_plato)) {
                            final Button button = new Button(Agregar_pedido.this);
                            button.setText(x.getNombrePlato()+", Precio: "+x.getCosto());
                            button.setTextColor(Color.YELLOW);
                            button.setBackgroundResource(R.drawable._bg__ipad_pro_12_9____1_ek2_shape);


                            button.setId(x.getIdPlato());


                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    PedidoFragment dialogFragment = new PedidoFragment();
                                    int ca=0;
                                    int veri=0;
                                    // Crea un Bundle para pasar los datos
                                    Bundle bundle = new Bundle();

                                    bundle.putString("nom_plato", x.getNombrePlato());
                                    bundle.putString("pre_plato", x.getCosto().toString());



                                    dialogFragment.setArguments(bundle);

                                    dialogFragment.show(getSupportFragmentManager(), "tag");


                                }
                            });

                            checkbox_container.addView(button);

                        }
                    }
                }
                else {
                    Toast.makeText(Agregar_pedido.this, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Toast.makeText(Agregar_pedido.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void receiveDataFromDialog(String stringData, int intData, boolean booleanData) {

    }







    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }
public  void addPedido(Pedido obj)
{
  Call<Pedido> call =serviceAPPIPedido.add(obj);
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
   String Prueba =  currentTime.toString();
    mensaje(Prueba);
  call.enqueue(new Callback<Pedido>() {
      @Override
      public void onResponse(Call<Pedido> call, Response<Pedido> response) {
          if(response.isSuccessful())
          {
              Pedido ped = response.body();
              mensaje("Registro grabado satisfactoriamente!");
          }
          else
          {
              mensaje("Ocurrio un error al grabar los datos!");
          }

      }

      @Override
      public void onFailure(Call<Pedido> call, Throwable throwable) {
          mensaje("Ocurrio un error al grabar los datos!");
      }
  });




}













}
