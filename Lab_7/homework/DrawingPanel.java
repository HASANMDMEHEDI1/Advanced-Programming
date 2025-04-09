class DrawingPanel extends JPanel {
  private java.util.List<Dot> dots = new java.util.ArrayList<>();
  private java.util.List<Line> lines = new java.util.ArrayList<>();
  private Dot selectedDot = null;
  private int currentPlayer = 0; // 0 = Blue, 1 = Red

  public DrawingPanel() {
      setBackground(Color.WHITE);

      addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
              Dot clicked = getDotAt(e.getX(), e.getY());
              if (clicked != null) {
                  if (selectedDot == null) {
                      selectedDot = clicked;
                  } else if (selectedDot != clicked) {
                      lines.add(new Line(selectedDot, clicked, currentPlayer == 0 ? Color.BLUE : Color.RED));
                      selectedDot = null;
                      currentPlayer = 1 - currentPlayer;
                      repaint();
                  }
              }
          }
      });
  }

  public void generateDots(int count) {
      dots.clear();
      lines.clear();
      selectedDot = null;
      java.util.Random rand = new java.util.Random();
      for (int i = 0; i < count; i++) {
          dots.add(new Dot(rand.nextInt(getWidth() - 40) + 20, rand.nextInt(getHeight() - 40) + 20));
      }
      repaint();
  }

  private Dot getDotAt(int x, int y) {
      for (Dot d : dots) {
          if (d.contains(x, y)) return d;
      }
      return null;
  }

  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      for (Line l : lines) l.draw(g2);
      for (Dot d : dots) d.draw(g2);

      if (selectedDot != null) {
          g2.setColor(Color.BLACK);
          g2.drawOval(selectedDot.x - 8, selectedDot.y - 8, 16, 16);
      }
  }

  public GameState getGameState() {
      return new GameState(dots, lines, currentPlayer);
  }

  public void setGameState(GameState state) {
      this.dots = state.dots;
      this.lines = state.lines;
      this.currentPlayer = state.currentPlayer;
      repaint();
  }

  public void exportAsImage() {
      BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = image.createGraphics();
      paint(g2);
      g2.dispose();
      try {
          javax.imageio.ImageIO.write(image, "PNG", new File("game_board.png"));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}