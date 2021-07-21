package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class UsuarioActivity extends AppCompatActivity {
    private TextView tvNombresUsuarios;
    private Usuario usuario;
    private Button btnEvento, btnCambiar, btnDelete;
    private ArrayList<Eventos> listaEventos = new ArrayList<>();
    private ListView eventoRegist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        inits();
        cargaBundle();
        dbEventos();
        events();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarEventos();
    }

    private void inits(){
        tvNombresUsuarios = findViewById(R.id.tvUsuarios);
        btnEvento = findViewById(R.id.btnEventosRegist);
        btnCambiar = findViewById(R.id.btnCambiarClave);
        btnDelete = findViewById(R.id.btnDelete);
        eventoRegist = (ListView) findViewById(R.id.lstRegistros);
    }

    private void cargaBundle(){
        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("usuario");
        tvNombresUsuarios.setText(tvNombresUsuarios.getText() + " " + usuario.getNombreUsuario());
    }

    private void dbEventos(){
        try {
            AdminSQLiteUsers conn = new AdminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();
            db.close();
            Log.d("TAG_", "Instanciando la base de datos de registro...");
        }catch (Exception ex){
            Log.d("TAG_", "ERROR!!" + ex.toString());
        }
    }

    //Metodo para obtener un arraylist con los eventos del usuario.
    private ArrayList<Eventos> baseDatosEvento(){
            AdminSQLiteUsers conn = new AdminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();
            Eventos eventos = null;
            String idUsuario = String.valueOf(usuario.getIdUsuario());
            String sql = "SELECT * FROM eventos WHERE id_usuario = " +idUsuario;
            Cursor c = db.rawQuery(sql, null);

            while (c.moveToNext()){
                eventos = new Eventos();
                eventos.setId_evento(c.getInt(0));
                eventos.setTitulo(c.getString(1));
                eventos.setImportancia(c.getString(2));
                eventos.setObservacion(c.getString(3));
                eventos.setLugar(c.getString(4));
                eventos.setFecha_evento(c.getString(5));
                eventos.setTiempo_aviso(c.getString(6));
                eventos.setId_usuario(c.getInt(7));
                listaEventos.add(eventos);
            }
            db.close();
            Log.d("TAG_", "listarEventos: " + listaEventos);
            return listaEventos;
    }

    private void listarEventos(){
        ArrayAdapter listAdapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, baseDatosEvento());
        eventoRegist.setAdapter(listAdapt);
    }

    //Metodo para mostrar un dialogo para eliminar un usuario.
    private void dialogDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UsuarioActivity.this);
        builder.setTitle("Eliminar usuario")
                .setMessage(usuario.getNombre())
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Borrar usuario
                        eliminarUser(String.valueOf(usuario.getIdUsuario()));
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //Metodo para realizar el delete del usuario
    private void eliminarUser(String idUsuario){
        try{
            AdminSQLiteUsers conn = new AdminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase miBD = conn.getWritableDatabase();
            miBD.execSQL("DELETE FROM usuario WHERE id_Usuario = " +idUsuario);
            Log.d("TAG_", "eliminarUser: ELIMINADO!!");
            finish();
        }catch (Exception e){
            Log.d("TAG_", "eliminarUsuario: ERROR" + e.toString());
        }
    }

    private void events(){
        btnEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UsuarioActivity.this, RegistrarEventos.class);
                startActivity(i);
                Toast.makeText(UsuarioActivity.this, "Transferencia hacia otra activity", Toast.LENGTH_SHORT).show();
            }
        });
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Transferir activity con bundle.
                Intent i = new Intent(UsuarioActivity.this, CambiarPassword.class);
                i.putExtra("usuario", usuario);
                startActivity(i);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete();
            }
        });
        //actividad de la listview.
        eventoRegist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}