package com.luis.espol.miscontactos.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.luis.espol.miscontactos.R;

import java.util.List;

/**
 * Created by Luis on 15/03/2017.
 */


/**
 * Created by Luis on 14/03/2017.
 */
public class ContactoListAdapter extends ArrayAdapter<Contacto> {
    private Activity ctx;

    public ContactoListAdapter(Activity context, List<Contacto>contactos) {
        super(context, R.layout.listview_item,contactos);
        this.ctx = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            view=ctx.getLayoutInflater().inflate(R.layout.listview_item,parent,false);
        }
        Contacto actual = this.getItem(position);
        InicializarCamposDeTexto(view,actual);
        return view;
    }

    private void InicializarCamposDeTexto(View view, Contacto actual) {
        TextView textView=(TextView)view.findViewById(R.id.viewNombre);
        textView.setText(actual.getNombre());
        textView=(TextView)view.findViewById(R.id.viewTelefono);
        textView.setText(actual.getTelefono());
        textView=(TextView)view.findViewById(R.id.viewEmail);
        textView.setText(actual.getEmail());
        textView=(TextView)view.findViewById(R.id.viewDireccion);
        textView.setText(actual.getDireccion());
        ImageView imgContacto=(ImageView)view.findViewById(R.id.imgViewContacto);
        imgContacto.setImageURI(actual.getImageUri());

    }
}
