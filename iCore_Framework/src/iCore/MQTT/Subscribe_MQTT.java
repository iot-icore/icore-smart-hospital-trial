/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iCore.MQTT;

import org.eclipse.paho.client.mqttv3.MqttException;


/**
 * The Class Subscribe_MQTT.
 */
public class Subscribe_MQTT {
	
/**
 * Subscribe.
 *
 * @param subTopic the sub topic
 * @param sr_id the sr_id
 * @param clientId the client id
 * @throws MqttException the mqtt exception
 */
public void Subscribe(String subTopic,int sr_id,String clientId) throws MqttException{

		// Default settings:
		boolean quietMode 	= false;
		String action 		= "subscribe";
		String topic 		= "";
		int qos 			= 2;
		boolean cleanSession = true;			// Non durable subscriptions
		String password = null;
		String userName = null;
		String url = "tcp://193.206.22.123:1883";

		clientId = clientId + action;

		
		if (topic.equals("")) {

			topic = subTopic;

			MQTT_Impl sampleClient = new MQTT_Impl(url, clientId, cleanSession, quietMode,userName,password,null,2,null);

			sampleClient.subscribe(topic,qos, sr_id);
		}

	}

}
