import javax.swing.*;

public class AnimationUtils {
    public static void attackAnimation(JLabel label) {
        int originalX = label.getX();
        int originalY = label.getY();

        for (int i = 0; i < 5; i++) {
            label.setLocation(originalX + 5, originalY);
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            label.setLocation(originalX - 5, originalY);
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        label.setLocation(originalX, originalY);
    }

    public static void fadeIn(JLabel label, ImageIcon icon) {
        label.setIcon(icon);
        for (float i = 0; i <= 1; i += 0.1) {
            label.setOpaque(true);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
