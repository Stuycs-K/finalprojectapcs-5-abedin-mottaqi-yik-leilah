import java.awt.Point;
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

    public void show(){
        sprite = loadImage("pea.png");
        image(sprite, getX(), getY());
    }
}
