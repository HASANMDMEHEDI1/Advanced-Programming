class GameState implements Serializable {
  java.util.List<Dot> dots;
  java.util.List<Line> lines;
  int currentPlayer;
  public GameState(java.util.List<Dot> dots, java.util.List<Line> lines, int currentPlayer) {
      this.dots = new java.util.ArrayList<>(dots);
      this.lines = new java.util.ArrayList<>(lines);
      this.currentPlayer = currentPlayer;
  }
}