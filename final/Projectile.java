import java.awt.Point;
import processing.core.PApplet;

public abstract class Projectile implements Displayable {
    protected float x, y;
    protected float speed;
    protected int damage;
    protected boolean remove = false;

    public Projectile(Point start, float speed, int damage) {
        this.x = start.x;
        this.y = start.y;
        this.speed = speed;
        this.damage = damage;
    }

    public Point getPos() {
        return new Point((int)x, (int)y);
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void markForRemoval() {
        remove = true;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void update();
    public abstract void show(PApplet p);
}
