package mancala;

import java.util.ArrayList;

public class Board {
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer; 
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores; 

    public Board() {
        // Initialize a new Mancala board with pits and stores
        pits = new ArrayList<>();
        stores = new ArrayList<>();
        setUpPits();
        setUpStores();
        registerPlayers(null, null);
        initializeBoard();
    }

    public void setUpPits() {
        // Establishes 12 Pits in the board, each initialized with 4 myStones
        for (int i = 0; i < 12; i++) {
            Pit pit = new Pit();
           // pit.addStones(4); // Add 4 myStones to each pit
            pits.add(pit);
        }
    }
    public ArrayList<Pit> getPits() {
        // Create a new ArrayList that includes only the pits (excluding stores)
        ArrayList<Pit> boardPits = new ArrayList<>(pits.subList(0, 12));
        return boardPits;
    }
     public ArrayList<Store> getStores() {
        // Create a new ArrayList that includes only the stores
        ArrayList<Store> boardStores = new ArrayList<>(stores);
        return boardStores;
    }

    public void setUpStores() {
        // Establishes 2 empty Stores in the board
        stores.add(new Store());
        stores.add(new Store());
    }

    public void initializeBoard(){
        for(Pit pit: pits){
            pit.removeStones(); // Remove any existing stones in the pit
            pit.addStones(4); // Add 4 stones to the pit
        }
         for (Store store : stores) { // Iterate over each store on the board
            store.emptyStore();
        }
    }
     public void registerPlayers(Player one, Player two) {
        // Connects Players to their Stores
        if (two != null && one != null) {
            stores.get(0).setOwner(one);
            stores.get(1).setOwner(two);
            playerOne = one; // Assign players to class variables
            playerTwo = two;
        }
    }
       public int moveStones(int startPit, Player player) throws InvalidMoveException {
        // Set the current player for the move
        currentPlayer = player;
        int myStones = 0; // Variable to track the number of stones moved

        if(currentPlayer == playerTwo){
             // Check if the selected pit for playerTwo is valid
            if (pits.get(startPit).getStoneCount() == 0 || startPit < 6 || startPit > 11) {
                throw new InvalidMoveException();
            }
        } else if (currentPlayer == playerOne){
            if (pits.get(startPit).getStoneCount() == 0 || startPit < 0 || startPit > 5) {
                throw new InvalidMoveException();
            }
        
        }             
          
        if (startPit < pits.size() && startPit >= 0) {
            if (pits.get(startPit).getStoneCount() > 0) {  // Check if there are stones in the selected pit
                myStones = distributeStones(startPit);
                return myStones;
            } else {
                System.out.println("No stones inside selected pit!");
            }
            return 1;
        } else {
            System.out.println("Invalid pit.");
            return -1; 
        }
    } 

    /**
 * Sums the stones in the pits and adds them to the respective stores.
 */
    public void sumTheStores() {
    // Variables to track the total sum of stones for each player
    int p2SumStones = 0; // Player 2's stones
    int p1SumStones = 0; // Player 1's stones

    // Iterate over the pits for Player 2 (indices 6 to 11)
    for (int i = 6; i < 12; i++) {
        // Remove stones from each pit and add to the sum for Player 2
        p2SumStones += pits.get(i).removeStones();
    }

    // Add the sum of stones for Player 2 to their store
    stores.get(1).addStones(p2SumStones);
    // Also, add the sum to Player 2's personal store
    playerTwo.getStore().addStones(p2SumStones);

    // Iterate over the pits for Player 1 (indices 0 to 5)
    for (int i = 0; i < 6; i++) {
        // Remove stones from each pit and add to the sum for Player 1
        p1SumStones += pits.get(i).removeStones();
    }

    // Add the sum of stones for Player 1 to their store
    stores.get(0).addStones(p1SumStones);
    // Also, add the sum to Player 1's personal store
    playerOne.getStore().addStones(p1SumStones);
}

    public int captureStones(int stoppingPoint) throws PitNotFoundException{
        // Initialize the number of myStones captured to zero
        int capturedStones = 0;
        int indexStarting = stoppingPoint - 1; // Calculate the starting index of the pit to capture myStones
        int pitOpposite;
         // Check if the stopping point is outside the valid range (0 to 12)
        if(stoppingPoint < 0 || stoppingPoint > 12){
            throw new PitNotFoundException();
        }
       
 // Check if the starting index is within the valid range (0 to 11) and if the pit has one stone
        if (pits.get(indexStarting).getStoneCount() == 1 && indexStarting >= 0 && indexStarting < 12) {
            pitOpposite = 11 - indexStarting; // Calculate the index of the opposite pit
            // Check if the opposite pit has myStones to capture
            if(pits.get(pitOpposite).getStoneCount()!=0) {
                 // Capture myStones from both the starting pit and the opposite pit
                capturedStones = pits.get(pitOpposite).removeStones();
                capturedStones += pits.get(indexStarting).removeStones();
            }
        }
        return capturedStones;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        // Check if the pitNum is within the valid range (0 to 11)
        if (pitNum >= 0 && pitNum < 12) {
         // Return the number of myStones in the specified pit
            return pits.get(pitNum).getStoneCount();
        } else {
            // Throw PitNotFoundException for an invalid pitNum
            throw new PitNotFoundException();
        }
    }
    
