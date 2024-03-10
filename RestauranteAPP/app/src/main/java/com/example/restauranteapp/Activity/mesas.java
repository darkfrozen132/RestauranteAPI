package com.example.restauranteapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.restauranteapp.Appi.ServiceAPPIMesas;
import com.example.restauranteapp.Appi.ServiceAPPIPlato;
import com.example.restauranteapp.Models.Mesas;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mesas extends AppCompatActivity {

   private Spinner spiner;

   private EditText editText;
    private ServiceAPPIMesas serviceAPI ;

    private Button btnEliminar;
    private List<Mesas> mesasList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesas);


obtenerMesasDeAPI();


    }

    private void obtenerMesasDeAPI() {
        ServiceAPPIMesas serviceAPPIMesas = ConnectionREST.getConnection().create(ServiceAPPIMesas.class);
        Call<List<Mesas>> call = serviceAPPIMesas.listProduct();

        call.enqueue(new Callback<List<Mesas>>() {
            @Override
            public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
                if (response.isSuccessful()) {
                    mesasList = response.body();

                    // Llama al método para crear los botones de mesa basados en la lista de mesas obtenida
                    crearBotones();
                } else {
                    Toast.makeText(mesas.this, "Error al obtener las mesas", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Mesas>> call, Throwable t) {
                Toast.makeText(mesas.this, "Ocurrió un error al obtener las mesas", Toast.LENGTH_LONG).show();
            }
        });

    }



    private int offsetX, offsetY;

    private void crearBotones() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (final Mesas mesa : mesasList) {
            final Button mesaButton = new Button(this);
            mesaButton.setText("Mesa " + mesa.getIdMesa());

            // Verificar la disponibilidad de la mesa y establecer el color de fondo
            if (mesa.getReservado() == 0) {
                // Mesa libre
                mesaButton.setBackgroundColor(Color.GREEN);
            } else {
                // Mesa ocupada
                mesaButton.setBackgroundColor(Color.RED);
            }

            mesaButton.setId(mesa.getIdMesa());
            int buttonWidth = 250;
            int buttonHeight = 250;
            mesaButton.setLayoutParams(new GridLayout.LayoutParams(
                    new ViewGroup.LayoutParams(buttonWidth, buttonHeight)
            ));

            // Configurar los márgenes para cada botón
            GridLayout.LayoutParams params = (GridLayout.LayoutParams) mesaButton.getLayoutParams();
            params.setMargins(16, 16, 16, 16); // (left, top, right, bottom)
            mesaButton.setLayoutParams(params);

            // Obtener las posiciones iniciales de las mesas
            final int initialLeft = mesa.getDleft();
            final int initialTop = mesa.getDup();

            mesaButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int X = (int) event.getRawX();
                    final int Y = (int) event.getRawY();
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            // Guardar las posiciones iniciales de X e Y
                            offsetX = X + initialLeft;
                            offsetY = Y + initialTop;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            // Actualizar las posiciones de X e Y mientras se mueve el botón
                            mesaButton.setX(X - offsetX);
                            mesaButton.setY(Y - offsetY);
                            break;
                    }
                    return true;
                }
            });

            gridLayout.addView(mesaButton);
        }
    }


    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }




}


