package com.example.charl.lostnfound.POJOs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "Favorite_Table")
public class LostFavorite implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Id")
    private String _id;

    @ColumnInfo(name = "Imagen")
    private String imagen;


    @ColumnInfo(name = "Name")
    private String nombre;


    @ColumnInfo(name = "Description")
    private String descripcion;


    @ColumnInfo(name = "Location")
    private String direccion;


    @ColumnInfo(name = "Date")
    private String fecha;

    @ColumnInfo(name = "User")
    private String usuario;

    @ColumnInfo(name = "State")
    private Boolean recuperado;

    @ColumnInfo(name = "FavUser")
    private String User;

    public LostFavorite() {
    }

    public LostFavorite(String _id, String imagen, String nombre, String descripcion, String direccion, String fecha, String usuario, Boolean recuperado, String user) {
        this._id = _id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.fecha = fecha;
        this.usuario = usuario;
        this.recuperado = recuperado;
        User = user;
    }

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getRecuperado() {
        return recuperado;
    }

    public void setRecuperado(Boolean recuperado) {
        this.recuperado = recuperado;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }
}
