package de.jackhammer.client;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public class RequestThread implements Runnable {

     private static final Logger LOGGER = Logger.getLogger(RequestThread.class);

    private BufferedWriter socketWriter;

    public RequestThread(ShellClient shellClient) throws IOException {
        this.socketWriter = shellClient.getSocketWriter();
    }

    @Override
    public void run() {
        String line = "";
        final Scanner systemIn = new Scanner(System.in );
        while( !(line = systemIn.nextLine()).equals("exit") ){
            try{
                LOGGER.info(String.format("Client CMD -> %s ", line));
                System.out.println(line);
                socketWriter.write(line );
                socketWriter.newLine();
                socketWriter.flush();
            }catch(IOException error){
                LOGGER.error("Client IO Exception ", error );
            }
        }
        LOGGER.info("Request thread end");
    }
}
