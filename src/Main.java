import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Main extends Application {
	static Stage mainStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setTitle("Reaction Test");
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		mainStage.setScene(scene);

		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void reactSpeedGamePressed(){
		try{
			Parent root = FXMLLoader.load(getClass().getResource("/ReactionSpeed/ReactionSpeedGame.fxml"));
			Scene gameScene = new Scene(root);
			mainStage.setScene(gameScene);
			mainStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
