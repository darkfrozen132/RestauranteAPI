package com.example.restauranteapp.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restauranteapp.R;

import  com.example.restauranteapp.Appi.ServiceAPPI;
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

import java.util.ArrayList;
import java.util.List;

public class Agregar_pedido extends AppCompatActivity {
    private View view;
    private Spinner spinner;

    private EditText edit_mesa;

    private Spinner spiner;
    private ServiceAPPI serviceAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_pedido);

        edit_mesa = (EditText) findViewById(R.id.edit_mesa);
        spiner=(Spinner) findViewById(R.id.spiner_platos);
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPI.class);


        edit_mesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mesa = Integer.parseInt(edit_mesa.getText().toString().trim());
            }
        });




    }
    public void llamar_platos(){
        Call<List<Usuario>> platos = serviceAPI.listProduct();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> elementos = response.body();

                    // Configura el ArrayAdapter con los datos obtenidos
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, elementos);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Establece el adaptador en el Spinner
                    spinner.setAdapter(adapter);
                } else {
                    // Manejar errores de la respuesta del servicio web
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                // Manejar errores de la solicitud HTTP
            }
    }
}
