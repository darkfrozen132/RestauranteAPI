package com.example.restauranteapp.Activity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restauranteapp.Appi.ServiceAPPIMesas;
import com.example.restauranteapp.Appi.ServiceAPPIPlato;
import com.example.restauranteapp.Models.Mesas;
import com.example.restauranteapp.Models.Plato;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Agregar_pedido extends AppCompatActivity {
    private View view;
    private Spinner spinner;

    private EditText edit_mesa;
    private TextView textView;
    private Spinner spiner_mesa;
    private ServiceAPPIPlato serviceAPI;

    private Spinner spiner_tipo;
    private Button boton_atras;
    private TextView seleccionar_tipo;
    private TextView scrollView;

    private LinearLayout checkbox_container;

    private String tipo_plato;

    private Button aceptar;
    private Button limpiar;
    private TextView summary;

    private TextView mesa;


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

        summary =(TextView) findViewById(R.id.text_resumen);
        mesa=(TextView) findViewById(R.id.mesa);

        int id_mesa = getIntent().getIntExtra("ID_MESA", 0);

        mesa.setText(String.valueOf(id_mesa));
        //spinner_mesas();

        get_mesas(id_mesa,textView);
        spinner_tipo();



        boton_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
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




    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }














}
