public class Waves {
    private ArrayList<Zombie> zombieTypes = new ArrayList<Zombie>();
    private ArrayList<Integer> zombieCount = new ArrayList<Integer>();

    private addWave(int num, Zombie type){
        zombieTypes.add(type);
        zombieCount.add(num);
    }


}
