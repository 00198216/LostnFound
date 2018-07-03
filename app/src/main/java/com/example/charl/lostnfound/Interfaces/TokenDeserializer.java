package com.example.charl.lostnfound.Interfaces;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TokenDeserializer implements JsonDeserializer<String> {

    public String token = ""; // El token a recibir.

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        System.out.println(json.getAsJsonObject()); // Muestra en consola el objeto Json.

        if(json.getAsJsonObject()!=null){           //Revisa si el Json esta vacio o no
            JsonObject Token = json.getAsJsonObject();  //Crea un objeto tipo Json Object
            token= Token.get("token").getAsString();    //Se pasa a el objeto a String
        }
        return token; //retornamos el token
    }
}
