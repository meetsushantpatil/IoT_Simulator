import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AMQPSend {

  private  String QUEUE_NAME;
  private String HOST;
  private String message;
  
  public AMQPSend(String QUEUE_NAME, String HOST, String message){
	  this.QUEUE_NAME = QUEUE_NAME;
	  this.HOST = HOST;
	  this.message = message;
  }

  public void sendMessage() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
	  
  }
  
  
}