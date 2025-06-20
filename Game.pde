import java.util.*;
import java.awt.Point;
public class Game {
  private ArrayList<Plant> plants = new ArrayList<>();
  private ArrayList<Zombie> zombies = new ArrayList<>();
  private ArrayList<Projectile> projectiles = new ArrayList<>();
  private ArrayList<NormalSun> sunObjects = new ArrayList<>();
  private ArrayList<Lawnmower> lawnmowers = new ArrayList<>();
  private PlayerSun suns = new PlayerSun();
  //private ArrayList<SoundFile> music = new ArrayList<SoundFile>();
  private Waves waves;
  private Board board;
  private UIManager menu;
  private boolean shovelMode = false;
  private int sunTimer = 0;
  private int waveTimer = 1200;
  private boolean startWave = false;

  private int peashooterCooldown = 0;
  private int sunflowerCooldown = 0;
  private int wallnutCooldown = 0;


  public Game(int ScreenWidth, int ScreenHeight) {
    board = new Board(5,9,ScreenWidth,ScreenHeight);
    for (int row=0;row<5;row++){
      lawnmowers.add(new Lawnmower(row));
    }
    this.menu = new UIManager();
  }
  public void startLevel(int idx){
    waves = new Waves(this);
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

    // cooldown timer logic
    if (sunflowerCooldown > 0) sunflowerCooldown--;
    if (peashooterCooldown > 0) peashooterCooldown--;
    if (wallnutCooldown > 0) wallnutCooldown--;

    for (Zombie z: zombies) z.update();
    for (Projectile pr: projectiles) pr.update();
    for (NormalSun s: sunObjects) s.update();
    for (Lawnmower l: lawnmowers) l.update(zombies);

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

    // check to see if a zombie reaches the end and to use mower or not
    for (Zombie z: zombies) {
      if (z.getX()<=0) {
        int row = (int)((z.getY()-100)/100);
        if (row>=0 && row < lawnmowers.size() && !lawnmowers.get(row).isUsed()) {
          lawnmowers.get(row).trigger();
          return;
        } else {
          menu.setInGameOver(true);
          return;
        }
      }
    }

    // check if user won
    if (waves != null && waves.isDone() && zombies.isEmpty()) {
      menu.setInWinScreen(true);
    }
  }

  public void render() {
    //board.drawGrid();
    board.drawPlants();
    for (Zombie z: zombies) z.show();
    for (Projectile pr: projectiles) pr.show();
    for (NormalSun sun: sunObjects) sun.show();
    for (Lawnmower l: lawnmowers) l.show();

    menu.drawUI(suns.getBalance(),sunflowerCooldown,peashooterCooldown,wallnutCooldown);

    if (shovelMode){
      fill(color(255, 100, 100));
    } else {
      fill(200);
    }
    rect(340,50,100,40);
    fill(0);
    textSize(16);
    text("Shovel", 390, 70);

    if (menu.inMainMenu()) {
      menu.showMainMenu();
      return;
    }
    if (menu.inGameOver()) {
      menu.showGameOverScreen();

      fill(200);
      rect(375,500,200,50);
      fill(0);
      textSize(24);
      text("Restart", 475, 525);

      return;
    }
    if (menu.inWinScreen()) {
      menu.showWinScreen();

      fill(200);
      rect(375,500,200,50);
      fill(0);
      textSize(24);
      text("Restart", 475, 525);

      return;
    }
    if (menu.inPauseMenu()) {
      menu.showPauseScreen();

      fill(200);
      rect(375,500,200,50);
      fill(0);
      textSize(24);
      text("Restart", 475, 525);

      return;
    }
  }

  // method to handle mouse clicks
  public void handleClick(int x,int y, int button) {
    // check if clicking a sun
    for (NormalSun sun: sunObjects) {
      if (!sun.isCollected() && sun.clicked(mouseX, mouseY)) {
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

    if ((menu.inGameOver() || menu.inWinScreen() || menu.inPauseMenu()) && x >= 375 && x <= 575 && y >= 500 && y <= 550) {
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
      } else if (x >= 230 && x <= 330){
        menu.setSelectedPlant("Wallnut");
        return;
      } else if (x >= 340 && x <= 440) {
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

      String selected = menu.getSelectedPlant();
      Plant newPlant = null;
      boolean canPlace = false;

      if (selected.equals("Sunflower") && sunflowerCooldown == 0) {
          newPlant = new Sunflower(cell[0], cell[1], this);
          canPlace = true;
      } else if (selected.equals("Peashooter") && peashooterCooldown == 0) {
          newPlant = new Peashooter(cell[0], cell[1], this);
          canPlace = true;
      } else if (selected.equals("Wallnut") && wallnutCooldown == 0){
          newPlant = new Wallnut(cell[0],cell[1]);
          canPlace = true;
      }

      if (newPlant != null && canPlace && !board.isOccupied(cell[0], cell[1])){
        if (suns.spendSun(newPlant.getCost())) {
          if (board.placePlant(newPlant, cell)) {
            plants.add(newPlant);

            if (selected.equals("Sunflower")) {
              sunflowerCooldown = 450;
            } else if (selected.equals("Peashooter")) {
              peashooterCooldown = 450;
            } else if (selected.equals("Wallnut")){
              wallnutCooldown = 1800;
            }
          }
        }
      }
    }
  }

  public void handleKeyPress(char key){
    if (key == 's' || key == 'S'){
      suns.addSun(100);
      println("added 100 sun");
    }
    if (key == 'k' || key == 'K') {
      zombies.clear();
      println("cleared all zombies");
    }
    if (key == 'P' || key == 'p') {
      togglePause();
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
    peashooterCooldown = 0;
    sunflowerCooldown = 0;
    wallnutCooldown = 0;
    for(Lawnmower l : lawnmowers){
      l.reset();
    }

    menu.setInGameOver(false);
    menu.setInWinScreen(false);
    menu.setInPauseMenu(false);
    menu.setInMainMenu(true);
  }
}
