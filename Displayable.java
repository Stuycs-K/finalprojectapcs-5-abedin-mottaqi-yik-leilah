public class Displayable {
    private Point pos;
    private boolean hidden;

    public Point getPos(){
        return pos;
    }

    public void show() {
        hidden = true;
    }

    public void hide() {
        hidden = false;
    }
}
