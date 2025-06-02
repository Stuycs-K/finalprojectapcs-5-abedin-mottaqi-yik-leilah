import processing.core.PApplet;
import java.awt.Point;

public interface Displayable {
    void show(PApplet p);
    Point getPos();
    // Removed hide, since it's useless
}
