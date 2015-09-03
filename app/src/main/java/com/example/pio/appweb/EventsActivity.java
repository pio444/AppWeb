package com.example.pio.appweb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pio.appweb.util.NotificationCaller;

import java.util.ArrayList;

/**
 * Created by pio on 03.09.15.
 */
public class EventsActivity extends AppCompatActivity {

    private ListView eventsListView;
    private ArrayList<String> eventsList;
    private ArrayAdapter<String> arrayAdapter;
    private NotificationCaller notificationCaller;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        notificationCaller = new NotificationCaller(this);

        eventsListView = (ListView)findViewById(R.id.list_view_events);

        myApplication = (MyApplication)getApplication();
        eventsList = myApplication.getEventsList();

        arrayAdapter = new ArrayAdapter<>(this,R.layout.activity_events,eventsList);

    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            arrayAdapter.notifyDataSetChanged();
            eventsListView.invalidateViews();
        }

    }


}
