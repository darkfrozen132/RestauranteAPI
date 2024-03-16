package com.example.restauranteapp.Activity;
import android.os.Bundle;

public class datos {
    private int ver;
    private int cantidad;

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("ver", ver);
        bundle.putInt("cantidad", cantidad);
        return bundle;
    }

    public void fromBundle(Bundle bundle) {
        ver = bundle.getInt("ver");
        cantidad = bundle.getInt("cantidad");
    }


}
