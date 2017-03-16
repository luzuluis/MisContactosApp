package com.luis.espol.miscontactos.util;

import android.net.Uri;

/**
 * Created by Luis on 15/03/2017.
 */
public class Contacto {
    private String nombre,telefono,email,direccion;
    private Uri imageUri;
    public Contacto(String nombre, String telefono, String email, String direccion, Uri imageUri) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.imageUri = imageUri;
    }

    //region Metodos Get
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    public String getDireccion() {
        return direccion;
    }

    public Uri getImageUri() {
        return imageUri;
    }
    //endregion

    //region Metodos Set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
    //endregion
}
