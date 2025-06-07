import processing.core.PApplet;
import java.awt.Point;

public class Peashooter extends Plant {
  private int cooldown = 0;
  private static Game gameRef;

  public Peashooter(int row, int col) {
    super(row, col);
  }

  public void update() {
    cooldown--;
    if (cooldown <= 0) {
      Point peaStart = new Point(col * 100 + 50, row * 100 + 50);
      Pea pea = new Pea(peaStart);
      gameRef.addProjectile(pea);
      cooldown = 60;
    }
  }

  public void show(PApplet p) {
    p.fill(0, 255, 0);
    p.ellipse(col * 100 + 50, row * 100 + 50, 40, 40);
  }

  public static void setGame(Game g) {
    gameRef = g;
  }
}
