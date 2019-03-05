package atj;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TicTacToe {
	
	private int id;
	private boolean isMyMove;
	private int moveCount;
	private String sign;
	private String opponentSign;
	private PTPProducer producer;
	private PTPConsumer consumer;
	private Button fields[][];
	
	@FXML
	Text whoMoveText;
	@FXML
	Text signText;
	
	@FXML
	Button button00;
	@FXML
	Button button01;
	@FXML
	Button button02;
	@FXML
	Button button10;
	@FXML
	Button button11;
	@FXML
	Button button12;
	@FXML
	Button button20;
	@FXML
	Button button21;
	@FXML
	Button button22;

	

	@FXML
	private void initialize() {
		id = new Random().nextInt(1000000);
		producer = new PTPProducer(id + "");
		consumer = new PTPConsumer(id + "", new QueueAsynchConsumer(this));
		sign = "x";
		opponentSign = "o";
		isMyMove = false;
		moveCount = 0;

		fields = new Button[3][3];
		fields[0][0] = button00;
		fields[0][1] = button01;
		fields[0][2] = button02;
		fields[1][0] = button10;
		fields[1][1] = button11;
		fields[1][2] = button12;
		fields[2][0] = button20;
		fields[2][1] = button21;
		fields[2][2] = button22;

		producer.sendQueueMessage("waiting" + id + "");
	}

	@FXML
	void button00_Click() {
		clickButton(00);
	}

	@FXML
	void button01_Click() {
		clickButton(01);
	}

	@FXML
	void button02_Click() {
		clickButton(02);
	}

	@FXML
	void button10_Click() {
		clickButton(10);
	}

	@FXML
	void button11_Click() {
		clickButton(11);
	}

	@FXML
	void button12_Click() {
		clickButton(12);
	}

	@FXML
	void button20_Click() {
		clickButton(20);
	}

	@FXML
	void button21_Click() {
		clickButton(21);
	}

	@FXML
	void button22_Click() {
		clickButton(22);
	}

	private boolean checkWin(int user) {
		String checkSign;
		if (user == 1)
			checkSign = sign;
		else
			checkSign = opponentSign;
		for (int i = 0; i < 3; i++) {
			if ((fields[0][i].getText().equals(checkSign) && fields[1][i].getText().equals(checkSign)
					&& fields[2][i].getText().equals(checkSign))
					|| (fields[i][0].getText().equals(checkSign) && fields[i][1].getText().equals(checkSign)
							&& fields[i][2].getText().equals(checkSign))) {
				return true;
			}
		}

		if ((fields[0][0].getText().equals(checkSign) && fields[1][1].getText().equals(checkSign)
				&& fields[2][2].getText().equals(checkSign))
				|| fields[0][2].getText().equals(checkSign) && fields[1][1].getText().equals(checkSign)
						&& fields[2][0].getText().equals(checkSign))
			return true;

		return false;
	}

	private boolean checkDraw() {
		if (moveCount == 9)
			return true;

		return false;
	}

	private void clickButton(int field) {

		if (isMyMove != true)
			return;
		switch (field) {
		case 00:
			if (button00.getText().equals("")) {
				pickField(1, 00);
				producer.sendQueueMessage("00");
			}
			break;
		case 01:
			if (button01.getText().equals("")) {
				pickField(1, 01);
				producer.sendQueueMessage("01");
			}
			break;
		case 02:
			if (button02.getText().equals("")) {
				pickField(1, 02);
				producer.sendQueueMessage("02");
			}
			break;
		case 10:
			if (button10.getText().equals("")) {
				pickField(1, 10);
				producer.sendQueueMessage("10");
			}
			break;
		case 11:
			if (button11.getText().equals("")) {
				pickField(1, 11);
				producer.sendQueueMessage("11");
			}
			break;
		case 12:
			if (button12.getText().equals("")) {
				pickField(1, 12);
				producer.sendQueueMessage("12");
			}
			break;
		case 20:
			if (button20.getText().equals("")) {
				pickField(1, 20);
				producer.sendQueueMessage("20");
			}
			break;
		case 21:
			if (button21.getText().equals("")) {
				pickField(1, 21);
				producer.sendQueueMessage("21");
			}
			break;
		case 22:
			if (button22.getText().equals("")) {
				pickField(1, 22);
				producer.sendQueueMessage("22");
			}
			break;
		}
	}

	public void setPlayers(int opponentId) {
		if (id > opponentId) {
			sign = "o";
			opponentSign = "x";
			isMyMove = false;
			signText.setText("Your sign: o");
			whoMoveText.setText("Waiting for opponent move");
		} else {
			isMyMove = true;
			signText.setText("Your sign: x");
			whoMoveText.setText("Your move");
		}
	}

	public void pickField(int who, int nr) {
		String whoMove;

		if (who == 1) {
			fields[nr / 10][nr % 10].setText(sign);
			isMyMove = false;
			whoMove = "Waiting for opponent move";
		} else {
			fields[nr / 10][nr % 10].setText(opponentSign);
			isMyMove = true;
			whoMove = "Your move";
		}

		moveCount++;

		if (checkWin(who)) {
			if (who == 1) {
				isMyMove = false;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("END");
				alert.setHeaderText("You win");
				alert.showAndWait();

			} else {
				isMyMove = false;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("END");
				alert.setHeaderText("You lose");
				alert.showAndWait();
			}
		} else {

			if (checkDraw()) {
				isMyMove = false;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("END");
				alert.setHeaderText("DRAW!");
				alert.showAndWait();
			} else {

				whoMoveText.setText(whoMove);
			}
		}
	}

	public void close() {
		consumer.close();
	}
}