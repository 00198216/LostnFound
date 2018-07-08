package com.example.charl.lostnfound.POJOs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users {



        private String Token; //Token unico que confirma el Login
        private String user; //Username
        private String password; //Contraseña.
        private String correo;

        //Constructor

        public Users(String user, String password){
            this.user = user;
            this.password = password;
        }

    public Users(String user, String password, String correo){
        this.user = user;
        this.password = password;
        this.correo = correo;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return correo;
    }

    public void setMail(String mail) {
        this.correo = mail;
    }
}
