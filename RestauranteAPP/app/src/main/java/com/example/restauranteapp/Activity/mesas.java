package com.example.restauranteapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    private List<Mesas> mesasList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesas);

        spiner = (Spinner) findViewById(R.id.spiner_mesas);
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPPIMesas.class);
       editText = (EditText) findViewById(R.id.editTextTextMultiLine);

      spinner();

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Mesas mesaSeleccionada = mesasList.get(position - 1);
                    String detallesMesa = "Cantidad de Asientos: " + mesaSeleccionada.getCantidadAsientos() + "\n\n"
                            + "Descripcion :"  + mesaSeleccionada.getDescripcion() + "\n\n"+"Reservado : " + mesaSeleccionada.getReservado();
                    editText.setText(detallesMesa);
                } else {
                    editText.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    mensaje("ERROR");
            }
        });


    }

    public  void spinner()
    {
    ServiceAPPIMesas serviceAPPIMesas =ConnectionREST.getConnection().create(ServiceAPPIMesas.class);
    Call<List<Mesas>> call =serviceAPPIMesas.listProduct();

    call.enqueue(new Callback<List<Mesas>>() {
       @Override
       public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
           if(response.isSuccessful())
           {
               mesasList = response.body();
               List<Mesas> respuesta = response.body();
               List<String> idsMesas = new ArrayList<>();
                  idsMesas.add("(SELECCIONAR)");
               for(Mesas x:respuesta)
               {

                   idsMesas.add(String.valueOf(x.getIdMesa()));
               }
              ArrayAdapter<String> adapter = new ArrayAdapter<>(mesas.this, android.R.layout.simple_spinner_item, idsMesas);
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

               spiner.setAdapter(adapter);
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

    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }




}


