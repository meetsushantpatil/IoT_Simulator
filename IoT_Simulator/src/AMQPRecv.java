import com.rabbitmq.client.*;

import java.io.IOException;

public class AMQPRecv {
	
	//This is just a change
	//
  private String QUEUE_NAME;
  private String HOST;
  
  public AMQPRecv(String QUEUE_NAME, String HOST){
	  this.QUEUE_NAME = QUEUE_NAME;
	  this.HOST = HOST;
  }

  public void recvMessage() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages.");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}