package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        inits();
    }

    private void inits(){
        edIdUser = findViewById(R.id.etIdUser);
        edNombre = findViewById(R.id.etNombre);
        edApodoUser = findViewById(R.id.etNameUser);
        edCorreo = findViewById(R.id.etEmail);
        edPassword = findViewById(R.id.etPassword);
        btnUsuario = findViewById(R.id.btnRegistrarUsuario);
        spnQuestions = (Spinner) findViewById(R.id.spnPreguntas);
        actAnswers = (AutoCompleteTextView) findViewById(R.id.actRespuesta);
    }

    private void databaseSQLiteUsers(){
        try{
            adminSQLiteUsers adminUsers = new adminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase miBD = adminUsers.getWritableDatabase();
        }catch (Exception ex){

        }
    }
}