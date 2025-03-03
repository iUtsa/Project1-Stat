import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameGUI extends JFrame {
    private Player player1, player2;
    private Deck player1Deck, player2Deck;

    private JLabel playerTurnLabel, playerCardsLabel, playerBenchLabel;
    private JButton playPokemonButton, attackButton, playTrainerButton;
    private JButton addPokemonFieldButton, addPokemonBenchButton, attachEnergyButton, drawCardButton, endTurnButton, exitPlayButton;

    private boolean isPlayer1Turn;

    private JPanel benchPanel, activePokemonPanel, battlefieldPanel;
    private JPanel deckCounterPanel;
    private JLabel deckCounterLabel, prizeCardLabel;



    private boolean hasDrawnCard = false;



    public GameGUI() {



        setTitle("Pokémon Card Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        //bg music start

        MusicPlayer.playMusic("/assets/bgmusic.wav");



        // ✅ Create Name Input Panel
        JPanel namePanel = new JPanel(null); // Use null layout for precise positioning
        namePanel.setPreferredSize(new Dimension(800, 600));

        // ✅ Set Background Image
        JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/assets/bg.jpg")));
        backgroundLabel.setBounds(0, 0, 800, 600);
        namePanel.add(backgroundLabel);

        // ✅ Headline Label (Bigger Text)
        JLabel titleLabel = new JLabel("Enter Player Names", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 50, 400, 40); // Adjust position & size
        backgroundLabel.add(titleLabel);

        // ✅ Player 1 Name Input
        JTextField player1Field = new JTextField();
        player1Field.setFont(new Font("Arial", Font.PLAIN, 18));
        player1Field.setBounds(250, 150, 300, 40); // Medium size
        backgroundLabel.add(player1Field);

        JLabel player1Label = new JLabel("Player 1:", SwingConstants.RIGHT);
        player1Label.setFont(new Font("Arial", Font.BOLD, 18));
        player1Label.setForeground(Color.WHITE);
        player1Label.setBounds(150, 150, 100, 40);
        backgroundLabel.add(player1Label);

        // ✅ Player 2 Name Input
        JTextField player2Field = new JTextField();
        player2Field.setFont(new Font("Arial", Font.PLAIN, 18));
        player2Field.setBounds(250, 220, 300, 40);
        backgroundLabel.add(player2Field);

        JLabel player2Label = new JLabel("Player 2:", SwingConstants.RIGHT);
        player2Label.setFont(new Font("Arial", Font.BOLD, 18));
        player2Label.setForeground(Color.WHITE);
        player2Label.setBounds(150, 220, 100, 40);
        backgroundLabel.add(player2Label);

        // ✅ Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBounds(300, 300, 200, 50);
        backgroundLabel.add(startButton);

        // ✅ Add Action Listener to Start Button
        startButton.addActionListener(e -> {
            String player1Name = player1Field.getText().trim();
            String player2Name = player2Field.getText().trim();

            // ✅ Check if any name field is empty
            if (player1Name.isEmpty() || player2Name.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "⚠ Please enter names for both players before starting!",
                        "Name Required",
                        JOptionPane.WARNING_MESSAGE
                );
                return; // Prevent proceeding
            }

            // ✅ Remove Name Panel and Initialize Players
            getContentPane().removeAll();
            player1 = new Player(player1Name);
            player2 = new Player(player2Name);

            player1Deck = new Deck();
            player2Deck = new Deck();

            revalidate();
            repaint();

            // Proceed to Coin Flip
            initiateCoinFlip();
        });

        // ✅ Set Content Pane and Show
        setContentPane(namePanel);
        setVisible(true);
    }

    private void initiateCoinFlip() {
        JPanel coinFlipPanel = new JPanel(null);
        coinFlipPanel.setPreferredSize(new Dimension(800, 600));

        // ✅ Ensure the image is correctly loaded
        ImageIcon bgImage = loadImage("/assets/bg.jpg",800,600);
        JLabel backgroundLabel = new JLabel(bgImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        coinFlipPanel.add(backgroundLabel);

        // ✅ Headline Label (Big Text)
        JLabel titleLabel = new JLabel(player1.getName() + ", Choose HEADs or TAILs!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(150, 50, 500, 50);
        backgroundLabel.add(titleLabel);

        // ✅ Heads Button with Image
        JButton headsButton = new JButton(loadImage("/assets/head.jpg", 150, 150));
        headsButton.setBounds(200, 200, 150, 150);
        headsButton.setBorderPainted(false);
        headsButton.setContentAreaFilled(false);
        headsButton.setFocusPainted(false);
        backgroundLabel.add(headsButton);

// ✅ "Head" Caption Below Coin
        JLabel headsLabel = new JLabel("Head", SwingConstants.CENTER);
        headsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headsLabel.setForeground(Color.WHITE);
        headsLabel.setBounds(200, 360, 150, 30); // Adjusted to align with the button and be below it
        backgroundLabel.add(headsLabel);

// ✅ Tails Button with Image
        JButton tailsButton = new JButton(loadImage("/assets/tail.jpg", 150, 150));
        tailsButton.setBounds(450, 200, 150, 150);
        tailsButton.setBorderPainted(false);
        tailsButton.setContentAreaFilled(false);
        tailsButton.setFocusPainted(false);
        backgroundLabel.add(tailsButton);

// ✅ "Tail" Caption Below Coin
        JLabel tailsLabel = new JLabel("Tail", SwingConstants.CENTER);
        tailsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tailsLabel.setForeground(Color.WHITE);
        tailsLabel.setBounds(450, 360, 150, 30); // Adjusted to align with the button and be below it
        backgroundLabel.add(tailsLabel);


        headsButton.addActionListener(e -> processCoinFlip(true));
        tailsButton.addActionListener(e -> processCoinFlip(false));

        setContentPane(coinFlipPanel);
        revalidate();
        repaint();
    }

    private ImageIcon loadImage(String path, int width, int height) {
        java.net.URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            System.err.println("⚠ Image not found: " + path);
            return new ImageIcon(); // Return an empty image to avoid crashes
        }
    }




    private void processCoinFlip(boolean player1ChoseHeads) {
        boolean flipResultIsHeads = CoinFlip.flip(); // Randomly flips
        String flipResult = flipResultIsHeads ? "Heads" : "Tails";

        // Determine first player
        boolean player1Starts = (player1ChoseHeads == flipResultIsHeads);
        isPlayer1Turn = player1Starts;
        String firstPlayer = player1Starts ? player1.getName() : player2.getName();

        // ✅ Display the result with a new panel
        JPanel resultPanel = new JPanel(null);
        resultPanel.setPreferredSize(new Dimension(800, 600));

        JLabel bgLabel = new JLabel(new ImageIcon(getClass().getResource("/assets/battle.png")));
        bgLabel.setBounds(0, 0, 800, 600);
        resultPanel.add(bgLabel);

        JLabel resultLabel = new JLabel("Coin Flip Result: " + flipResult, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 28));
        resultLabel.setForeground(Color.RED);
        resultLabel.setBounds(200, 50, 400, 50);
        bgLabel.add(resultLabel);

        JLabel firstPlayerLabel = new JLabel(firstPlayer + " goes first!", SwingConstants.CENTER);
        firstPlayerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        firstPlayerLabel.setForeground(Color.YELLOW);
        firstPlayerLabel.setBounds(200, 120, 400, 50);
        bgLabel.add(firstPlayerLabel);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 18));
        continueButton.setBounds(300, 300, 200, 50);
        bgLabel.add(continueButton);

        continueButton.addActionListener(e -> {
            // ✅ Proceed to Game Start
            getContentPane().removeAll();
            SwingUtilities.invokeLater(this::startGame);
        });

        setContentPane(resultPanel);
        revalidate();
        repaint();
    }




    private void startGame() {
        distributeInitialHands(player1, player1Deck);
        distributeInitialHands(player2, player2Deck);

        // ✅ Remove previous UI components
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        // ✅ Ensure UI labels are initialized
        if (playerTurnLabel == null) playerTurnLabel = new JLabel();
        if (playerCardsLabel == null) playerCardsLabel = new JLabel();
        if (playerBenchLabel == null) playerBenchLabel = new JLabel();

        playerTurnLabel.setText("Turn: " + getCurrentPlayer().getName());
        playerCardsLabel.setText("Hand: ");
        playerBenchLabel.setText("Bench: ");

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(playerTurnLabel);
        topPanel.add(playerCardsLabel);
        topPanel.add(playerBenchLabel);
        add(topPanel, BorderLayout.NORTH);

        // ✅ Ensure UI Components Are Properly Initialized Before Using setupGameBoard()
        setupGameBoard();
        // ✅ Update deck counter in UI
        updateDeckCounterDisplay();

        // ✅ Update labels with actual data
        updateHandAndBenchDisplay();

        // ✅ Ensure UI updates properly
        revalidate();
        repaint();
        setVisible(true);
    }

    private void updateDeckCounterDisplay() {
        Deck currentDeck = isPlayer1Turn ? player1Deck : player2Deck;
        if (deckCounterLabel != null) {
            deckCounterLabel.setText("Deck: " + currentDeck.getDeckSize());
        }
    }



    private void setupGameBoard() {
        // ✅ Create a layered pane for better layout management
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // ✅ Ensure UI Components Are Not Null Before Adding
        if (exitPlayButton == null) {
            exitPlayButton = new JButton("Exit Play");
            exitPlayButton.addActionListener(e -> returnToMainMenu());
        }

        // ✅ Load Background Image
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/assets/battle.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(1)); // ✅ Background is at the bottom layer

        // ✅ Player Name & Cards Panel
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setOpaque(false);
        topPanel.setBounds(50, 20, 700, 100);

        // ✅ Deck Counter Label (Top Right)

        Deck currentDeck = isPlayer1Turn ? player1Deck : player2Deck;
        deckCounterLabel = new JLabel("Deck: " + currentDeck.getDeckSize());
        deckCounterLabel.setFont(new Font("Arial", Font.BOLD, 18));
        deckCounterLabel.setForeground(Color.WHITE);
        deckCounterLabel.setBounds(680, 20, 100, 30); // Position it at top-right corner
        layeredPane.add(deckCounterLabel, Integer.valueOf(2)); // Add on top of background

        //prizepool label

        int poolAmount = getCurrentPlayer().getPrizeAmount();
        prizeCardLabel = new JLabel("Prize Card: " + poolAmount);
        prizeCardLabel.setFont(new Font("Arial", Font.BOLD, 16));
        prizeCardLabel.setForeground(Color.YELLOW);
        prizeCardLabel.setBounds(680, 43, 200, 30);
        layeredPane.add(prizeCardLabel, Integer.valueOf(2));


        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 35));
        playerTurnLabel.setForeground(Color.RED);
        playerTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        playerCardsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerCardsLabel.setForeground(Color.BLACK);
        playerCardsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(playerTurnLabel);
        topPanel.add(playerCardsLabel);
        layeredPane.add(topPanel, Integer.valueOf(2)); // ✅ On top of the background

        // ✅ Buttons Panel (Ensure They Are Initialized)
        JPanel buttonPanel = new JPanel(new GridLayout(1, 8, 5, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(50, 140, 700, 60);

        if (playTrainerButton == null) {
            playTrainerButton = new JButton("Trainer");
            playTrainerButton.addActionListener(e -> useTrainerCard()); // ✅ Calls useTrainerCard()
        }

        if (playPokemonButton == null) playPokemonButton = new JButton("Play Pokémon");
        playPokemonButton.addActionListener(e -> playPokemon());  // ✅ Calls the playPokemon() method

        if (attackButton == null) {
            attackButton = new JButton("Attack");
            attackButton.addActionListener(e -> attack()); // ✅ Add ActionListener to call attack() method
        }
        addPokemonFieldButton = new JButton("To Field");
        addPokemonFieldButton.addActionListener(e -> addPokemonToField());  // ✅ Add Event Listener

        if (addPokemonBenchButton == null) addPokemonBenchButton = new JButton("To Bench");
        addPokemonBenchButton.addActionListener(e -> addPokemonToBench());
        if (attachEnergyButton == null) {
            attachEnergyButton = new JButton("Energy");
            attachEnergyButton.addActionListener(e -> attachEnergyToPokemon());  // ✅ Add Event Listener
        }

        if (drawCardButton == null) {
            drawCardButton = new JButton("Draw");
            drawCardButton.addActionListener(e -> drawCard()); // ✅ Calls drawCard() method
        }

        if (endTurnButton == null) {
            endTurnButton = new JButton("End Turn");
            endTurnButton.addActionListener(e -> endTurn()); // ✅ Attach event listener
        }

        JButton[] buttons = {
                (playTrainerButton == null ? new JButton("Trainer") : playTrainerButton),
                (playPokemonButton == null ? new JButton("Play Pokémon") : playPokemonButton),
                (attackButton == null ? new JButton("Attack") : attackButton),
                (addPokemonFieldButton == null ? new JButton("To Field") : addPokemonFieldButton),
                (addPokemonBenchButton == null ? new JButton("To Bench") : addPokemonBenchButton),
                (attachEnergyButton == null ? new JButton("Energy") : attachEnergyButton),
                (drawCardButton == null ? new JButton("Draw") : drawCardButton),
                (endTurnButton == null ? new JButton("End Turn") : endTurnButton)
        };

// Ensure buttons are initialized before modifying them
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setPreferredSize(new Dimension(85, 30));
            buttonPanel.add(button);
        }


        layeredPane.add(buttonPanel, Integer.valueOf(2)); // ✅ Buttons on top of the background

        // ✅ Battle Ground Panel (Ensure It Exists)
        battlefieldPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgImage = new ImageIcon(getClass().getResource("/assets/battle.png"));
                g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // ✅ Battlefield Panel (Holds Left & Right Sections)
        battlefieldPanel = new JPanel();
        battlefieldPanel.setLayout(new GridBagLayout()); // GridBagLayout for flexible sizing
        battlefieldPanel.setBounds(50, 220, 700, 300);
        battlefieldPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),  // Border style
                "Battlefield",  // Title
                javax.swing.border.TitledBorder.CENTER,  // Center align title
                javax.swing.border.TitledBorder.TOP,  // Position at the top
                new Font("Arial", Font.BOLD, 18),  // Font settings
                Color.BLACK // Title color
        ));

        battlefieldPanel.setBackground(new Color(220, 220, 220)); // Light background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

