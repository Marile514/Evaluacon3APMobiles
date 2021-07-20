package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarUsuario extends AppCompatActivity {
    private EditText edNombre, edApodoUser, edCorreo, edPassword, edAnswer;
    private Spinner spnQuestions;
    private AutoCompleteTextView actAnswers;
    private Button btnUsuario;
    private ArrayAdapter adapterSpin;
    private String[] secureQuestion = new String[4];
    private Usuario usuario;
    private String spinPreguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        inits();
        events();
    }

    private void inits(){
        arreglos();
        edNombre = findViewById(R.id.etNombre);
        edApodoUser = findViewById(R.id.etNameUser);
        edCorreo = findViewById(R.id.etEmail);
        edPassword = findViewById(R.id.etPassword);
        btnUsuario = findViewById(R.id.btnRegistrarUsuario);
        spnQuestions = (Spinner) findViewById(R.id.spnPreguntas);
        edAnswer = findViewById(R.id.etRespuestas);

        adapterSpin = new ArrayAdapter(this, android.R.layout.simple_list_item_1, secureQuestion);
        spnQuestions.setAdapter(adapterSpin);

        spinAndAutocomplete();
    }

    private void arreglos(){
        //Arreglo del spinner Preguntas.
        secureQuestion[0] = "Nombre de tu ciudad natal";
        secureQuestion[1] = "Nombre de tu deporte favorito";
        secureQuestion[2] = "Nombre de tu amigo(a)";
        secureQuestion[3] = "Nombre de tu comida favorita";
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
    }

    public boolean valNombre(EditText edNombre){
        if(!edNombre.getText().toString().isEmpty()){
            return true;
        }else{
            edNombre.setError("No ingresó el nombre completo del usuario");
            return false;
        }
    }

    public boolean valNombreUsuario(EditText edApodoUser){
        if(!edApodoUser.getText().toString().isEmpty()){
            return true;
        }else{
            edApodoUser.setError("No ingresó el apodo del usuario");
            return false;
        }
    }

    public boolean valCorreo(EditText edCorreo){
        if(!edCorreo.getText().toString().isEmpty()){
            String strCorreo = edCorreo.getText().toString();
            //Usar el método validarCorreo(strCorreo) para la expresión regular.
            if(validarCorreo(strCorreo)){
                return true;
            } else {
                edCorreo.setError("No es un Correo!");
                return false;
            }
        } else {
            edCorreo.setError("Correo vacío");
            return false;
        }
    }

    public boolean validarCorreo(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean valPassword(EditText edPassword){
        if(!edPassword.getText().toString().isEmpty()){
            return true;
        }else{
            edPassword.setError("No ingresó la contraseña");
            return false;
        }
    }

    public boolean valRespuesta(EditText edAnswer){
        if(!edAnswer.getText().toString().isEmpty()){
            return true;
        }else{
            edAnswer.setError("No ingresó la respuesta");
            return false;
        }
    }

    private void insertBaseDatos(){
       try{
           AdminSQLiteUsers connInsert = new AdminSQLiteUsers(this, "dbUsuarios", null, 1);
           SQLiteDatabase db = connInsert.getWritableDatabase();

           ContentValues values = new ContentValues();
           values.put(Utilidades.NOMBRE, edNombre.getText().toString());
           values.put(Utilidades.USENAME, edApodoUser.getText().toString());
           values.put(Utilidades.CORREO, edCorreo.getText().toString());
           values.put(Utilidades.PASSWORD, edPassword.getText().toString());
           values.put(Utilidades.PREGUNTA, spinPreguntas);
           values.put(Utilidades.RESPUESTA, edAnswer.getText().toString());
           db.close();

           Log.d("TAG_", "Se logró insertar los datos");
       }catch (Exception ex){
           Log.d("TAG_", "Error " +ex.toString());
       }

    }

    private void events(){
        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valNombre(edNombre);
                valNombreUsuario(edApodoUser);
                valCorreo(edCorreo);
                valPassword(edPassword);
                valRespuesta(edAnswer);
                if(valNombre(edNombre) && valNombreUsuario(edApodoUser) && valCorreo(edCorreo) &&
                        valPassword(edPassword) && valRespuesta(edAnswer)){
                    insertBaseDatos();
                }else{
                    Log.d("TAG_", "No funciona el registro del usuario.");
                }
            }
        });
    }
}