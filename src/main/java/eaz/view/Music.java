package eaz.view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {
    private Clip clip;
    private FloatControl volumeControl;

    public Music(String filePath) {
        try {
//            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Initialize volume control
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
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
            clip.setFramePosition(0); 
            clip.close();
        }
    }

//    public void volumeUp() {
//        if (volumeControl != null) {
//            float currentVolume = volumeControl.getValue();
//            float maxVolume = volumeControl.getMaximum();
//            float newVolume = Math.min(currentVolume + 1.0f, maxVolume);
//            volumeControl.setValue(newVolume);
//        }
//    }
//
//    public void volumeDown() {
//        if (volumeControl != null) {
//            float currentVolume = volumeControl.getValue();
//            float minVolume = volumeControl.getMinimum();
//            float newVolume = Math.max(currentVolume - 1.0f, minVolume);
//            volumeControl.setValue(newVolume);
//        }
//    }
}
