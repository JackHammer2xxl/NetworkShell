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
public class ShellInputThread implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ShellInputThread.class);

    private final PrintWriter socketWriter;
    private final BufferedReader shellReader;


    protected ShellInputThread(final PrintWriter socketWriter, final BufferedReader shellReader) {
        this.shellReader = shellReader;
        this.socketWriter = socketWriter;
    }


    @Override
    public void run() {
        String line = "";
        try {

            while ((line = shellReader.readLine()) != null) {
                LOGGER.info(String.format("Shell: %s", line));
                socketWriter.print(line + "\r\n");
                socketWriter.flush();
            }

        } catch (IOException error) {
            LOGGER.error("IOException ", error);
        }
        LOGGER.warn("Output thread ends");
    }
}
