package application;

import java.awt.Button;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class MediaBar extends HBox {
	
	Slider timeSlider = new Slider();
	Slider volumeSlider= new Slider();
	Button playButton=new Button(" || ");
	Label volume = new Label("Volume: ");
	MediaPlayer mediaPlayer;
	
	public MediaBar(MediaPlayer player)
	{
		
	}

}
