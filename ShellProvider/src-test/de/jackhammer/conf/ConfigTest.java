package de.jackhammer.conf;

import de.jackhammer.conf.Config;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 02:37
 * To change this template use File | Settings | File Templates.
 */
public class ConfigTest {


    @Test
    public void testCheckOperationSystem() throws Exception {

        final boolean expected  = true;
        final boolean isWindows = Config.isOperationSystemWindows();

        assertEquals(expected, isWindows );

    }
}
