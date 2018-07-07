package com.example.charl.lostnfound.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.POJOs.LostFavorite;
import com.example.charl.lostnfound.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavLostViewHolder> {

public ArrayList<LostFavorite> Lost; //Creamos un arrayList de objetos perdidos
        Context Contxt;

public static class FavLostViewHolder extends RecyclerView.ViewHolder {
    CardView card;
    TextView name;
    TextView Sname;
    ImageView img;
    Context cxt;


    //Donde inicializamos los objetos
    public FavLostViewHolder(View itemView) {
        super(itemView);
        cxt = itemView.getContext();
        card = itemView.findViewById(R.id.card_view2);
        name = itemView.findViewById(R.id.name2);
        Sname = itemView.findViewById(R.id.Subname2);
        img = itemView.findViewById(R.id.img2);
    }


}

    public FavAdapter(ArrayList<LostFavorite> Lost, Context con) {
        this.Lost = Lost;
        Contxt = con;
    }

    @NonNull
    @Override
    public FavAdapter.FavLostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_user, parent, false);
        return (new FavAdapter.FavLostViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.FavLostViewHolder holder, final int position) {
        holder.name.setText(Lost.get(position).getNombre());//Holder para el nombre
        holder.Sname.setText(Lost.get(position).getDescripcion()); //Holder para el subtitulo

        //Cargando la imagen via Picasso
        if (!(Lost.get(position).getImagen() == null) &&
                Lost.get(position).getImagen().length() > 20) {
            Picasso.with(holder.cxt).load(Lost.get(position).getImagen()).error(R.drawable.lost).into(holder.img);
        } else {
            Picasso.with(holder.cxt).load(R.drawable.lost).error(R.drawable.lost).into(holder.img);
        }

    }

    @Override
    public int getItemCount() {
        return Lost.size();
    }
}
