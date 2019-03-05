package atj;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import javafx.application.Platform;

public class QueueAsynchConsumer implements MessageListener {
	TicTacToe game;

	public QueueAsynchConsumer(TicTacToe game) {
		this.game = game;
	}

	@Override
	public void onMessage(Message message) {
		Platform.runLater(() -> {
			try {
				String info = message.getStringProperty("MESSAGE");

				if (info.length() > 7 && info.substring(0, 7).equals("waiting")) {
					game.setPlayers(Integer.parseInt(info.substring(7, info.length())));
					return;
				}

				switch (info) {
				case "00":
					game.pickField(0, 00);
					break;
				case "01":
					game.pickField(0, 01);
					break;
				case "02":
					game.pickField(0, 02);
					break;
				case "10":
					game.pickField(0, 10);
					break;
				case "11":
					game.pickField(0, 11);
					break;
				case "12":
					game.pickField(0, 12);
					break;
				case "20":
					game.pickField(0, 20);
					break;
				case "21":
					game.pickField(0, 21);
					break;
				case "22":
					game.pickField(0, 22);
					break;
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		});
	}
}