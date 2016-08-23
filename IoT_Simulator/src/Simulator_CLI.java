import java.util.Properties;

public class Simulator_CLI {

	public static void main(String[] args) throws Exception {
		
		Properties properties = new Properties();
		properties = ReadPropertiesFile.readProperty(args[0]);
		
		// TODO Auto-generated method stub
		
		String protocolName  = properties.getProperty("protocolName");
		String QueueName = properties.getProperty("queueName");
		String brokerIP = properties.getProperty("brokerIP");
		String topic = properties.getProperty("topicName");
		String msg = properties.getProperty("messageContent");
		
		
		if(protocolName.equals("amqp")){
			
		System.out.println("Sending Message with AMQP");
		AMQPRecv AMQPRecv = new AMQPRecv(QueueName, brokerIP);
		AMQPRecv.recvMessage();
		
		AMQPSend AMQPSender = new AMQPSend(QueueName, brokerIP, msg);
		AMQPSender.sendMessage();
		
		}
		
		if(protocolName.equals("mqtt")){
		System.out.println("Sending Message with MQTT");
		MQTTRecv MQTTRecver = new MQTTRecv(brokerIP, topic);
		MQTTRecver.recvMessage();
	
		MQTTSend MQTTSender = new MQTTSend(brokerIP, topic, msg);
		MQTTSender.sendMessage();
		
		MQTTRecver.recvMessage();
		
		}
		
		Thread.sleep(1000);
		System.exit(0);
		
		
	}

}
