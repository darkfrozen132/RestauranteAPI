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

import  com.example.restauranteapp.Appi.ServiceAPPIUsuario;
import com.example.restauranteapp.Models.Usuario;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
public class Login extends AppCompatActivity {

    private View _bg__login_ek2;
    private TextView login_ek3;
    private ImageView rectangle_1;
    private ImageView rectangle_2;
    private TextView usuario_;
    private TextView contrase_a_;
    private TextView user;
    private TextView password;
    private View _bg__group_1_ek1;
    private View rectangle_3;
    private Button iniciar_sesion;

    private ServiceAPPIUsuario serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        _bg__login_ek2 = (View) findViewById(R.id._bg__login_ek2);
        login_ek3 = (TextView) findViewById(R.id.login_ek3);
        rectangle_1 = (ImageView) findViewById(R.id.rectangle_1);
        rectangle_2 = (ImageView) findViewById(R.id.rectangle_2);
        usuario_ = (TextView) findViewById(R.id.usuario_);
        contrase_a_ = (TextView) findViewById(R.id.contrase_a_);
        user = (TextView) findViewById(R.id.user);
        password = (TextView) findViewById(R.id.password);
        _bg__group_1_ek1 = (View) findViewById(R.id._bg__group_1_ek1);
        rectangle_3 = (View) findViewById(R.id.rectangle_3);
        iniciar_sesion = (Button) findViewById(R.id.iniciar_sesion);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIUsuario.class);


        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString().trim();
                String passwords = password.getText().toString().trim();

                Usuario usuario = new Usuario(0,"", username, passwords);

             verificar(usuario);
            }
        });

    }

    private void verificar(Usuario obj) {
        String parametro= obj.getUsuario();
        Call<Void> call = serviceAPI.verificacion(obj);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

               if(response.isSuccessful())
               {

                   Intent intent = new Intent(Login.this, MainActivity.class);
                   intent.putExtra("MensajeBienvenida","REGRISTRO PRODUCTOS!!!");
                   startActivity(intent);

               }else {
                 mensaje("SU CUENTA NO EXISTE");
               }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Login.this,"Error", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Login.this);
        alerta.setMessage(msg);
        alerta.show();
    }


}