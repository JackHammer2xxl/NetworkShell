package de.jackhammer.main;


import de.jackhammer.server.IShellProvider;
import de.jackhammer.server.impl.ShellServer;
import de.jackhammer.shell.IShell;
import de.jackhammer.shell.impl.ShellWrapper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

import java.io.IOException;

import static de.jackhammer.conf.Config.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 02:22
 * Start new shell provider.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);


    public static void main(String[] args) throws IOException {
        //BasicConfigurator.configure();
        PropertyConfigurator.configure(LOG_CONFIG);

        if (!isOperationSystemWindows())
            LOGGER.error("Shell provider works currently only on windows OS" );
        else
            runShellServer();

    }

    private static void runShellServer() throws IOException {
        LOGGER.info("Start shell server ");
        IShell shell = new ShellWrapper();
        IShellProvider provider = new ShellServer();
        provider.hostShellAndRun(shell);
    }


}
