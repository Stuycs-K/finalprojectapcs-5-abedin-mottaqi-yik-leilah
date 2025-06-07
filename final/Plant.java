import java.awt.Point;
import processing.core.PApplet;

public abstract class Plant implements Displayable, Interactable {
    protected int row, col;
    protected boolean remove = false;

    public Plant(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Point getPos() {
        return new Point(col * 100 + 50, row * 100 + 50);
    }

    public boolean clicked(int mx, int my) {
        return Math.abs(mx - getPos().x) < 40 && Math.abs(my - getPos().y) < 40;
    }

    public void onClick() {
        remove = true;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public abstract void update();
    public abstract void show(PApplet p);
}
