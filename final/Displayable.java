import processing.core.PApplet;
import java.awt.Point;

public interface Displayable {
    void show(PApplet p);   // How it appears (drawing)
    Point getPos();         // For layout/collision/debug
}
