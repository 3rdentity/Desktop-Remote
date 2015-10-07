package com.musciano.adam.desktopremote;

import android.os.AsyncTask;

/**
 * Created by adam on 3/16/2015.
 */
public class ServerConnections extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        /**
         * show dialog
         */
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... ip) {
        RemoteConnection control= new RemoteConnection(ip[0]);//calls remote connection
        String connectionStatus=control.connect();

        return connectionStatus;
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
