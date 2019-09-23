package ReactionSpeed;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReactionSpeedGame {
	private int attempts;
	private int [] reactionTimes;
	private Color [] colors = {Color.BLACK,Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW,Color.ORANGE,Color.TURQUOISE,Color.PURPLE};

	@FXML
	AnchorPane mainPane;
	@FXML
	Button playGame;

	public void initialize(){
		this.attempts = 5;
		reactionTimes = new int[attempts];
	}

	public void playGame(){
		int switches = 0;
		Random rand = new Random();

		Scene scene = this.mainPane.getScene();
		mainPane.setStyle("-fx-background-color: black");

		new AnimationTimer(){
			long lastNanoTime = System.nanoTime();

			public void handle(long currentNanoTime){
				double t = currentNanoTime - lastNanoTime / 100000000;
				lastNanoTime = currentNanoTime;

				//TODO Change the color randomly, allow user input
				try{
					TimeUnit.SECONDS.sleep(rand.nextInt(14) + 1);
				}catch(Exception e){
					e.printStackTrace();
				}

				System.out.println(t);
			}
		}.start();
	}

}
