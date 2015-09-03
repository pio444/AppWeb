package com.example.pio.appweb.internet;

import android.os.Handler;
import android.util.Log;

import com.example.pio.appweb.EventListener;
import com.saulpower.fayeclient.FayeClient;

import org.json.JSONObject;

import java.net.URI;

/**
 * Created by pio on 02.09.15.
 */
public class WebSocketForEvent implements FayeClient.FayeListener {


    private EventListener eventListener;
    private FayeClient client;

    public WebSocketForEvent(EventListener eventListener) {

        this.eventListener = eventListener;
        URI uri = URI.create(String.format("http://%s:9292/faye", "172.16.20.179"));
        String channel = "/spy";

        client = new FayeClient(new Handler(), uri, channel);
        client.setFayeListener(this);

    }

    public void listenOnEvents(){
        client.connectToServer(new JSONObject());
    }

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

        eventListener.getEventsData(json);
        Log.i("received", String.format("Received message %s", json.toString()));
    }

}
