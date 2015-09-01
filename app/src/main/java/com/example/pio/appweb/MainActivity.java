package com.example.pio.appweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pio.appweb.clientserver.Client;
import com.example.pio.appweb.clientserver.Server;

public class MainActivity extends AppCompatActivity {


    private TextView textViewEvent;
    private Server server;
    private Client client;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        server = new Server(this);
        server.runServer();

        textViewEvent = (TextView) findViewById(R.id.edit_text_event);
        button = (Button) findViewById(R.id.button_send_event);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new Client("172.16.20.157", 8080, textViewEvent.getText().toString());
                client.beginConnection();
            }
        });

    }

}
