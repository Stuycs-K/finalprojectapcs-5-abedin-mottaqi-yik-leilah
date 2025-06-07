import processing.core.PApplet;
import java.awt.Point;
import java.util.*;

public class Game {
    private Board board;
    private ArrayList<Zombie> zombies = new ArrayList<>();
    private ArrayList<Plant> plants = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<NormalSun> sunObjects = new ArrayList<>();
    private PlayerSun suns = new PlayerSun();

    private PApplet p;

    public Game(int w, int h, PApplet sketch) {
        this.p = sketch;
        board = new Board(5, 9, w, h, p);
        Peashooter.setGame(this);
        Sunflower.setGame(this);
    }

    public void update() {
        board.updatePlants();

        for (Zombie z : zombies) z.update();
        for (Projectile pr : projectiles) pr.update();
        for (NormalSun s : sunObjects) s.update();

        // Collision: Peas hit zombies
        for (Projectile pr : projectiles) {
            Point pp = pr.getPos();
            for (Zombie z : zombies) {
                float zx = z.getX();
                float zy = z.getY();
                if (Math.abs(pp.x - zx) < 30 && Math.abs(pp.y - zy) < 60) {
                    z.takeDamage(pr.getDamage());
                    pr.markForRemoval();
                }
            }
        }

        // Remove flagged objects
        projectiles.removeIf(p -> p.shouldRemove());
        zombies.removeIf(z -> z.getHealth() <= 0);
        sunObjects.removeIf(s -> s.isCollected());
    }

    public void render() {
        board.drawGrid();
        board.drawPlants();

        for (Zombie z : zombies) z.show(p);
        for (Projectile pr : projectiles) pr.show(p);
        for (NormalSun sun : sunObjects) sun.show(p);

        // Draw sun counter
        p.fill(0);
        p.textSize(24);
        p.text("Sun: " + suns.getBalance(), 10, 30);
    }

    public void handleClick(int x, int y) {
        // Check if clicking a sun
        for (NormalSun sun : sunObjects) {
          if (!sun.isCollected() && sun.clicked(p.mouseX, p.mouseY)) {
            sun.onClick();
            suns.addSun(25);
            return;
           }
         }

        // Try to place plant if user has enough sun
        int[] cell = board.pixelToCell(x, y);
        if (cell != null) {
            // Example logic: left 1/2 of screen = sunflower, right = peashooter
            if (x < p.width / 2 && suns.spendSun(50)) {
                Sunflower flower = new Sunflower(cell[0], cell[1]);
                board.placePlant(flower, cell);
                plants.add(flower);
            } else if (x >= p.width / 2 && suns.spendSun(100)) {
                Peashooter shooter = new Peashooter(cell[0], cell[1]);
                board.placePlant(shooter, cell);
                plants.add(shooter);
            }
        }
    }

    public void startLevel(int idx) {
        // For now, spawn one test zombie
        zombies.add(new NormalZombie(new Point(850, 100)));
    }

    public void addProjectile(Projectile pr) {
        projectiles.add(pr);
    }

    public void spawnSun(Point location) {
        sunObjects.add(new NormalSun(location));
    }
}
