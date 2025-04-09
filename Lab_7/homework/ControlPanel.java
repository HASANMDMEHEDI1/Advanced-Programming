class ControlPanel extends JPanel {
  public ControlPanel(ConnectDotsGame game) {
      JButton saveBtn = new JButton("Save");
      JButton loadBtn = new JButton("Load");
      JButton exportBtn = new JButton("Export PNG");
      JButton exitBtn = new JButton("Exit");

      saveBtn.addActionListener(e -> game.saveGame());
      loadBtn.addActionListener(e -> game.loadGame());
      exportBtn.addActionListener(e -> game.exportPNG());
      exitBtn.addActionListener(e -> System.exit(0));

      add(saveBtn);
      add(loadBtn);
      add(exportBtn);
      add(exitBtn);
  }
}