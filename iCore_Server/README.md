The files represent the server implementation of the Smart Asset Management Framework tested and deployed in a hospital environment

---------------------------------------------------------------------------------------------------------------------------------------------

It defines the REST APIs to instantiate the iCoreFramework. 
These APIs are used by the Android application in order to interface with the iCore Framework to provide services to the hospital staff.

---------------------------------------------------------------------------------------------------------------------------------------------

Points to be noted:
- Requires a SQL Database to be configured. 
- Currently the version does not contain the generic APIs for reuse.
- The code can be used for other hospital scenarios, given that a similar database as used in the current implementation is configured
- The server address can be updated by changing the parameter in the RESTServer.java

- @version 1.0 does not support generic APIs


---------------------------------------------------------------------------------------------------------------------------------------------
HOW TO USE:

- Import the project as an eclipse project
- Run the RESTServer.java code, currently the base address is set as http://localhost:8182/rest/
- Open a browser and the following are the links that returns values

(Only works provided the Database is present!)

The following are the service requests: 

(i) Retrieve List of Objects
    http://localhost:8182/rest/ObjectList 
 
(ii) Retrieve Position of an Object
     http://localhost:8182/rest/Locate/9340351261180526
 
(iii) Retrieve Alarms(Time/Geo-Fence)
  http://localhost:8182/rest/Alarms 
  
(iv) Maintenance - Updates the database with the maintenance schedule
  http://localhost:8182/rest/MainDate