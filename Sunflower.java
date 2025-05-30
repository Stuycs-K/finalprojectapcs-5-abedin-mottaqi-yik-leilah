import java.awt.Point;
import java.util.*;
public class Sunflower extends Plant {
    private int timer = 0;
    private final int cooldown = 480;

    public Sunflower(int[] pos,ArrayList<NormalSun> sunList){
      super(pos, 300, 50); // figure out health and cost k thanks
    }

    @Override
    public void update(){
      if (timer == cooldown) ability();
      timer = (timer + 1)%cooldown;
    }

    @Override
    public void ability(){
      spawnSun();
    }

    private void spawnSun(){
        Point sunPosition = new Point(getPos().x, getPos().y);
        NormalSun sun = new NormalSun(sunPosition);
    }

    @Override
    public void show(){

    }
    @Override
    public void hide(){

    }
}
