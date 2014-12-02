package iCore.MQTT;

import java.io.IOException;
import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * The Class MQTT_Impl 
 */
public class MQTT_Impl implements MqttCallback {

	// Private instance variables
	/** The client. */
	private MqttClient 			client;
	
	/** The broker url */
	private String 				brokerUrl;
	
	/** The quiet mode. */
	private boolean 			quietMode;
	
	/** The con opt. */
	private MqttConnectOptions 	conOpt;
	
	/** The clean. */
	private boolean 			clean;
	
	/** The password. */
	private String password;
	
	/** The user name. */
	private String userName;
	
	/** The sr_id. */
	private static int sr_id;
	
	/**
	 * Constructs an instance of the sample client wrapper.
	 *
	 * @param brokerUrl the url of the server to connect to
	 * @param clientId the client id to connect with
	 * @param cleanSession clear state at end of connection or not (durable or non-durable subscriptions)
	 * @param quietMode whether debug should be printed to standard out
	 * @param userName the username to connect with
	 * @param password the password for the user
	 * @param message the message
	 * @param flag the flag
	 * @param message1 the message1
	 * @throws MqttException the mqtt exception
	 */
    public MQTT_Impl(String brokerUrl, String clientId, boolean cleanSession, boolean quietMode, String userName, String password,String message,int flag, MqttMessage message1) throws MqttException {
    	
     	this.brokerUrl = brokerUrl;
    	this.quietMode = quietMode;
    	this.clean 	   = cleanSession;
    	this.password = password;
    	this.userName = userName;

    	MemoryPersistence dataStore = new MemoryPersistence();

    	try {
    		// Construct the connection options object that contains connection parameters

	    	conOpt = new MqttConnectOptions();
	    	conOpt.setCleanSession(clean);
	    	if(password != null ) {
	    	  conOpt.setPassword(this.password.toCharArray());
	    	}
	    	if(userName != null) {
	    	  conOpt.setUserName(this.userName);
	    	}

    		// Construct an MQTT blocking mode client
			client = new MqttClient(this.brokerUrl,clientId, dataStore);

			// Set this wrapper as the callback handler
	    	client.setCallback(this);

		} catch (MqttException e) {
			e.printStackTrace();
			log("Unable to set up client: "+e.toString());
			System.exit(1);
		}
    	
    }

    /**
     * Publish / send a message to an MQTT server.
     *
     * @param topicName the name of the topic to publish to
     * @param qos the quality of service to delivery the message at (0,1,2)
     * @param payload the set of bytes to send to the MQTT server
     * @param message the message
     * @throws MqttException the mqtt exception
     */
    public void publish(String topicName, int qos, byte[] payload, MqttMessage message) throws MqttException {

    	// Connect to the MQTT server
    	log("Connecting to "+brokerUrl + " with client ID "+client.getClientId());
    	client.connect(conOpt);
    	log("Connected");

    	String time = new Timestamp(System.currentTimeMillis()).toString();
    	log("Publishing at: "+time+ " to topic \""+topicName+"\" qos "+qos);

    	// Create and configure a message
    	message.setQos(qos);

    	// Send the message to the server, control is not returned until
    	// it has been delivered to the server meeting the specified
    	// quality of service.
    	client.publish(topicName, message);

    	// Disconnect the client
    	client.disconnect();
    	log("Disconnected");
    }

    /**
     * Subscribe to a topic on an MQTT server
     * Once subscribed this method waits for the messages to arrive from the server
     * that match the subscription. It continues listening for messages until the enter key is
     * pressed.
     *
     * @param topicName to subscribe to (can be wild carded)
     * @param qos the maximum quality of service to receive messages at for this subscription
     * @param sr_id1 the sr_id1
     * @throws MqttException the mqtt exception
     */
    public void subscribe(String topicName, int qos, int sr_id1) throws MqttException {
    	
    	setSR_ID(sr_id1);
    	// Connect to the MQTT server
    	client.connect(conOpt);
    	log("Connected to "+brokerUrl+" with client ID "+client.getClientId());

    	// Subscribe to the requested topic
    	log("Subscribing to topic \""+topicName+"\" qos "+qos);
    	client.subscribe(topicName, qos);

    	// Continue waiting for messages until the Enter is pressed
    	log("Press <Enter> to exit");
		try {
			System.in.read();
		} catch (IOException e) {
			//If we can't read we'll just exit
		}
		
		// Disconnect the client from the server
		client.disconnect();
		log("Disconnected");
		
    }
    
    /**
     * Sets the sr id.
     *
     * @param sr_id1 the new sr id
     */
    private void setSR_ID(int sr_id1) {
		
    	sr_id = sr_id1;
		
	}
    
    /**
     * Gets the sr id.
     *
     * @return the sr id
     */
    private int getSR_ID() {
		
    	return sr_id;
		
	}


    /**
     * Utility method to handle logging. If 'quietMode' is set, this method does nothing
     * @param message the message to log
     */
    private void log(String message) {
    	if (!quietMode) {
    		System.out.println(message);
    	}
    }

	/**
	 * *************************************************************.
	 *
	 * @param cause the cause
	 */
	/* Methods to implement the MqttCallback interface              */
	/****************************************************************/

    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
	public void connectionLost(Throwable cause) {
		// Called when the connection to the server has been lost.
		log("Connection to " + brokerUrl + " lost!" + cause);
		System.exit(1);
	}

    /**
     * Delivery complete.
     *
     * @param token the token
     * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
     */
	public void deliveryComplete(IMqttDeliveryToken token) {

	}

    /**
     * Message arrived.
     *
     * @param topic the topic
     * @param message the message
     * @throws MqttException the mqtt exception
     * @see MqttCallback#messageArrived(String, MqttMessage)
     */
	public void messageArrived(String topic, MqttMessage message) throws MqttException {

		String time = new Timestamp(System.currentTimeMillis()).toString();
		
		System.out.println("Time:\t" +time +
                           "  Topic:\t" + topic +
                           "  Message:\t" + new String(message.getPayload()) +
                           "  QoS:\t" + message.getQos());
		
		PayloadProperties payload = new PayloadProperties();

		
		if(getSR_ID() ==1){
			
			System.out.println("Success");
			
			payload.updateDB_positions(new String(message.getPayload()));			
		}
		
		else if(getSR_ID()==2){
			
			System.out.println("Success");
			
			payload.updateDB_alarms(new String(message.getPayload()));			
		}
	      
    }

}
