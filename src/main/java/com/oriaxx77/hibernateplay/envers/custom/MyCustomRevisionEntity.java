/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.custom;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultTrackingModifiedEntitiesRevisionEntity;
import org.hibernate.envers.RevisionEntity;

/**
 * A custom revision entity that stores the name of the user who ran
 * the transaction of the audit. So it associates the revision with
 * a user. 
 * 
 *
 */
@Entity
@RevisionEntity( MyCustomRevisionListener.class )
public class MyCustomRevisionEntity extends
        DefaultTrackingModifiedEntitiesRevisionEntity
{

    /**
     * Default serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * User name who ran the transaction of the audit
     */
    private String userName;

    /**
     * @return the userName
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    

}
