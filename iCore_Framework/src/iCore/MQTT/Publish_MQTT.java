package iCore.MQTT;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


/**
 * The Class Publish_MQTT.
 */
public class Publish_MQTT {

/**
 * Publish.
 *
 * @param pubTopic the pub topic
 * @param payload the payload
 * @throws MqttException the mqtt exception
 */
public void Publish(String pubTopic, String payload) throws MqttException{

		// Default settings:
		boolean quietMode 	= false;
		String action 		= "publish";
		String topic 		= "";
		int qos 			= 2;
		String clientId 	= null;
		boolean cleanSession = true;			// Non durable subscriptions
		String password = null;
		String userName = null;
		String url = "tcp://193.206.22.123:1883";

		clientId = "CNET"+action;
				
		
		if (topic.equals("")) {
			// Set the default topic according to the specified action			
			topic = pubTopic;
			
			byte[] test2 = payload.getBytes();    
			MqttMessage message1 = new MqttMessage(test2);
			message1.setQos(qos);

			MQTT_Impl sampleClient = new MQTT_Impl(url, clientId, cleanSession, quietMode,userName,password,payload,1,message1);

			sampleClient.publish(topic,qos,payload.getBytes(),message1);

		}
	}

}
