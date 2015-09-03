package com.example.pio.appweb.internet;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by pio on 10.08.15.
 */
public interface WebAPI {

    String baseURL = "http://172.16.20.179:3000";

    @POST("/api/events")
    void sendEvent(@Body Event event, Callback<Event> response);


}
