public class UIManager {
    private boolean inMainMenu = true;
    private boolean inPauseMenu = false;
    private boolean inGameOver = false;
    private boolean inWinScreen = false;
    private String selectedPlant = "Sunflower";
    PImage menu = loadImage("start_screen.jpg");
    PImage loss = loadImage("lose_screen.jpg");
    PImage win = loadImage("win_screen.jpg");

    public void drawUI(int sunBalance, int sunflowerCooldown, int peashooterCooldown, int wallnutCooldown){
        drawButtons(sunflowerCooldown,peashooterCooldown,wallnutCooldown);
        drawSunCounter(sunBalance);
    }

    public void drawButtons(int sunflowerCooldown, int peashooterCooldown, int wallnutCooldown) {
        if (selectedPlant.equals("Sunflower")) {
          fill(color(255, 255, 100));
        } else {
          fill(200);
        }
        rect(10,50,100,40);
        fill(0);
        textSize(12);
        text("Sunflower", 10 + 50, 50 + 10);
        if (sunflowerCooldown > 0) {
            text("CD: " + sunflowerCooldown/60 + "s", 10 + 50, 50 + 30);
        } else {
            text("Ready", 10 + 50, 50 + 30);
        }

        if (selectedPlant.equals("Peashooter")) {
          fill(color(100, 255, 100));
        } else {
          fill(200);
        }
        rect(120,50,100,40);
        fill(0);
        textSize(12);
        text("Peashooter", 120 + 50, 50 + 10);
        if (peashooterCooldown > 0) {
            text("CD: " + peashooterCooldown/60 + "s", 120 + 50, 50 + 30);
        } else {
            text("Ready", 120 + 50, 50 + 30);
        }

        if (selectedPlant.equals("Wallnut")) {
          fill(color(88, 57, 39));
        } else {
          fill(200);
        }
        rect(230,50,100,40);
        fill(0);
        textSize(12);
        text("Wallnut", 230 + 50, 50 + 10);
        if (wallnutCooldown > 0) {
            text("CD: " + wallnutCooldown/60 + "s", 230 + 50, 50 + 30);
        } else {
            text("Ready", 230 + 50, 50 + 30);
        }
    }

    public void drawSunCounter(int sunBalance){
        fill(0);
        textSize(24);
        text("Sun: " + sunBalance, 50, 30);
    }

    public void showMainMenu(){
<<<<<<< HEAD
      image(mainMenu,0,0);
        //background(200);
        //fill(0);
        //textSize(48);
        //textAlign(CENTER, CENTER);
        //text("Plants vs Zombies", width/2, height/2 - 50);
        //textSize(24);
        //text("Click to Start", width/2, height/2 + 20);
=======
        image(menu, 0, 0, width, height);
        textAlign(CENTER, CENTER);
>>>>>>> 952ab037bb6b470c4ea361c1c49cb99b402700df
    }

    public void showPauseScreen(){
        fill(0,100);
        rect(0,0,width,height);
        fill(255);
        textSize(32);
        text("Paused",width/2,height/2);
    }

    public void showGameOverScreen() {
<<<<<<< HEAD
      image(loseScreen,0,0);
        //background(0);
        //fill(255, 0, 0);
        //textSize(48);
        //textAlign(CENTER, CENTER);
        //text("Game Over", width / 2, height / 2);
    }

    public void showWinScreen() {
      image(winScreen,0,0);
        //background(255);
        //fill(0, 200, 0);
        //textSize(48);
        //textAlign(CENTER, CENTER);
        //text("You Win", width / 2, height / 2);
=======
        image(loss, 0, 0, width, height);
        textAlign(CENTER, CENTER);
    }

    public void showWinScreen() {
        image(win, 0, 0, width, height);
        textAlign(CENTER, CENTER);
>>>>>>> 952ab037bb6b470c4ea361c1c49cb99b402700df
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
