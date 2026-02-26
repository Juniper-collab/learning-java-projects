import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameGUI {

    public static void main(String[] args){

        SwingUtilities.invokeLater(() ->{

            JFrame frame = new JFrame("Number Guess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 380);
            frame.setLocationRelativeTo(null);

            // Output links
            JTextArea outputArea = new JTextArea();
            outputArea.setEditable(false);
            outputArea.setLineWrap(true);
            outputArea.setWrapStyleWord(true);
            JScrollPane outputScroll = new JScrollPane(outputArea);

            // Right panel: label + input + buttons
            JLabel label = new JLabel("Rate eine Zahl (1-100):");
            JTextField inputField = new JTextField();

            JButton guessButton = new JButton("Raten");
            JButton resetButton = new JButton("Neues Spiel");

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            inputField.setAlignmentX(Component.LEFT_ALIGNMENT);
            guessButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            resetButton.setAlignmentX(Component.LEFT_ALIGNMENT);

            rightPanel.add(label);
            rightPanel.add(Box.createVerticalStrut(6));
            rightPanel.add(inputField);
            rightPanel.add(Box.createVerticalStrut(10));
            rightPanel.add(guessButton);
            rightPanel.add(Box.createVerticalStrut(6));
            rightPanel.add(resetButton);
            rightPanel.add(Box.createVerticalGlue());

            // Main layout
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            mainPanel.add(outputScroll, BorderLayout.CENTER);
            mainPanel.add(rightPanel, BorderLayout.EAST);

            frame.setContentPane(mainPanel);

            // Game state
            Random random = new Random();
            final int[] secretNumber = { random.nextInt(100) + 1 };
            final int[] attempts = { 0 };

            outputArea.append("Spiel gestartet. Viel Erfolg!\n");

            guessButton.addActionListener(e -> {
                String text = inputField.getText().trim();

                int guess;
                try {
                    guess = Integer.parseInt(text);
                } catch (NumberFormatException ex) {
                    outputArea.append("Bitte eine gültige Zahl eingeben.\n");
                    inputField.setText("");
                    inputField.requestFocus();
                    return;
                }

                if (guess < 1 || guess > 100) {
                    outputArea.append("Bitte nur zwischen 1 und 100 raten.\n");
                    inputField.setText("");
                    inputField.requestFocus();
                    return;
                }

                attempts[0]++;

                if (guess < secretNumber[0]) {
                    outputArea.append("Zu klein. Versuche: " + attempts[0] + "\n");
                } else if (guess > secretNumber[0]) {
                    outputArea.append("Zu groß. Versuche: " + attempts[0] + "\n");
                } else {
                    outputArea.append("Richtig! Du hast " + attempts[0] + " Versuche gebraucht.\n");
                    guessButton.setEnabled(false);
                }

                outputArea.setCaretPosition(outputArea.getDocument().getLength());
                inputField.setText("");
                inputField.requestFocus();
            });

            resetButton.addActionListener(e -> {
                secretNumber[0] = random.nextInt(100) + 1;
                attempts[0] = 0;
                guessButton.setEnabled(true);
                outputArea.append("\nNeues Spiel gestartet.\n");
                outputArea.setCaretPosition(outputArea.getDocument().getLength());
                inputField.setText("");
                inputField.requestFocus();
            });

            frame.setVisible(true);
            inputField.requestFocus();
        });
    }
}
