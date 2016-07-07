/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.util;

/**
 * Helper class to store the name of the authenticated user in the current thread.
 * 
 *
 */
public class AuthUser
{
    /** User name thread local */
    public static ThreadLocal<String> threadLocalUserName = new ThreadLocal<String>();
    
    /**
     * Hidden constructor. It is a utility class.
     */
    private AuthUser()
    {
    }
    
    /**
     * Ties the provided name to the current thread.
     * @param name Name of the authenticated user
     */
    public static void setName( String name )
    {
        threadLocalUserName.set( name );
    }
    
    /**
     * Returns with the name of the authenticated user tied to the current thread or null
     * if there is none tied.
     * @return The name of the authenticated user tied to the current thread or null
     *          if there is none tied
     */
    public static String getName()
    {
        return threadLocalUserName.get();
    }

}
