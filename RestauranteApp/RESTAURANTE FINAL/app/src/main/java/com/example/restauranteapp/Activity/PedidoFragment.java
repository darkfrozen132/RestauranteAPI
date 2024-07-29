package com.example.restauranteapp.Activity;

import android.annotation.SuppressLint;
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


import androidx.fragment.app.DialogFragment;

import com.example.restauranteapp.Appi.ServiceAPPIMesas;
import com.example.restauranteapp.Appi.ServiceAPPIPlato;
import com.example.restauranteapp.Models.Mesas;
import com.example.restauranteapp.Models.Plato;
import com.example.restauranteapp.R;
import com.example.restauranteapp.Util.ConnectionREST;



import com.example.restauranteapp.R;

public class PedidoFragment  extends DialogFragment {

    private int num;

    private double totalPedido = 0.0;
    private String nombre;

    private  String precioPlato;
    private int cant;

    private Button btn_aceptar;
    private Button btn_cancelar;
    private Button btn_inscremento;
    private Button btn_decremento;




    private int contador=0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.agregar_pedido_fragment, null);
        builder.setView(v);


        Button btn_aceptar = v.findViewById(R.id.btn_aceptar_p);
        Button btn_cancelar = v.findViewById(R.id.btn_cancelar_p);
        Button btn_inscremento = v.findViewById(R.id.btn_inscremento);
        Button btn_decremento = v.findViewById(R.id.btn_decremento);
        TextView cantidad = v.findViewById(R.id.cantidad);
        TextView plato = v.findViewById(R.id.textplato);
        TextView precio_Plato = v.findViewById(R.id.precioplato);

        
        Bundle args = getArguments();
        // Verificar si el Bundle no es nulo y contiene los datos necesarios

        if (args != null) {
            // Recuperar los datos del Bundle
            nombre = args.getString("nom_plato");
            precioPlato = args.getString("pre_plato");


        }
        plato.setText(nombre);
        cantidad.setText("1");
        precio_Plato.setText(precioPlato);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btn_inscremento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cant = 1;
                String can = cantidad.getText().toString();
                cant = Integer.parseInt(can);

                cant++;

                cantidad.setText(String.valueOf(cant));


            }


        });
        btn_decremento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cant = 1;
                String can = cantidad.getText().toString();
                cant = Integer.parseInt(can);
                if (cant >= 2) {
                    cant--;
                }
                cantidad.setText(String.valueOf(cant));

            }


        });
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            private int contador = 0;
           private  double precio_ant ;
            @Override
            public void onClick(View view) {
                int cant = Integer.parseInt(cantidad.getText().toString());
                String nombrePlato = plato.getText().toString();
                double precio = Double.parseDouble(precio_Plato.getText().toString());

                LinearLayout linearLayoutResumen = ((Agregar_pedido) getActivity()).findViewById(R.id.linearlayout_resumen);
                boolean platoExistente = false;

                for (int i = 0; i < linearLayoutResumen.getChildCount(); i++) {
                    View child = linearLayoutResumen.getChildAt(i);
                    if (child instanceof LinearLayout) {
                        LinearLayout rowLayout = (LinearLayout) child;
                        TextView textViewPlato = (TextView) rowLayout.getChildAt(0);
                        TextView textViewCantidad = (TextView) rowLayout.getChildAt(1);
                        TextView textViewPrecio = (TextView) rowLayout.getChildAt(2);

                        if (textViewPlato.getText().toString().equals(nombrePlato)) {
                            int cantidadActual = Integer.parseInt(textViewCantidad.getText().toString());
                            double precioActual = Double.parseDouble(textViewPrecio.getText().toString());

                            int nuevaCantidad = cantidadActual + cant;
                            double nuevoPrecio = precio * nuevaCantidad;

                            textViewCantidad.setText(String.valueOf(nuevaCantidad));
                            textViewPrecio.setText(String.format("%.2f", nuevoPrecio));

                            totalPedido = totalPedido - precioActual + nuevoPrecio;
                            platoExistente = true;
                            break;
                        }
                    }
                }

                if (!platoExistente) {
                    LinearLayout nuevoRowLayout = new LinearLayout(getActivity());
                    nuevoRowLayout.setOrientation(LinearLayout.HORIZONTAL);
                    nuevoRowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));

                    TextView nuevoTextViewPlato = new TextView(getActivity());
                    nuevoTextViewPlato.setText(nombrePlato);
                    nuevoTextViewPlato.setTextColor(Color.YELLOW);
                    nuevoTextViewPlato.setTextSize(18);
                    nuevoTextViewPlato.setLayoutParams(new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1
                    ));
                    nuevoRowLayout.addView(nuevoTextViewPlato);

                    TextView nuevoTextViewCantidad = new TextView(getActivity());
                    nuevoTextViewCantidad.setText(String.valueOf(cant));
                    nuevoTextViewCantidad.setTextColor(Color.YELLOW);
                    nuevoTextViewCantidad.setTextSize(18);
                    nuevoTextViewCantidad.setLayoutParams(new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1
                    ));
                    nuevoTextViewCantidad.setPadding(16, 0, 0, 0);
                    nuevoRowLayout.addView(nuevoTextViewCantidad);

                    TextView nuevoTextViewPrecio = new TextView(getActivity());
                    nuevoTextViewPrecio.setText(String.format("%.2f", precio * cant));
                    nuevoTextViewPrecio.setTextColor(Color.YELLOW);
                    nuevoTextViewPrecio.setTextSize(18);
                    nuevoTextViewPrecio.setLayoutParams(new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1
                    ));
                    nuevoTextViewPrecio.setPadding(16, 0, 0, 0);
                    nuevoRowLayout.addView(nuevoTextViewPrecio);

                    linearLayoutResumen.addView(nuevoRowLayout);



                }
                TextView totalTextView2 = ((Agregar_pedido) getActivity()).findViewById(R.id.txtTotalPlatos);
                String text = totalTextView2.getText().toString();
                if(text.isEmpty()) {
                    precio_ant = 0.0;
                }  else {
                    precio_ant =Double.parseDouble(text);
                }
                totalPedido = precio * cant + precio_ant;
               String totalPedidoStr = String.format("%.2f", totalPedido);
                 TextView totalTexView =  ((Agregar_pedido) getActivity()).findViewById(R.id.txtTotalPlatos);
               totalTexView.setText(totalPedidoStr);

                contador++;
                cantidad.setText("");
                plato.setText("");
                precio_Plato.setText("");

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
