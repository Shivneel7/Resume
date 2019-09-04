package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
/**
 * Makes a playable clip
 */
public class Audio{

	private Clip clip;
	private AudioInputStream stream;
	/**
	 * 
	 * @param path -String with the path for the .wav file that will be converted into playable audio
	 * @param volume -a float for the volume control negative values decrease volume, positive increases volume
	 */
	public Audio(String path, float volume){
		try {
			clip = AudioSystem.getClip();
			stream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip.open(stream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param path -String with the path for the .wav file that will be converted into playable audio
	 */
	public Audio(String path){
		try {
			clip = AudioSystem.getClip();
			stream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip.open(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * play the audio. resets audio if this is called while current audio is playing
	 */
	public void play() {
		clip.start();
		if(!clip.isActive()) {
		    clip.setFramePosition(0);
			clip.flush();
		}
	}
	/**
	 * play the audio. resets audio if this is called while current audio is playing
	 * 
	 * @param -start position when play is called.
	 */
	public void play(int start) {
		clip.start();
		if(!clip.isActive()) {
		    clip.setFramePosition(start);
			clip.flush();
		}
	}
}