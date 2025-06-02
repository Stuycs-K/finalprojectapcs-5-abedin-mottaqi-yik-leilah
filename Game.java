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
  private boolean shovelMode = false;
  private int sunTimer = 0;
  private int waveTimer = 1800;
  private boolean startWave = false;


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
    NormalZombie.setGame(this);
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
  }
  public void update() {
    // check if in menu, pause, or end screen
    if (menu.anyOverlayActive()) return;

    board.updatePlants();

    if (!startWave){
      waveTimer--;
      if (waveTimer <= 0){
        startWave = true;
        startLevel(0);
      }
    }
    if (startWave && waves != null){
      ArrayList<Zombie> newZombies = waves.update();
      for (Zombie z: newZombies) zombies.add(z);
    }

    sunTimer++;
    if (sunTimer >= 480) {
      spawnSun(null);
      sunTimer = 0;
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
    
    // check if game is over
    for (Zombie z: zombies) {
      if (z.getX() <= 0) {
        menu.setInGameOver(true);
        return;
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

    menu.drawUI(suns.getBalance());

    if (shovelMode){
      p.fill(p.color(255, 100, 100));
    } else {
      p.fill(200);
    }
    p.rect(230,50,100,40);
    p.fill(0);
    p.textSize(16);
    p.text("Shovel", 280, 70);

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

      p.fill(200);
      p.rect(375,400,200,50);
      p.fill(0);
      p.textSize(24);
      p.text("Restart", 475, 425);

      return;
    }
    if (menu.inWinScreen()) {
      menu.showWinScreen();

      p.fill(200);
      p.rect(375,400,200,50);
      p.fill(0);
      p.textSize(24);
      p.text("Restart", 475, 425);

      return;
    }
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

    if ((menu.inGameOver() || menu.inWinScreen()) && x >= 375 && x <= 575 && y >= 400 && y <= 450) {
      restartGame();
      return;
    }

    if (y >= 50 && y <= 90) {
      if (x >= 10 && x <= 110) {
        menu.setSelectedPlant("Sunflower");
        return;
      } else if (x >= 120 && x <= 220) {
        menu.setSelectedPlant("Peashooter");
        return;
      } else if (x >= 230 && x <= 330) {
        toggleShovel();
        return;
      }
    }

    int[] cell = board.pixelToCell(x, y);
    
    if (cell != null) {
      if (shovelMode) {
        board.removePlant(cell);
        shovelMode = false;
        return;
      }  

      Plant newPlant = null;

      if (menu.getSelectedPlant().equals("Sunflower")) {
          newPlant = new Sunflower(cell[0], cell[1]);
      } else if (menu.getSelectedPlant().equals("Peashooter")) {
          newPlant = new Peashooter(cell[0], cell[1]);
      }

      if (newPlant != null && suns.spendSun(newPlant.getCost())) {
        if (board.placePlant(newPlant, cell)) {
          plants.add(newPlant);
        }
      }
    }
  }

  public void addProjectile(Projectile pr) {
    projectiles.add(pr);
  }

  public void spawnSun(Point spawn) {
    if (spawn == null) {
      sunObjects.add(new NormalSun());
    } else {
      sunObjects.add(new NormalSun(spawn));
    }
  }

  public void togglePause() {
    if (menu.inPauseMenu()) {
      menu.setInPauseMenu(false);
    } else {
      menu.setInPauseMenu(true);
    }
  }
  
  public void toggleShovel(){
    shovelMode = !shovelMode;
  }

  public ArrayList<Zombie> getZombies() {
    return zombies;
  }

  public Board getBoard() {
    return board.getBoard();
  }

  public void restartGame() {
    plants.clear();
    zombies.clear();
    projectiles.clear();
    sunObjects.clear();
    waves = null;
    suns.reset();
    board.clear();
    waveTimer = 1800;
    startWave = false;

    menu.setInGameOver(false);
    menu.setInWinScreen(false);
    menu.setInPauseMenu(false);
    menu.setInMainMenu(true);
  }
}
