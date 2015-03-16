package com.musciano.adam.desktopremote;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

public class RemoteConnection {
    private Socket socket;
    private String ipAddress;
    private PrintWriter out;
    private BufferedReader in;

    public RemoteConnection(String ipAddress){
            this.ipAddress= ipAddress;
        }

    public String connect(){
        boolean ready = false;
        int x=0;
        while(!ready){
            try{
                socket = new Socket(ipAddress,9898);
                ready= true;
            }catch(Exception e){
                if(x==10){
                    return "Unsuccessful Connection";
                }
                ready = false;
                x++;
                System.out.println("Failed Connection...");
            }
        }
        try{
            this.in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out= new PrintWriter(socket.getOutputStream(),true);

        }catch(Exception e){
            return "error";
        }
        return ("Successful Connection to: "+ipAddress+" at: "+ new Date());
    }

    public void sendCommand(String command){
        out.println(command);
    }
    public String readResponse(){
        try{
            return in.readLine();
        }catch(Exception e){

            System.exit(0);
            return null;
        }
    }
}


