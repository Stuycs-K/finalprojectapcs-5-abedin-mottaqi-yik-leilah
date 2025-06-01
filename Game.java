import java.util.*;
import processing.core.PApplet;
import processing.core.PConstants;
import java.awt.Point;
public class Game {
  private ArrayList<Plant> plants = new ArrayList<>();
  private ArrayList<Zombie> zombies = new ArrayList<>();
  private ArrayList<Projectile> projectiles = new ArrayList<>();
  private ArrayList<NormalSun> sunObjects = new ArrayList<>();
  private PlayerSun suns = new PlayerSun();
  //private ArrayList<SoundFile> music = new ArrayList<SoundFile>();
  //private ArrayList<Waves> levels = new ArrayList<Waves>(); removed for now for testing
  private Waves waves;
  private Board board;
  private UIManager menu;
  private String selectedPlant = "Sunflower";

  private PApplet p;

  /* removed for now for testing
  private boolean gameMode;
  private boolean mainMenu;
  private boolean pauseScreen;
  private boolean endScreen; */


  public Game(int ScreenWidth, int ScreenHeight, PApplet p) {
    board = new Board(5,9,ScreenWidth,ScreenHeight,p);
    // Waves level1 = new Waves(); removed for now for testing
    this.p = p;
    Peashooter.setGame(this); // need these for now to make work
    Sunflower.setGame(this); 
    this.menu = new UIManager(p);
    /* removed for now for testing
    for (int i=0; i<5; i++) {
      level1.addWave(1, new NormalZombie(new Point(screenWidth, i * 100)), 60*i); // should spawn 1 zombie every second i think
    }
    levels.add(level1); */
  }
  public void startLevel(int idx){
    // one wave of 5 normalZombies, randomly spawning
    waves = new Waves();
    for (int i = 0; i < 5; i++) {
      int row = (int) (Math.random() * 5);
      Point spawnPos = new Point(p.width,row * 100 + 40);
      waves.addZombie(i*120,new NormalZombie(spawnPos));
    }
    /* removed for now for testing
    if (idx>=0 && idx<levels.size()) {
      currentWave = levels.get(idx);
      mainMenu = false;
      pauseScreen = false;
      endScreen = false;
      zombies.clear();
      plants.clear();
      lawn.clear();
    }*/
  }
  public void update() {
    board.updatePlants();

    if (waves != null){
      ArrayList<Zombie> newZombies = waves.update();
      for (Zombie z: newZombies) zombies.add(z);
    }

    for (Zombie z: zombies) z.update();
    for (Projectile pr: projectiles) pr.update();
    for (NormalSun s: sunObjects) s.update();

    // logic for peas hitting zombies (is a little bugged but will fix later)
    for (Projectile pr: projectiles) {
      Point pp = pr.getPos();
      for (Zombie z: zombies) {
        float zx = z.getX();
        float zy = z.getY();
        if (Math.abs(pp.x - zx) < 30 && Math.abs(pp.y - zy) < 60) {
          z.takeDamage(pr.getDamage());
          pr.markRemoval();
        }
      }
    }
    // logic for peas hitting zombies (is a little bugged but will fix later)
    for (Projectile pr: projectiles) {
      Point pp = pr.getPos();
      for (Zombie z: zombies) {
        float zx = z.getX();
        float zy = z.getY();
        if (Math.abs(pp.x - zx) < 30 && Math.abs(pp.y - zy) < 60) {
          z.takeDamage(pr.getDamage());
          pr.markRemoval();
        }
      }
    }

    // logic for removing anything that should be removed
    for (int i = zombies.size() - 1; i >= 0; i--) {
      Zombie z = zombies.get(i);
      if (z.getHealth() <= 0) {
        zombies.remove(i);
      }
    }
    for (int i = sunObjects.size() - 1; i >= 0; i--) {
      NormalSun s = sunObjects.get(i);
      if (s.isCollected()) {
        sunObjects.remove(i);
      }
    }
    for (int i = projectiles.size() - 1; i >= 0; i--) {
      Projectile pr = projectiles.get(i);
      if (pr.shouldRemove()) {
        projectiles.remove(i);
      }
    }

    // logic for when wave is complete
    if (waves != null && waves.isDone() && zombies.isEmpty()) {
      menu.setInWinScreen(true);
    }
  }

  public void render() {
    board.drawGrid();
    board.drawPlants();
    for (Zombie z: zombies) z.show(p);
    for (Projectile pr: projectiles) pr.show(p);
    for (NormalSun sun: sunObjects) sun.show(p);

    if (menu.inMainMenu()) {
      menu.showMainMenu();
      return;
    }
    if (menu.inPauseMenu()) {
      menu.showPauseScreen();
      return;
    }
    if (menu.inGameOver()) {
      menu.showGameOverScreen();
      return;
    }
    if (menu.inWinScreen()) {
      menu.showWinScreen();
      return;
    }

    // drawing buttons here for now, will move to UIManager later
    if (selectedPlant.equals("Sunflower")) {
      p.fill(p.color(255, 255, 100));
    } else {
      p.fill(200);
    }
    p.rect(10,50,100,40);
    p.fill(0);
    p.textSize(16);
    p.text("Sunflower", 60, 70);

    if (selectedPlant.equals("Peashooter")) {
      p.fill(p.color(100, 255, 100));
    } else {
      p.fill(200);
    }
    p.rect(120,50,100,40);
    p.fill(0);
    p.textSize(16);
    p.text("Peashooter", 170, 70);

    // sun balance display will move to UIManager later
    p.fill(0);
    p.textSize(24);
    p.text("Sun: " + suns.getBalance(), 50, 30);
  }

  // method to handle mouse clicks
  public void handleClick(int x,int y, int button) {
    // check if clicking a sun
    for (NormalSun sun: sunObjects) {
      if (!sun.isCollected() && sun.clicked(p.mouseX, p.mouseY)) {
        sun.onClick();
        suns.addSun(25);
        return;
      }
    }

    if (menu.inMainMenu()) {
      menu.setInMainMenu(false);
      startLevel(0);
      return;
    }

    if (y >= 50 && y <= 90) {
      if (x >= 10 && x <= 110) {
        selectedPlant = "Sunflower";
        return;
      } else if (x >= 120 && x <= 220) {
        selectedPlant = "Peashooter";
        return;
      }
    }

    int[] cell = board.pixelToCell(x, y);
      if (cell != null) {
          if (selectedPlant.equals("Sunflower") && suns.spendSun(50)) {
              Sunflower flower = new Sunflower(cell[0], cell[1]);
              board.placePlant(flower, cell);
              plants.add(flower);
          } else if (selectedPlant.equals("Peashooter") && suns.spendSun(10)) {
              Peashooter shooter = new Peashooter(cell[0], cell[1]);
              board.placePlant(shooter, cell);
              plants.add(shooter);
          }
      }
  }

  public void addProjectile(Projectile pr) {
    projectiles.add(pr);
  }

  public void spawnSun(Point spawn) {
    sunObjects.add(new NormalSun(spawn));
  }
}
