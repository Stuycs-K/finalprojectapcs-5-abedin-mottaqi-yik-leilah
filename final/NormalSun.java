import java.awt.Point;
import processing.core.PApplet;

public class NormalSun implements Displayable, Interactable {
    private float x, y;
    private boolean collected = false;

    public NormalSun(Point location) {
        this.x = location.x;
        this.y = location.y;
    }

    // Show the sun on screen
    public void show(PApplet p) {
        if (!collected) {
            p.fill(255, 255, 0);
            p.ellipse(x, y, 30, 30);
        }
    }

    // Return current position
    public Point getPos() {
        return new Point((int)x, (int)y);
    }

    // Return true if the mouse clicked within a small circle around sun
    public boolean clicked(int mouseX, int mouseY) {
        float dx = mouseX - x;
        float dy = mouseY - y;
        return dx * dx + dy * dy <= 400;  // radius 20
    }

    // Collect the sun (mark it as removed)
    public void onClick() {
        collected = true;
    }

    // Check if the sun is collected
    public boolean isCollected() {
        return collected;
    }

    // Optional update logic if you want it to float down or expire
    public void update() {
       if (!collected) y += 1;
    }
}
