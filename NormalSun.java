import processing.core.PApplet;
import java.awt.Point;
public class NormalSun implements Displayable, Interactable {
    private float x,y;
    private boolean collected = false;
    // private int finalRow; gonna remove for now, trying to make a functioning game
    /*
     * notes:
     * - sun spawns roughly every 8 seconds
     * - normal sun provides 25 sun
     * - sunflower production rate is every 24 seconds
     */

    public NormalSun(Point spawn){
        // spawn in random location on lawn
        int row = (int)(Math.random() * 5);
        int col = (int)(Math.random() * 9);
        this.x = col * 50 + 25;
        this.y = row * 50 + 25;
    }
    @Override
    public void show(PApplet p) {
        if (!collected) {
            p.fill(255, 255, 0);
            p.ellipse(x, y, 30, 30);
        }
    }

    public Point getPos() {
        return new Point((int)x, (int)y);
    }

    public void update() {
        // make it fall, can change later
        if (!collected){
            y += 1;
        }
    }
    public boolean isCollected() {
        return collected;
    }

    @Override
    public void onClick(){
        collected = true;
    }

    @Override
    public boolean clicked(int mouseX, int mouseY) {
        float dx = mouseX - x;
        float dy = mouseY - y;
        return dx * dx + dy * dy <= 400;  // radius 20
    }
}
