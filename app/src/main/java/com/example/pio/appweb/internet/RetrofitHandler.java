package com.example.pio.appweb.internet;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by pio on 10.08.15.
 */
public class RetrofitHandler {


    private Context context;
    private WebAPI webAPI;

    public RetrofitHandler(Context context) {

        this.context = context;
        initRetrofit();

    }

    private void initRetrofit() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(WebAPI.baseURL).setLogLevel(RestAdapter.LogLevel.FULL).
                setConverter(new GsonConverter(new GsonBuilder().create())).build();
        webAPI = restAdapter.create(WebAPI.class);

    }

    public void sendMessage(String id, String name){

        Event event = new Event(id,name);

        webAPI.sendEvent(event, new Callback<Event>() {
            @Override
            public void success(Event event, Response response) {
                response.getHeaders();
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }
}
