package com.example.charl.lostnfound.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charl.lostnfound.Activities.Login;
import com.example.charl.lostnfound.Activities.MoreInfoO;
import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.POJOs.LostFavorite;
import com.example.charl.lostnfound.R;
import com.example.charl.lostnfound.Room.DAOs.FavDAO;
import com.example.charl.lostnfound.Room.DAOs.LostDAO;
import com.example.charl.lostnfound.Room.Database.LostnFoundDatabase;
import com.example.charl.lostnfound.Room.Repositories.LostRepository;
import com.example.charl.lostnfound.Room.ViewModels.LostViewModels;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LostAdapter extends RecyclerView.Adapter<LostAdapter.LostViewHolder> {
    public ArrayList<Lobjects> Lost; //Creamos un arrayList de objetos perdidos
    Context Contxt;



    public static class LostViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView name;
        TextView Sname;
        ImageView img;
        Context cxt;
        CheckBox star;



        //Donde inicializamos los objetos
        public LostViewHolder(View itemView) {
            super(itemView);
            cxt = itemView.getContext();
            card = itemView.findViewById(R.id.card_view);
            name = itemView.findViewById(R.id.name);
            Sname = itemView.findViewById(R.id.Subname);
            img = itemView.findViewById(R.id.img);
            star = itemView.findViewById(R.id.star);
        }


    }


    public LostAdapter(ArrayList<Lobjects> Lost, Context con) {
        this.Lost = Lost;
        Contxt = con;
    }

    @NonNull
    @Override
    public LostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview, parent, false);
        return (new LostAdapter.LostViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull final LostViewHolder holder, final int position) {
        holder.name.setText(Lost.get(position).getNombre());//Holder para el nombre
        holder.Sname.setText(Lost.get(position).getDescripcion()); //Holder para el subtitulo

        //Cargando la imagen via Picasso
        if (!(Lost.get(position).getImagen() == null) &&
                Lost.get(position).getImagen().length() > 20) {
            Picasso.with(holder.cxt).load(Lost.get(position).getImagen()).error(R.drawable.lost).into(holder.img);
        } else {
            Picasso.with(holder.cxt).load(R.drawable.lost).error(R.drawable.lost).into(holder.img);
        }

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Contxt, MoreInfoO.class);
                Bundle caja = new Bundle();
                caja.putSerializable("info",Lost.get(position));
                newIntent.putExtras(caja);
                Contxt.startActivity(newIntent);
            }
        });

        //En el titulo:

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Contxt, MoreInfoO.class);
                Bundle caja = new Bundle();
                caja.putSerializable("info", Lost.get(position));
                newIntent.putExtras(caja);
                Contxt.startActivity(newIntent);
            }
        });

        //En el body:

        holder.Sname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Contxt, MoreInfoO.class);
                Bundle caja = new Bundle();
                caja.putSerializable("info",Lost.get(position));
                newIntent.putExtras(caja);
                Contxt.startActivity(newIntent);
            }
        });

        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class AsyncTaskI extends AsyncTask<ArrayList<LostFavorite>, Void, Void> {

                    private FavDAO lostDao;

                    AsyncTaskI() {
                    }

                    @Override
                    protected Void doInBackground(ArrayList<LostFavorite>... arrayLists) {
                        LostFavorite fav;
                        SharedPreferences SavedLogin;
                        String Name;
                        LostnFoundDatabase db = LostnFoundDatabase.getAppDataBase(holder.cxt);


                        SavedLogin = holder.cxt.getSharedPreferences("LToken", Context.MODE_PRIVATE);
                        Name = SavedLogin.getString("usuario", "");

                        if (!(Lost.get(position).getImagen() == null)) {
                            fav = new LostFavorite(Lost.get(position).get_id(),Lost.get(position).getImagen(), Lost.get(position).getNombre(), Lost.get(position).getDescripcion(), Lost.get(position).getDireccion(), Lost.get(position).getFecha(), Lost.get(position).getUsuario(), Lost.get(position).getRecuperado(), Name);
                            FavDAO fave;
                            fave = db.favDAO();
                            fave.insertLostFav(fav);
                        } else {
                            fav = new LostFavorite(Lost.get(position).get_id()," ", Lost.get(position).getNombre(), Lost.get(position).getDescripcion(), Lost.get(position).getDireccion(), Lost.get(position).getFecha(), Lost.get(position).getUsuario(), Lost.get(position).getRecuperado(), Name);
                            FavDAO fave;
                            fave = db.favDAO();
                            fave.insertLostFav(fav);
                        }


                        return null;
                    }


                }
                new AsyncTaskI().execute();
            }



        });


    }



    @Override
    public int getItemCount() {
        return Lost.size();
    }
}
