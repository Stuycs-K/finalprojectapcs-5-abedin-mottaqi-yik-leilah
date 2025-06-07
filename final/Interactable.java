public interface Interactable {
    boolean clicked(int mouseX, int mouseY);  // Is the mouse clicking on this?
    void onClick();                           // What happens when clicked?
}
