package com.juanchango.data.suppliers.cache.serializer;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Json Serializer/De-Serializer
 * It is Singleton
 */
@Singleton
public class Serializer {

    private final Gson gson = new Gson();
    private static Serializer instance;

    @Inject
    Serializer() {
    }

    public static Serializer getInstance(){
        if(instance==null){
            instance = new Serializer();
        }
        return instance;
    }

    /**
     * Serialize an object to Json.
     *
     * @param object Object to serialize.
     * @return Json String.
     */
    public String serialize(Object object, Class clazz){
        return gson.toJson(object, clazz);
    }

    /**
     * Deserialize an Json string.
     *
     * @param string Json string to deserialize.
     */
    public <T> T deserialize(String string, Class<T> clazz){
        return gson.fromJson(string, clazz);
    }
}
