package com.example.charl.lostnfound.POJOs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Lost_Table") //El nombre que le daremos a nuestra tabla.
public class Lobjects {

    @NonNull                   // Para evitar campos nulos
    @PrimaryKey
    //(autoGenerate = true) Esto es si hubieramos querido autogenerar cada id. En este caso pues, ya vienen con las noticias
    @ColumnInfo(name = "Id") // Id del objeto perdido
    private String _id;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "Imagen") //Imagen del objeto perdido
    private String imagen;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "Name") // Nombre del objeto perdido
    private String nombre;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "Description") //Descripcion del objeto perdido
    private String descripcion;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "Location") //Lugar encontrado
    private String direccion;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "Date") //Fecha de la noticia sobre el objeto perdido
    private String fecha;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "User") //Usuario de la noticia
    private String usuario;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "Mail") //Correo del Usuario
    private String correo;

    //@NonNull Puede que este vacio y no es vital como la primary key
    @ColumnInfo(name = "Version") //Correo del Usuario
    private int __v;

    public Lobjects() {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
