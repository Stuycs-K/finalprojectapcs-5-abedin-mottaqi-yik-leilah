import java.awt.Point;
public class NormalSun implements Interactable {
    private Point pos;
    private boolean collected = false;
    private final int fallSpeed = 1; // idk what this value should be pls figure it out leilah thanks
    private final int radius = 20; // idk what this value should be pls figure it out leilah thanks 
    
    /*
     * notes:
     * - sun spawns roughly every 8 seconds
     * - normal sun provides 25 sun 
     * - sunflower production rate is every 24 seconds
     */


    public NormalSun(Point pos) {
        this.pos = new Point(pos);
    }

    @Override
    public void interact() {
        collected = true;
    }
    @Override    
    public void update() {
    }
    @Override
    public void show() {
    }
    @Override
    public void hide() {
    }
    @Override
    public Point getPos() {
        return pos;
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
