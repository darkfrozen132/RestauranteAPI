package com.example.restauranteapp.Activity;
// Componentes de UI de Android
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// AppCompat para soporte de compatibilidad
import androidx.appcompat.app.AppCompatActivity;

// Importaciones específicas del proyecto
import com.example.restauranteapp.R;
//MOdels y servicio api
import com.example.restauranteapp.Appi.ServiceAPPIUsuario;
import com.example.restauranteapp.Models.Usuario;
//Conexion
import com.example.restauranteapp.Util.ConnectionREST;

// Biblioteca Retrofit para networking
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {


    private Button pedido_llevar;
    private Button pedido_mesa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pedido_llevar=(Button) findViewById(R.id.ped_lleva);
        pedido_mesa=(Button) findViewById(R.id.ped_mesa);

        pedido_llevar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Agregar_pedido.class);

                startActivity(intent);
            }
        });
        pedido_mesa.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, mesas.class);

                 startActivity(intent);
             }
         });
    }
}