import java.util.*;
public class Game {
  private ArrayList<Plant> plants = new ArrayList<Plant>();
  private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
  private PlayerSun suns = new PlayerSun();
  private ArrayList<Waves> levels = new ArrayList<Waves>();
  private Board lawn;
  private UIManager menu;
  private boolean gameMode;
  private boolean mainMenu;
  private boolean pauseScreen;
  private boolean endScreen;
  
  public Game(int row, int col) {
    lawn = new Board(row, col);
  }
  private void startLevel(int idx){
    // smth with levels
  }
}
