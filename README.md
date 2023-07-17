# crm
This is a sample UI to show off some of the new domino-ui version capabilities and features.
It is not meant to be a real app and does not have any real business logic, data is static and not all navigation items link to a new page.

### How to run 

- Build the project `mvn clean verify`

- Super Development mode :

In one terminal run  `mvn tomcat7:run -pl *-server -am -Denv=dev`

In another terminal run `mvn gwt:codeserver -pl *-client -am`

- For production get the war from the target folder and deploy it to your preferred application server
