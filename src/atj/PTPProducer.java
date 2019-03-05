package atj;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;

public class PTPProducer 
{
	private String id;
	
	public void sendQueueMessage(String message) 
	{
		ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		
		try 
		{
			((com.sun.messaging.ConnectionFactory) connectionFactory).setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676/jms");
			JMSContext jmsContext = connectionFactory.createContext() ;
			Message msg = jmsContext.createMessage();
			msg.setStringProperty("id", id);
			msg.setStringProperty("MESSAGE", message);
			JMSProducer jmsProducer = jmsContext.createProducer();
			Queue queue = new com.sun.messaging.Queue("ATJQueue");
			jmsProducer.send(queue, msg);
			
			jmsContext.close();
		}
		catch(JMSException e) { e.printStackTrace(); }
	}
	
	public PTPProducer(String id)
	{
		this.id = id;
	}

}