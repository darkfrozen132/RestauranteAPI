package com.example.restauranteapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restauranteapp.Appi.ServiceAPPIMesas;
import com.example.restauranteapp.Appi.ServiceAPPIPlato;
import com.example.restauranteapp.Models.Mesas;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mesas extends AppCompatActivity {

   private Spinner spinerPisos;

   private EditText editText;
    private ServiceAPPIMesas serviceAPI ;

    private Button btnEliminar;
    private List<Mesas> mesasList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesas);

        spinerPisos = findViewById(R.id.spinnerPisos);

obtenerMesasDeAPI();

serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIMesas.class);

    }

    private void obtenerMesasDeAPI() {
        ServiceAPPIMesas serviceAPPIMesas = ConnectionREST.getConnection().create(ServiceAPPIMesas.class);
        Call<List<Mesas>> call = serviceAPPIMesas.listProduct();

        call.enqueue(new Callback<List<Mesas>>() {
            @Override
            public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
                if (response.isSuccessful()) {
                    mesasList = response.body();
                    Set<String> pisosSet = new HashSet<>();
                    for (Mesas mesa : mesasList) {
                        pisosSet.add(mesa.getDescripcion());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(mesas.this,
                            android.R.layout.simple_spinner_item, new ArrayList<>(pisosSet));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinerPisos.setAdapter(adapter);

                    // Ajustar la posición del Spinner
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    params.gravity = Gravity.CENTER_VERTICAL | Gravity.END; // Alinear al centro vertical y a la derecha
                    spinerPisos.setLayoutParams(params);

                    // Ajustar el tamaño del texto del Spinner
                  // Cambiar el tamaño del texto

                    // Ajustar el tamaño del Spinner
                    ViewGroup.LayoutParams spinnerParams = spinerPisos.getLayoutParams();
                    spinnerParams.width = 300; // Ancho del Spinner
                    spinnerParams.height = 150; // Altura del Spinner (largo)
                    spinerPisos.setLayoutParams(spinnerParams);


                    spinerPisos.setLayoutParams(spinnerParams);

                    spinerPisos.setBackgroundColor(Color.YELLOW);

                    spinerPisos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String pisoSeleccionado = parent.getItemAtPosition(position).toString();
                            crearBotones(pisoSeleccionado);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
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



    private boolean modificarMesas = false; // Variable para controlar si se pueden modificar las mesas

    private void crearBotones(final String pisoSeleccionado) {

        FrameLayout frameLayout = findViewById(R.id.gridLayout);
        frameLayout.removeAllViews(); //
        frameLayout.addView(spinerPisos);
        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
        final int screenHeight = getResources().getDisplayMetrics().heightPixels;
        for (final Mesas mesa : mesasList) {
            if (mesa.getDescripcion().equals(pisoSeleccionado)) {
                final TextView mesaButton = new TextView(this);
                mesaButton.setText("Mesa " + mesa.getIdMesa());
                mesaButton.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                mesaButton.setTextSize(18);
                mesaButton.setTextColor(Color.WHITE);

                int darkGreen = Color.rgb(0, 110, 0);
                int red = Color.rgb(140, 0, 0);
                if (mesa.getReservado() == 0) {
                    mesaButton.setBackgroundResource(R.drawable.button_background);
                    mesaButton.setBackgroundColor(darkGreen);
                } else if (mesa.getReservado() == 1) {
                    mesaButton.setBackgroundColor(red);
                } else if (mesa.getReservado() == 2) {
                    mesaButton.setBackgroundColor(getResources().getColor(R.color.colorDarkYellow));
                }

                int buttonWidth = 250;
                int buttonHeight = 250;

                int posX = mesa.getdLeft();
                int posY = mesa.getdUp();
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(buttonWidth, buttonHeight);
                params.leftMargin = posX;
                params.topMargin = posY;
                mesaButton.setLayoutParams(params);

                mesaButton.setOnTouchListener(new View.OnTouchListener() {
                    float dX, dY;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (modificarMesas) {
                            final int X = (int) event.getRawX();
                            final int Y = (int) event.getRawY();
                            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                case MotionEvent.ACTION_DOWN:
                                    dX = mesaButton.getX() - event.getRawX();
                                    dY = mesaButton.getY() - event.getRawY();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    float newX = event.getRawX() + dX;
                                    float newY = event.getRawY() + dY;

                                    // Verificar límites horizontales y verticales
                                    newX = Math.max(0, Math.min(newX, screenWidth - mesaButton.getWidth()));
                                    newY = Math.max(0, Math.min(newY, screenHeight - mesaButton.getHeight()));

                                    mesaButton.setX(newX);
                                    mesaButton.setY(newY);

                                    // Actualizar las coordenadas de la mesa en mesasList
                                    mesa.setdLeft((int) newX);
                                    mesa.setdUp((int) newY);

                                    modificarMesa(mesa);
                                    break;
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                frameLayout.addView(mesaButton);
            }
        }

        final Button modificarMesasButton = new Button(this);
        modificarMesasButton.setText("Modificar mesas");

        int buttonWidth = 250;
        int buttonHeight = 150;
        FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(buttonWidth, buttonHeight);
        buttonParams.gravity = Gravity.BOTTOM | Gravity.START;
        buttonParams.bottomMargin = 200;
        buttonParams.leftMargin = 100;

        modificarMesasButton.setLayoutParams(buttonParams);

        modificarMesasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarMesas = !modificarMesas;
                modificarMesasButton.setText(modificarMesas ? "Dejar de Modificar" : "Modificar mesas");
            }
        });

        frameLayout.addView(modificarMesasButton);
    }


    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }

    private void modificarMesa(Mesas pObj)
    {
        Call<Mesas> call = serviceAPI.put(pObj);
        call.enqueue(new Callback<Mesas>() {
            @Override
            public void onResponse(Call<Mesas> call, Response<Mesas> response) {
                if(response.isSuccessful())
                {
                    Mesas  mesas =  response.body();


                }
            }

            @Override
            public void onFailure(Call<Mesas> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });

    }


}


