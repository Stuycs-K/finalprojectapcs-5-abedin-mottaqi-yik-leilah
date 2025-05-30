import java.awt.Point;
public class NormalSun implements Interactable {
    private Point pos;
    private boolean collected = false;
    private final int fallSpeed = 1; // idk what this value should be pls figure it out leilah thanks
    private final int radius = 20; // idk what this value should be pls figure it out leilah thanks
    private int finalRow;
    /*
     * notes:
     * - sun spawns roughly every 8 seconds
     * - normal sun provides 25 sun
     * - sunflower production rate is every 24 seconds
     */

     public NormalSun(){
       // spawn in random location on lawn
       this.pos = new Point(Math.random() * width, 0);
       finalRow = (int) (Math.random() * 6);
     }

     public NormalSun(int row){
       // spawn in random location on lawn
       this.pos = new Point(Math.random() * width, 0);
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
        if (pos.getY() < finalRow * 15 + 50) this.pos.move(pos.getX(), pos.get(Y) + fallSpeed * 0.05);
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
