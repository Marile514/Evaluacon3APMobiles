package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CambiarPassword extends AppCompatActivity {
    private TextView tvNombreUsuario;
    private TextInputEditText edPass1, edPass2;
    private Button btnChangePass;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);

        inits();
        chargesBundles();
    }

    private void inits(){
        tvNombreUsuario = findViewById(R.id.tvUserNameCP);
        edPass1 = (TextInputEditText) findViewById(R.id.tiedOldPass);
        edPass2 = (TextInputEditText) findViewById(R.id.tiedNewPass);
        btnChangePass = findViewById(R.id.btnCambiarPass);
    }

    private void events(){
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logica para el cambio de password.
                valOldPassword(edPass1);
                valNewPassword(edPass2);
                if (valOldPassword(edPass1) && valNewPassword(edPass2)){
                    String antPass = edPass1.getText().toString();
                    String sigPass = edPass2.getText().toString();
                    //Validar que la password anterior edPass1 sea correcta.
                    valPassword(antPass);
                    if (valPassword(antPass)){
                        //Se cambiará la password
                        dialogUpdatePass(sigPass);
                    }else{
                        Toast.makeText(CambiarPassword.this, "La contraseña ingresada no es correcta", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CambiarPassword.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Metodo para cambiar la contraseña del usuario en sesión.
    private void modificarPass(String upPass){
        try {
            AdminSQLiteUsers conn = new AdminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase miBD = conn.getWritableDatabase();
            miBD.execSQL("UPDATE usuario SET password = " + " '" + upPass + "' " + "WHERE id_Usuario = " + usuario.getIdUsuario());
            Log.d("TAG_", "modificarPass: Al usuario: " +usuario.getNombre());
            limpiarCampos();
            finish();
        }catch(Exception ex){
            Log.d("TAG_", "modificarPass: ERROR" + ex.toString());
        }
    }

    //Metodo para recibir datos desde el UsuarioActivity.
    private void chargesBundles(){
        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("usuario");
        tvNombreUsuario.setText(tvNombreUsuario.getText() + "" + usuario.getNombre());
    }

    //Implementar un alert dialog para cambiar la contraseña
    private void dialogUpdatePass(String upPass){
        AlertDialog.Builder builder = new AlertDialog.Builder(CambiarPassword.this);
        builder.setTitle("¿Desea cambiar la contraseña?")
                .setMessage("Cambiar la contraseña")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modificarPass(upPass);
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

    //Metodos para validar las passwords.
    private boolean valPassword(String pass){
        if (usuario.getPassword().equals(pass)){
            return true;
        }
        return false;
    }

    private boolean valOldPassword(TextInputEditText edPass1){
        if(!edPass1.getText().toString().isEmpty()){
            return true;
        }else{
            edPass1.setError("No ingresó la contraseña");
            return false;
        }
    }

    private boolean valNewPassword(TextInputEditText edPass2){
        if(!edPass2.getText().toString().isEmpty()){
            return true;
        }else{
            edPass2.setError("No ingresó la contraseña");
            return false;
        }
    }

    private void limpiarCampos(){
        edPass1.setText("");
        edPass2.setText("");
    }
}