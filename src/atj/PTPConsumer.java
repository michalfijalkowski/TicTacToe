package atj;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

public class PTPConsumer 
{
	private JMSContext jmsContext;
	private JMSConsumer jmsConsumer;
	private Queue q;
	
	public PTPConsumer(String id, QueueAsynchConsumer queue)
	{
		ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		jmsContext = connectionFactory.createContext();
		try 
		{
			q = new com.sun.messaging.Queue("ATJQueue");
			jmsConsumer = jmsContext.createConsumer(q, "id <> '" + id + "'");
			jmsConsumer.setMessageListener(queue);
		}
		catch(JMSException e) { e.printStackTrace(); }
	}
	
	public void close() 
	{
		jmsConsumer.close();
		jmsContext.close();
		
	}

}