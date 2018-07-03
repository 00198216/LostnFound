package com.example.charl.lostnfound.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.charl.lostnfound.R;

public class LostnFound_SplashScreen extends AppCompatActivity {

    ImageView foto;
    private SharedPreferences SavedLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_lostn_found__splash_screen);

        SavedLogin = getSharedPreferences("LToken", Context.MODE_PRIVATE); //Jalamos los datos


        foto= findViewById(R.id.LogoSS);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Login = new Intent(getApplicationContext(), Login.class);
                Intent Main = new Intent(getApplicationContext(), MainActivity.class);

                if (TextUtils.isEmpty(SavedLogin.getString("usuario", "")) &&
                        TextUtils.isEmpty(SavedLogin.getString("password", ""))
                        && TextUtils.isEmpty(SavedLogin.getString("Token", ""))) {

                    Login.setFlags(Login.FLAG_ACTIVITY_NEW_TASK | Login.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(Login);

                } else {
                    Main.setFlags(Main.FLAG_ACTIVITY_NEW_TASK | Main.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(Main);
                }
            }
        });


    }
}
