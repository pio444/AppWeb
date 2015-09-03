package com.example.pio.appweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pio.appweb.internet.WebSocketForEvent;
import com.example.pio.appweb.util.NotificationCaller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pio on 03.09.15.
 */
public class EventsActivity extends AppCompatActivity implements EventListener {

    private ListView eventsListView;
    private ArrayList<String> eventsList;
    private ArrayAdapter<String> arrayAdapter;
    private NotificationCaller notificationCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        notificationCaller = new NotificationCaller(this);

        eventsListView = (ListView)findViewById(R.id.list_view_events);


        WebSocketForEvent webSocketForEvent = new WebSocketForEvent(this);
        webSocketForEvent.listenOnEvents();

        eventsList = new ArrayList<>();
        eventsList.add("");

    }

    @Override
    public void getEventsData(JSONObject jsonObject) {
        try {
            notificationCaller.setTitleAndContent("New notification", jsonObject.getString("event_name"));
            notificationCaller.showNotification();
            eventsList.add(jsonObject.getString("event_name"));
            arrayAdapter.notifyDataSetChanged();
            eventsListView.invalidateViews();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
