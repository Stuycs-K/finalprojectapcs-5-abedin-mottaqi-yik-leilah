import java.awt.Point;
import processing.core.PApplet;

public abstract class Zombie implements Displayable {
    protected float x, y;
    protected int health;

    public Zombie(Point start, int hp) {
        this.x = start.x;
        this.y = start.y;
        this.health = hp;
    }

    public Point getPos() {
        return new Point((int)x, (int)y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
    }

    public abstract void update();
    public abstract void show(PApplet p);
}
