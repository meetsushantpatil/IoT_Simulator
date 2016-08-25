import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;


/*This is a simulator main file which creates sends & then receives message*/
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
		String number = properties.getProperty("properties");
		//Integer noOfMessages = Integer.parseInt(number);
		int noOfMessages = 10;
		
		if(protocolName.equals("amqp"))
		{
			
		
			System.out.println("Sending Message with AMQP");
			AMQPRecv AMQPRecv = new AMQPRecv(QueueName, brokerIP);
			AMQPRecv.recvMessage();
		
			AMQPSend AMQPSender = new AMQPSend(QueueName, brokerIP, msg);
			for (int i=0;i<noOfMessages;i++)
			{	
				AMQPSender.sendMessage();
			}
		
		}
		
		if(protocolName.equals("mqtt")){
			
		
			System.out.println("Sending Message with MQTT");
			MQTTRecv MQTTRecver = new MQTTRecv(brokerIP, topic, "MQTTReceiver");
			MQTTRecver.recvMessage();
	
			MQTTSend MQTTSender = new MQTTSend(brokerIP, topic, msg, "MQTTSender");
			MqttClient MQTT = MQTTSender.createClient();
			
			for (int j=0;j<10;j++)
			{
			System.out.println(j);
			MQTTSender.sendMessage(MQTT);
			MQTTRecver.recvMessage();
			}
		
		}
		
		Thread.sleep(1000);
		System.exit(0);
		
		
	}

}
