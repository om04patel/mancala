package mancala;

public class Store {

   // Instance variables
    private int countStone;
    private Player storeOwner;

 // Constructor
    public Store() {
        countStone = 0;
        storeOwner = null;
    }

     // Method to set the owner of the store
    public void setOwner(Player player) {
        storeOwner = player;
    }

    // Method to get the owner of the store
    public Player getOwner() {
        return storeOwner;
    }
    
     // Method to add stones to the store
    public void addStones(int amount) {
        countStone += amount;
    }

    // Method to get the total number of stones in the store
    public int getTotalStones() {
        return countStone;
    }

   // Method to empty the store and return the number of stones
    public int emptyStore() {
        int stones = countStone;
        countStone = 0;
        return stones;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Store owned by: " 
               + (storeOwner != null ? storeOwner.getName() : "No owner") 
               +
               ", Total Stones: " 
               + countStone;
    }

}