import java.util.*;
public class Waves {
    private ArrayList<Zombie> zombieTypes = new ArrayList<Zombie>();
    private ArrayList<Integer> zombieCount = new ArrayList<Integer>();
    private ArrayList<Integer> spawnTime = new ArrayList<Integer>();
    // time basically means spawn after n seconds even tho usually the condition is killing a zomb
    private int timer = 0;
    private int index = 0; // to track what zombie we are on

    private addWave(int num, Zombie type, int time){
        zombieTypes.add(type);
        zombieCount.add(num);
        spawnTime.add(time);
    }

    public ArrayList<Zombie> spawn(){
      Arraylist<Zombie> spawned = new ArrayList<>();
      timer++;
      while (index < zombieTypes.size() && timer >= spawnTime.get(index)){
        Zombie type = ZombieTypes.get(index);
        int count = zombieCount.get(index);
        for (int i=0; i<count; i++) {
          Zombie z = type.copy();
          spawned.add(z);
        }
        index++;
      }
      return spawned;
    }
    
    public boolean waveDone(){
      return index >= zombieTypes.size();
    }
}
