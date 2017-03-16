package com.luis.espol.miscontactos;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.luis.espol.miscontactos.util.Contacto;
import com.luis.espol.miscontactos.util.ContactoListAdapter;
import com.luis.espol.miscontactos.util.TextChangedListener;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText txtNombre,txtTelefono,txtEmail,txtDireccion;
    private ArrayAdapter<Contacto> adapter;
    private ListView contactosListView;
    private ImageView imgViewContacto;
    private int request_code=1;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        inicializarListaContactos();
        inicializarTabs();


    }
    private void inicializarListaContactos(){
        adapter=new ContactoListAdapter(this,new ArrayList<Contacto>());
        contactosListView.setAdapter(adapter);
    }
    private void inicializarTabs() {
        TabHost tabhost=(TabHost)findViewById(R.id.tabHost);
        tabhost.setup();
        TabHost.TabSpec spec=tabhost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Crear");
        tabhost.addTab(spec);

        spec=tabhost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Lista");
        tabhost.addTab(spec);
    }

    private void inicializarComponentesUI() {
        txtNombre=(EditText)findViewById(R.id.cmpNombre);
        txtTelefono=(EditText)findViewById(R.id.cmpTelefono);
        txtEmail=(EditText)findViewById(R.id.cmpEmail);
        txtDireccion=(EditText)findViewById(R.id.cmpDireccion);
        contactosListView=(ListView)findViewById(R.id.listView);
        imgViewContacto=(ImageView)findViewById(R.id.imgViewContacto);
        txtNombre.addTextChangedListener(new TextChangedListener(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                btnAgregar=(Button)findViewById(R.id.btnAgregar);
                btnAgregar.setEnabled(!s.toString().trim().isEmpty());

            }
        });

    }

    public void onclick(View view) {
        agregarContacto(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtEmail.getText().toString(),
                txtDireccion.getText().toString(),
                (Uri)imgViewContacto.getTag()
        );
        String msg=String.format("%s ha sido agregado a la lista",txtNombre.getText());
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        limpiarCampos();
    }

    private void agregarContacto(String nombre, String telefono, String email, String direccion,Uri imageUrl) {
        Contacto nuevo=new Contacto(nombre,telefono,email,direccion,imageUrl);
        adapter.add(nuevo);
    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        //Restablecemos la imagen por defecto
        imgViewContacto.setImageResource(R.drawable.usuario);
        txtNombre.requestFocus();
    }

    public void onImgClick(View view) {
        Intent intent=null;
        if(Build.VERSION.SDK_INT<19){
            intent=new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        else{
            intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(intent,request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode==request_code){
            imgViewContacto.setImageURI(data.getData());
            //utilizamos el atributo TAG para almacenar la url del archivo seleccionado
            imgViewContacto.setTag(data.getData());
        }
    }
}
