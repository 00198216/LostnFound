package com.example.charl.lostnfound.Interfaces;

import com.example.charl.lostnfound.POJOs.Lobjects;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LostnFoundAPI {

    public static final String FINISH= "https://proyectopdm2018.herokuapp.com";

    //Metodo Post
    @FormUrlEncoded
    @POST("/login/usuario")
    Call<String> login(@Field("user") String username, @Field("password") String password);
    //                 El field de usuario              El field de password       Ambos van en String.

    //Metodo Get
    @GET("/ver/objeto")
    Call<ArrayList<Lobjects>> getObjects(@Header("Authorization") String autorizazion);
    //Llamamos el Plain Old Java Object de noticias.
}
