import java.awt.Point;

public interface Displayable {
    void show();
    Point getPos();
    // Removed hide, since it's useless
}