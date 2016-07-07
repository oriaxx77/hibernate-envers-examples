/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for all Hibenrate example.
 * It provides access to the current Hibernate session via {@link #getSession()}
 * and the Hibernate session factory via {@link #getSessionFactory()}
 * 
 */
public class HibernateExampleBase
{
    /** 
     * Hibernate SessionFactory.
     * It should not be @Autowired.
     * It should be injected via constructor. Then a new constructor should have been created for each 
     * descendant. It is a demo. Keep it simple. 
     */
    private SessionFactory sessionFactory;
    
    /**
     * Returns with the current Hibernate session.
     * @return The current Hibernate session.
     */
    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    /**
     * Returns with the SessionFactory.
     * @return The SessionFactory.
     */
    protected SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    
}
