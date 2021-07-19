package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class registrarUsuario extends AppCompatActivity {
    private EditText edIdUser, edNombre, edApodoUser, edCorreo, edPassword;
    private Spinner spnQuestions;
    private AutoCompleteTextView actAnswers;
    private Button btnUsuario;
    private ArrayAdapter adapterSpin, adapterAutoComplete;
    private String[] secureQuestion = new String[4];
    private String[] secureAnswer = new String[4];
    private Usuario usuario;
    private String spinPreguntas, actRespuestas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        inits();
    }

    private void inits(){
        arreglos();
        edIdUser = findViewById(R.id.etIdUser);
        edNombre = findViewById(R.id.etNombre);
        edApodoUser = findViewById(R.id.etNameUser);
        edCorreo = findViewById(R.id.etEmail);
        edPassword = findViewById(R.id.etPassword);
        btnUsuario = findViewById(R.id.btnRegistrarUsuario);
        spnQuestions = (Spinner) findViewById(R.id.spnPreguntas);
        actAnswers = (AutoCompleteTextView) findViewById(R.id.actRespuesta);

        adapterSpin = new ArrayAdapter(this, android.R.layout.simple_list_item_1, secureQuestion);
        spnQuestions.setAdapter(adapterSpin);

        adapterAutoComplete = new ArrayAdapter(this, android.R.layout.simple_list_item_1, secureAnswer);
        actAnswers.setAdapter(adapterAutoComplete);
        actAnswers.setThreshold(2);

        spinAndAutocomplete();
    }

    private void arreglos(){
        //Arreglo del spinner Preguntas.
        secureQuestion[0] = "PREGUNTA 1";
        secureQuestion[1] = "PREGUNTA 2";
        secureQuestion[2] = "PREGUNTA 3";
        secureQuestion[3] = "PREGUNTA 4";

        //Arreglo del AutoComplete Respuestas.
        secureAnswer[0] = "RESPUESTA 1";
        secureAnswer[1] = "RESPUESTA 2";
        secureAnswer[2] = "RESPUESTA 3";
        secureAnswer[3] = "RESPUESTA 4";
    }

    //Metodos para el spinner y autoComplete
    private void spinAndAutocomplete(){
        spnQuestions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object q = parent.getItemAtPosition(position);
                String Pregunta = q.toString();
                spinPreguntas = Pregunta;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        actAnswers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object a = parent.getItemAtPosition(position);
                String Respuesta = a.toString();
                actRespuestas = Respuesta;
            }
        });
    }

    private void databaseSQLiteUsers(){
        try{
            adminSQLiteUsers adminUsers = new adminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase miBD = adminUsers.getWritableDatabase();

            if(miBD != null){
                String[] parameters = {edIdUser.getText().toString(), edNombre.getText().toString(), edApodoUser.getText().toString(),
                edCorreo.getText().toString(), edPassword.getText().toString(), spinPreguntas, actRespuestas};
                miBD.execSQL("INSERT INTO USUARIO (ID_USUARIO, NOMBRE, APODO_USUARIO, CORREO, CONTRASENIA, " +
                        "PREGUNTA, RESPUESTA) VALUES(?,?,?,?,?,?,?)", parameters);
            }
            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", "ERROR " +ex.toString());
        }
    }

    public boolean valIdUsuario(EditText edIdUser){
        boolean id_resultado;
        try {
            Integer.parseInt(String.valueOf(edIdUser));
            id_resultado = true;
        }catch (NumberFormatException exception){
            id_resultado = false;
        }
        return id_resultado;
    }

    public boolean valNombre(EditText edNombre){

    }
}