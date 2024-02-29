package com.example.restauranteapp.Activity;
import androidx.appcompat.app.AppCompatActivity;

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

                Usuario usuario = new Usuario(0,"", username, password);

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
                      mensaje("LOGEO EXITOSO");
               }else {
                 mensaje("SU CUENTA NO EXISTE");
               }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(null,"Error", Toast.LENGTH_LONG).show();
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