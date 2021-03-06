package application;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MediaBar extends HBox {

	Slider timeSlider = new Slider();
	Slider volumeSlider = new Slider();
	Button playButton = new Button(" || ");
	Label volumeLabel = new Label("Volume: ");
	MediaPlayer mediaPlayer;

	public MediaBar(MediaPlayer player) {
		mediaPlayer = player;

		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 10, 5, 10));

		volumeSlider.setPrefWidth(70);
		volumeSlider.setMinWidth(30);
		volumeSlider.setValue(100);
		
		HBox.setHgrow(timeSlider, Priority.ALWAYS);

		playButton.setPrefWidth(30);

		getChildren().add(playButton);
		getChildren().add(timeSlider);
		getChildren().add(volumeLabel);
		getChildren().add(volumeSlider);

		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Status status = mediaPlayer.getStatus();

				if (status == Status.PLAYING) {
					if (mediaPlayer.getCurrentTime().greaterThanOrEqualTo(mediaPlayer.getTotalDuration())) {
						mediaPlayer.seek(mediaPlayer.getStartTime());
						mediaPlayer.play();
					} else
						mediaPlayer.pause();
					    playButton.setText(">");
				}

				if (status == status.PAUSED || status == status.HALTED || status == status.STOPPED) {
					mediaPlayer.play();
					playButton.setText("||");
				}
			}
		});
		mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable arg0) {
				updateValues();		
			}
		});
		timeSlider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				if (timeSlider.isPressed())
				{
					mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(timeSlider.getValue()/100));
				}	
			}
		});
		
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				if (volumeSlider.isPressed())
				{
					mediaPlayer.setVolume(volumeSlider.getValue()/100);
				}
				
			}
		});
	}
	protected void updateValues() {
		Platform.runLater(new Runnable() {
			public void run() {
				timeSlider.setValue(mediaPlayer.getCurrentTime().toMillis()/mediaPlayer.getTotalDuration().toMillis()*100);
			}
		});
		
	}
	

}
