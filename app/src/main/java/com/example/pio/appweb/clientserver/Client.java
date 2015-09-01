package com.example.pio.appweb.clientserver;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by pio on 12.08.15.
 */
public final class Client  {

    private String hostName;
    private int port;
    private String messageToServer;
    private String response = "";

    public Client(String hostName, int port, String messageToServer){
        this.hostName = hostName;
        this.port = port;
        this.messageToServer = messageToServer;


    }
    public String getResponseFromServer(){
        if (response==""){
            return "There is no data back from Server. Try again or check your connection";
        }
        else
            return response;

    }
    public void beginConnection(){
        MyClientTask myClientTask = new MyClientTask(hostName, port,
                messageToServer);
        myClientTask.execute();
    }
    private class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String msgToServer;

        MyClientTask(String addr, int port, String msgTo) {
            dstAddress = addr;
            dstPort = port;
            msgToServer = msgTo;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;

            try {
                socket = new Socket(dstAddress, dstPort);
                dataOutputStream = new DataOutputStream(
                        socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());

                if (msgToServer != null) {
                    dataOutputStream.writeUTF(msgToServer);

                }
                //
                response = dataInputStream.readUTF();


            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
