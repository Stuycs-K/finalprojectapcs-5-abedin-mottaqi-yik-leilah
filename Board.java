import java.util.*;
public class Board {
    private Plant[][] plantGrid = new Plant[5][9];
    // he said to use smth else for zombies bc they wont really be on a grid
    private ArrayList<Double> zombieRowPos;
    private ArrayList<Zombie> zombies;
  
    public Board(int rows, int cols, int screenWidth, int screenHeight){
      plantGrid = new Plant[rows][cols];
      zombies = new ArrayList<>();
      zombieRowPos = new ArrayList<>();
    }
    
    public boolean isOccupied(int row, int col){
        if (plantGrid[row][col] == null) return false;
        return true;
    }
    
    public void drawGrid() {
      int cellWidth = 100;
      int cellHeight = 100;
      for (int r=0;r<plantGrid.length;r++){
        for (int c=0;c<plantGrid[0].length;c++){
          stroke(0);
          fill(100,200,100);
          rect(c*cellWidth,r*cellHeight,cellWidth,cellHeight);
        }
      }
    }

    public void placePlant(Plant plant, int[] location){
        if ((!isOccupied(location[0], location[1]))){
            plantGrid[location[0]][location[1]] = plant;
        }
        // make it angry and red
    }

    public void spawnZombie(Zombie zomb, int row){
        zombies.add(zomb);
        // lowk i dont know hwat im doin gnone of this makes sense...
        // add it to the end of the row but we dont know
        zombieRowPos.add();
    }

    public void removePlant(int[] location){
        if (isOccupied(location[0], location[1])){
            plantGrid[location[0]][location[1]] = null;
        }
    }

    public void removeZombie(Zombie zomb){
        zombies.remove(zomb);
    }
}
