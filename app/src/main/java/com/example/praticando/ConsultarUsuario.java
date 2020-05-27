package com.example.praticando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.praticando.Utilidades.Utilidades;

public class ConsultarUsuario extends AppCompatActivity {
    EditText campoId, campoNombre , campoTelefono;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        conn = new ConexionSQLiteHelper(this,"db_usuarios",null,1);
        campoId = (EditText) findViewById(R.id.id_con);
        campoNombre = (EditText) findViewById(R.id.nombre_con);
        campoTelefono = (EditText) findViewById(R.id.telefono_con);

        Bundle bundle = this.getIntent().getExtras();
        if( bundle != null){
            Integer id = bundle.getInt("id");
            String nombre= bundle.getString("nombre");
            String telefono =bundle.getString("telefono");

            campoId.setText(id.toString());
            campoNombre.setText(nombre);
            campoTelefono.setText(telefono);
        }
    }
    public  void  consultar(View vie){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametro ={campoId.getText().toString()};
        String [] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametro,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documeto no existe",Toast.LENGTH_SHORT).show();
            limpiar();
        }finally {
            db.close();
        }


    }

    private void limpiar() {
        campoNombre.setText("");
        campoTelefono.setText("");
    }

    public void eliminar(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametro ={campoId.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametro);
        Toast.makeText(getApplicationContext(),"ya se elimino",Toast.LENGTH_LONG).show();
        campoId.setText("");
        limpiar();
        db.close();
    }

    public void moodificar(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametro ={campoId.getText().toString()};
        ContentValues contentValues = new ContentValues();

        contentValues.put(Utilidades.CAMPO_NOMBRE , campoNombre.getText().toString());
        contentValues.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());
         db.update(Utilidades.TABLA_USUARIO,contentValues,Utilidades.CAMPO_ID+"=?",parametro);
        Toast.makeText(getApplicationContext(),"ya se actualizo",Toast.LENGTH_LONG).show();
        db.close();
    }
}
