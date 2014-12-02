
The project presents files of the iCore Framework adapted to suit the requirements of the Smart Asset Management 

The current files do not have generic APIs, but can be used as in its entirety. 
The next version will be modular and more generic and thereby easily extendible to other use case scenarios.
For further questions, please contact the authors.

TO USE THE FRAMEWORK:

- The main entry point is iCore.Main.MainEntryPoint. 
- It requires a Service Request as input to trigger the framework. Service Requests are usually of the form Locate/Trace ObjectID
- It then calls the service level functionalities of translate, analysis and check with the RWK overall model. 
- Update the database access credentials.
- Currently the address of the MQTT broker used for all MQTT communications is hard-coded. 


---------------------------------------------------------------------------------------------------------------------------------------------  
 License:
 
 Copyright 2014 iCore

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

