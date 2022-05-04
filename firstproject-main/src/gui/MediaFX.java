/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author masmoudi
 */
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class MediaFX extends Application {
MediaPlayer mediaPlayer;
String URL;

public MediaFX(String ch)
{
    this.URL=ch;
}
    
    public void start() throws Exception {
        Media musicfile = new Media(URL);
        mediaPlayer = new MediaPlayer(musicfile);
        mediaPlayer.setAutoPlay(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
