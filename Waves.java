public class Waves {
    private ArrayList<Zombie> zombieTypes = new ArrayList<Zombie>();
    private ArrayList<Integer> zombieCount = new ArrayList<Integer>();
    private ArrayList<Integer> spawnTime = new ArrayList<Integer>();
    // time basically means spawn after n seconds even tho usually the condition is killing a zomb


    private addWave(int num, Zombie type, int time){
        zombieTypes.add(type);
        zombieCount.add(num);
        spawnTime.add(time);
    }


}
