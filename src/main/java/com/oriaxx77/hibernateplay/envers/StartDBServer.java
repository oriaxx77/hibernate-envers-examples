/**
 * 
 */
package com.oriaxx77.hibernateplay.envers;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.derby.drda.NetworkServerControl;

/**
 * Starts the Derby DB on port 8888.
 * 
 */
public class StartDBServer
{
    
    
    /**
     * Starts Derby on port 8888.
     * @param args Command line args. Not used atm.
     * @throws UnknownHostException
     * @throws Exception
     */
    public static void main(String[] args) throws UnknownHostException, Exception
    {
        NetworkServerControl serverControl = new NetworkServerControl(InetAddress.getByName("localhost"),8888);
        serverControl.start( new PrintWriter( System.out  ) );
        while( true );
    }
}
