package de.jackhammer.client;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 10:11
 * To change this template use File | Settings | File Templates.
 */
public class ResponseThread implements Runnable {

     private static final Logger LOGGER = Logger.getLogger(ResponseThread.class);

    private BufferedReader shellReader;

    public ResponseThread(ShellClient shellClient) throws IOException{
        this.shellReader = shellClient.getShellReader();
    }

    @Override
    public void run() {
        String line = "";
        try{
            while((line = shellReader.readLine()) != null ){
                LOGGER.debug(String.format("SHELL -> %s ", line));
                System.out.println(line);
            }
        }catch(IOException error){
            LOGGER.error("Client IO Exception", error );
        }
        LOGGER.info("Response thread end");
    }
}
