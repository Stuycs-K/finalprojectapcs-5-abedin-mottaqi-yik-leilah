import processing.core.PApplet;
public class UIManager {
    private boolean inMainMenu = true;
    private boolean inPauseMenu = false;
    private boolean inGameOver = false;
    private boolean inWinScreen = false;

    private PApplet p;

    public UIManager(PApplet p) {
        this.p = p;
    }

    public void showMainMenu(){
        p.background(200);
        p.fill(0);
        p.textSize(48);
        p.textAlign(p.CENTER, p.CENTER);
        p.text("Plants vs Zombies", p.width/2, p.height/2 - 50);
        p.textSize(24);
        p.text("Click to Start", p.width/2, p.height/2 + 20);
    }

    public void showPauseScreen(){
        p.fill(0,100);
        p.rect(0,0,p.width,p.height);
        p.fill(255);
        p.textSize(32);
        p.text("Paused",p.width/2,p.height/2);
    }

    public void showGameOverScreen() {
        p.background(0);
        p.fill(255, 0, 0);
        p.textSize(48);
        p.textAlign(p.CENTER, p.CENTER);
        p.text("Game Over", p.width / 2, p.height / 2);
    }

    public void showWinScreen() {
        p.background(255);
        p.fill(0, 200, 0);
        p.textSize(48);
        p.textAlign(p.CENTER, p.CENTER);
        p.text("You Win", p.width / 2, p.height / 2);
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
