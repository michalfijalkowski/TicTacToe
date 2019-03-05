package atj;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tlo.fxml"));
			AnchorPane root = fxmlLoader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Game TicTacToe");
			TicTacToe game = fxmlLoader.getController();
			primaryStage.setOnHiding(e -> game.close());
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}