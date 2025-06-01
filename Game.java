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
  //private UIManager menu; removed for now for testing
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
    // all zombies spawned and defeated — level complete
      System.out.println("Level complete!");
    // add win screen here
    }
  }

  public void render() {
    board.drawGrid();
    board.drawPlants();
    for (Zombie z: zombies) z.show(p);
    for (Projectile pr: projectiles) pr.show(p);
    for (NormalSun sun: sunObjects) sun.show(p);

    // sun balance display
    p.fill(0);
    p.textSize(24);
    p.text("Sun: " + suns.getBalance(), 10, 30);
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
    
    /*// converting pixel to cell
    int[] cell = board.pixelToCell(x, y);
    if (cell == null) return;
    int row = cell[0];
    int col = cell[1];

    // mouse button logic
    if (button == PConstants.LEFT) {
      // place peashooter on left click
      if (suns.spendSun(100)) {
        Peashooter shooter = new Peashooter(row, col);
        board.placePlant(shooter, cell);
        plants.add(shooter);
      }
      // place sunflower on right click
      else if (button == PConstants.RIGHT) {
        if (suns.spendSun(50)) {
          Sunflower flower = new Sunflower(row, col);
          board.placePlant(flower, cell);
          plants.add(flower);
        }
      }
    }*/

      int[] cell = board.pixelToCell(x, y);
        if (cell != null) {
            // left half of screen = sunflower, right = peashooter
            if (x < p.width / 2 && suns.spendSun(50)) {
                Sunflower flower = new Sunflower(cell[0], cell[1]);
                board.placePlant(flower, cell);
                plants.add(flower);
            } else if (x >= p.width / 2 && suns.spendSun(10)) {
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
