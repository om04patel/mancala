package mancala;

import java.util.ArrayList;

public class MancalaGame {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    
    public MancalaGame() {
        board = new Board();
        currentPlayer = playerOne;
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
        board.registerPlayers(playerOne, playerTwo);
        startNewGame();
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        playerOne = onePlayer; // Assign the first player to playerOne and the second player to playerTwo.
        playerTwo = twoPlayer;
        // Register the players with the game board.
        board.registerPlayers(playerOne, playerTwo);
        // Set the initial current player to playerOne.
        currentPlayer = playerOne;
    }


    public ArrayList<Player> getPlayers() {
        // Create a new ArrayList to hold the players
        ArrayList<Player> players = new ArrayList<>();

        // Add playerOne and playerTwo to the list
        players.add(playerOne);
        players.add(playerTwo);

        // Return the list of players
        return players;
    }

    public Player getCurrentPlayer() {
        // Return the current player
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        // Set the current player to the provided player
        currentPlayer = player;
    }

    public void setBoard(Board theBoard) {
        // Set the game board to the provided board
        board = theBoard;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        // Check if the pitNum is within the valid range (0 to 11)
        if (pitNum >= 0 && pitNum < 12) {
            // If valid, return the number of stones in the specified pit from the board
            return board.getNumStones(pitNum);
        } else {
            // If invalid, throw a PitNotFoundException
            throw new PitNotFoundException();
        }
    }

    public Board getBoard() {
        return board; //return the board
    }

public int move(int startPit) throws InvalidMoveException {
    // Initialize variables
    int stones = 0;
    int sumPit = 0;

    // Check if the current player is valid
    if (!(currentPlayer == playerOne || currentPlayer == playerTwo)) {
        throw new IllegalStateException("Invalid player");
    }

    // Check if the startPit is within the valid range (0 to 11)
    if (startPit < 0 || startPit > 11) {
        throw new InvalidMoveException();
    }

    // Attempt to move stones on the board
    stones = board.moveStones(startPit, currentPlayer);

    // If stones were moved, switch to the next player
    if (stones > 0) {
        switchPlayer();
    }

    // Calculate the sum of stones in pits based on the current player and startPit
    if (startPit > 0 && startPit < 6 && currentPlayer == playerOne) {
        for (int i = 0; i < 6; i++) {
            sumPit += board.getNumStones(i);
        }
    } else if (startPit > 6 && startPit < 12 && currentPlayer == playerTwo) {
        for (int i = 6; i < 12; i++) {
            sumPit += board.getNumStones(i);
        }
    }
    // Return the sum of stones in pits
    return sumPit;
}
  
    public int getStoreCount(Player player) throws NoSuchPlayerException {
        // Check if the specified player is not the current player
        if (currentPlayer != playerTwo && currentPlayer != playerOne){
            // Throw NoSuchPlayerException if the specified player is not the current player
            throw new NoSuchPlayerException();
        }
        return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException {
        if (!isGameOver()) {
            throw new GameNotOverException();
        }
        //Get the total number of stones for each player
        int p2Stones = playerTwo.getStore().getTotalStones();
        int p1Stones = playerOne.getStore().getTotalStones();
        // Determine the winner as previously defined
        if(p1Stones < p2Stones){ //p2 wins
            System.out.println("Player 2 wins\n");
            return playerTwo;
        } else if(p1Stones > p2Stones){ //p1 wins
            System.out.println("Player 1 wins\n");
            return playerOne;
        }
        System.out.println("Its a draw");
        return null; //else return null
    }

     public void switchPlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo; //switch Players
        } else {
            currentPlayer = playerOne;
        }
    }

    public boolean isGameOver() {
        boolean emptyP2Pits = true;
        boolean emptyP1Pits = true;
       
        // Check pits for player one (indices 0 to 5)
        for (int i = 0; i < 6; i++) {
            if (board.getNumStones(i) != 0) {
                emptyP1Pits = false;
                break;  // No need to check further once we find a pit with stones
            }
        }

        // Check pits for player two (indices 6 to 11)
        for (int i = 6; i < 12; i++) {
            if (board.getNumStones(i) != 0) {
                emptyP2Pits = false;
                break;  // No need to check further once we find a pit with stones
            }
        }

        // If all pits for either player are empty, the game is over
        if (emptyP1Pits || emptyP2Pits) {
            board.sumTheStores();
            return true;
        }

        return false;
    }

    public void startNewGame() {
        board.resetBoard();  //reset the board and assign playerOne to start
        currentPlayer = playerOne;
    }
    
    @Override
    public String toString() {
        return "MancalaGame: " + playerOne.getName() + " vs. " + playerTwo.getName();
    }
}
