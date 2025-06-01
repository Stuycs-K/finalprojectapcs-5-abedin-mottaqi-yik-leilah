import processing.core.PApplet;
import java.util.*;
public class Waves {
    /*private ArrayList<Zombie> zombieTypes = new ArrayList<Zombie>();
    private ArrayList<Integer> zombieCount = new ArrayList<Integer>();
    private ArrayList<Integer> spawnTime = new ArrayList<Integer>();
    // time basically means spawn after n seconds even tho usually the condition is killing a zomb
    private int timer = 0;
    private int index = 0; // to track what zombie we are on

    public void addWave(int num, Zombie type, int time){
        zombieTypes.add(type);
        zombieCount.add(num);
        spawnTime.add(time);
    }

    public ArrayList<Zombie> spawn(){
      ArrayList<Zombie> spawned = new ArrayList<>();
      timer++;
      while (index < zombieTypes.size() && timer >= spawnTime.get(index)){
        Zombie type = zombieTypes.get(index);
        int count = zombieCount.get(index);
        for (int i=0; i<count; i++) {
          //Zombie z = type.copy();
          //spawned.add(z);
        }
        index++;
      }
      return spawned;
    }

    public boolean waveDone(){
      return index >= zombieTypes.size();
    }*/

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
