import java.awt.Point;
public class NormalSun implements Displayable, Interactable {
    private float x,y;
    private float yFinal;
    private boolean collected = false;
    private PImage sprite;
    /*
     * notes:
     * - sun spawns roughly every 8 seconds
     * - normal sun provides 25 sun
     * - sunflower production rate is every 24 seconds
     */

    // random spawning
    public NormalSun(){
        // spawn in random location on lawn
        int col = (int)(Math.random() * 9);
        this.x = col * 100 + 50;
        this.y = -10;
        // sun will stop falling at a certain point
        this.yFinal = (float) Math.random() * (500) + 100;
    }

    // sunflower spawning
    public NormalSun(Point spawn){
        this.x = spawn.x;
        this.y = spawn.y;
        this.yFinal = spawn.y;
    }
    @Override
    public void show() {
        if (!collected) {
            sprite = loadImage("sun.png");
            sprite.resize(50,50);
            image(sprite, x-25, y-25);
        }
    }

    public Point getPos() {
        return new Point((int)x, (int)y);
    }

    public void update() {
        if (!collected && Math.abs(y - yFinal) > 0.7){
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