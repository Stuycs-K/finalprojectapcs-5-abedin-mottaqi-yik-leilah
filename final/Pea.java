import processing.core.PApplet;
import java.awt.Point;

public class Pea extends Projectile {

    public Pea(Point start) {
        super(start, 4f, 20); // speed, damage
    }

    public void update() {
        x += speed;
        if (x > 900) remove = true;
    }

    public void show(PApplet p) {
        p.fill(50, 200, 50);
        p.ellipse(x, y, 15, 15);
    }
}
