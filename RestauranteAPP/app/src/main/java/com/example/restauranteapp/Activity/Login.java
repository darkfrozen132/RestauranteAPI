package com.example.restauranteapp.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;

import android.app.AlertDialog;
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

public class Login extends AppCompatActivity {

    private EditText _etUser;
    private EditText _etPasword;
    private Button _Log;

    private ServiceAPPI serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        _etUser = (EditText) findViewById(R.id.txtUser);
        _etPasword = (EditText) findViewById(R.id.txtPassword);
        _Log = (Button) findViewById(R.id.buttonLogin);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPI.class);


        _Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = _etUser.getText().toString().trim();
                String password = _etPasword.getText().toString().trim();
                if (!TextUtils.isEmpty(username) &&
                        !TextUtils.isEmpty(password)) {
                    Usuario usuario = new Usuario(0, "", username, password);
                    verificar(usuario);
                } else {
                         mensaje("COMPLETE TODOS LOS CAMPOS");
                }
            }
        });

    }

    private void verificar(Usuario obj) {
<<<<<<< Updated upstream

        Call<Void> call = serviceAPI.verificacion(obj);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
              //  int statusCode = response.code();
             //   String valorString = String.valueOf(statusCode);
              //  mensaje(valorString);
               if(response.isSuccessful())
               {
                      mensaje("LOGEO EXITOSO");
               
               }else {
                 mensaje("SU CUENTA NO EXISTE");
               }
=======
        Call<Response<Void>> call = serviceAPI.verificacion(obj);
        call.enqueue(new Callback<Response<Void>>() {
            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if(response.isSuccessful()) {
                    mensaje("LOGEO EXITOSO");
                } else {
                    mensaje("SU CUENTA NO EXISTE");
                }
>>>>>>> Stashed changes
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                mensaje("Error: " + t.getMessage());
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