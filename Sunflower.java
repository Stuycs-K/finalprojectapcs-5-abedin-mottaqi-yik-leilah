import processing.core.PApplet;
import java.awt.Point;
public class Sunflower extends Plant {
    private int cooldown = 0; // renamed for consistency
    private static Game gameRef; // need this for now to make it run properly

    public Sunflower(int row, int col){
      super(row,col);
    }

    @Override
    public void update(){
      cooldown--;
      if(cooldown <= 0){
        int px = col * 100 + 50;
        int py = row * 100 + 50;
        gameRef.spawnSun(new Point(px, py));
        cooldown = 1440; // every 24 seconds
      }
    }
    @Override
    public void show(PApplet p){
      p.fill(255, 204, 0);
      p.ellipse(getCol() * 100 + 50, getRow() * 100 + 50, 40, 40);
    }

    // need this for now to make it run properly
    public static void setGame(Game g){
      gameRef = g;
    }
}
