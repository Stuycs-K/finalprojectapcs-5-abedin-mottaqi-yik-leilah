import java.util.*;
import processing.core.PApplet;
public class Game {
  private ArrayList<Plant> plants = new ArrayList<Plant>();
  private ArrayList<Zombie> zombie = new ArrayList<Zombie>();
  private PlayerSun suns = new PlayerSun();
  private ArrayList<SoundFile> music = new ArrayList<SoundFile>();
  private ArrayList<Waves> levels = new ArrayList<Waves>();
  private Waves current = null;
  private Board lawn;
  private UIManager menu;

  private boolean gameMode;
  private boolean mainMenu;
  private boolean pauseScreen;
  private boolean endScreen;


  public Game(int ScreenWidth, int ScreenHeight, PApplet p) {
    lawn = new Board(5,9,ScreenWidth,ScreenHeight,p);
    Waves level1 = new Waves();
    for (int i=0; i<5; i++) {
      level1.addWave(1, new NormalZombie(new Point(screenWidth, i * 100)), 60*i); // should spawn 1 zombie every second i think
    }
    levels.add(level1);
  }
  private void startLevel(int idx){
    if (idx>=0 && idx<levels.size()) {
      currentWave = levels.get(idx);
      mainMenu = false;
      pauseScreen = false;
      endScreen = false;
      zombies.clear();
      plants.clear();
      lawn.clear();
    }
  }
  public void update() {
    if (mainMenu || pauseScreen || endScreen) return;
    lawn.updatePlants();
    lawn.updateZombies();
  }
}
