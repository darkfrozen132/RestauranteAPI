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

import com.example.restauranteapp.Appi.ServiceAPPIUsuario;
import com.example.restauranteapp.R;


import com.example.restauranteapp.Models.Usuario;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    private Button agregar_pedido;
    private Button agregar_plato;
    private Button agregar_cliente;
    private Button cambiar_pedido;
    private Button mesas;
    private Button personal;

    private View menu_principal;

    private ServiceAPPIUsuario serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar_pedido=(Button) findViewById(R.id.agregar_pedido);
        agregar_plato=(Button) findViewById(R.id.agregar_plato);
        agregar_cliente=(Button) findViewById(R.id.agregar_cliente);
        cambiar_pedido=(Button) findViewById(R.id.cambiar_pedido);
        mesas=(Button) findViewById(R.id.mesas);
        personal=(Button) findViewById(R.id.personal);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIUsuario.class);

        agregar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Agregar_pedido.class);
                intent.putExtra("MensajeBienvenida", "REGISTRO PRODUCTOS!!!");
                startActivity(intent);
            }
        });

    }
}