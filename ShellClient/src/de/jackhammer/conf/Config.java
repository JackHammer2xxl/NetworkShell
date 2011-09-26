package de.jackhammer.conf;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 02:23
 * To change this template use File | Settings | File Templates.
 */
public final class Config {


    /**
     * Log4j config file.
     */
    public static String LOG_CONFIG = "clientlog4j.properties";

    public static int DELAY = 60 *1000;

    /**
     * Listen on port ...
     */
    public static int PORT = 8782;

    public static int MAX_TRY = 10;
    /**
     * ShellServer address.
     */
    public static String SHELL_IP = "";

    /**
     * Show all events commands and so on...
     */
    public static boolean VERBOSE = false;


    /**
     * Load the command line parameter value from the specified key.
     * @param key
     * @param values
     * @return
     */
    public static String getCLIValueFromKey(String key, List<String> values ){
        String param = "";
        if(values.contains(key) ){
            final int index = values.indexOf(key);
            param = values.get(index + 1);
        }
        return param;
    }

}
