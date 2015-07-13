#SpringBootJersey Example.
  It describes the SpringBoot Rest with Jersey implementation.  
  
#Tech Stack.
  1. Spring boot with Jersey and Test module.  
  2. HSQL InMemory Database.  
  3. Gradle build script.  
  4. Lombok framework.  
  
#PreRequisites.  
  1. JDK 1.8
  2. Gradle.
  
#How to import into IDE?  
 Clone the project and get into the root directory and run the below command.  
  <code>$gradle eclipse</code>  
 It generates the eclipse .project and .classpath files, you are good to import now.  
 
#How to run.?  
  To build: Clone the repository, Get inside the root directory and then run the below command.  
    <code>gradle build</code>  
  It builds the application and prepares the artifacts, artifacts can be fine in /build/distributions dir.  
  
  To Run from shell/command tool:  
    1. Get inside the build/distributions dir and unzip the SpringBootJerseyExample-<version>.zip.  
    2. Get into the SpringBootJerseyExample-<version>/bin directory and use either bat/.sh file to run from the cmd/shell.  
     
    Note: Windows user has to suppress the classpath in bat file prior to run.
         Replace the classpath entry with "set CLASSPATH=%APP_HOME%\lib\*" (without quotes)
         
  To Run from IDE.  
    1. if you have gradle plugin in eclipse, gradle window is the best choice to run different tasks.  
    2. To run the server open JerseyApplication.java and run as java application.  
    
  To Run test cases.  
    Open JerseyApplicationTest.java class highlight the test method you want to run and run as Junit test.  
    
  
#Sample Json request body.  
  <code>{ 
      "code":"12334",
      "areaName":"area name"
  }</code>
  
  