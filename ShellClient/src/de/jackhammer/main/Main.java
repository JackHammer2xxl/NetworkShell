package de.jackhammer.main;

import de.jackhammer.client.ShellClient;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static de.jackhammer.conf.Config.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 08:20
 * Shell client.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);


    public static void main(String... args) {
        //BasicConfigurator.configure();
        PropertyConfigurator.configure(LOG_CONFIG );
        //PropertyConfigurator.configureAndWatch(LOG_CONFIG, DELAY);
        LOGGER.info(String.format("Load logger config from %s ", LOG_CONFIG));

        if (args == null || args.length < 2) {
            LOGGER.info("Syntax error type -help or Shell.exe -h [-p optional] ");
            LOGGER.info("Sample: shell.exe -a 127.0.0.1 -p 6947");
        } else {

            loadCommandLineArgs(args);

            final ShellClient shellClient = new ShellClient();

            try {
                shellClient.startRemote();
            } catch (IOException error) {
                LOGGER.error("Connection refused", error);
            }
        }
    }

    private static void loadCommandLineArgs(String[] args) {
        final List<String> cmdParams = Arrays.asList(args);

        if (cmdParams.contains("-help")) {
            LOGGER.info("Currently no help support implemented.");
        }

        SHELL_IP = getCLIValueFromKey("-a", cmdParams);
        LOGGER.info(String.format("SET address   -> %s ", SHELL_IP));
        PORT = Integer.valueOf(getCLIValueFromKey("-p", cmdParams));
        LOGGER.info(String.format("SET port      -> %s ", PORT));


    }
}