    public int distributeStones(int startingPoint) throws PitNotFoundException{
     // Check if the starting point is outside the valid range (0 to 12)
        int myAddedStones = 0;
        int originalStore;
        int toDistributeStones = pits.get(startingPoint).removeStones();
        int counterIndex = startingPoint;
        
        if(startingPoint < 0 || startingPoint > 12){
            throw new PitNotFoundException();
        }
        
        // Determine whether to increment the counterIndex based on the startingPoint value
        boolean checkToIncrement = (startingPoint != 5 && startingPoint != 11);

        while (toDistributeStones > 0) {
            if (checkToIncrement) { // If checkToIncrement is true, increment counterIndex and reset if it exceeds 11
                counterIndex++;
                if (counterIndex > 11) { 
                    counterIndex = 0; //If counterIndex exceeds 11, wrap around to 0
                }
                pits.get(counterIndex).addStone(); // Add a stone to the current pit
                myAddedStones++; 
                toDistributeStones--; // Decrease stones to distribute and increment stones added
                
            }
    
           
    
            // Set the currentPlayer based on the current player's store
            if (currentPlayer == playerOne) {
                // Check if the current pit is within playerOne's pits and perform actions accordingly
                if (toDistributeStones == 0 &&  counterIndex >= 0 && counterIndex <= 5) {
                    int lastPitIndex = counterIndex;
                    if (pits.get(lastPitIndex).getStoneCount() == 1) {
                        int capturedStones = captureStones(lastPitIndex + 1);
                        originalStore = stores.get(0).getTotalStones();
                        stores.get(0).addStones(capturedStones);
                        currentPlayer.getStore().addStones(capturedStones);
                        myAddedStones = myAddedStones + (stores.get(0).getTotalStones() - originalStore);
                    }
                } else if (counterIndex == 5) {
                    if (toDistributeStones == 1) {
                        stores.get(0).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        toDistributeStones--;
                        return -1;
                    } else if (toDistributeStones > 0 && toDistributeStones != 1) {
                        stores.get(0).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        myAddedStones++;
                        toDistributeStones--;           
                    }
                }
            } else if (currentPlayer == playerTwo) {
                // Check if the current pit is playerTwo's store and perform actions accordingly
                if (currentPlayer == playerTwo && counterIndex == 11) {
                    if (toDistributeStones == 1) {
                        stores.get(1).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        toDistributeStones--;
                        return -1;
                    } else if(toDistributeStones > 0 && toDistributeStones != 1) {
                        stores.get(1).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        myAddedStones++;
                        toDistributeStones--;                    
                    } 
                }
                // Check if the last stone ends up in playerTwo's store
                if (counterIndex >= 6 && counterIndex <= 11 && toDistributeStones == 0) {
                    int lastPitIndex = counterIndex;
                    if (pits.get(lastPitIndex).getStoneCount() == 1) {
                        int capturedStones = captureStones(lastPitIndex + 1);
                        originalStore = stores.get(0).getTotalStones();
                        stores.get(1).addStones(capturedStones);
                        currentPlayer.getStore().addStones(capturedStones);
                        myAddedStones = myAddedStones + (stores.get(0).getTotalStones() - originalStore);
                    }
                }
            }
               
        checkToIncrement = true;
        }
    
        return myAddedStones;
    }
    

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
        int indexEnding = pitNum;
        int indexStarting;
        if(pitNum < 0 || pitNum > 12){
            throw new PitNotFoundException();
        }  
        if (indexEnding > 6) {
            indexStarting = 6;
            indexEnding = 11;
        } else {
            indexStarting = 0;
            indexEnding = 6;
        }
        // 0-5 for Player One, 7-12 for Player Two

        for (int i = indexStarting; i <= indexEnding; i++) {
            if (pits.get(i).getStoneCount() > 0) {  // Check if the pit has stones; if so, the side is not empty
                return false;
            }
        }
        return true; // If no pit on the specified side has stones, the side is considered empty
    }  
   
    public void resetBoard() {
        // Resets the board by redistributing myStones but retains the players
        initializeBoard();
    } 
   @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append("Mancala Board\n");

        // Add pits and stores information here
        for (int i = 0; i < 12; i++) {
        boardString.append("Pit").append(i + 1).append(":").append(pits.get(i).getStoneCount()).append("myStones\n");
        }
        boardString.append("Store 1: ").append(stores.get(0).getTotalStones()).append(" myStones\n");
        boardString.append("Store 2: ").append(stores.get(1).getTotalStones()).append(" myStones\n");

        return boardString.toString();
    }

}
