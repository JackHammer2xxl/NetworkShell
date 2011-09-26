package de.jackhammer.server.impl;

import de.jackhammer.server.IShellProvider;
import de.jackhammer.shell.IShell;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static de.jackhammer.conf.Config.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 02:56
 * The Server implementation.
 */
public class ShellServer implements IShellProvider {

    private static final Logger LOGGER = Logger.getLogger(ShellServer.class);

    private ServerSocket serverSocket;
    private Socket clientSocket;


    /**
     * Host Server provide the shell process.
     *
     * @param shell
     * @throws IOException
     */
    @Override
    public void hostShellAndRun(IShell shell) throws IOException {
        LOGGER.info("initialize server sockets");
        serverSocket = new ServerSocket(PORT);
        LOGGER.info("Wait for incoming client");
        clientSocket = serverSocket.accept();
        LOGGER.info(String.format("Client connected %s ", clientSocket.getInetAddress()) );

        LOGGER.info("init input streams reader");
        InputStream inputStream = clientSocket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader socketReader = new BufferedReader(inputStreamReader);

        LOGGER.info("init output stream writer");
        OutputStream outputStream = clientSocket.getOutputStream();
        final PrintWriter socketWriter = new PrintWriter(outputStream);

        LOGGER.info("start client listener and to shell writer");
        Thread shellOutputThread = new Thread(new ShellOutputThread(socketReader, shell.getShellWriter()));


        LOGGER.info("start shell listener and to client writer");
        Thread shellInputThread = new Thread(new ShellInputThread(socketWriter, shell.getShellReader()));


        LOGGER.info("start threads");
        shellOutputThread.start();
        shellInputThread.start();
    }
}
