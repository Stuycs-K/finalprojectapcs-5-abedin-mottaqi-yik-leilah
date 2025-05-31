import java.awt.Point;
import processing.core.PApplet;
public class Pea extends Projectile {
    public Pea(Point start) {
        super(start, 4f, 20); // speed, damage
    }

    public void update(){
        addX(getSpeed());
        if (getX() > 900) {
            markRemoval();
        };
    }

    public void show(PApplet p){
        p.fill(50, 200, 50);
        p.ellipse(getX(), getY(), 15, 15);
    }
}
