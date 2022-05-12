package m4u.mediaplayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;



public class M4UMediaPlayerController implements Initializable
{ 
    @FXML private AnchorPane anpMediaPlayer;
    @FXML private MediaView midvMediaPlayer;
    
    private MediaPlayer mediaPlayer;
    private Media media;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// Random number in between 0 (inclusive) and 2 (exclusive)
		String[] str = {"movie1", "movie2"};
		Random ran = new Random();
		int low = 0;
		int high = 2;
		String randMovie = str[ran.nextInt(high-low) + low];
		
		// Media Player
		String path = new File("src/m4u/movie/"+randMovie+".mp4").getAbsolutePath();
		media = new Media(new File(path).toURI().toString()); 
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		midvMediaPlayer.setMediaPlayer(mediaPlayer);
		
		DoubleProperty width = midvMediaPlayer.fitWidthProperty();
		DoubleProperty height = midvMediaPlayer.fitHeightProperty();
		width.bind(Bindings.selectDouble(midvMediaPlayer.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(midvMediaPlayer.sceneProperty(), "height"));
	}
	
	
	@FXML
	private void goBack(ActionEvent event) throws IOException
	{
		mediaPlayer.stop();
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../library/m4uMovieLibrary.fxml"));
		anpMediaPlayer.getChildren().setAll(pane);
	}
	
	
	@FXML
	private void play(ActionEvent event)
	{
		mediaPlayer.play();
	}
	
	
	@FXML
	private void pause(ActionEvent event)
	{
		mediaPlayer.pause();
	}
	
	
	@FXML
	private void replay(ActionEvent event)
	{
		mediaPlayer.seek(mediaPlayer.getStartTime());
		mediaPlayer.play();
	}
	

}
