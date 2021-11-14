import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class RacingCircles extends GraphicsApp {

    private static final Color RED = new Color(234, 49, 63); // "Selbstgemischter" RGB-Farbe (rot)
    private static final Color YELLOW = new Color(234, 182, 56); // "Selbstgemischter" RGB-Farbe (gelb)
    private static final Color GREEN = new Color(76, 149, 80); // "Selbstgemischter" RGB-Farbe (grün)
    private static final Color BLUE = new Color(53, 129, 184); // "Selbstgemischter" RGB-Farbe (blau)
    private static final Color GREY = new Color(47, 61, 76); // "Selbstgemischter" RGB-Farbe (grau)

    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 720;
    private static final Color BACKGROUND_COLOR = GREY;

    @Override
    public void initialize() {
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch("RacingCircles");
    }
}
