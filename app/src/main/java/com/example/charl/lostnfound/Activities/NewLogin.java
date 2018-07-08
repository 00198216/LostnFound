package com.example.charl.lostnfound.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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

public class NewLogin extends AppCompatActivity {

    Button button;
    EditText User;
    EditText Pass;
    EditText Mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        button=findViewById(R.id.Newcrear);
        User = findViewById(R.id.NewUsername);
        Pass= findViewById(R.id.Newpassword);
        Mail= findViewById(R.id.NewMail);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!User.getText().toString().isEmpty() || !Pass.getText().toString().isEmpty() || !Mail.getText().toString().isEmpty()) {
                    Register();
                }
                else{
                    Toast.makeText(NewLogin.this,"Termine de Llenar los Datos",Toast.LENGTH_SHORT).show();
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
        Call<String> call= Ln.register(users.getUser(),users.getPassword(),users.getMail());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() ){
                    Toast.makeText(NewLogin.this,"Registrado con exito!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(NewLogin.this,"Usuario Ya creado",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {   //Si la llamda falla
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(NewLogin.this,"Error de conexion",Toast.LENGTH_SHORT).show();
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
