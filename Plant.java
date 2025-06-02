import java.awt.Point;
import processing.core.PApplet;
public abstract class Plant implements Displayable, Interactable{
  private int cost;
  private int health;
  private int row, col;
  private boolean remove = false; // having a boolean 'remove' might be easier, could change later, just trying to make game work 

  public Plant(int row, int col, int cost, int health){
    this.row=row; // lets use row and col instead of point since plants dont need to move smoothly
    this.col=col;
    this.cost=cost;
    this.health=health;
  }
  
  public int getRow(){
    return row;
  }

  public int getCol(){
    return col;
  }

  public Point getPos(){
    return new Point(col * 100 + 50, row * 100 + 50 + 100); // will need this for collision with zombie later
  }

  public int getHealth(){
    return health;
  }

  public int getCost(){
    return cost;
  }

  public boolean clicked(int mouseX, int mouseY){
    return Math.abs(mouseX - getPos().x) < 40 && Math.abs(mouseY - getPos().y) < 40;
  }

  public void onClick(){
    remove = true;
  }

  public boolean shouldRemove(){
    return remove;
  }

  public void takeDamage(int damage){
    if(damage>health){
      health=0;
    }
    this.health -= damage;
  }

  public abstract void update();
  public abstract void show(PApplet p);
}
