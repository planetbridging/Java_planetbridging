/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

/**
 *
 * @author shadowlord101
 */
import java.net.URL;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PlayPop extends Application {
    private String Sound;
    void playMedia() {
        URL resource = getClass().getResource(Sound);
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    public void Play(String s) {
        Sound = "/mp3/" + s;
        JFXPanel fxPanel = new JFXPanel();
        playMedia();
    }
    @Override
    public void start(Stage stage) throws Exception {
    }
}
