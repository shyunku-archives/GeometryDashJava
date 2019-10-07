package Managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Core.Functions;
import Objects.SoundTrack;

public class SoundManager {
	public static final int MAIN_LOOP = 1000;
	
	public HashMap<Integer, SoundTrack> soundBundle = new HashMap<>();
	public SoundTrack curPlaying = null;
	
	public static String getSoundPath(String filename) {
		return "resources\\sound\\"+filename;
	}
	
	public SoundManager() {
		soundBundle.put(MAIN_LOOP, new SoundTrack("menuLoop.wav", true));
	}
	
	public void playSoundTrack(int key) {
		if(curPlaying != null)curPlaying.end();
		curPlaying = getSoundTrack(key);
		curPlaying.start();
	}
	
	public void stopSoundTrack() {
		curPlaying.end();
		curPlaying = null;
	}
	
	public static SoundTrack getSoundTrack(int KEY) {
		SoundTrack track = (SoundTrack) ManagerManager.sm.soundBundle.get(KEY);
		return track;
	}
}
