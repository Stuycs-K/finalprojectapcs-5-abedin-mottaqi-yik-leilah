Game game;

void setup() {
  size(900, 500);
  game = new Game(width, height, this);
}

void draw() {
  background(100, 200, 100);
  game.update();
  game.render();
}

void mousePressed() {
  game.handleClick(mouseX, mouseY,mouseButton);
}

void keyPressed() {
  if (key == 'P' || key == 'p') {
    game.togglePause();
  }
}
