package com.example.charl.lostnfound.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charl.lostnfound.Interfaces.LostnFoundAPI;
import com.example.charl.lostnfound.Interfaces.TokenDeserializer;
import com.example.charl.lostnfound.POJOs.Users;
import com.example.charl.lostnfound.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewObject extends AppCompatActivity {

    ImageView img;
    EditText name;
    EditText desc;
    EditText date;
    EditText Usr;
    EditText Location;
    Button but;
    String Username;
    String UsrToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_object);


        Usr = findViewById(R.id.ObjUsrn);
        img = findViewById(R.id.ImgV2);
        desc = findViewById(R.id.ObjDescr);
        name = findViewById(R.id.ObjTitle);
        date = findViewById(R.id.ObjDate);
        Location = findViewById(R.id.ObjDir);
        but = findViewById(R.id.CreateObj);


        SharedPreferences sharedPref = NewObject.this.getSharedPreferences("LToken", Context.MODE_PRIVATE);
        UsrToken = sharedPref.getString("Token","");
        Username = sharedPref.getString("usuario","");

        Usr.setText(Username);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!desc.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !date.getText().toString().isEmpty() && !Location.getText().toString().isEmpty()) {
                    Send();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(NewObject.this,"Termine de Llenar los Datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Send(){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create(); //Creamos el Gson por medio de Gson builder
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LostnFoundAPI.FINISH).addConverterFactory(GsonConverterFactory.create(gson)); //La creacion de el retrofit para sus uso.
        Retrofit retrofit = builder.build();
        LostnFoundAPI Ln = retrofit.create(LostnFoundAPI.class);

        Call<String> call= Ln.add("Bearer " + UsrToken,"Imagen.png",name.getText().toString(),desc.getText().toString(),Location.getText().toString(),Username,"Correo@uca.edu,sv");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) { //Si la llamada es exitosa

                    Toast.makeText(NewObject.this,"Publicado!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {   //Si la llamda falla
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(NewObject.this,"Error de conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
