package de.jackhammer.server;

import de.jackhammer.shell.IShell;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 06:58
 */
public interface IShellProvider {


    public abstract void hostShellAndRun(IShell shell) throws IOException;
}
