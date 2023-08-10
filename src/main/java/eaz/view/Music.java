package eaz.view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {
    private Clip clip;

    public Music(String filePath) {
        try {
            File audioFile = new File("src/main/resources/MapleStory_RainDrops.mp3");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
