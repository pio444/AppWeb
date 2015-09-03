package com.example.pio.appweb;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;

import com.saulpower.fayeclient.FayeClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by pio on 03.09.15.
 */
public class MyApplication extends Application {

    private ArrayList<String> eventsList;
    private FayeClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        eventsList = new ArrayList<>();
        URI uri = URI.create(String.format("http://%s:9292/faye", "172.16.20.179"));
        String channel = "/spy";

        client = new FayeClient(new Handler(), uri, channel);
        client.setFayeListener(new FayeClient.FayeListener() {
            @Override
            public void connectedToServer() {

            }

            @Override
            public void disconnectedFromServer() {

            }

            @Override
            public void subscribedToChannel(String subscription) {

            }

            @Override
            public void subscriptionFailedWithError(String error) {

            }

            @Override
            public void messageReceived(JSONObject json) {
                try {
                    getEventsList().add(json.getString("event_name"));
                    Intent i = new Intent("com.example.pio.AppWeb.SEND_NEW_DATA_EVENT");
                    sendBroadcast(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        client.connectToServer(new JSONObject());
    }
    public ArrayList<String>  getEventsList(){
        return eventsList;
    }
}
