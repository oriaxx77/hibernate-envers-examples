/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.custom;

import org.apache.log4j.Logger;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionListener;

import com.oriaxx77.hibernateplay.envers.examples.EnversExamples;
import com.oriaxx77.hibernateplay.envers.util.AuthUser;

/**
 * A revision listener used by the {@link MyCustomRevisionEntity}.
 * The {@link #newRevision(Object)} associates the revision with a user
 * name stored attached to the current thread. It uses the {@link AuthUser}
 * to obtain that user name
 * 
 */
public class MyCustomRevisionListener implements RevisionListener
{

    /** Logger */
    final static Logger logger = Logger.getLogger( EnversExamples.class );
    
    
    
    /**
     * Constructor.
     */
    public MyCustomRevisionListener()
    {
    }

    /**
     * Called when a new revision is created.
     * @param revisionEntity  An instance of the entity annotated with {@link RevisionEntity}, which will be persisted after this method returns. 
     *                        All properties on this entity that are to be persisted should be set by this method.
     */
    @Override
    public void newRevision(Object revisionEntity)
    {
        MyCustomRevisionEntity customRevisionEntity =  (MyCustomRevisionEntity) revisionEntity; 
        customRevisionEntity.setUserName(  AuthUser.getName() );
        logger.info( "MyCustomRevisionListener#newRevision. Using user name: " + AuthUser.getName() );
    }
    
    

}
