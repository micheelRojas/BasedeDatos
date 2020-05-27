package com.example.praticando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.praticando.R;
import com.example.praticando.Utilidades.Utilidades;

public class RegistroUsuariosActivity extends AppCompatActivity {
EditText campoId, campoNombre , campoTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        campoId = (EditText) findViewById(R.id.id_text);
        campoNombre = (EditText) findViewById(R.id.nombre_text);
        campoTelefono = (EditText) findViewById(R.id.telefono_text);

    }
    public  void onClick(View view){
        registrarUsuarios();

    }

    private void registrarUsuarios() {
        int id =Integer.parseInt(campoId.getText().toString());
        String nombre = campoNombre.getText().toString();
        String telefono = campoTelefono.getText().toString();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"db_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        try {

            ContentValues values=new ContentValues();
            values.put(Utilidades.CAMPO_ID ,id);
            values.put(Utilidades.CAMPO_NOMBRE , nombre);
            values.put(Utilidades.CAMPO_TELEFONO ,telefono);
            long idResultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);
            if (idResultante != -1){
                Toast.makeText(getApplicationContext(),"Id Registro:"+ idResultante,Toast.LENGTH_SHORT).show();

        }
            else  Toast.makeText(getApplicationContext(),"Error:"+ idResultante,Toast.LENGTH_SHORT).show();

}
finally {
            db.close();
            onBackPressed();
        }



        }
}
