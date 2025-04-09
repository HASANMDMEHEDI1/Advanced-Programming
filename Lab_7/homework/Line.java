class Line implements Serializable {
  Dot start, end;
  Color color;
  public Line(Dot start, Dot end, Color color) {
      this.start = start;
      this.end = end;
      this.color = color;
  }
  public void draw(Graphics2D g) {
      g.setColor(color);
      g.drawLine(start.x, start.y, end.x, end.y);
  }
}