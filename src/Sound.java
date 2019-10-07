import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static void gameoverSound(){
		Clip music;
		try{
			AudioInputStream stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/sounds/gameover.wav"));
			music = AudioSystem.getClip();
			music.open(stream);
			music.start();
		}
		catch(Exception e){
			e.printStackTrace();
			music = null;
		}
	}
	
	public static void levelupSound(){
		Clip music;
		try{
			AudioInputStream stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/sounds/levelup.wav"));
			music = AudioSystem.getClip();
			music.open(stream);
			music.start();
		}
		catch(Exception e){
			e.printStackTrace();
			music = null;
		}
	}
	
	public static void hitSound(){
		Clip music;
		try{
			AudioInputStream stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/sounds/hit.wav"));
			music = AudioSystem.getClip();
			music.open(stream);
			music.start();
		}
		catch(Exception e){
			e.printStackTrace();
			music = null;
		}
	}
}