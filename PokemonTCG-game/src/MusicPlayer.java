import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class MusicPlayer {
    private static Clip clip;

    public static void playMusic(String filePath) {
        try {
            URL soundURL = MusicPlayer.class.getResource(filePath);
            if (soundURL == null) {
                System.err.println("⚠ Music file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // ✅ Add a loop listener to restart when music ends
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.setFramePosition(0); // Rewind to the beginning
                    clip.start(); // Restart music
                }
            });

            clip.start(); // Start playing
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
