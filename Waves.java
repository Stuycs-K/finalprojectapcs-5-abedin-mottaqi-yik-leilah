import processing.core.PApplet;
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

    public void addZombie(int time, Zombie z){ // time is in frames
      zombieSpawns.add(new ZombieSpawn(time, z));
    }

    public ArrayList<Zombie> update(){
      ArrayList<Zombie> toSpawn = new ArrayList<>();
      for (int i = zombieSpawns.size() - 1; i >= 0; i--){
        ZombieSpawn z = zombieSpawns.get(i);
        if (timer >= z.spawnTime){
          toSpawn.add(z.zombie);
          zombieSpawns.remove(i);
        }
      }
      timer++;
      return toSpawn;
    }

    public boolean isDone(){
      return zombieSpawns.size() == 0;
    }

    public void reset(){
      zombieSpawns.clear();
      timer = 0;
    }
}
