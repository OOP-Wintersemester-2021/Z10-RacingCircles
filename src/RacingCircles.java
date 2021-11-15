import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Circle;
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
    private static final Color[] CIRCLE_COLORS = {RED, YELLOW, GREEN, BLUE};
    private static final int NUMBER_OF_CIRCLES = 36;
    private static final int MAX_CIRCLE_SPEED = 8;

    private Circle[] circles;

    @Override
    public void initialize() {
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        circles = createRandomCircles();
    }

    private Circle[] createRandomCircles() {
        Circle[] circles = new Circle[NUMBER_OF_CIRCLES];
        int circleRadius = (getHeight() / NUMBER_OF_CIRCLES) / 4;
        for (int i = 0; i < circles.length; i++) {
            circles[i] = createRandomCircleForPosition(i, circleRadius);
        }
        return circles;
    }

    private Circle createRandomCircleForPosition(int position, int radius) {
        int x = -radius;
        int y = (2 * radius) + (position * (4 * radius));
        int randomIndex = getRandomInt(0, CIRCLE_COLORS.length - 1);
        return new Circle(x, y, radius, CIRCLE_COLORS[randomIndex]);
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        updateAndDrawCircles(circles);
    }

    private void updateAndDrawCircles(Circle[] circles) {
        for (Circle circle : circles) {
            int speed = getRandomInt(1, MAX_CIRCLE_SPEED);
            circle.move(speed, 0);
            if (circle.getXPos() > (getWidth() + circle.getRadius())) {
                circle.setXPos(-circle.getRadius());
            }
            circle.draw();
        }
    }

    private int getRandomInt(int min, int max) {
        int range = (max - min) + 1;
        return min + (int) (Math.random() * range);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch("RacingCircles");
    }
}
