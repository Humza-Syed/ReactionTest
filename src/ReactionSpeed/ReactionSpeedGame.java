package ReactionSpeed;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReactionSpeedGame {
	private int attempts;
	private float [] reactionTimes;

	@FXML
	AnchorPane mainPane;
	@FXML
	Button playGame;

	public void initialize(){
		this.attempts = 5;
		reactionTimes = new float[attempts];
	}

	public void playGame(){
		int switches = 0;
		boolean colourSwapped = false;
		long reactionTime = 0;
		Random rand = new Random();
		Scene scene = this.mainPane.getScene();


		new AnimationTimer(){
			long lastNanoTime = System.nanoTime();
			long timeBetweenSwap = (rand.nextInt(15 - 5) + 5) * 1000;
			int swapped =0;

			public void handle(long currentNanoTime){
				long t = currentNanoTime - lastNanoTime;
				long ms = TimeUnit.NANOSECONDS.toMillis(t);

				if(ms >= timeBetweenSwap){
					System.out.println("hi");
					timeBetweenSwap = (rand.nextInt(15 -5) + 5) * 1000;
					changeBackground(swapped);
					swapped++;
					lastNanoTime = currentNanoTime;
				}

				//TODO Allow user input
			}
		}.start();
	}

	private void changeBackground(int newColour){
		switch (newColour){
			case 0: mainPane.setStyle("-fx-background-color: green");
			break;
			case 1: mainPane.setStyle("-fx-background-color: blue");
				break;
			case 2: mainPane.setStyle("-fx-background-color: red");
				break;
			case 3: mainPane.setStyle("-fx-background-color: yellow");
				break;
			case 4: mainPane.setStyle("-fx-background-color: orange");
				break;
			case 5: mainPane.setStyle("-fx-background-color: purple");
				break;
			case 6: mainPane.setStyle("-fx-background-color: turquoise");
				break;
			case 7: mainPane.setStyle("-fx-background-color: darkgreen");
				break;
		}
	}

}
