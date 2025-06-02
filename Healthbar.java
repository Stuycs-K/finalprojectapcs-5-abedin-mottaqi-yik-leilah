public class Healthbar{
    float x, y;
    float barWidth = 100;
    float barheight = 20;
    float currentHealth, maxHealth;
    color barColor = color(0, 255, 0);
    color bgColor = color(255, 0, 0);

    HealthBar(float x, float y, float maxHealth) {
    this.x = x;
    this.y = y;
    this.maxHealth = maxHealth;
    this.currentHealth = maxHealth;

    public void update(float newHealth) {
        currentHealth = constrain(newHealth, 0, maxHealth);
    }

    public void setX(float x) {
        this.x = x;
    }

    void display() {
        fill(bgColor);
        rect(x - barWidth / 2, y - 20, barWidth, barheight);
        fill(barColor);
        float healthWidth = (currentHealth / maxHealth) * barWidth;
        rect(x - barWidth / 2, y - 20, healthWidth, h);
    }
  }
}
