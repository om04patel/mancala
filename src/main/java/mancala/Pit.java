package mancala;

public class Pit {

  // Instance variable
    private int countStone;

    public Pit() {  // Constructor
        this.countStone = 0; // Initialize a new pit
    }
    public int getStoneCount(){
        return countStone;
    }
    public void addStones(int count) { //METHOD to add more than one stone
        countStone += count;
    }
    public void addStone() { // Method to add a stone to the pit
        this.countStone++;
    }
// Method to remove and return the stones from the pit
    public int removeStones() {
        int stonesRemoved = this.countStone;
        this.countStone = 0;
        return stonesRemoved;
    }
    // Override toString method
    @Override
    public String toString() {
        return "Pit with Stone Count: " + countStone;
    }
}