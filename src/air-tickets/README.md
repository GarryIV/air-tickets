Development environment:
-------------------------------------
* Download and install the latest JDK 8 from `http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html`
* Make sure JAVA_HOME refers to the this JDK.
* Make sure java executable of correct version is available in PATH environment variable. Run "java -version" to check it.

Build and run instructions:
* Navigate to `/config` directory and review ui.yml and core.yml
* Run `mvnw clean install` command in root directory to build and test solution
* run `java -jar air-tickets-core/target/air-tickets-core.jar` 
* run `java -jar air-tickets-ui/target/air-tickets-ui.jar` 
* open `http://localhost:8080` to check if client works

Design document:
* See `Architecture and Design.doc`