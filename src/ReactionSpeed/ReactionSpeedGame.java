package ReactionSpeed;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReactionSpeedGame {
	private int attempts;
	private float [] reactionTimes;

	private boolean playerReacted;
	private boolean colourSwapped = false;

	@FXML
	AnchorPane mainPane;
	@FXML
	Button playGame;

	public void initialize(){
		this.attempts = 5;
		reactionTimes = new float[attempts];
	}

	public void playGame(){
		playGame.setVisible(false);

		Random rand = new Random();
		Scene scene = this.mainPane.getScene();

		scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if(colourSwapped)
				playerReacted = true;
		});

		new AnimationTimer(){
			long lastNanoTime = System.nanoTime();
			long timeBetweenSwap = (rand.nextInt(15 - 5) + 5) * 1000;
			int swapped =0;

			long timeColourSwapped;
			long playerReactionTime;

			public void handle(long currentNanoTime){
				long t = currentNanoTime - lastNanoTime;
				long ms = TimeUnit.NANOSECONDS.toMillis(t);

				if(ms >= timeBetweenSwap && !colourSwapped){
					timeColourSwapped = currentNanoTime - lastNanoTime;
					colourSwapped = true;
					timeBetweenSwap = (rand.nextInt(15 -5) + 5) * 1000;
					changeBackground(swapped);
					lastNanoTime = currentNanoTime;
				}

				if(playerReacted){
					playerReactionTime = currentNanoTime - lastNanoTime;
					playerReactionTime = TimeUnit.NANOSECONDS.toMillis(playerReactionTime);
					reactionTimes[swapped] = playerReactionTime;
					System.out.println(reactionTimes[swapped]);
					swapped++;
					playerReacted = false;
					colourSwapped = false;
					lastNanoTime = currentNanoTime;
				}

				if(swapped == attempts){
					System.out.println("Game Over");
					stop();
				}

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
