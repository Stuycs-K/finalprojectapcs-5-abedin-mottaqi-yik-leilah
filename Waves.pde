import java.util.*;
public class Waves {
    // private data container class for spawning zombies, easier organization
    private class ZombieSpawn{
      int spawnTime; // in frames
      Zombie zombie; // type of zombie
      
      ZombieSpawn(int time, Zombie z){
        this.spawnTime = time;
        this.zombie = z;
      }
    }

    private ArrayList<ZombieSpawn> zombieSpawns = new ArrayList<>();
    private int timer = 0;
    boolean done = false;

    Waves(Game game){
      Random rand = new Random();

      //patterns
      int[][] zombieCounts = {
        {1}, // 1 normal
        {1}, // 1 normal
        {0,1}, // 1 flag
        {1,1}, // 1 normal 1 flag
        {2,1}, // 2 normal 1 flag
        {2,2}, // 2 normal 2 flag
        {0,0,1}, // 1 cone
        {4,0,1}, // 4 normal 1 cone
        {2,2,1}, // 2 normal 2 flag, 1 cone
        {6,2,2} // 6 normal 2 flag 2 cone
      };

      int spawnSpacingTime = 30;
      int waveSpacingTime = 240;
      int frameCount = 0;

      for (int wave = 0; wave < zombieCounts.length; wave++) {
        int[] zombiesInWave = zombieCounts[wave];
        int normals = 0;
        int flags = 0;
        int cones = 0;
        if (zombiesInWave.length > 0) {
          normals = zombiesInWave[0];
        }
        if (zombiesInWave.length > 1) {
          flags = zombiesInWave[1];
        } 
        if (zombiesInWave.length > 2) {
          cones = zombiesInWave[2];
        }
        for (int i = 0; i < normals; i++) {
          int row = rand.nextInt(5);
          Point spawnPos = new Point(width,row * 100 + 40);
          zombieSpawns.add(new ZombieSpawn(frameCount + i * spawnSpacingTime, new NormalZombie(spawnPos, game)));
        }
        for (int i = 0; i < flags; i++) {
          int row = rand.nextInt(5);
          Point spawnPos = new Point(width,row * 100 + 40);
          zombieSpawns.add(new ZombieSpawn(frameCount + i * spawnSpacingTime, new FlagZombie(spawnPos, game)));
        }
        for (int i = 0; i < cones; i++) {
          int row = rand.nextInt(5);
          Point spawnPos = new Point(width,row * 100 + 40);
          zombieSpawns.add(new ZombieSpawn(frameCount + i * spawnSpacingTime, new ConeheadZombie(spawnPos, game)));
        }
        frameCount+= waveSpacingTime;
      }
    }

    ArrayList<Zombie> update(){
      ArrayList<Zombie> toSpawn = new ArrayList<>();
      for (int i = zombieSpawns.size() - 1; i >= 0; i--){
        ZombieSpawn z = zombieSpawns.get(i);
        if (timer >= z.spawnTime){
          toSpawn.add(z.zombie);
          zombieSpawns.remove(i);
        }
      }
      timer++;

      if (zombieSpawns.isEmpty()) {
        done = true;
      }

      return toSpawn;
    }

    public boolean isDone(){
      return done;
    }

    public void reset(){
      zombieSpawns.clear();
      timer = 0;
    }
}
