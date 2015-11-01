package com.lipisha.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.util.Date;

/**
 * Generates Lipisha API Service.
 */
public class ServiceGenerator {

    private static final String DEFAULT_API_TYPE = "Callback";
    private static final String DEFAULT_API_VERSION = "1.3.0";

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public static <S> S createService(Class<S> serviceClass, String baseURL){
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson))
                .setEndpoint(baseURL);
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
