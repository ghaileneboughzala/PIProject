/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Culture;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import services.CultureService;
import javafx.beans.value.ChangeListener;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CultureFrontFXMLController implements Initializable {

    @FXML
    private ScrollPane Scrollepane;
    
    
    CultureService cs= new CultureService();
    @FXML
    private VBox pnl_scrollO;
    @FXML
    private Label songLabel;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private ComboBox<String> speedBox;
    @FXML
    private Slider volumeSlider;
    
    private Media media;
	private MediaPlayer mediaPlayer;
	
	private File directory;
	private File[] files;
	
	private ArrayList<File> songs;
	
	private int songNumber;
	private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};
	
	private Timer timer;
	private TimerTask task;
	
	private boolean running;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO
        
        songs = new ArrayList<File>();
		
		directory = new File("music");
		
		files = directory.listFiles();
		
		if(files != null) {
			
			for(File file : files) {
				
				songs.add(file);
			}
		}
		
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		songLabel.setText(songs.get(songNumber).getName());
		
		for(int i = 0; i < speeds.length; i++) {
			
			speedBox.getItems().add(Integer.toString(speeds[i])+"%");
		}
		
		speedBox.setOnAction(this::changeSpeed);
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);			
			}
		});
		
		songProgressBar.setStyle("-fx-accent: #00FF00;");
                refreshNodes();
                playMedia();
    }    
    
    
    private void refreshNodes() {
        pnl_scrollO.getChildren().clear();

        List<Culture> listC = cs.recupererList();
        Node[] nodes = new Node[listC.size()];
        int i = 0;

        for (Culture each : listC) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemCulture.fxml"));
            ItemCultureController cont = new ItemCultureController();
            try {
                cont.c = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scrollO.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(ItemCultureController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }

    @FXML
    public void playMedia() {
		
		beginTimer();
		changeSpeed(null);
		mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
		mediaPlayer.play();
	}
	
    @FXML
	public void pauseMedia() {
		
		cancelTimer();
		mediaPlayer.pause();
	}
	
    @FXML
	public void resetMedia() {
		
		songProgressBar.setProgress(0);
		mediaPlayer.seek(Duration.seconds(0));
	}
	
    @FXML
	public void previousMedia() {
		
		if(songNumber > 0) {
			
			songNumber--;
			
			mediaPlayer.stop();
			
			if(running) {
				
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs.get(songNumber).getName());
			
			playMedia();
		}
		else {
			
			songNumber = songs.size() - 1;
			
			mediaPlayer.stop();
			
			if(running) {
				
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs.get(songNumber).getName());
			
			playMedia();
		}
	}
	
    @FXML
	public void nextMedia() {
		
		if(songNumber < songs.size() - 1) {
			
			songNumber++;
			
			mediaPlayer.stop();
			
			if(running) {
				
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs.get(songNumber).getName());
			
			playMedia();
		}
		else {
			
			songNumber = 0;
			
			mediaPlayer.stop();
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs.get(songNumber).getName());
			
			playMedia();
		}
	}
	
    @FXML
	public void changeSpeed(ActionEvent event) {
		
		if(speedBox.getValue() == null) {
			
			mediaPlayer.setRate(1);
		}
		else {
			
			//mediaPlayer.setRate(Integer.parseInt(speedBox.getValue()) * 0.01);
			mediaPlayer.setRate(Integer.parseInt(speedBox.getValue().substring(0, speedBox.getValue().length() - 1)) * 0.01);
		}
	}
	
	public void beginTimer() {
		
		timer = new Timer();
		
		task = new TimerTask() {
			
                        @Override
			public void run() {
				
				running = true;
				double current = mediaPlayer.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				songProgressBar.setProgress(current/end);
				
				if(current/end == 1) {
					
					cancelTimer();
				}
			}
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	public void cancelTimer() {
		
		running = false;
		timer.cancel();
	}
    
}