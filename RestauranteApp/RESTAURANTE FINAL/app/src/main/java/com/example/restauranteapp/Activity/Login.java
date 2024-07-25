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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
public class Login extends AppCompatActivity {


    private TextView user;
    private TextView password;

    private Button iniciar_sesion;

    private ServiceAPPIUsuario serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        user = (TextView) findViewById(R.id.user);
        password = (TextView) findViewById(R.id.password);;
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
        String parametro = obj.getUsuario();
        Call<Void> call = serviceAPI.verificacion(obj);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("MensajeBienvenida", "LOGEO EXITOSO");
                    startActivity(intent);
                } else {
                    mensaje("SU CUENTA NO EXISTE");
                }


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

                // Utiliza el contexto actual (Login.this) en lugar de null


                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
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