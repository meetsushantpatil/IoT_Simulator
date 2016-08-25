import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTSend {
	
	private String topic;
    private String content;
    private int qos             = 2;
    private String broker;
    private String clientId;
	
	public MQTTSend(String broker, String topic, String content, String clientId){
		this.topic = topic;
		this.content = content;
		this.broker = "tcp://"+broker+":1883";
		this.clientId = clientId;
	}
	
	public MqttClient createClient() throws MqttException
	{
		MqttClient sampleClient = new MqttClient(broker, clientId);
		return sampleClient;
	}
    public void sendMessage(MqttClient sampleClient) {

        //MemoryPersistence persistence = new MemoryPersistence();

        try {
            //MqttClient sampleClient = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing Message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}