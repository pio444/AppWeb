package com.example.pio.appweb;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pio.appweb.internet.RetrofitHandler;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {

    private Button buttonSendEvent;
    private Button buttonCheckForEvents;
    private EditText editTextEven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEven = (EditText)findViewById(R.id.edit_text_event);
        buttonSendEvent = (Button) findViewById(R.id.button_send_event);
        buttonCheckForEvents = (Button)findViewById(R.id.button_check_events);

        final RetrofitHandler retrofitHandler = new RetrofitHandler(this);

        buttonSendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitHandler.sendMessage("5", editTextEven.getText().toString());
            }
        });

        buttonCheckForEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EventsActivity.class);
                startActivity(i);
            }
        });



    }

}
