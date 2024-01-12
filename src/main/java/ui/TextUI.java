package ui;

import mancala.MancalaGame;
import mancala.PitNotFoundException;
import mancala.InvalidMoveException;
import mancala.Board;
import mancala.Player;
import mancala.Pit;
import mancala.Store;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private Scanner scanner;
    private MancalaGame myGame;

    public TextUI() {
        myGame = new MancalaGame();
        scanner = new Scanner(System.in);
    }
        public void display() {
        Board board = myGame.getBoard();
        ArrayList<Pit> pits = board.getPits();
        ArrayList<Store> stores = board.getStores();
        
        System.out.println("****************************************");

        System.out.println("Player Two's Store");
        System.out.print("   ");
        for (int i = 12; i > 6; i--) {
            System.out.print("   " + pits.get(i - 1).getStoneCount() + " ");
        }
        System.out.println();
        System.out.println("[" + stores.get(1).getTotalStones() + "] ---- ---- ---- ---- ---- ---- [" + stores.get(0).getTotalStones() + "]");
        System.out.print("   ");
        for (int i = 1; i <= 6; i++) {
            System.out.print("   " + pits.get(i - 1).getStoneCount() + " ");
        }
        System.out.println();
        System.out.println("                                Player One's Store");
        System.out.println();

        System.out.println("****************************************");
    }

    public void gameToBegin() {
        System.out.println("Welcome to Mancala!");

        // Set up players
        System.out.print("Enter Player One's name: ");
        String playerOneName = scanner.nextLine();
        Player playerOne = new Player(playerOneName);

        System.out.print("Enter Player Two's name: ");
        String playerTwoName = scanner.nextLine();
        Player playerTwo = new Player(playerTwoName);

        // Set up the myGame with players
        myGame.setPlayers(playerOne, playerTwo);
        myGame.startNewGame();

        // Main myGame loop
        while (!myGame.isGameOver()) {
            int startPit;
            System.out.println("Current board:");
            display();
            Player currentPlayer = myGame.getCurrentPlayer();
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");

            // Prompt the current player to enter the pit number to move from
            if (currentPlayer == playerOne) {
                System.out.print("Enter the pit number to move from (1-6): ");
            } else if (currentPlayer == playerTwo) {
                System.out.print("Enter the pit number to move from (7-12): ");
            }

            // Get the input for the pit number
            try {
                startPit = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            // Try to make a move and handle exceptions
            try {
                int stonesMoved = myGame.getNumStones(startPit - 1);
                myGame.move(startPit - 1);
                System.out.println(currentPlayer.getName() + " moved " + stonesMoved + " stones.");
            } catch (PitNotFoundException e) {
                System.out.println("Invalid pit. " + e.getMessage());
            }catch (InvalidMoveException e){
                System.out.println("Invalid move. " + e.getMessage());
            }

            System.out.println();
        }

        // Game over, print the final results
        System.out.println("Game Over!");
        display();
        Player winner = myGame.getWinner();
        if (winner != null) {
            System.out.println(winner.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        textUI.gameToBegin();
    }
}