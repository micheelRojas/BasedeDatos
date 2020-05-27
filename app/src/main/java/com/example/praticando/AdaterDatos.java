package com.example.praticando;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.praticando.Entidades.Usuario;

import java.util.ArrayList;

public class AdaterDatos extends RecyclerView.Adapter<AdaterDatos.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Usuario> Datos;
    private View.OnClickListener listener;
    public AdaterDatos(ArrayList<Usuario> datos) {
        this.Datos = datos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.id.setText(Datos.get(position).getId().toString());
        holder.nombre.setText(Datos.get(position).getNombre());
        holder.telefono.setText((Datos.get(position).getTelefono()));


    }

    @Override
    public int getItemCount() {

        return Datos.size();
    }
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;

    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView id;
        TextView nombre;
        TextView telefono;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.id);
            nombre= itemView.findViewById(R.id.nombre);
            telefono= itemView.findViewById(R.id.telefono);

        }

      //  public void asignarDatos(Usuario datos) {
        //    dato.setText((CharSequence) datos);
        //}
    }
}
