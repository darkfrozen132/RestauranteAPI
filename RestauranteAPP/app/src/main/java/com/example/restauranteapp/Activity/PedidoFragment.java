package com.example.restauranteapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.restauranteapp.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.restauranteapp.R;

public class PedidoFragment  extends DialogFragment {
    private int num;
    private String nombre;

    private int cant;

    private Button btn_aceptar;
    private Button btn_cancelar;
    private Button btn_inscremento;
    private Button btn_decremento;

    private TextView cantidad;

    private TextView plato;





    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View v=inflater.inflate(R.layout.agregar_pedido_fragment,null);
        builder.setView(v);


        Button btn_aceptar= v.findViewById(R.id.btn_aceptar_p);
        Button btn_cancelar= v.findViewById(R.id.btn_cancelar_p);
        Button btn_inscremento=v.findViewById(R.id.btn_inscremento);
        Button btn_decremento=v.findViewById(R.id.btn_decremento);

        TextView cantidad =v.findViewById(R.id.cantidad);
        TextView plato=v.findViewById(R.id.textplato);

        Bundle args = getArguments();
        // Verificar si el Bundle no es nulo y contiene los datos necesarios
        if (args != null) {
            // Recuperar los datos del Bundle
           nombre = args.getString("nom_plato");




        }
        plato.setText(nombre);
        cantidad.setText("1");

        btn_inscremento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String can = cantidad.getText().toString();
                int cant = Integer.parseInt(can);

                cant++;

                cantidad.setText(String.valueOf(cant));


            }


        });
        btn_decremento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String can = cantidad.getText().toString();
                int cant = Integer.parseInt(can);
                if(cant>=2) {
                    cant--;
                }


                cantidad.setText(String.valueOf(cant));


            }


        });
        btn_aceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });



        return builder.create();


    }
    public static PedidoFragment newInstance(Bundle datos) {
        PedidoFragment fragment = new PedidoFragment();
        fragment.setArguments(datos);
        return fragment;
    }
    public void mensaje(String msg) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(requireContext());

        alerta.setMessage(msg);
        alerta.show();
    }


}
