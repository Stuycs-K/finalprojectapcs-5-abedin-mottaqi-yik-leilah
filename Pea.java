import java.awt.Point;
import processing.core.PApplet;
import processing.core.PImage;
public class Pea extends Projectile {
    private PImage sprite;
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
        sprite = p.loadImage("pea.png");
        p.image(sprite, getX(), getY());
    }
}
