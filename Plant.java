import java.awt.Point;
import processing.core.PApplet;
public abstract class Plant implements Displayable, Interactable{
  // private int cost; gonna remove for now, trying to make a functioning game
  // private int health; gonna remove for now, trying to make a functioning game
  private int row, col;
  private boolean remove = false; // having a boolean 'remove' might be easier, could change later, just trying to make game work 

  public Plant(int row, int col){
    this.row=row; // lets use row and col instead of point since plants dont need to move smoothly
    this.col=col;
  }
  
  public int getRow(){
    return row;
  }

  public int getCol(){
    return col;
  }

  public Point getPos(){
    return new Point(col * 100 + 50, row * 100 + 50); // will need this for collision with zombie later
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

  public abstract void update();
  public abstract void show(PApplet p);
  /* gonna remove for now, trying to make a functioning game 
  public int getCost(){
    return cost;
  } 
  public int getHealth(){
    return health;
  }
  public void takeDamage(int damage){
    if(damage>health){
      health=0;
    }
    this.health -= damage;
  }
  public abstract void update();
  public abstract void ability();
  */ 
}
