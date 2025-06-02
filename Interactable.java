import processing.core.PApplet;
public interface Interactable{ // made it not extend Displayable since it would be easier to code it this way
    boolean clicked(int mouseX, int mouseY); // did the mouse click on the object
    void onClick(); // action made when clicked
}
