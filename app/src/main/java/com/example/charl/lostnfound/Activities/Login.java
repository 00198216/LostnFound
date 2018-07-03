package com.example.charl.lostnfound.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {

    Button button;
    EditText User;
    EditText Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button=findViewById(R.id.enviar);
        User = findViewById(R.id.email);
        Pass= findViewById(R.id.password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!User.getText().toString().isEmpty() || !Pass.getText().toString().isEmpty()) {
                    FindToken();
                }
                else{
                    Toast.makeText(Login.this,"Termine de Llenar los Datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Esta funcion se encargara de evaluar el tocken.
    private void FindToken(){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create(); //Creamos el Gson por medio de Gson builder
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LostnFoundAPI.FINISH).addConverterFactory(GsonConverterFactory.create(gson)); //La creacion de el retrofit para sus uso.
        Retrofit retrofit = builder.build(); //Inicializamos el Retrofit.
        LostnFoundAPI Ln = retrofit.create(LostnFoundAPI.class); //Se manda la info a la API.
        Users users = new Users(User.getText().toString(),Pass.getText().toString()); //Creamos un nuevo usuario.
        Call<String> call= Ln.login(users.getUser(),users.getPassword()); //inicializamos Call
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) { //Si la llamada es exitosa
                if(response.isSuccessful() && !response.body().equals("")){
                    sharedpreferences(response.body(),User.getText().toString(),Pass.getText().toString());
                    Toast.makeText(Login.this,response.body(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Login.this,"Usuario Erroneo",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {   //Si la llamda falla
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(Login.this,"false",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Mediante SharedPreferences guardamos la informacion
    private void sharedpreferences(String token,String Usr,String Pass){
        SharedPreferences sharedPreferences = this.getSharedPreferences("LToken", Context.MODE_PRIVATE); //Inicializamos el SharedPreference
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",token);
        editor.putString("usuario", Usr);
        editor.putString("password", Pass);
        //Ya que queremos el proceso se quiere que trabaje en background usaremos apply(Asynchrono). De lo contrario se usaria commit()(synchrono).
        editor.apply();
    }
}
