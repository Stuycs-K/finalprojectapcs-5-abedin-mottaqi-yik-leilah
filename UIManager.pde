public class UIManager {
    private boolean inMainMenu = true;
    private boolean inPauseMenu = false;
    private boolean inGameOver = false;
    private boolean inWinScreen = false;
    private String selectedPlant = "Sunflower";

    public void drawUI(int sunBalance){
        drawButtons();
        drawSunCounter(sunBalance);
    }

    public void drawButtons() {
        if (selectedPlant.equals("Sunflower")) {
          fill(color(255, 255, 100));
        } else {
          fill(200);
        }
        rect(10,50,100,40);
        fill(0);
        textSize(16);
        text("Sunflower", 60, 70);

        if (selectedPlant.equals("Peashooter")) {
          fill(color(100, 255, 100));
        } else {
          fill(200);
        }
        rect(120,50,100,40);
        fill(0);
        textSize(16);
        text("Peashooter", 170, 70);

        if (selectedPlant.equals("Wallnut")) {
          fill(color(88, 57, 39));
        } else {
          fill(200);
        }
        rect(230,50,100,40);
        fill(0);
        textSize(16);
        text("Wallnut", 280, 70);
    }

    public void drawSunCounter(int sunBalance){
        fill(0);
        textSize(24);
        text("Sun: " + sunBalance, 50, 30);
    }

    public void showMainMenu(){
        background(200);
        fill(0);
        textSize(48);
        textAlign(CENTER, CENTER);
        text("Plants vs Zombies", width/2, height/2 - 50);
        textSize(24);
        text("Click to Start", width/2, height/2 + 20);
    }

    public void showPauseScreen(){
        fill(0,100);
        rect(0,0,width,height);
        fill(255);
        textSize(32);
        text("Paused",width/2,height/2);
    }

    public void showGameOverScreen() {
        background(0);
        fill(255, 0, 0);
        textSize(48);
        textAlign(CENTER, CENTER);
        text("Game Over", width / 2, height / 2);
    }

    public void showWinScreen() {
        background(255);
        fill(0, 200, 0);
        textSize(48);
        textAlign(CENTER, CENTER);
        text("You Win", width / 2, height / 2);
    }

    public boolean inMainMenu() {
        return inMainMenu;
    }

    public boolean inPauseMenu() {
        return inPauseMenu;
    }

    public boolean inGameOver() {
        return inGameOver;
    }

    public boolean inWinScreen() {
        return inWinScreen;
    }

    public String getSelectedPlant() {
        return selectedPlant;
    }

    public void setSelectedPlant(String plant) {
        this.selectedPlant = plant;
    }

    public void setInMainMenu(boolean inMainMenu) {
        this.inMainMenu = inMainMenu;
    }

    public void setInPauseMenu(boolean inPauseMenu) {
        this.inPauseMenu = inPauseMenu;
    }

    public void setInGameOver(boolean inGameOver) {
        this.inGameOver = inGameOver;
    }

    public void setInWinScreen(boolean inWinScreen) {
        this.inWinScreen = inWinScreen;
    }

    public boolean anyOverlayActive(){
        return inMainMenu || inPauseMenu || inGameOver || inWinScreen;
    }
}
