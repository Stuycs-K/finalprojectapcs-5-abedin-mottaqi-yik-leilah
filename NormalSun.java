import processing.core.PApplet;
import java.awt.Point;
import processing.core.PApplet;
public class NormalSun implements Interactable {
    private Point pos;
    private boolean collected = false;
    private final int fallSpeed = 1; // idk what this value should be pls figure it out leilah thanks
    private final int radius = 20; // idk what this value should be pls figure it out leilah thanks
    private int finalRow;
    private PApplet p;
    /*
     * notes:
     * - sun spawns roughly every 8 seconds
     * - normal sun provides 25 sun
     * - sunflower production rate is every 24 seconds
     */

     public NormalSun(){
       // spawn in random location on lawn
       this.pos = new Point((int)(Math.random() * p.width), 0);
       finalRow = (int) (Math.random() * 6);
     }

     public NormalSun(int row){
       // spawn in random location on lawn
       this.pos = new Point((int)(Math.random() * p.width), 0);
       finalRow = row;
     }

     // what does this constructor do...
    public NormalSun(Point pos) {
        this.pos = new Point(pos);
    }

    @Override
    public void interact() {
        collected = true;
    }

    @Override
    public void update() {
        // update 15 to be whatever the width of a row will be + the sky
        if (pos.getY() < finalRow * 15 + 50) this.pos.move((int)pos.getX(), (int)(pos.getY() + fallSpeed * 0.05));
    }
    @Override
    public void show() {
    }
    @Override
    public void hide() {
    }
    public boolean isCollected() {
        return collected;
    }
    public void collect(PlayerSun playersun){
        if (!collected) {
            collected = true;
            playersun.addSun(25); // too lazy to research
        }
    }
}
