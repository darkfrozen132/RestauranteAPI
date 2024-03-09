package com.example.restauranteapp.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restauranteapp.Appi.ServiceAPPIMesas;
import com.example.restauranteapp.Models.Mesas;
import com.example.restauranteapp.Models.Plato;
import com.example.restauranteapp.R;

import  com.example.restauranteapp.Appi.ServiceAPPIPlato;
import com.example.restauranteapp.Models.Usuario;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.EditText;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;
import android.widget.TextView;
public class Agregar_pedido extends AppCompatActivity {
    private View view;
    private Spinner spinner;

    private EditText edit_mesa;
    private TextView textView;
    private Spinner spiner_mesa;
    private ServiceAPPIPlato serviceAPI;

    private Button boton_atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_pedido);

        //edit_mesa = (EditText) findViewById(R.id.edit_mesa);
        spiner_mesa = (Spinner) findViewById(R.id.spinner_mesa);
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIPlato.class);
        boton_atras=(Button) findViewById(R.id.btn_atras);
        textView =(TextView) findViewById(R.id.textView_mesa);

        spinner_mesas();


        spiner_mesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = parentView.getItemAtPosition(position).toString();

                get_mesas(selectedItem,textView);

            }
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar la situación cuando no se selecciona nada en el Spinner
                textView.setText("No se ha seleccionado ninguna opción");
            }


        });
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
    public void get_mesas(String mesa_seleccionada,TextView textView){
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
                        if(String.valueOf(x.getIdMesa())==mesa_seleccionada){
                             asientos=String.valueOf(x.getCantidadAsientos());
                             descripcion=String.valueOf(x.getDescripcion());
                             reservado=x.getReservado();
                        }
                    }
                    if(reservado==1) {
                        textView.setText("La mesa contiene:" + asientos + " y esta ubicada en " + descripcion);
                    }
                    else if (reservado==0){
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

    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }














}
