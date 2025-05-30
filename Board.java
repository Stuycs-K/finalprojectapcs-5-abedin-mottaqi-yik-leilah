import processing.core.PApplet;
import java.util.*;
public class Board {
    private Plant[][] plantGrid;
    // he said to use smth else for zombies bc they wont really be on a grid
    // private ArrayList<Double> zombieRowPos; ima be honest I don't think we need this since theres no need to track this seperately
    private ArrayList<Zombie> zombies;
    private PApplet p;

    public Board(int rows, int cols, int screenWidth, int screenHeight, PApplet sketch){
      plantGrid = new Plant[rows][cols];
      zombies = new ArrayList<>();
      p = sketch;
    }

    public boolean isOccupied(int row, int col){
        if (plantGrid[row][col] == null) return false;
        return true;
    }
    public boolean placePlant(Plant plant, int[] location){
      if ((!isOccupied(location[0], location[1]))){
        plantGrid[location[0]][location[1]] = plant;
        return true;
      }
      return false;
        // make it angry and red
        // what does that even mean
    }

   public void removePlant(int[] location){
      if (isOccupied(location[0], location[1])){
        plantGrid[location[0]][location[1]] = null;
      }
    }

    // do we need an updatePlants method...?
    // idk this is supposed to update any plants that are interactable if i know what i'm doing
    public void updatePlants(){
      for (int r=0;r<plantGrid.length;r++){
        for (int c=0;c<plantGrid[0].length;c++){
          if (plantGrid[r][c] instanceof Interactable){
            ((Interactable)plantGrid[r][c]).update();
          }
        }
      }
    }

    // this should draw all plants currently on the board;
    public void drawPlants(){
      for (int r=0;r<plantGrid.length;r++){
        for (int c=0;c<plantGrid[0].length;c++){
          if (plantGrid[r][c] != null){
            plantGrid[r][c].show();
          }
        }
      }
    }

    public void drawGrid() {
      int cellWidth = 100;
      int cellHeight = 100;
      for (int r=0;r<plantGrid.length;r++){
        for (int c=0;c<plantGrid[0].length;c++){
          p.stroke(0);
          p.fill(100,200,100);
          p.rect(c*cellWidth,r*cellHeight,cellWidth,cellHeight);
        }
      }
    }

    public void spawnZombie(Zombie zomb, int row){
      zombies.add(zomb);
    }

    public ArrayList<Zombie> getZombies(){
      return zombies;
    }
    public void removeZombie(Zombie zomb){
      zombies.remove(zomb);
    }

    public boolean rowHasZomb(int row){
      for (int i = 0; i < zombies.size(); i++){
        if(zombies.get(i).getRow() == row){
          return true;
        }
      }
      return false;
    }

    // we need to make methods to convert a pixel to a cell on the grid so that we can track where the user clicked
}
