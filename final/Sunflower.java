import processing.core.PApplet;
import java.awt.Point;

public class Sunflower extends Plant {
    private int cooldown = 0;
    private static Game gameRef;

    public Sunflower(int row, int col) {
        super(row, col);
    }

    public void update() {
        cooldown--;
        if (cooldown <= 0) {
            int px = col * 100 + 50;
            int py = row * 100 + 50;
            gameRef.spawnSun(new Point(px, py));
            cooldown = 300; // every 5 seconds
        }
    }

    public void show(PApplet p) {
        p.fill(255, 204, 0);
        p.ellipse(col * 100 + 50, row * 100 + 50, 40, 40);
    }

    public static void setGame(Game g) {
        gameRef = g;
    }
}
