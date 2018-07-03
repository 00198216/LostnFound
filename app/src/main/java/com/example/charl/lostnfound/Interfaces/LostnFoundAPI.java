package com.example.charl.lostnfound.Interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LostnFoundAPI {

    public static final String FINISH= "https://proyectopdm2018.herokuapp.com";

    //Metodo Post
    @FormUrlEncoded
    @POST("/login/usuario")
    Call<String> login(@Field("user") String username, @Field("password") String password);
    //                 El field de usuario              El field de password       Ambos van en String.
}
