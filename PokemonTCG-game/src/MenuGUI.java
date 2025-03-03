import java.awt.*;
import javax.swing.*;

public class MenuGUI extends JFrame {
    public MenuGUI() {
        setTitle("Pokémon Card Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ✅ Load Background Image
        ImageIcon backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("assets/pokemon.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // ✅ Layered Pane to Keep Title & Buttons on Top
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // ✅ Background Panel
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // ✅ Title Label (Foreground Layer)
        JLabel titleLabel = new JLabel("Pokémon Card Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 50, 800, 50); // Adjust Y position
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER); // Upper Layer

        // ✅ Button Panel (Foreground Layer)
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(250, 200, 300, 150); // Centered button position

        // ✅ Buttons
        JButton startButton = new JButton("Start Game");
        JButton rulesButton = new JButton("Rules");
        JButton quitButton = new JButton("Quit");

        startButton.addActionListener(e -> {
            System.out.println("Start Game Clicked!");
            SwingUtilities.invokeLater(() -> new GameGUI());
            dispose();
        });

        // ✅ Improved Rules Display
        rulesButton.addActionListener(e -> showGameRules());

        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(rulesButton);
        buttonPanel.add(quitButton);

        layeredPane.add(buttonPanel, JLayeredPane.MODAL_LAYER); // Upper Layer

        add(layeredPane);
        setVisible(true);
    }

    // ✅ Function to Show Full Game Rules
    private void showGameRules() {
        String rulesText = """
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; color: #333; padding: 10px; }
                    h2 { text-align: center; color: #e63946; font-size: 24px; margin-bottom: 10px; }
                    b { color: #1d3557; font-size: 16px; }
                    ul { padding-left: 20px; }
                    li { margin-bottom: 5px; }
                    .highlight { color: #e76f51; font-weight: bold; }
                    .section { background: #f1faee; padding: 8px; border-radius: 8px; margin-bottom: 10px; }
                    .centered { text-align: center; font-weight: bold; font-size: 18px; color: #2a9d8f; }
                </style>
            </head>
            <body>
                <h2>Pokémon Card Game Rules</h2>

                <div class='section'>
                    <b>1. Game Setup:</b>
                    <ul>
                        <li>Each player starts with a shuffled deck.</li>
                        <li>Players draw <span class='highlight'>7 cards</span> at the beginning.</li>
                        <li>A coin flip decides who plays first.</li>
                        <li>The first player <span class='highlight'>cannot attack</span> on their first turn.</li>
                    </ul>
                </div>

                <div class='section'>
                    <b>2. Turn Structure:</b>
                    <ul>
                        <li>Each turn consists of:
                            <ul>
                                <li><span class='highlight'>Draw</span> a card (Mandatory).</li>
                                <li><span class='highlight'>Play Pokémon</span>, attach Energy, or use Trainer cards (Optional).</li>
                                <li><span class='highlight'>Attack</span> the opponent’s Pokémon (If possible).</li>
                                <li><span class='highlight'>End turn</span>, switching to the other player.</li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <div class='section'>
                    <b>3. Playing Cards:</b>
                    <ul>
                        <li>Pokémon must be placed on the <span class='highlight'>bench</span> before being used in battle.</li>
                        <li>Energy cards are <span class='highlight'>attached</span> to Pokémon for attacks.</li>
                        <li>Trainer cards provide <span class='highlight'>special effects</span>.</li>
                    </ul>
                </div>

                <div class='section'>
                    <b>4. Battle Rules:</b>
                    <ul>
                        <li>Players attack using their <span class='highlight'>Active Pokémon</span>.</li>
                        <li>If a Pokémon’s <span class='highlight'>HP reaches 0</span>, it is knocked out.</li>
                        <li>The winner collects a <span class='highlight'>Prize Card</span> after knocking out an opponent’s Pokémon.</li>
                    </ul>
                </div>

                <div class='section'>
                    <b>5. Winning Conditions:</b>
                    <ul>
                        <li>A player wins if:
                            <ul>
                                <li>They collect <span class='highlight'>all 3 Prize Cards</span>.</li>
                                <li>The opponent has <span class='highlight'>no Pokémon left</span>.</li>
                                <li>The opponent runs out of cards to <span class='highlight'>draw</span>.</li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <div class='section'>
                    <b>6. Special Mechanics:</b>
                    <ul>
                        <li>Status effects like <span class='highlight'>Paralyze, Burn, and Sleep</span> impact gameplay.</li>
                        <li>Players must manage <span class='highlight'>Energy Cards</span> effectively.</li>
                        <li><span class='highlight'>Prize Cards</span> are drawn after every knockout.</li>
                    </ul>
                </div>

                <p class='centered'>🎉 Enjoy the game and be the very best! 🎉</p>
            </body>
            </html>
            """;

        // Use JTextPane to render styled HTML
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(rulesText);
        textPane.setEditable(false);
        textPane.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Game Rules", JOptionPane.INFORMATION_MESSAGE);
    }
}
