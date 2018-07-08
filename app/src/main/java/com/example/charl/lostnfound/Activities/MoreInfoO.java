package com.example.charl.lostnfound.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.POJOs.LostFavorite;
import com.example.charl.lostnfound.R;
import com.squareup.picasso.Picasso;

public class MoreInfoO extends AppCompatActivity {

    ImageView img;
    TextView name;
    TextView desc;
    TextView date;
    TextView Usr;
    TextView Location;
    String Choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_o);


        Usr = findViewById(R.id.NUser);
        img = findViewById(R.id.ImgV);
        desc = findViewById(R.id.NDesc);
        name = findViewById(R.id.Ntitulo);
        date = findViewById(R.id.NFecha);
        Location = findViewById(R.id.NLocal);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        SharedPreferences estado = getApplicationContext().getSharedPreferences("State", Context.MODE_PRIVATE);

        Choice = estado.getString("chosen", "");

        if (Choice.contains("All") || Choice.contains("Own") ) {

            final Lobjects newL = (Lobjects) bundle.getSerializable("info");

            if (!(newL.getImagen() == null)) {
                Picasso.with(getApplicationContext()).load(newL.getImagen()).error(R.drawable.lost).into(img);
            } else {
                Picasso.with(getApplicationContext()).load(R.drawable.lost).error(R.drawable.lost).into(img);
            }

            name.setText(newL.getNombre());
            desc.setText(newL.getDescripcion());
            Usr.setText(newL.getUsuario());
            Location.setText(newL.getDireccion());
            date.setText(newL.getFecha());


        }

        if (Choice.contains("Fav")) {

            final LostFavorite newL2 = (LostFavorite) bundle.getSerializable("info2");

            if (!(newL2.getImagen() == null)) {
                Picasso.with(getApplicationContext()).load(newL2.getImagen()).error(R.drawable.lost).into(img);
            } else {
                Picasso.with(getApplicationContext()).load(R.drawable.lost).error(R.drawable.lost).into(img);
            }

            name.setText(newL2.getNombre());
            desc.setText(newL2.getDescripcion());
            Usr.setText(newL2.getUsuario());
            Location.setText(newL2.getDireccion());
            date.setText(newL2.getFecha());


        }

    }
}
