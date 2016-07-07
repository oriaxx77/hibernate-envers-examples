/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.examples;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oriaxx77.hibernateplay.envers.model.Address;
import com.oriaxx77.hibernateplay.envers.model.Animal;
import com.oriaxx77.hibernateplay.envers.model.Sponsor;
import com.oriaxx77.hibernateplay.envers.model.StepParent;
import com.oriaxx77.hibernateplay.envers.model.Zoo;
import com.oriaxx77.hibernateplay.envers.util.HibernateExampleBase;

/**
 * Fluent style Hibernate envers examples.
 * 
 */
@Service
public class EnversExamples extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger( EnversExamples.class );
    
    /**
     * Creates 
     * - the Budapest Zoo with two animals,
     * - the StepParent Madonna
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples createZoo()
    {
        Zoo zoo = new Zoo();
        zoo.setName( "BP" );
        zoo.addAnimal( new Animal( "Garfield" ));
        zoo.addAnimal( new Animal( "The Great White Shark" ));
        zoo.setAddress( new Address( "Budapest" ));
        getSession().save( zoo );
        logger.info( "Zoo created: " + zoo);
        StepParent stepParent = new StepParent();
        stepParent.setName( "Madonna" );
        getSession().saveOrUpdate( stepParent );
        logger.info( "StepParent created: " + stepParent );
        return this;
    }
    
    /**
     * Creates Bob and Mary as sponsors.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples newSponsors()
    {
        Zoo zoo = getBudapestZoo();
        zoo.addSponsor( new Sponsor( "Bob" ) );
        zoo.addSponsor( new Sponsor( "Mary" ) );
        logger.info( "Bob and Mary sponsors Budapest zoo." );
        return this;
    }
    
    /**
     * Adds two new animals to the Budapest zoo.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples newAnimalsArrived()
    {
        Zoo zoo = getBudapestZoo();
        zoo.addAnimal( new Animal( "Mila" ));
        zoo.addAnimal( new Animal( "Mozart" ));
        getSession().saveOrUpdate( zoo );
        logger.info( "New animals arrived to: " + zoo );
        return this;
    }
    
    /**
     * Loads the Budapest zoo from DB.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public Zoo getBudapestZoo()
    {
        Zoo zoo = (Zoo)getSession().createCriteria( Zoo.class)
                .setFetchMode( "animals" , FetchMode.JOIN )
                .setFetchMode( "sponsors" , FetchMode.JOIN )
                .add( Restrictions.eq( "name" , "BP" )).uniqueResult();
        logger.info( "Zoo loaded: " + zoo  );
        return zoo;
        
    }
    
    
    /**
     * Creates a StepParent (w/o) an animal
     * @return EnversExamples to use fluently
     */
    @Transactional 
    public EnversExamples createStepParent()
    {
        StepParent stepParent = new StepParent();
        stepParent.setName( "M.J." );
        getSession().saveOrUpdate( stepParent );
        logger.info( "StepParent created: " + stepParent );
        return this;
    }
    
    /**
     * Logs the revisions numbers of the Budapest Zoo.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples logZooRevisionNumbers()
    {
        Zoo zoo = getBudapestZoo();
        logger.info( "Get revision numbers of: " + zoo.getName() );
        List<Number> versions = AuditReaderFactory.get( getSession() ).getRevisions( Zoo.class, zoo.getId() );
        versions.stream().forEach( v -> logger.info( "Zoo revision number: " + v ));
        return this;
    }
    
    /**
     * Log audits of the Zoo entities.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples logZooAudits()
    {
        logger.info( "All Zoo.class audit records: " );
        AuditQuery auditQuery = AuditReaderFactory.get( getSession() )
                .createQuery()
                .forRevisionsOfEntity( Zoo.class, false, true );                
        List<?> audits = auditQuery.getResultList();
        audits.stream().forEach( audit -> logger.info( "Zoo audit record: " + Arrays.toString( (Object[])audit ) ) );
        return this;
    }
    
    /**
     * Logs the audits where Zoo.animals changed.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples logZooAnimalsAudits()
    {
        logger.info( "Zoo.animals change audits: " );
        AuditQuery auditQuery = AuditReaderFactory.get( getSession() )
                .createQuery()
                .forRevisionsOfEntity( Zoo.class, false, true )
                .add( AuditEntity.property( "animals" ).hasChanged() );
        List<?> audits = auditQuery.getResultList();
        audits.stream().forEach( audit -> logger.info( "Zoo.animals change audit: " + Arrays.toString( (Object[])audit ) ) );
        return this;
    }
    
    /**
     * Logs audits of the StepParent entities.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples logStepParentAudits()
    {
        logger.info( "StepParent audits: " );
        AuditQuery auditQuery = AuditReaderFactory.get( getSession() )
                .createQuery()
                .forRevisionsOfEntity( StepParent.class, false, true );                
        List<?> audits = auditQuery.getResultList();
        audits.stream().forEach( audit -> logger.info( "StepParent audit: " + Arrays.toString( (Object[])audit ) ) );
        return this;
    }
    
   
    /**
     * Logs all entities involved in the first revision.
     * @return EnversExamples to use fluently
     */ 
    @Transactional
    public EnversExamples logAllEntitiesInFirstRevision()
    {
        logger.info( "All entities changed in the first revision: " );
        AuditReader auditReader = AuditReaderFactory.get( getSession() );
        auditReader.getCrossTypeRevisionChangesReader()
                   .findEntities( 1 )
                   .stream().forEach( e -> { logger.info( "Audit in first revision: " + e ); } );
        return this;
    }
    
    /**
     * Logs all entities involved in the last revision.
     * @return EnversExamples to use fluently
     */
    @Transactional
    public EnversExamples logAllEntitiesInLastRevision()
    {
        logger.info( "All entities changed in the last revision: " );
        AuditReader auditReader = AuditReaderFactory.get( getSession() );
        Number lastRevision = auditReader.getRevisionNumberForDate( new Date() );
        auditReader.getCrossTypeRevisionChangesReader()
                   .findEntities( lastRevision )
                   .stream().forEach( e -> { logger.info( "Audit in last revision: " + e ); } );
        return this;
    }
}
