import processing.core.PApplet;
public class Board {
    private Plant[][] plantGrid;
    private int cellWidth = 100; // how wide each cell is
    private int cellHeight = 100; // how tall each cell is
    // i removed all the zombie stuff for testing, will add back later
    private PApplet p;

    public Board(int rows, int cols, int screenWidth, int screenHeight, PApplet p){
      plantGrid = new Plant[rows][cols];
      this.p = p;
    }

    // removed isOccupied method since we can just check if its null instead

    public boolean placePlant(Plant plant, int[] cell){
      if (plantGrid[cell[0]][cell[1]] == null){
        plantGrid[cell[0]][cell[1]] = plant;
        return true;
      }
      return false;
        // make it angry and red
        // what does that even mean
    }

   /* i will implement this later 
   public void removePlant(int[] location){
      if (isOccupied(location[0], location[1])){
        plantGrid[location[0]][location[1]] = null;
      }
    } */

    public void updatePlants(){
      for (int r=0;r<plantGrid.length;r++){
        for (int c=0;c<plantGrid[0].length;c++){
          if (plantGrid[r][c] != null){
            plantGrid[r][c].update();
          }
        }
      }
    }
    // this should draw all plants currently on the board;
    public void drawPlants(){
      for (int r=0;r<plantGrid.length;r++){
        for (int c=0;c<plantGrid[0].length;c++){
          if (plantGrid[r][c] != null){
            plantGrid[r][c].show(p);
          }
        }
      }
    }

    public void drawGrid() {
      for (int r=0;r<plantGrid.length;r++){
        for (int c=0;c<plantGrid[0].length;c++){
          p.stroke(0);
          p.fill(100,200,100);
          p.rect(c*cellWidth,r*cellHeight,cellWidth,cellHeight);
        }
      }
    }

    // convert pixel coordinates to cell coordinates
    public int[] pixelToCell(int x, int y){
      int row = y/cellHeight;
      int col = x/cellWidth;
      if (row >= 0 && row < plantGrid.length && col >= 0 && col < plantGrid[0].length){
        return new int[]{row,col};
      }
      return null;
    }
}
