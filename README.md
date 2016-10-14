Development environment:
------------------------
* Download and install the latest JDK 8 from `http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html`
* Make sure JAVA_HOME refers to the this JDK.
* Make sure java executable has correct version. Run "java -version" to check it.

Build and run instructions:
---------------------------
* Navigate to `/config` directory and review ui.yml and core.yml. 
  Application mostly works as is but you need to set mail properties in ordrer to use notification service. 
* Run `mvnw clean install` command in root directory to build and test solution
* Run `java -jar air-tickets-core/target/air-tickets-core.jar` 
* Run `java -jar air-tickets-ui/target/air-tickets-ui.jar` 
* Open `http://localhost:8080` to check if client works
