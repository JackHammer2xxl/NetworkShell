package de.jackhammer.client;

import org.apache.log4j.Logger;
import org.omg.CORBA.Request;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import static de.jackhammer.conf.Config.*;
/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 10:01
 */
public class ShellClient  {

     private static final Logger LOGGER = Logger.getLogger(ShellClient.class);

    private Socket clientSocket;

    public boolean startRemote() throws IOException {

        boolean isConnected;
        this.clientSocket = new Socket(SHELL_IP, PORT );

        if((isConnected = clientSocket.isConnected()) ){
            LOGGER.info("Success connected to shell ");
            Thread responseThread = new Thread(new ResponseThread(this) );
            Thread requestThread  = new Thread(new RequestThread(this) );

            responseThread.start();
            requestThread.start();
            return true;
        }else{
            return false;
        }


    }


    public BufferedWriter getSocketWriter() throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream );

        return new BufferedWriter(outputStreamWriter);
    }

    public BufferedReader getShellReader() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        return new BufferedReader(inputStreamReader);
    }


}
