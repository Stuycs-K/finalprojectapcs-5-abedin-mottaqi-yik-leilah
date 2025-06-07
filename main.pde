Game game;
PImage lawn;

void setup() {
  size(950, 600);
  game = new Game(width, height);
  lawn = loadImage("lawn.png");
}

void draw() {
  image(lawn,0,0);
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
