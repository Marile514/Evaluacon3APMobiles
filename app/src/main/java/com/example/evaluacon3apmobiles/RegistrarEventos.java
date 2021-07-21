package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrarEventos extends AppCompatActivity {
    private TextInputEditText titulo, importancia, observ, lugar, fechaEvento, tiempoAviso;
    private Button btnEventRegister;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_eventos);

        inits();
        cargarBundles();
    }

    private void inits(){
        titulo = findViewById(R.id.tiedTitulo);
        importancia = findViewById(R.id.tiedImportancia);
        observ = findViewById(R.id.tiedObservacion);
        lugar = findViewById(R.id.tiedLugar);
        fechaEvento = findViewById(R.id.tiedFecha);
        tiempoAviso = findViewById(R.id.tiedTiempo);
        btnEventRegister = findViewById(R.id.btnCapturaEvento);
    }

    private void buttonEvents(){
        btnEventRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actions(v);
            }
        });
    }

    private void actions(View v){
        if (v.getId() == R.id.btnEventosRegist){
            valTitulo(titulo);
            valImportancia(importancia);
            valObservacion(observ);
            valLugar(lugar);
            valFecha(fechaEvento);
            valTiempo(tiempoAviso);
            if (valTitulo(titulo) && valImportancia(importancia) && valObservacion(observ) && valLugar(lugar) && valFecha(fechaEvento) && valTiempo(tiempoAviso)){
                //Enviar al insert con el formato String.
                String idUser = String.valueOf(usuario.getIdUsuario());
                String title = titulo.getText().toString();
                String importance = importancia.getText().toString();
                String observation = observ.getText().toString();
                String place = lugar.getText().toString();
                String date = fechaEvento.getText().toString();
                String aviso = tiempoAviso.getText().toString();
                //Enviar los strings al método de insert.
                insertarEvento(title, importance, observation, place, date, aviso, idUser);
                limpiarCampos();
                finish();
            }else{
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cargarBundles(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario = (Usuario) bundle.getSerializable("usuario");
            Log.d("TAG_", "Usuario en sesión: " + usuario.toString());
        }
    }

    //Metodo para insertar eventos.
    private void insertarEvento(String edTitulo, String edImportance, String edObserv, String edLugar, String edFecha, String edTiempo, String idUsuario){
        try{
            AdminSQLiteUsers conn = new AdminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase miBD = conn.getWritableDatabase();
            if (miBD != null){
                //Llenar arreglo de parametros de forma INSERT INTO evento
                String[] params = {null, edTitulo, edImportance, edObserv, edLugar, edFecha, edTiempo, idUsuario};
                miBD.execSQL("INSERT INTO evento (id_evento,titulo,importancia,observacion,lugar,fecha_evento," +
                        "tiempo_aviso,id_Usuario) VALUES (?,?,?,?,?,?,?,?)", params);
            }
            miBD.close();
            Log.d("TAG_", "insertarEvento: EXITOSO!!");
        }catch (Exception ex){
            Log.d("TAG_", "insertarEvento: ERROR" + ex.toString());
        }
    }


    private boolean valTitulo(TextInputEditText titulo){
        if (!titulo.getText().toString().isEmpty()){
            return true;
        }else{
            titulo.setError("No ingresó el titulo del evento");
            return false;
        }
    }

    private boolean valImportancia(TextInputEditText importancia){
        if (!importancia.getText().toString().isEmpty()){
            return true;
        }else{
            importancia.setError("No ingresó la importancia");
            return false;
        }
    }

    private boolean valObservacion(TextInputEditText observ){
        if (!observ.getText().toString().isEmpty()){
            return true;
        }else{
            observ.setError("No escribió la observación");
            return false;
        }
    }

    private boolean valLugar(TextInputEditText lugar){
        if (!lugar.getText().toString().isEmpty()){
            return true;
        }else{
            lugar.setError("No ingresó el lugar del evento");
            return false;
        }
    }

    private boolean valFecha(TextInputEditText fechaEvento){
        if (!fechaEvento.getText().toString().isEmpty()){
            return true;
        }else{
            fechaEvento.setError("No ingresó la fecha del evento");
            return false;
        }
    }

    private boolean valTiempo(TextInputEditText tiempoAviso){
        if (!tiempoAviso.getText().toString().isEmpty()){
            return true;
        }else{
            tiempoAviso.setError("No ingresó el tiempo de aviso del evento");
            return false;
        }
    }

    private void limpiarCampos(){
        titulo.setText("");
        importancia.setText("");
        observ.setText("");
        lugar.setText("");
        fechaEvento.setText("");
        tiempoAviso.setText("");
    }
}