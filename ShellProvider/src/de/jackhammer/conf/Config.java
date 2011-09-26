package de.jackhammer.conf;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 02:23
 * To change this template use File | Settings | File Templates.
 */
public final class Config {

     private static final Logger LOGGER = Logger.getLogger(Config.class );

    /**
     * Log4j config file.
     */
    public static String LOG_CONFIG = "serverlog4j.properties";

    public static int DELAY = 60 *1000;


    /**
     * Listen on port ...
     */
    public static int PORT = 8782;

    /**
     *Login values.
     */
    public static String CLIENT_NAME = "root";
    public static String CLIENT_PASS = "admin";

    /**
     * Show all events commands and so on...
     */
    public static boolean VERBOSE = false;


    /**
     * Operation system CLI currently windows based.
     */
    public static final String SHELL_NAME = "cmd.exe";

    /**
     * Check operation system is windows based.
     * @return
     */
    public static boolean isOperationSystemWindows() {
        final String osName = System.getProperty("os.name");
        LOGGER.info(String.format("Operation system -> %s ", osName ));
        return osName.toLowerCase().startsWith("win" );
    }


}
