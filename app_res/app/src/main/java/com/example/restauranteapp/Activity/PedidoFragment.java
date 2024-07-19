package com.example.restauranteapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    public TextView txtResumen;
    private int num;
    private String nombre;

    private int cant;

    private Button btn_aceptar;
    private Button btn_cancelar;
    private Button btn_inscremento;
    private Button btn_decremento;

    private TextView cantidad;

    private TextView plato;



    private int contador=0;

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

        btn_cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btn_inscremento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int cant=1;
                String can = cantidad.getText().toString();
                cant = Integer.parseInt(can);

                cant++;

                cantidad.setText(String.valueOf(cant));


            }


        });
        btn_decremento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int cant=1;
                String can = cantidad.getText().toString();
                cant = Integer.parseInt(can);
                if(cant>=2) {
                    cant--;
                }
                cantidad.setText(String.valueOf(cant));

            }


        });
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int cant = Integer.parseInt(cantidad.getText().toString());
                String nombrePlato = plato.getText().toString();


                // Obtener una referencia al LinearLayout
                LinearLayout linearLayoutResumen = ((Agregar_pedido) getActivity()).findViewById(R.id.linearlayout_resumen);
                boolean platoExistente = false;

                // Crear un nuevo TextView y configurar su texto
                TextView nuevoTextView = new TextView(getActivity());

                nuevoTextView.setId(contador);
                for (int i = 0; i < linearLayoutResumen.getChildCount(); i++) {
                    View child = linearLayoutResumen.getChildAt(i);
                    if (child instanceof TextView) {
                        TextView textView = (TextView) child;
                        String textoActual = textView.getText().toString();


                String nuevoTexto = textoActual + "\nPlato: " + nombrePlato + ", Cantidad: " + cant;

                    // Actualizar el TextView summary con el texto acumulado
                txtResumen.setText(nuevoTexto);

                        if (textoActual.contains(nombrePlato) ) {
                            // Si el plato ya está en el resumen, actualizar la cantidad
                            int indiceSeparador = textoActual.indexOf(":");
                            String parteAntesDelSeparador = textoActual.substring(indiceSeparador+1);
                            int num = Integer.parseInt(parteAntesDelSeparador);
                            cant = num + cant;

                            textView.setText(nombrePlato + "Cantidad:" + cant);
                            textView.setTextColor(Color.YELLOW);
                            textView.setTextSize(18);
                            platoExistente = true;
                            break;

                        }
                    }
                }

                if (!platoExistente) {
                    nuevoTextView.setText(nombrePlato + "Cantidad:" + cant);
                    nuevoTextView.setTextColor(Color.YELLOW);
                    nuevoTextView.setTextSize(18);
                    linearLayoutResumen.addView(nuevoTextView);
                }






                contador++;
                cantidad.setText("");
                plato.setText("");

                dismiss();
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener(){
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
