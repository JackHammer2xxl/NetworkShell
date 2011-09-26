package de.jackhammer.shell.impl;

import de.jackhammer.shell.IShell;
import org.apache.log4j.Logger;

import java.io.*;


import static de.jackhammer.conf.Config.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 02:28
 * CLI windows shell.
 */
public class ShellWrapper implements IShell {

    private static final Logger LOGGER = Logger.getLogger(ShellWrapper.class);

    private Process shellProcess;


    /**
     * Init the shell process.
     *
     * @throws IOException
     */
    public ShellWrapper() throws IOException {
        LOGGER.info(String.format("initialize runtime and execute shell %s", SHELL_NAME));
        final Runtime runtime = Runtime.getRuntime();
        shellProcess = runtime.exec(SHELL_NAME);
    }

    @Override
    public PrintWriter getShellWriter() {
        OutputStream outputStream = shellProcess.getOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
        PrintWriter printWriter = new PrintWriter(streamWriter);

        return printWriter;
    }

    @Override
    public BufferedReader getShellReader() {
        InputStream inputStream = shellProcess.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        return bufferedReader;
    }

    @Override
    public InputStream getErrorStream() {
        return shellProcess.getErrorStream();
    }

    @Override
    public void shellExit() {
        LOGGER.info("Shell terminated");
        shellProcess.destroy();
    }
}
