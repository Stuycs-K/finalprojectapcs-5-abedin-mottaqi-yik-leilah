import processing.core.PApplet;

public class Board {
  private Plant[][] plantGrid;
  private int cellWidth = 100;
  private int cellHeight = 100;
  private PApplet p;

  public Board(int rows, int cols, int w, int h, PApplet sketch) {
    plantGrid = new Plant[rows][cols];
    p = sketch;
  }

  public void drawGrid() {
    for (int r = 0; r < plantGrid.length; r++) {
      for (int c = 0; c < plantGrid[0].length; c++) {
        p.stroke(0);
        p.fill(100, 200, 100);
        p.rect(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
      }
    }
  }

  public void drawPlants() {
    for (int r = 0; r < plantGrid.length; r++) {
      for (int c = 0; c < plantGrid[0].length; c++) {
        if (plantGrid[r][c] != null) {
          plantGrid[r][c].show(p);
        }
      }
    }
  }

  public boolean placePlant(Plant plant, int[] cell) {
    if (plantGrid[cell[0]][cell[1]] == null) {
      plantGrid[cell[0]][cell[1]] = plant;
      return true;
    }
    return false;
  }

  public int[] pixelToCell(int x, int y) {
    int row = y / cellHeight;
    int col = x / cellWidth;
    if (row >= 0 && row < plantGrid.length && col >= 0 && col < plantGrid[0].length) {
      return new int[]{row, col};
    }
    return null;
  }

  public void updatePlants() {
    for (int r = 0; r < plantGrid.length; r++) {
      for (int c = 0; c < plantGrid[0].length; c++) {
        if (plantGrid[r][c] != null) {
          plantGrid[r][c].update();
        }
      }
    }
  }
}
