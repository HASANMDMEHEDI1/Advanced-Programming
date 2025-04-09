class ConfigPanel extends JPanel {
  private JSpinner dotSpinner;
  private JButton newGameButton;

  public ConfigPanel(ConnectDotsGame game) {
      add(new JLabel("Number of Dots:"));
      dotSpinner = new JSpinner(new SpinnerNumberModel(5, 2, 50, 1));
      add(dotSpinner);

      newGameButton = new JButton("New Game");
      add(newGameButton);
      newGameButton.addActionListener(e -> {
          int count = (Integer) dotSpinner.getValue();
          game.createNewGame(count);
      });
  }
}