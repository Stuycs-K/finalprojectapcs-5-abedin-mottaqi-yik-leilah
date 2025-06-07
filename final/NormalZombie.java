import java.awt.Point;
import processing.core.PApplet;

public class NormalZombie extends Zombie {
    private float speed = 0.5f;

    public NormalZombie(Point start) {
        super(start, 100); // 100 HP
    }

    public void update() {
        x -= speed;
    }

    public void show(PApplet p) {
        p.fill(100, 150, 100);
        p.rect(x - 20, y - 40, 40, 80);
    }
}
