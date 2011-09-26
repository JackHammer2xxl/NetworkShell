package de.jackhammer.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.AbstractExecutorService;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 03:39
 * To change this template use File | Settings | File Templates.
 */
public interface IShell {

    /**
     * Write to the shell process.
     *
     * @return
     */
    public abstract PrintWriter getShellWriter();

    /**
     * Read from the shell process.
     *
     * @return
     */
    public abstract BufferedReader getShellReader();

    /**
     * Process shell terminated.
     */
    public abstract void shellExit();

    /**
     * Returns the input stream connected to the error output of the shell.
     * @return
     */
    public abstract InputStream getErrorStream();


}
