package com.example.restauranteapp.Activity;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

    private Spinner spiner_tipo;
    private Button boton_atras;
    private TextView seleccionar_tipo;
    private TextView scrollView;

    private LinearLayout checkbox_container;

    private String tipo_plato;

    private Button aceptar;
    private Button limpiar;
    private TextView summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_pedido);

        //edit_mesa = (EditText) findViewById(R.id.edit_mesa);
        spiner_mesa = (Spinner) findViewById(R.id.spinner_mesa);
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIPlato.class);
        boton_atras=(Button) findViewById(R.id.btn_atras);
        textView =(TextView) findViewById(R.id.textView_mesa);
        spiner_tipo=(Spinner) findViewById(R.id.spinner_tipo);
        seleccionar_tipo=(TextView) findViewById(R.id.textView_mesa);
        checkbox_container  =(LinearLayout) findViewById(R.id.checkboxContainer);
        limpiar= (Button) findViewById(R.id.btn_limpiar);
        aceptar=(Button) findViewById(R.id.btn_aceptar);
        summary =(TextView) findViewById(R.id.text_resumen);
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
        spinner_tipo();



        boton_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear una nueva cadena para guardar el estado anterior
                String textoCheckBoxesAnterior = summary.getText().toString();

                StringBuilder textoCheckBoxesNuevo = new StringBuilder();

                // Iterar a través de todos los CheckBoxes presentes en el contenedor
                for (int i = 0; i < checkbox_container.getChildCount(); i++) {
                    View view = checkbox_container.getChildAt(i);
                    if (view instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) view;
                        // Verificar si el CheckBox está seleccionado
                        if (checkBox.isChecked()) {
                            // Agregar el texto del CheckBox seleccionado al StringBuilder
                            textoCheckBoxesNuevo.append(checkBox.getText()).append("\n");
                        }
                    }
                }

                // Mostrar los textos de los CheckBoxes seleccionados en el TextView
                summary.setText(textoCheckBoxesNuevo.toString());

                // Aquí puedes comparar textoCheckBoxesAnterior con textoCheckBoxesNuevo según lo que necesites
                // por ejemplo, si deseas saber si ha habido un cambio en las selecciones de CheckBoxes
                if (!textoCheckBoxesAnterior.equals(textoCheckBoxesNuevo.toString())) {
                    // Ha habido cambios en las selecciones de CheckBoxes
                    // Puedes realizar las acciones necesarias aquí
                }
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpiar todos los CheckBoxes del contenedor
                checkbox_container.removeAllViews();
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
    public void spinner_tipo(){

        ServiceAPPIPlato serviceAPPIPlatos =ConnectionREST.getConnection().create(ServiceAPPIPlato.class);

        Call<List<Plato>> call1 =serviceAPPIPlatos.listProduct();


        call1.enqueue(new Callback<List<Plato>>() {

            @Override
            public void onResponse(Call<List<Plato>> call1, Response<List<Plato>> response1) {

                if(response1.isSuccessful())
                {

                    List<Plato> respuesta = response1.body();
                    List<String> tipos = new ArrayList<>();
                    tipos.add("Seleccionar");

                    for(Plato x:respuesta)
                    {
                        String tipoPlato = x.getTipoPlato();
                        if(!tipos.contains(tipoPlato))
                            tipos.add(x.getTipoPlato());

                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Agregar_pedido.this, android.R.layout.simple_spinner_item, tipos);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spiner_tipo.setAdapter(adapter);
                    spiner_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // Obtener la opción seleccionada
                            tipo_plato  = parent.getItemAtPosition(position).toString();
                            crear_checkbox(tipo_plato);

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Método requerido pero no necesitamos hacer nada aquí en este ejemplo
                        }
                    });

                }
                else
                {
                    Toast.makeText(Agregar_pedido.this,"Error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {

                Toast.makeText(Agregar_pedido.this,"Ocurrop un error", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void crear_checkbox(String tipo_plato){
        ServiceAPPIPlato serviceAPPIPlatos =ConnectionREST.getConnection().create(ServiceAPPIPlato.class);

        Call<List<Plato>> call1 =serviceAPPIPlatos.listProduct();


        call1.enqueue(new Callback<List<Plato>>() {

            @Override
            public void onResponse(Call<List<Plato>> call1, Response<List<Plato>> response1) {

                if (response1.isSuccessful()) {

                    List<Plato> respuesta = response1.body();
                    List<String> tipos = new ArrayList<>();
                    checkbox_container.removeAllViews();


                    for (Plato x : respuesta) {
                        String tipoPlato = x.getTipoPlato();
                        if (tipo_plato != null && tipoPlato.equals(tipo_plato)) {

                            CheckBox checkBox = new CheckBox(Agregar_pedido.this);
                            checkBox.setText(x.getNombrePlato());
                            checkBox.setTextColor(Color.YELLOW);
                            // Agregar un ID único para cada CheckBox (opcional)
                            checkBox.setId(x.getIdPlato());

                            // Agregar los CheckBoxes al contenedor
                            checkbox_container.addView(checkBox);

                        }
                    }
                }
                else
                    {
                        Toast.makeText(Agregar_pedido.this, "Error", Toast.LENGTH_LONG).show();
                    }

            }
            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {

                Toast.makeText(Agregar_pedido.this,"Ocurrop un error", Toast.LENGTH_LONG).show();
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
