package models;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	Clip clip;
	public void sound(){
		try {
			File file = new File("sounds/music.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			System.out.println("Music...");
			clip.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
	}
}