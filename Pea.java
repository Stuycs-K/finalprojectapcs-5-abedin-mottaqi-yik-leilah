import java.awt.Point;
import processing.core.PApplet;
public class Pea extends Projectile {
    public Pea(Point start, int damage) {
        super(start, 4f, damage); // speed, damage
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
