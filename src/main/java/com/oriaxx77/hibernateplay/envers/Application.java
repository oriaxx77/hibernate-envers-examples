package com.oriaxx77.hibernateplay.envers;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.oriaxx77.hibernateplay.envers.examples.EnversExamples;
import com.oriaxx77.hibernateplay.envers.util.AuthUser;

/**
 * Spring Boot application. Static entry point of this app.
 * It runs the examples.
 * 
 */
@SpringBootApplication
// If you see HttpMapperProperties deprecation warning 
// You can safely ignore this warning as it will be fixed in IDE-1366 for release STS 3.7.0.
// http://stackoverflow.com/questions/28818178/class-org-springframework-boot-autoconfigure-web-httpmapperproperties-is-marke
public class Application
{
    /** Logger */
    final static Logger logger = Logger.getLogger( Application.class );
   
    
	/**
	 * Static entry point of the app.
	 * @param args Command line argumemnts. Use it to pass arguments to the Spring boot app.
	 */
	public static void main(String[] args) throws Exception
	{
		ConfigurableApplicationContext ctx = SpringApplication.run( Application.class, args );
		ctx.registerShutdownHook();
		
		AuthUser.setName( "SuperUser" );
		
		EnversExamples examples = ctx.getBean( EnversExamples.class );
		examples.createZoo()
		        .logZooAudits()
		        .newAnimalsArrived()
		        .newSponsors()
		        .logZooAudits()
		        .logZooAnimalsAudits()
		        .createStepParent()
		        .logStepParentAudits()
		        .logAllEntitiesInFirstRevision()
		        .logAllEntitiesInLastRevision();
        		
		
	}
}
