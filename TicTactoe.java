import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTactoe extends JFrame {
    
    private JButton[][] buttons = new JButton[3][3]; // 3x3 grid of buttons
    private char currentPlayer = 'X'; // Player X starts
    private boolean gameOver = false; // Track if the game is over

    public TicTactoe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // 3x3 layout for the buttons

        initializeButtons();
        setVisible(true);
    }

    // Initialize the buttons and add them to the frame
    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("-");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                add(buttons[row][col]); // Add the button to the frame
            }
        }
    }

    // Handle button clicks (player moves)
    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameOver || !buttons[row][col].getText().equals("-")) {
                return; // Ignore clicks if the game is over or if the cell is already taken
            }

            buttons[row][col].setText(String.valueOf(currentPlayer)); // Set the button text to the current player's symbol

            if (checkWin()) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(null, "It's a draw!");
                gameOver = true;
            } else {
                switchPlayer(); // Switch to the other player
            }
        }
    }

    // Switch the current player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check if the current player has won
    private boolean checkWin() {
        // Check rows, columns, and diagonals
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check for a draw (if all spaces are filled and there's no winner)
    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check all rows for a win
    private boolean checkRows() {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    // Check all columns for a win
    private boolean checkColumns() {
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][col].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    // Check both diagonals for a win
    private boolean checkDiagonals() {
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    // Main method to launch the game
    public static void main(String[] args) {
        new TicTactoe(); // Create and show the Tic Tac Toe game window
    }
}