// ✅ Left Panel (Bench Pokémon)
        benchPanel = new JPanel();
        benchPanel.setBorder(BorderFactory.createTitledBorder("Bench Pokémon"));
        benchPanel.setBackground(new Color(200, 200, 200)); // Light gray
        benchPanel.setLayout(new BoxLayout(benchPanel, BoxLayout.Y_AXIS)); // Ensures Pokémon are stacked
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4; // Half the battlefield width
        battlefieldPanel.add(benchPanel, gbc);

// ✅ Vertical Divider (Creates a line between panels)
        JPanel divider = new JPanel();
        divider.setBackground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.05; // Thin vertical divider
        gbc.fill = GridBagConstraints.VERTICAL;
        battlefieldPanel.add(divider, gbc);

// ✅ Right Panel (Active Pokémon)
        activePokemonPanel = new JPanel();
        activePokemonPanel.setBorder(BorderFactory.createTitledBorder("Active Pokémon"));
        activePokemonPanel.setBackground(new Color(150, 150, 150)); // Darker gray
        activePokemonPanel.setLayout(new BoxLayout(activePokemonPanel, BoxLayout.Y_AXIS)); // Stacks Pokémon
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        battlefieldPanel.add(activePokemonPanel, gbc);

// ✅ Add Battlefield Panel to the Layered Pane
        layeredPane.add(battlefieldPanel, Integer.valueOf(2));


        // ✅ Bottom Panel with Exit Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBounds(350, 530, 100, 40);
        bottomPanel.add(exitPlayButton);
        layeredPane.add(bottomPanel, Integer.valueOf(2));

        // ✅ Set the Content Pane Properly
        setContentPane(layeredPane);
        revalidate();
        repaint();
    }

    //  Distributes Initial Hands (Ensuring at Least 1 Pokémon)
    private void distributeInitialHands(Player player, Deck playerDeck) {
        ArrayList<Card> tempHand = new ArrayList<>();
        boolean hasPokemon = false;

        while (tempHand.size() < 7) {
            Card drawnCard = playerDeck.drawCard(); // ✅ Now draws from the correct deck
            if (drawnCard instanceof PokemonCard) {
                hasPokemon = true;
            }
            tempHand.add(drawnCard);
        }
        player.setHand(tempHand);
    }


    private void updateActivePokemonDisplay() {
        if (activePokemonPanel == null) return; // Prevent NullPointerException

        activePokemonPanel.removeAll(); // Clear previous Pokémon display
        Player currentPlayer = getCurrentPlayer();
        PokemonCard active = currentPlayer.getActive();

        if (active != null) {
            JLabel activeLabel = new JLabel(active.getName() + " (HP: " + active.getHp() + ", Energy: " + active.getEnergy() + ")");
            activeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            activeLabel.setForeground(Color.WHITE);
            activePokemonPanel.add(activeLabel);
        } else {
            JLabel noPokemonLabel = new JLabel("No Active Pokémon");
            noPokemonLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noPokemonLabel.setForeground(Color.RED);
            activePokemonPanel.add(noPokemonLabel);
        }

        activePokemonPanel.revalidate();
        activePokemonPanel.repaint();
    }








    private void updateHandAndBenchDisplay() {
        if (playerCardsLabel != null && playerBenchLabel != null) {
            playerCardsLabel.setText("Hand: " + getCurrentPlayer().displayHand());
            playerBenchLabel.setText("Bench: " + getCurrentPlayer().displayBench());
        }

        updateActivePokemonDisplay(); // Ensure Active Pokémon panel is updated
        updateBenchDisplay(); // Ensure Bench Pokémon panel is updated
    }

    private void updatePrizeCardDisplay(Player player) {
        if (!player.getPool().isEmpty()) {
            prizeCardLabel.setText("Prize Card: " + player.getPool().get(0).getName());
        } else {
            prizeCardLabel.setText("Prize Card: 0");
        }
    }
    private void updateBenchDisplay() {
        if (benchPanel == null) return; // Prevent NullPointerException

        benchPanel.removeAll(); // Clear previous Pokémon display
        Player currentPlayer = getCurrentPlayer();
        ArrayList<PokemonCard> bench = currentPlayer.getBench();

        if (bench.isEmpty()) {
            JLabel noBenchPokemonLabel = new JLabel("No Pokémon on Bench");
            noBenchPokemonLabel.setFont(new Font("Arial", Font.BOLD, 14));
            noBenchPokemonLabel.setForeground(Color.RED);
            benchPanel.add(noBenchPokemonLabel);
        } else {
            for (PokemonCard pokemon : bench) {
                JLabel benchLabel = new JLabel(pokemon.getName() + " (HP: " + pokemon.getHp() + ")");
                benchLabel.setFont(new Font("Arial", Font.BOLD, 14));
                benchLabel.setForeground(Color.YELLOW);
                benchPanel.add(benchLabel);
            }
        }

        benchPanel.revalidate();
        benchPanel.repaint();
    }





    private Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }

    private void useTrainerCard() {
        Player currentPlayer = getCurrentPlayer();
        ArrayList<Card> hand = new ArrayList<>(currentPlayer.getHand());

        TrainerCard selectedTrainer = null;

        // ✅ Find the first Trainer card in hand
        for (Card card : hand) {
            if (card instanceof TrainerCard) {
                selectedTrainer = (TrainerCard) card;
                break;
            }
        }

        if (selectedTrainer == null) {
            JOptionPane.showMessageDialog(this, "No Trainer Card available!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Remove Trainer card from hand
        currentPlayer.getHand().remove(selectedTrainer);
        JOptionPane.showMessageDialog(this, "Used Trainer Card: " + selectedTrainer.getName(), "Trainer Card Used", JOptionPane.INFORMATION_MESSAGE);

        // ✅ Apply Trainer Card Effect
        applyTrainerEffect(selectedTrainer, currentPlayer);

        // ✅ Update UI
        updateHandAndBenchDisplay();
    }

    private void applyTrainerEffect(TrainerCard trainer, Player currentPlayer) {
        PokemonCard activePokemon = currentPlayer.getActive();

        switch (trainer.getName()) {
            case "Potion":
                if (activePokemon != null) {
                    activePokemon.heal(20); // ✅ Heals the active Pokémon by 20 HP
                    JOptionPane.showMessageDialog(this, activePokemon.getName() + " healed by 20 HP!", "Potion Used", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No Active Pokémon to heal!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Full Heal":
                if (activePokemon != null) {
                    activePokemon.setStatusEffect("None"); // ✅ Removes status effects
                    activePokemon.heal(currentPlayer.getActive().getHp());
                    JOptionPane.showMessageDialog(this, activePokemon.getName() + " is fully healed from status effects!", "Full Heal Used", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No Active Pokémon to heal!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Professor Oak":
                Deck currentDeck = isPlayer1Turn ? player1Deck : player2Deck;
                for (int i = 0; i < 3; i++) {
                    currentPlayer.drawCard(currentDeck); // ✅ Draws 3 new cards
                    deckCounterLabel.setText("Deck: " + currentDeck.getDeckSize());
                }
                JOptionPane.showMessageDialog(this, "Drew 3 cards from the deck!", "Professor Oak Used", JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Unknown Trainer Card effect!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

        // ✅ Update UI to reflect changes
        updateHandAndBenchDisplay();
    }



    private void playPokemon() {
        Player currentPlayer = getCurrentPlayer();

        // ✅ Ensure there is NO active Pokémon before setting one
        if (currentPlayer.getActive() != null) {
            JOptionPane.showMessageDialog(this,
                    "You already have an active Pokémon! Use 'To Bench' to add more Pokémon.",
                    "Cannot Replace Active Pokémon",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Send out Pokémon from hand (Only if none is active)
        sendOutPokemonFromHand(currentPlayer);

        // ✅ Update UI
        updateHandAndBenchDisplay();
        updateActivePokemonDisplay();
    }




    private void addPokemonToBench() {
        Player currentPlayer = getCurrentPlayer();

        // Find a Pokémon card in hand
        PokemonCard selectedPokemon = null;
        for (Card card : new ArrayList<>(currentPlayer.getHand())) { // Check hand for a Pokémon
            if (card instanceof PokemonCard) {
                selectedPokemon = (PokemonCard) card;
                break;
            }
        }

        if (selectedPokemon == null) {
            JOptionPane.showMessageDialog(this, "No Pokémon available to play!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Ensure the bench isn't full (Limit to 5 Pokémon)
        if (currentPlayer.getBench().size() >= 5) {
            JOptionPane.showMessageDialog(this, "Your bench is full! Cannot add more Pokémon.", "Bench Full", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Add to the Bench
        currentPlayer.getBench().add(selectedPokemon);
        JOptionPane.showMessageDialog(this, selectedPokemon.getName() + " added to the bench!");

        // ✅ Remove Pokémon from hand after playing
        currentPlayer.getHand().remove(selectedPokemon);

        // ✅ Update UI
        updateHandAndBenchDisplay();
        updateBenchDisplay();
    }



    private void switchTurn() {
        isPlayer1Turn = !isPlayer1Turn; // Toggle turn
        playerTurnLabel.setText("Turn: " + getCurrentPlayer().getName()); //  Update UI

        // Refresh hand and bench display
        updateHandAndBenchDisplay();

        JOptionPane.showMessageDialog(this, "Turn switched to " + getCurrentPlayer().getName(), "Turn Ended", JOptionPane.INFORMATION_MESSAGE);
    }


    private void returnToMainMenu() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit play and return to the main menu?",
                "Exit Play Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            dispose(); // Close current game window
            new MenuGUI(); // Open main menu
        }
        // If NO is chosen, do nothing, and the game continues
    }

    private void attack() {
        Player currentPlayer = getCurrentPlayer();
        Player opponent = (currentPlayer == player1) ? player2 : player1;

        PokemonCard attacker = currentPlayer.getActive();
        PokemonCard defender = opponent.getActive();

        if (attacker == null) {
            JOptionPane.showMessageDialog(this, "No Active Pokémon to attack with!", "Attack Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (defender == null) {
            JOptionPane.showMessageDialog(this, "Opponent has no Active Pokémon to attack!", "Attack Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (attacker.getEnergy() <= 0) {
            JOptionPane.showMessageDialog(this, attacker.getName() + " has no Energy! Attach an Energy card before attacking.",
                    "Energy Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get attack details dynamically
        Attack attack1 = attacker.getAttack1();
        Attack attack2 = attacker.getAttack2();

        String attack1Name = attack1.getName();
        String attack2Name = attack2.getName();
        int attack1Damage = attack1.getDamage();
        int attack2Damage = attack2.getDamage();

        // Prompt the user to choose an attack
        String[] attackOptions = {
                attack1Name + " (" + attack1Damage + " DMG)",
                attack2Name + " (" + attack2Damage + " DMG)"
        };

        int attackChoice = JOptionPane.showOptionDialog(this,
                "Choose an attack for " + attacker.getName() + ":",
                "Select Attack",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                attackOptions,
                attackOptions[0]);

        if (attackChoice == -1) {
            return; // Cancel attack if no option is selected
        }

        int damageDealt;
        if (attackChoice == 0) {
            damageDealt = attacker.getAttack1().getDamage();
            attacker.useAttack(defender);
        } else {
            damageDealt = attacker.getAttack2().getDamage();
            attacker.useAttack2(defender);
        }

        JOptionPane.showMessageDialog(this,
                attacker.getName() + " attacked " + defender.getName() + " for " + damageDealt + " damage!\n"
                        + defender.getName() + " now has " + defender.getHp() + " HP left.",
                "Attack Successful",
                JOptionPane.INFORMATION_MESSAGE);

        // **Check if opponent’s Pokémon fainted**
        if (defender.getHp() <= 0) {
            JOptionPane.showMessageDialog(this, defender.getName() + " has fainted!", "Defeated!", JOptionPane.INFORMATION_MESSAGE);

            // ✅ Award the winner a prize card
            currentPlayer.claimPrizeCard();

            // ✅ Update UI for prize pool and deck
            updatePrizeCardDisplay(currentPlayer);
            updateDeckCounterDisplay(); // ✅ Update deck counter after drawing prize card

            // ✅ Check if the current player has taken all 3 prize cards
            if (currentPlayer.getPrizeAmount() == 0) {
                endGame(currentPlayer);
                return;
            }

            // ✅ Check if opponent has Pokémon in the bench
            if (!opponent.getBench().isEmpty()) {
                opponent.switchToNextPokemon();
                JOptionPane.showMessageDialog(this,
                        opponent.getName() + " sent out " + opponent.getActive().getName() + "!",
                        "New Active Pokémon",
                        JOptionPane.INFORMATION_MESSAGE);

                // ✅ End turn so opponent can play
                endTurn();
                return;
            }

            // ✅ If no Pokémon in bench, check hand for any playable Pokémon
            if (opponent.getHand().stream().anyMatch(card -> card instanceof PokemonCard)) {
                JOptionPane.showMessageDialog(this,
                        opponent.getName() + " must select a Pokémon from their hand!",
                        "Choose New Active Pokémon",
                        JOptionPane.INFORMATION_MESSAGE);

                sendOutPokemonFromHand(opponent);

                // ✅ End turn so opponent can play their new Pokémon
                endTurn();
                return;
            }

            // ✅ If no Pokémon anywhere, declare the loss
            JOptionPane.showMessageDialog(this,
                    opponent.getName() + " has no Pokémon left! " + currentPlayer.getName() + " wins!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            endGame(currentPlayer);
            return;
        }

        // ✅ Update UI after attack
        updateHandAndBenchDisplay();
        updateActivePokemonDisplay();
        updateBenchDisplay();

        // ✅ End Turn After Attack
        endTurn();
    }




    public boolean checkPrizePool(){
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.getPrizeAmount() <= 0){
            return true;
        }
        return false;
    }

    private void addPokemonToField() {
        String message = getCurrentPlayer().addPokemonToField();
        JOptionPane.showMessageDialog(this, message, "Active Pokémon Update", JOptionPane.INFORMATION_MESSAGE);

        // ✅ Ensure UI updates after changing active Pokémon
        updateHandAndBenchDisplay();
    }



    private void drawCard() {
        if (hasDrawnCard) {
            JOptionPane.showMessageDialog(this, "You can only draw once per turn!", "Draw Limit Reached", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Player currentPlayer = getCurrentPlayer();
        Player opponent = (currentPlayer == player1) ? player2 : player1;
        Deck currentDeck = (currentPlayer == player1) ? player1Deck : player2Deck; // ✅ Use correct deck

        if (currentDeck.getDeckSize() == 0) {
            JOptionPane.showMessageDialog(this, currentPlayer.getName() + " has no cards left to draw! " + opponent.getName() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            endGame(opponent);
            return;
        }

        Card drawnCard = currentDeck.drawCard(); // ✅ Correct deck
        currentPlayer.getHand().add(drawnCard);

        JOptionPane.showMessageDialog(this, currentPlayer.getName() + " drew a card: " + drawnCard.getName(), "Card Drawn", JOptionPane.INFORMATION_MESSAGE);
        updateHandAndBenchDisplay();
        hasDrawnCard = true;
        deckCounterLabel.setText("Deck: " + currentDeck.getDeckSize());
    }



    private void checkForNoPokemonLoss(Player player) {
        boolean hasPokemon = false;

        // ✅ Check Active Pokémon
        if (player.getActive() != null) {
            hasPokemon = true;
        }

        // ✅ Check Hand for Pokémon
        for (Card card : player.getHand()) {
            if (card instanceof PokemonCard) {
                hasPokemon = true;
                break;
            }
        }

        // ✅ Check Bench for Pokémon
        if (!player.getBench().isEmpty()) {
            hasPokemon = true;
        }

        // ✅ If no Pokémon available anywhere, player loses
        if (!hasPokemon) {
            Player opponent = (player == player1) ? player2 : player1;
            JOptionPane.showMessageDialog(this,
                    player.getName() + " has no Pokémon left! " + opponent.getName() + " wins!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            endGame(opponent);
        }
    }



    private void attachEnergyToPokemon() {
        Player currentPlayer = getCurrentPlayer();
        ArrayList<Card> hand = new ArrayList<>(currentPlayer.getHand());

        EnergyCard selectedEnergy = null;

        // ✅ Find an Energy card in hand
        for (Card card : hand) {
            if (card instanceof EnergyCard) {
                selectedEnergy = (EnergyCard) card;
                break;
            }
        }

        if (selectedEnergy == null) {
            JOptionPane.showMessageDialog(this, "No Energy Card available!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PokemonCard activePokemon = currentPlayer.getActive();

        if (activePokemon != null) {
            activePokemon.addEnergy(selectedEnergy);  // ✅ Attach Energy to Active Pokémon
            JOptionPane.showMessageDialog(this, "Attached " + selectedEnergy.getName() + " to " + activePokemon.getName() + "!\n"
                            + activePokemon.getName() + " now has " + activePokemon.getEnergy() + " Energy.",
                    "Energy Attached", JOptionPane.INFORMATION_MESSAGE);
        } else if (!currentPlayer.getBench().isEmpty()) {
            // ✅ Attach to Bench Pokémon if Active is empty
            PokemonCard benchPokemon = currentPlayer.getBench().get(0);
            benchPokemon.addEnergy(selectedEnergy);
            JOptionPane.showMessageDialog(this, "Attached " + selectedEnergy.getName() + " to " + benchPokemon.getName() + "!",
                    "Energy Attached", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No Pokémon to attach Energy!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Remove Energy card from hand after attaching
        currentPlayer.getHand().remove(selectedEnergy);

        // ✅ Update UI (Including Active Pokémon Energy)
        updateHandAndBenchDisplay();
        updateActivePokemonDisplay();  // ✅ Ensure Energy is updated
    }

    private void playPokemonFromHand(Player player) {
        ArrayList<PokemonCard> handPokemon = new ArrayList<>();
        for (Card card : player.getHand()) {
            if (card instanceof PokemonCard) {
                handPokemon.add((PokemonCard) card);
            }
        }

        if (handPokemon.isEmpty()) return; // ✅ No Pokémon in hand

        // ✅ Ask the player which Pokémon to play
        String[] options = handPokemon.stream().map(PokemonCard::getName).toArray(String[]::new);
        String chosenPokemon = (String) JOptionPane.showInputDialog(this,
                "Choose a Pokémon to send out!",
                "Select Pokémon",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (chosenPokemon != null) {
            for (PokemonCard pokemon : handPokemon) {
                if (pokemon.getName().equals(chosenPokemon)) {
                    player.setActivePokemon(pokemon);
                    player.getHand().remove(pokemon);
                    JOptionPane.showMessageDialog(this,
                            player.getName() + " sent out " + pokemon.getName() + "!",
                            "New Active Pokémon",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }

    private void sendOutPokemonFromHand(Player player) {
        // ✅ If player already has an active Pokémon, do nothing
        if (player.getActive() != null) {
            return;
        }

        // ✅ Filter Pokémon from hand
        ArrayList<PokemonCard> handPokemon = new ArrayList<>();
        for (Card card : player.getHand()) {
            if (card instanceof PokemonCard) {
                handPokemon.add((PokemonCard) card);
            }
        }

        // ✅ If there are no Pokémon in hand, return (No action needed)
        if (handPokemon.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    player.getName() + " has no Pokémon in hand to send out!",
                    "No Pokémon Available",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Ask the player which Pokémon to send out
        String[] options = handPokemon.stream().map(PokemonCard::getName).toArray(String[]::new);
        String chosenPokemon = (String) JOptionPane.showInputDialog(this,
                player.getName() + ", choose a Pokémon to send out!",
                "Select Pokémon",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // ✅ If a Pokémon was chosen, assign it as the active Pokémon
        if (chosenPokemon != null) {
            for (PokemonCard pokemon : handPokemon) {
                if (pokemon.getName().equals(chosenPokemon)) {
                    player.setActivePokemon(pokemon);
                    player.getHand().remove(pokemon);

                    JOptionPane.showMessageDialog(this,
                            player.getName() + " sent out " + pokemon.getName() + "!",
                            "New Active Pokémon",
                            JOptionPane.INFORMATION_MESSAGE);

                    // ✅ Refresh UI to reflect changes
                    updateHandAndBenchDisplay();
                    updateActivePokemonDisplay();
                    return;
                }
            }
        }
    }





    private void endTurn() {
        // ✅ Toggle the turn
        this.hasDrawnCard = false;
        isPlayer1Turn = !isPlayer1Turn;
        Player currentPlayer = getCurrentPlayer();
        Player opponent = (currentPlayer == player1) ? player2 : player1;
        Deck currentDeck = isPlayer1Turn ? player1Deck : player2Deck;
        deckCounterLabel.setText("Deck: " + currentDeck.getDeckSize());

        // ✅ Update UI to show whose turn it is (officially switched)
        playerTurnLabel.setText("Turn: " + currentPlayer.getName());

        // ✅ Apply any status effects (like Sleep, Burn) before the next turn starts
        if (currentPlayer.getActive() != null) {
            currentPlayer.getActive().applyStatusEffect();
        }

        // ✅ Refresh UI Components
        updateHandAndBenchDisplay();
        updateActivePokemonDisplay();
        updateBenchDisplay();

        // ✅ Notify Players that turn has switched
        JOptionPane.showMessageDialog(this,
                "Turn ended! It's now " + currentPlayer.getName() + "'s turn.",
                "Turn Switched",
                JOptionPane.INFORMATION_MESSAGE);

        // ✅ After switching turn, check if the opponent has an active Pokémon
        boolean opponentHasActive = (opponent.getActive() != null);
        boolean opponentHasBench = !opponent.getBench().isEmpty();
        boolean opponentHasHand = opponent.getHand().stream().anyMatch(card -> card instanceof PokemonCard);

        if (!opponentHasActive) {
            if (opponentHasBench) {
                PokemonCard newActive = opponent.getBench().remove(0);
                opponent.setActivePokemon(newActive);
                JOptionPane.showMessageDialog(this,
                        opponent.getName() + " sent out " + newActive.getName() + "!",
                        "New Active Pokémon",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (opponentHasHand) {
                sendOutPokemonFromHand(opponent);
            }
        }

        // ✅ If the opponent has NO Pokémon left after switching turns, they lose
        if (!opponentHasActive && !opponentHasBench && !opponentHasHand) {
            JOptionPane.showMessageDialog(this,
                    opponent.getName() + " has no Pokémon left! " + currentPlayer.getName() + " wins!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            endGame(currentPlayer);  // ✅ Current player wins
        }
    }






    private void endGame(Player winner) {
        JOptionPane.showMessageDialog(this,
                "🎉 Congratulations " + winner.getName() + "! You won the game! 🎉",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);

        //bg music stop

        MusicPlayer.stopMusic();


        // ✅ Close the game window
        dispose();

        // ✅ Redirect to the main menu
        new MenuGUI();
    }







}
