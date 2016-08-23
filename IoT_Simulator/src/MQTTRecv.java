import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttClient;

public class MQTTRecv implements MqttCallback {

	String receivedMessage;
	private String brokerIP;
	private String topic;

	public MQTTRecv(String brokerIP, String topic) {
		this.brokerIP = brokerIP;
		this.topic = topic;
		
	}

	public MqttClient recvMessage() throws InterruptedException {
		try {   
			
			MqttClient clientR = new MqttClient("tcp://"+brokerIP+":1883", "Receiver");
			clientR.connect();

			clientR.subscribe(topic);   
			clientR.setCallback(this);
					
			Thread.sleep(10);
			return clientR;
				
		} 
		catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
		return null;
		
}
	public void closeConnection(MqttClient clientR) throws MqttException{
		try{
		clientR.disconnect();   
		clientR.close(); }
		catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
	}

	@Override
	public void connectionLost(Throwable cause) {
    // TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
	{
		//System.out.println("Received: " + message.toString());
		receivedMessage = message.toString();
		System.out.println("Received Message:   " + receivedMessage);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}
	
	public String getMessage()
	{
		return receivedMessage;
	}

}