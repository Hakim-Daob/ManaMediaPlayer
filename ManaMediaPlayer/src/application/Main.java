package application;
	

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	Player player;
	FileChooser fileChosser;
	public void start(final Stage primaryStage) {
		
		MenuItem openFile = new MenuItem("openFile");
		Menu fileMenu=new Menu("File");
		MenuBar menuBar = new MenuBar();
		
		fileMenu.getItems().add(openFile);
		menuBar.getMenus().add(fileMenu);
		
		fileChosser= new FileChooser();
		openFile.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				File file = fileChosser.showOpenDialog(primaryStage);
				if (file!=null)
					try {
						player=new Player(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene(player, 720, 535, Color.BLACK);
						primaryStage.setScene(scene);
					} catch (MalformedURLException el){
						el.printStackTrace();
					}			
			}
		});
	
		player = new Player("file:///d:Life.mp4");
		player.setTop(menuBar);
		Scene scene= new Scene(player,1080, 480, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();			
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}