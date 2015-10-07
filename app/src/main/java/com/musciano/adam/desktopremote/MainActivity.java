package com.musciano.adam.desktopremote;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends ActionBarActivity {
    private String ip;
    private RemoteConnection control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnConnect= (Button)findViewById(R.id.btnConnect);
        final Button btnShutdown= (Button)findViewById(R.id.btnShutdown);
        final Button btnPandora= (Button)findViewById(R.id.btnPandora);
        final Button btnLogOff= (Button)findViewById(R.id.btnLogOff);

        btnLogOff.setEnabled(false);
        btnShutdown.setEnabled(false);
        btnPandora.setEnabled(false);


        final TextView txtIP= (TextView)findViewById(R.id.txtViewIP);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtIP.getWindowToken(), 0);
                String ips[]= {ip};
                if(!txtIP.getText().toString().isEmpty()){
                  String connectionStatus;
                 ServerConnections connect = new ServerConnections();//Here is the start of the asynctask
                    //connect.execute(ips);
                    connectionStatus= connect.doInBackground(ips);
                    ip=txtIP.getText().toString();
                    Toast.makeText(getApplicationContext(),ip,Toast.LENGTH_SHORT).show();


                    if(connectionStatus.equals("error")){
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }else if(connectionStatus.equals("Unsuccessful Connection")){
                        Toast.makeText(getApplicationContext(),connectionStatus,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),connectionStatus,Toast.LENGTH_SHORT).show();
                        btnLogOff.setEnabled(true);
                        btnPandora.setEnabled(true);
                        btnShutdown.setEnabled(true);
                        btnConnect.setEnabled(false);
                    }
                }
            }

        });

        btnLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control.sendCommand("logoff");
            }
        });

        btnPandora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control.sendCommand("pandora");
            }
        });

        btnShutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { control.sendCommand("shutdown"); }
        });

    }


}
