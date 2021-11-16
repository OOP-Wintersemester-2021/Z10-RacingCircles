import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * In dieser Anwendung werden eine große Anzahl an Kreisen animiert. Die Kreise starten außerhalb des linken
 * Bildschirmrands, vertikal untereinander und bewegen sich horizontal nach rechts. Sobald ein Kreis den rechten
 * Bildschirmrand vollständig verlassen hat, wird er auf die initiale Position links zurückgesetzt. Die Farbe der
 * einzelnen Kreise wird beim Erstellen zufällig aus einem Array möglicher Werte bestimmt. Die Geschwindigkeit jedes
 * Kreises wird für jeden Frame ebenfalls zufällig innerhalb bestimmter Grenzen bestimmt.
 */
public class RacingCircles extends GraphicsApp {

    private static final Color RED = new Color(234, 49, 63); // "Selbstgemischter" RGB-Farbe (rot)
    private static final Color YELLOW = new Color(234, 182, 56); // "Selbstgemischter" RGB-Farbe (gelb)
    private static final Color GREEN = new Color(76, 149, 80); // "Selbstgemischter" RGB-Farbe (grün)
    private static final Color BLUE = new Color(53, 129, 184); // "Selbstgemischter" RGB-Farbe (blau)
    private static final Color GREY = new Color(47, 61, 76); // "Selbstgemischter" RGB-Farbe (grau)

    // Breite des Anwendungsfensters
    private static final int WINDOW_WIDTH = 1280;
    // Höhe des Anwendungsfensters
    private static final int WINDOW_HEIGHT = 720;
    // Hintergrundfarbe
    private static final Color BACKGROUND_COLOR = GREY;
    // Array mit möglichen Farben für die einzelnen Kreise
    private static final Color[] CIRCLE_COLORS = {RED, YELLOW, GREEN, BLUE};
    // Anzahl der darzustellenden Kreise
    private static final int NUMBER_OF_CIRCLES = 36;
    // Maximale Geschwindigkeit der Kreise in "Pixel pro Frame"
    private static final int MAX_CIRCLE_SPEED = 8;

    // Array mit allen in der Anwendung animierten Kreisen
    private Circle[] circles;

    @Override
    public void initialize() {
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // Das randomisierte Erstellen aller Kreise wird in eine Methode ausgelagert, die ein Array zurückgibt
        circles = createRandomCircles();
    }

    /**
     * Die Methode erzeugt ein Array mit Circle-Objekten. Die Anzahl der Kreise wird über die Werte der
     * Konstanten bestimmt, die auf Klassenebene deklariert werden. Auf Basis dieser Parameter werden Radius
     * und Position der Kreise dynamisch bestimmt.
     *
     * @return Ein Array mit Kreisen
     */
    private Circle[] createRandomCircles() {
        // Wir erstellen das Array, in dem die Kreise gespeichert werden sollen
        Circle[] circles = new Circle[NUMBER_OF_CIRCLES];
        // Aus der Anzahl der Kreise und des verfügbaren Platzes im Anwendungsfensters berechnen wir den Radius
        int circleRadius = (getHeight() / NUMBER_OF_CIRCLES) / 4;
        // Wir iterieren über alle Stellen im Array ...
        for (int i = 0; i < circles.length; i++) {
            // ... und erstellen in jeder Zelle einen neuen Kreis
            circles[i] = createRandomCircleForPosition(i, circleRadius);
        }
        // Das befüllte Array wird anschließend zurückgegeben
        return circles;
    }

    /**
     * Die Methode erzeugt einen neuen Kreis für die Animation. Die Position wird dabei relativ (der wievielte
     * Kreis auf der vertikalen Ebene) angegeben, der Radius wird absolut angegeben. Die Farbe des neuen
     * Kreises wird zufällig ausgewählt.
     *
     * @param position Relative Position des neuen Kreises
     * @param radius Absoluter Radius des neuen Kreises
     * @return Der neu erstellte Kreis
     */
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

    /**
     * Die Methode bewegt alle Kreise im übergebenen Array zuerst um einen zufälligen Wert
     * nach rechts und zeichnet diese anschließend.
     *
     * @param circles Array mit den zu bewegenden und zu zeichnenden Kreisen
     */
    private void updateAndDrawCircles(Circle[] circles) {
        // Iteration über alle Kreise im Array ...
        for (Circle circle : circles) {
            // ... zufälliges Bestimmen der Geschwindigkeit für diesen Frame ...
            int speed = getRandomInt(1, MAX_CIRCLE_SPEED);
            // ... bewegen des Kreises mit der ausgewählte Geschwindigkeit.
            circle.move(speed, 0);
            // Wenn der Kreis durch die Bewegung den Bildschirm verlässt ...
            if (circle.getXPos() > (getWidth() + circle.getRadius())) {
                // ... setzen wir dessen Position auf die linke Seite des Bildschirms zurück
                circle.setXPos(-circle.getRadius());
            }
            circle.draw();
        }
    }

    /**
     * Die Methode gibt einen zufälligen int-Wert zwischen den angegebenen Parametern
     * zurück.
     *
     * @param min Untere Grenze für den Zufallswert
     * @param max Oberer Grenze für den Zufallswert
     * @return der zufällig bestimmte Wert
     */
    private int getRandomInt(int min, int max) {
        // Distanz zwischen unterer und oberer Grenze
        int range = (max - min) + 1; // Plus 1, um die obere Grenze mit einzubeziehen
        return min + (int) (Math.random() * range);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch("RacingCircles");
    }
}
