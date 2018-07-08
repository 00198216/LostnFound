package com.example.charl.lostnfound.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ModifyUser extends AppCompatActivity {

    Button button;
    EditText User;
    EditText Pass;
    EditText Mail;
    String Username;
    String UsrToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

        button=findViewById(R.id.ModifyB);
        User = findViewById(R.id.ModUsername);
        Pass= findViewById(R.id.Modpassword);
        Mail= findViewById(R.id.ModMail);

        SharedPreferences sharedPref = ModifyUser.this.getSharedPreferences("LToken", Context.MODE_PRIVATE);
        UsrToken = sharedPref.getString("Token","");
        Username = sharedPref.getString("usuario","");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!User.getText().toString().isEmpty() && !Pass.getText().toString().isEmpty() && !Mail.getText().toString().isEmpty()) {
                    Register();
                }
                else{
                    Toast.makeText(ModifyUser.this,"Termine de Llenar los Datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Register(){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LostnFoundAPI.FINISH).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        LostnFoundAPI Ln = retrofit.create(LostnFoundAPI.class);
        Users users = new Users(User.getText().toString(),Pass.getText().toString(),Mail.getText().toString());
        Call<String> call= Ln.update("Bearer " + UsrToken,users.getUser(),users.getPassword(),users.getMail(),Username);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() ){
                        Toast.makeText(ModifyUser.this,"Usuario modificdo! Por favor reiniciar sesion.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(ModifyUser.this,"Nombre en uso",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {   //Si la llamda falla
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(ModifyUser.this,"Error de conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
