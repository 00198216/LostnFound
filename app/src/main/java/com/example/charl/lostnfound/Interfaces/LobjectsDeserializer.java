package com.example.charl.lostnfound.Interfaces;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class LobjectsDeserializer implements JsonDeserializer<Lobjects> {

    public Lobjects Lost; // Creamos un objeto tipo news donde la informacion sera cargada.
    @Override
    public Lobjects deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Lost = new Lobjects(); //Inicializamos el objecto perdido.

        JsonObject jsonObject = json.getAsJsonObject(); //Usaremos Objects

        Lost.set_id(jsonObject.get("_id").getAsString());
        Lost.setImagen(jsonObject.get("imagen").getAsString());
        Lost.setNombre(jsonObject.get("nombre").getAsString());
        Lost.setDescripcion(jsonObject.get("descripcion").getAsString());
        Lost.setDireccion(jsonObject.get("direccion").getAsString());
        Lost.setFecha(jsonObject.get("fecha").getAsString());
        Lost.setUsuario(jsonObject.get("usuario").getAsString());
        Lost.setCorreo(jsonObject.get("correo").getAsString());

        return Lost;
    }
}
