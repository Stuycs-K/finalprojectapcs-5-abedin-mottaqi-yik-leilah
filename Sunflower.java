import java.awt.Point;
import java.util.*;
public class Sunflower extends Plant implements Interactable{
    private int timer = 0;
    private final int cooldown = 300;
    private ArrayList<NormalSun> suns = new ArrayList<>();

    public Sunflower(Point cell,ArrayList<NormalSun> sunList){
      super(cell,50, 300); // figure out health and cost k thanks
    }

    @Override
    public void update(){

    }

    private void spawnSun(){
        Point sunPosition = new Point(getPos().x, getPos().y);
        NormalSun sun = new NormalSun(sunPosition);
        suns.add(sun);
    }
    @Override
    public void show(){

    }
    @Override
    public void hide(){

    }
    @Override
    public void interact(){
    }
}
