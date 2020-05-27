package com.example.praticando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.praticando.Entidades.Usuario;
import com.example.praticando.Utilidades.Utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Usuario> Datos;
    RecyclerView recycler;
    ConexionSQLiteHelper conn;
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
       // displayDatabaseInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn=new ConexionSQLiteHelper(this,"db_usuarios",null,1);
        Datos= new ArrayList<>();
        recycler= findViewById(R.id.Recyclerid);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ConsultarLista();
      AdaterDatos adater = new AdaterDatos(Datos);
      adater.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Integer id =  Datos.get(recycler.getChildAdapterPosition(v)).getId();
            String nombre = Datos.get(recycler.getChildAdapterPosition(v)).getNombre();
            String telefono=  Datos.get(recycler.getChildAdapterPosition(v)).getTelefono();
              Intent intent = new Intent(MainActivity.this, ConsultarUsuario.class);
              Bundle  bundle = new Bundle();
                     bundle.putInt("id",id);
                     bundle.putString("nombre",nombre);
                     bundle.putString("telefono",telefono);
              intent.putExtras(bundle);
              startActivity(intent);
          }
      });
      recycler.setAdapter(adater);

    }

    private void ConsultarLista() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuario= null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO,null);
        while (cursor.moveToNext()){
            usuario= new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            Datos.add(usuario);

        }
        db.close();
    }


    public void Registro(View view) {
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.button_registrar:
                miIntent= new Intent(MainActivity.this, RegistroUsuariosActivity.class);
                break;

        }
        if (miIntent != null){
           startActivity(miIntent);
        }

        //Intent intent = new Intent(this, RegistroUsuariosActivity.class);

    }
    public  void Consulta (View view){


        startActivity(new Intent(MainActivity.this,ConsultarUsuario.class));

    }

}
