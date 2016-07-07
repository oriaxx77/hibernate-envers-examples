Hibernate envers examples.
--------------------------------
--------------------------------

1. Running the examples:
--------------------------------
1 - Start the com.oriaxx77.hibernateplay.envers.StartDBServer
	It starts a Dervy server that listens on port 8888
2 - Start com.oriaxx77.hibernateplay.envers.Application
	It starts the examples. The settings are in the application.properties.
	See it for your settings
	
2. Example code
--------------------------------
com.oriaxx77.hibernateplay.envers.examples.EnversExamples

3. Domain
--------------------------------

Zoo 
- represents a zoo
- has an embedded Address
- can have more Animals
- can have more Sponsors

Sponsor
- represents a sponsor of the zoo

Animal
- a zoo animal

StepParent
- step parent of an animal
 

4. Issues/TODO
- Review the code
- Use derby with spring boot
- Update the README.md based on the PDF in the repo