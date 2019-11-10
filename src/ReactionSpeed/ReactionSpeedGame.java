package ReactionSpeed;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReactionSpeedGame {
	private int attempts;
	private float [] reactionTimes;
	private float bestTime = 0;

	private boolean playerReacted;
	private boolean colourSwapped = false;
	private Text gameInfo = new Text();

	@FXML
	AnchorPane mainPane;
	@FXML
	Button playGame;
	@FXML
	Button restartGame;
	@FXML
	Button exitGame;

	public void initialize(){
		this.attempts = 5;
		reactionTimes = new float[attempts];
		mainPane.getChildren().add(gameInfo);
		gameInfo.setVisible(false);
	}

	public void playGame(){
		playGame.setVisible(false);

		Random rand = new Random();
		Scene scene = this.mainPane.getScene();

		gameInfo.setText("Press any key when the background colour changes");
		gameInfo.setStyle("-fx-font-size: 20");
		gameInfo.setX(mainPane.getWidth()/6);
		gameInfo.setY(mainPane.getHeight()/4);
		gameInfo.setVisible(true);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if(colourSwapped)
				playerReacted = true;
		});

		new AnimationTimer(){
			long lastNanoTime = System.nanoTime();
			long timeBetweenSwap = (rand.nextInt(10 - 5) + 5) * 1000;
			int swapped =0;

			long timeColourSwapped;
			long playerReactionTime;

			public void handle(long currentNanoTime){
				long t = currentNanoTime - lastNanoTime;
				long ms = TimeUnit.NANOSECONDS.toMillis(t);

				if(ms >= timeBetweenSwap && !colourSwapped){
					timeColourSwapped = currentNanoTime - lastNanoTime;
					colourSwapped = true;
					timeBetweenSwap = (rand.nextInt(10 - 3) + 3) * 1000;
					changeBackground(swapped);
					lastNanoTime = currentNanoTime;
				}

				if(playerReacted){
					playerReactionTime = currentNanoTime - lastNanoTime;
					playerReactionTime = TimeUnit.NANOSECONDS.toMillis(playerReactionTime);
                    lastNanoTime = currentNanoTime;
                    reactionTimes[swapped] = playerReactionTime;

                    // Keep track of the best time set by the player.
                    if(playerReactionTime < bestTime || bestTime == 0)
                        bestTime = playerReactionTime;

                    gameInfo.setText("Best Time: " + bestTime + " Previous Time: " + playerReactionTime);
                    swapped++;
                    playerReacted = false;
                    colourSwapped = false;
				}

				if(swapped == attempts){
					stop();
					mainPane.getChildren().remove(gameInfo);
                    endGameScreen();
                }

			}
		}.start();
	}

	private void changeBackground(int newColour){
		switch (newColour % 8){
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

	private void endGameScreen(){
        gameInfo.setText("Game Over\nBest score: "  + bestTime + " Average score: " + averageTime());
        mainPane.getChildren().add(gameInfo);

        restartGame.setVisible(true);
        exitGame.setVisible(true);

    }

    private float averageTime(){
	    float averageTime = 0;

	    for(float reaction : reactionTimes)
	        averageTime += reaction;

	    return averageTime / reactionTimes.length;
    }

    public void restartGame(){
		Arrays.fill(reactionTimes, 0);

		bestTime = 0;
		colourSwapped = false;
		playerReacted = false;
		restartGame.setVisible(false);
		exitGame.setVisible(false);
		playGame();
	}

	public void exitProgram(){
		Platform.exit();
	}
}
