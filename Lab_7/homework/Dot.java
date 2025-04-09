class Dot implements Serializable {
  int x, y;
  public Dot(int x, int y) {
      this.x = x;
      this.y = y;
  }
  public boolean contains(int px, int py) {
      return Math.hypot(x - px, y - py) <= 10;
  }
  public void draw(Graphics2D g) {
      g.setColor(Color.BLACK);
      g.fillOval(x - 5, y - 5, 10, 10);
  }
}