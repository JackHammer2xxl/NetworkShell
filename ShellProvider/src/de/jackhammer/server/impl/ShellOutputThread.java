package de.jackhammer.server.impl;

import de.jackhammer.shell.IShell;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 04:11
 * To change this template use File | Settings | File Templates.
 */
public class ShellOutputThread implements Runnable{

     private static final Logger LOGGER = Logger.getLogger(ShellOutputThread.class);

    private final BufferedReader socketReader;
    private PrintWriter shellWriter;


    protected ShellOutputThread(final BufferedReader socketReader, final PrintWriter shellWriter) {
        this.shellWriter  = shellWriter;
        this.socketReader = socketReader;
    }


    @Override
    public void run() {
        String line = "";
        try{
            while((line = socketReader.readLine()) != null ){
                LOGGER.info(String.format("Client: %s", line));
                shellWriter.print(line + "\r\n");
                shellWriter.flush();
            }

         }catch(IOException error){
            LOGGER.error("IOException ", error );
        }
    }
}
