/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.util;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * Utility class to print staistics onto the log.
 * 
 */
public class LogInfo
{
    /**
     * Logs query cache statistics from a {@link SessionFactory}
     * with the specified {@link Logger}
     * @param logger Logger to log with.
     * @param sessionFactory SessionFactory that holds the query cache statistics
     */
    public static void printQueryCacheStaistics( Logger logger, SessionFactory sessionFactory)
    {
        logger.info( " ---- Query Cache Hit count: " + sessionFactory.getStatistics().getQueryCacheHitCount() );
        logger.info( " ---- Query Cache Miss count: " + sessionFactory.getStatistics().getQueryCacheMissCount() );
        logger.info( " ---- Query Cache Put count: " + sessionFactory.getStatistics().getQueryCachePutCount() );
        logger.info( " ---- Query Cache Queries: " + Arrays.toString( sessionFactory.getStatistics().getQueries() ) );
    }
    
}
