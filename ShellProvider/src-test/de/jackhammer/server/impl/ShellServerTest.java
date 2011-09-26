package de.jackhammer.server.impl;

import org.junit.Test;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.Enumeration;

import static de.jackhammer.conf.Config.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jack
 * Date: 25.09.11
 * Time: 08:26
 * To change this template use File | Settings | File Templates.
 */
public class ShellServerTest {


    @Test
    public void testReadLocalAddress() throws Exception {
        ServerSocket socket = new ServerSocket(PORT);

        System.out.println("InetAddress = " + socket.getInetAddress());

        System.out.println("LocalSocketAddress = " + socket.getLocalSocketAddress());

    }

    @Test
    public void testGetSystemShell() throws Exception {
        final String[] result;
        String osName = System.getProperty("os.name");
        if( osName.startsWith("Windows")) {
            String comspec = System.getProperty("ComSpec", "cmd.exe");
            result = new String[] { comspec, "/C" };
        }
        else {
            String shell = System.getProperty("SHELL", "/bin/sh");
            result = new String[] { shell, "-c" };
        }
    }

    @Test
    public void testReadLocalIP() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();

        // Get IP Address
        byte[] ipAddr = addr.getAddress();
        System.out.println("IP = " + ipAddr);

        // Get hostname
        String hostname = addr.getHostName();
        System.out.println("hostname = " + hostname);
    }

    @Test
    public void testNetworkInterface() throws Exception {
        try {
            NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            Enumeration<InetAddress> ia = ni.getInetAddresses();
            while (ia.hasMoreElements()) {
                InetAddress elem = ia.nextElement();
                if (elem instanceof Inet6Address) {
                    System.out.println("IPv6:");
                } else {
                    System.out.println("IPv4:");
                }
                String str = String.format(" hostname: %s", elem.getCanonicalHostName());
                System.out.println(str);
                str = String.format(" address: %s", elem.getHostAddress());
                System.out.println(str);
                System.out.println(str);
            }
        } catch (NullPointerException e) {
            System.out.println("Retrieving Information from NetworkInterface failed");
        }
    }


    @Test
    public void testListNetworkInterfaces() throws Exception {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface networkInterface : Collections.<NetworkInterface>list(networkInterfaces)) {
            Enumeration<NetworkInterface> virtualInterfaces = networkInterface.getSubInterfaces();
            for (NetworkInterface virtualInterface : Collections.<NetworkInterface>list(virtualInterfaces)) {
                System.out.println(networkInterface.getDisplayName() + " VIRT " + virtualInterface.getDisplayName());
                Enumeration<InetAddress> virtualInterfaceAddresses = virtualInterface.getInetAddresses();
                for (InetAddress virtualAddress : Collections.<InetAddress>list(virtualInterfaceAddresses)) {
                    System.out.println("\t" + virtualAddress.toString());
                }
            }
            System.out.println("Real networkInterface addresses: " + networkInterface.getDisplayName());
            Enumeration<InetAddress> realAddresses = networkInterface.getInetAddresses();
            for (InetAddress realAddress : Collections.<InetAddress>list(realAddresses)) {
                System.out.println("\t" + realAddress.toString());
            }
        }
    }
}
